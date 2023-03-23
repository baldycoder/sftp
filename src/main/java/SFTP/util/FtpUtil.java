package SFTP.util;

import com.jcraft.jsch.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * @author ：a123145
 * @date ：Created in 2023/3/10 13:46
 * @description：
 * @modified By：`
 * @version: 1.0
 */

public class FtpUtil {

    public static ThreadLocal<ChannelSftp> channelSftpMap = new ThreadLocal<ChannelSftp>();

    private static final Logger logger = LoggerFactory.getLogger(FtpUtil.class);

    public  boolean loginSFTP(String hostname, int port, String username, String pubKeyPath, int timeout){
        ChannelSftp channelSftp = channelSftpMap.get();
        if(null!=channelSftp && channelSftp.isConnected()){
            return true;
        }
        channelSftpMap.remove();
        JSch jsch = new JSch();
        Session session = null;
        Channel channel = null;
        channelSftp = null;
        try {
            session = jsch.getSession(username, hostname, port);
        } catch (JSchException e) {
            logger.warn("SFTP Server[" + hostname + "] Session created failed,堆栈轨迹如下", e);
            return false;
        }
        try {
            jsch.addIdentity(pubKeyPath);
        } catch (JSchException e1) {
            logger.warn("SFTP Server[" + hostname + "] Session connected failed,堆栈轨迹如下", e1);
            e1.printStackTrace();
        }
        //Security.addProvider(new com.sun.crypto.provider.SunJCE());
        //Setup Strict HostKeyChecking to no so we dont get the unknown host key exception
        session.setConfig("StrictHostKeyChecking", "no");
        try {
            session.setTimeout(timeout);
            session.connect();
        } catch (Exception e) {
            logger.warn("SFTP Server[" + hostname + "] Session connected failed,堆栈轨迹如下", e);
            return false;
        }
        try {
            channel = session.openChannel("sftp");
            channel.connect();
            channelSftp = (ChannelSftp)channel;
            channelSftpMap.set(channelSftp);
            logger.warn("SFTP Server[" + hostname + "] connected success...当前所在目录为" + channelSftp.pwd());
            return true;
        } catch (Exception e) {
            logger.warn("SFTP Server[" + hostname + "] Opening FTP Channel failed,堆栈轨迹如下", e);
            return false;
        }
    }

    /**
     * 链接登录远程服务器
     * @return
     */
    public ChannelSftp connect(){
        return channelSftpMap.get();
    }

    /**
     * 关闭链接
     * @param sftp
     */
    public void disconnect(ChannelSftp sftp){
        try{
            if(sftp != null){
                if(sftp.getSession().isConnected()){
                    sftp.getSession().disconnect();
                }
            }
        }catch(Exception e){
            logger.warn("关闭异常");
        }
    }

    public String downloadFile(String localDirPath, String remoteFilePath) {
        FileOutputStream output = null;
        ChannelSftp sftp = null;

        try {
            sftp = connect();
            if(sftp == null){
                return null;
            }
            String fileName = remoteFilePath.substring(remoteFilePath.lastIndexOf("/") + 1);
            String localFilePath = localDirPath+"/"+fileName;
            if(!new File(localDirPath).exists()){
                new File(localDirPath).mkdirs();
            }
            File localFile = new File(localFilePath);
            sftp.cd(localDirPath);
            output = new FileOutputStream(localFile);
            sftp.get(remoteFilePath,output);

            logger.info("成功接收文件，本地路径：" + localFile.getAbsolutePath());
            return localFile.getAbsolutePath();
        } catch(SftpException | IOException ex) {
            logger.error("拉取文件失败。", ex);
        } finally {
            try {
                if(output != output ){
                    output.close();
                }
                disconnect(sftp);
            } catch (Exception e) {
                logger.error("关闭文件或关闭链接出错。", e);
            }
        }
        return null;
    }

    /**
     * 读取远程文件
     * @param remoteFilePath
     */
    public void readFile(String remoteFilePath){
        InputStream in = null;
        ArrayList<String> strings = new ArrayList<>();
        ChannelSftp sftp = null;
        try {
            sftp = connect();
            if(sftp == null){
                return;
            }
            String fileName = remoteFilePath.substring(remoteFilePath.lastIndexOf("/") + 1);
            String fileDir = remoteFilePath.substring(0, remoteFilePath.lastIndexOf("/"));
            sftp.cd(fileDir);

            if(!listFiles(fileDir).contains(fileName)){
                logger.info("未能找到远程服务器文件：" + fileName);
                return;
            }

            in = sftp.get(fileName);
            if(null != in){
                BufferedReader br = new BufferedReader(new InputStreamReader(in,"utf-8"));
                String str = null;
                while((str = br.readLine()) != null){
                    logger.info("读取文件行：" + str);
                }
            }else{
                logger.error("input Stream 为空");
            }
        }catch (Exception e){
            logger.error("读取文件失败", e);
        } finally{
            try{
                if(null != in ){
                    in.close();
                }
                disconnect(sftp);
            }catch (Exception e){
                logger.error("关闭文件流或sft时异常",e);
            }
        }
    }

    public void writeFile(String remoteFilePath){
        ChannelSftp sftp = null;
        ByteArrayInputStream input = null;
        try {
            sftp = connect();
            if(null == sftp ){
                return;
            }
            String fileName = remoteFilePath.substring(remoteFilePath.lastIndexOf("/") + 1);
            String fileDir = remoteFilePath.substring(0, remoteFilePath.lastIndexOf("/"));
            sftp.cd(fileDir);

            String content = "测试内容";
            input = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
            sftp.put(input, fileName);
        }catch(Exception e){
            logger.error("写入服务器文件异常",e);
        }finally {
            try{
                if(null != input){
                    input.close();
                }
                disconnect(sftp);
            }catch (Exception e){
                logger.error("关闭写入服务器文件或sftp链接异常",e);
            }
        }
    }

    public void uploadFile(String localFilePath, String remoteFilePath){
        FileInputStream fis = null;
        ChannelSftp sftp = null;
        try{
            sftp = connect();
            if(sftp == null){
                return ;
            }
            File localFile = new File(localFilePath);
            fis = new FileInputStream(localFile);
            sftp.put(fis,remoteFilePath);
            logger.info("文件上传至" + remoteFilePath + "成功" );
        }catch (Exception e){
            logger.error("文件上传至" + remoteFilePath + "失败！", e);
        }finally {
            try{
                if(null != fis){
                    fis.close();
                }
                disconnect(sftp);
            }catch (Exception e){
                logger.error("关闭上传文件流或sftp失败！", e);
            }
        }
    }

    /**
     * 遍历文件
     * @param remotePath
     * @return
     */
    public List<String> listFiles(String remotePath){
        List<String> ftpFileNamelist = new ArrayList<>();
        ChannelSftp.LsEntry isEntity = null;
        String fileName = null;
        ChannelSftp sftp = null;
        try{
            sftp = connect();
            if(null == sftp){
                return null;
            }
            Vector<ChannelSftp.LsEntry> sftpFile = sftp.ls(remotePath);
            Iterator<ChannelSftp.LsEntry> sftpFileNames = sftpFile.iterator();
            while(sftpFileNames.hasNext()){
                isEntity = (ChannelSftp.LsEntry) sftpFileNames.next();
                fileName = isEntity.getFilename();
                ftpFileNamelist.add(fileName);
            }
            return ftpFileNamelist;
        }catch (Exception e){
            logger.error("遍历sftp服务器文件异常");
            return null;
        }finally {
            disconnect(sftp);
        }
    }

    /**
     * 删除文件
     * @param remoteFilePath
     */
    public void deleteFile(String remoteFilePath){
        boolean success = false;
        ChannelSftp sftp = null;
        try {
            sftp = connect();
            if(null == sftp){
                return;
            }
            String fileName = remoteFilePath.substring(remoteFilePath.lastIndexOf("/") + 1);
            String fileDir = remoteFilePath.substring(0, remoteFilePath.lastIndexOf("/"));

            sftp.cd(fileDir);
            if(listFiles(fileDir).contains(fileName)){
                sftp.rm(fileName);
                logger.info("删除文件" + fileName + "成功");
            }
        }catch (Exception e){
            logger.info("删除文件 异常");
        }finally {
            disconnect(sftp);
        }
    }
}
