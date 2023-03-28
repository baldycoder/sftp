package SFTP;

import SFTP.bean.ThreadParamBean;
import SFTP.config.SftpConfig;
import SFTP.dao.SftpDao;
import SFTP.thread.DownLoadThread;
import SFTP.util.FtpUtil;
import com.mysql.cj.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @author ：a123145
 * @date ：Created in 2023/3/11 00:37
 * @description：线程池 循环方式
 * @modified By：`
 * @version: 1.0
 */

public class ToolMain {
    private static final Logger logger = LoggerFactory.getLogger(ToolMain.class);

    public static void main(String[] args) throws SQLException, UnsupportedEncodingException, InterruptedException, ParseException {

        //1.读取参数配置
        SftpConfig sftpConfig = new SftpConfig();
        Properties props = new Properties();

        try {
            //项目相对路径
            props.load(new FileInputStream("src/main/resources/config/application.properties"));
        } catch (IOException e) {
            logger.error("读取系统配置文件报错！");
            throw new RuntimeException("读取系统配置文件报错！");
        }
        if(StringUtils.isNullOrEmpty(props.getProperty("host-name")) ||
                StringUtils.isNullOrEmpty(props.getProperty("port")) ||
                StringUtils.isNullOrEmpty(props.getProperty("user-name")) ||
                StringUtils.isNullOrEmpty(props.getProperty("password")) ||
                StringUtils.isNullOrEmpty(props.getProperty("local-path")) ||
                StringUtils.isNullOrEmpty(props.getProperty("remote-path"))
        ){
            logger.error("请确认application.properties参数有效性");
            throw new RuntimeException("请确认application.properties参数有效性");
        }
        //配置类赋值
        sftpConfig.setHostName(props.getProperty("host-name"));
        sftpConfig.setPort(Integer.parseInt(props.getProperty("port")));
        sftpConfig.setUsername(props.getProperty("user-name"));
        sftpConfig.setPassword(props.getProperty("password"));
        sftpConfig.setLocalPath(props.getProperty("local-path"));
        sftpConfig.setRemotePath(props.getProperty("remote-path"));

        //3. 创建定长线程池 5核心 5最大
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(5);

        //变量定义
        SimpleDateFormat sdf = sdf = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式;
        File file = null;
        Date now = null;
        SftpDao sftpDao = new SftpDao();
        FtpUtil ftpUtil = new FtpUtil();
        List<String> newFileList = null;
        List<String> filterParams = null;
        List<String> intersection = null;
        List<ThreadParamBean> result;
        DownLoadThread downLoadThread = null;

        while (true) {
            //1.按YYYY-MM-DD创建下载文件夹
            now = new Date();
            String dateString = sdf.format(now);
            dateString = "2023-03-15";
            String localPath = URLDecoder.decode(sftpConfig.getLocalPath() + "/" + dateString, "UTF-8");
            file = new File(localPath);

            if (!file.exists()) {
                //不存在则创建文件夹
                file.mkdir();
            }

            //2.读取sftpParam参数表，获取当前状态正常的记录，且在明细中未能处理成功的记录
            List<ThreadParamBean> threadParamBeans = sftpDao.selectSftpParam(dateString);

            //3.读取远程服务文件列表
            boolean isConnected = ftpUtil.loginSFTP("localhost", 22, sftpConfig.getUsername(), "src/main/resources/id_rsa", 2000);
            if(isConnected == false){
                logger.error("登录失败!");
                throw new RuntimeException("登录失败!");
            }
            List<String> pathList = ftpUtil.listFiles(sftpConfig.getRemotePath() + "/" + dateString);
            //当日还未创建文件，未能生成，则跳过后续处理，进行sleep
            if(null != pathList && pathList.size() > 0){
                //4.远程列表过滤出.check结尾的文件,只取表名 A.ok A.dat
                String finalDateString = dateString;
                newFileList = pathList.stream()
                        .filter(str -> str.endsWith(".ok"))
                        .map(str -> str.substring(0, str.indexOf(".")))
                        .collect(Collectors.toList());
                //5.取参数表中文件名
                filterParams = threadParamBeans.stream()
                        .map(ThreadParamBean::getFileName)
                        .collect(Collectors.toList());
                //6.取3、4交集
                intersection = new ArrayList<>(filterParams);
                intersection.retainAll(newFileList);
                //7.按5中结果过滤ThreadParamBean
                List<String> finalIntersection = intersection;
                result = threadParamBeans.stream()
                        .filter(bean -> finalIntersection.contains(bean.getFileName()))
                        .collect(Collectors.toList());
                logger.info("本次结果集："+ result.toString());
                for (int i = 0; i < result.size(); i++) {
                    //有待办任务，则创建线程
                    downLoadThread = new DownLoadThread(threadParamBeans.get(i), ftpUtil,sftpConfig,dateString);
                    // 将任务交给线程池管理
                    newFixedThreadPool.execute(downLoadThread);

                }
            }
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                logger.error("多线程阻断异常："+ e.getMessage());
                throw new InterruptedException();
            }
        }
    }
}
