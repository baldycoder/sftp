package demo2;

import SFTP.bean.ThreadParamBean;
import SFTP.config.SftpConfig;
import SFTP.dao.SftpDao;
import SFTP.thread.DownLoadThread;
import SFTP.util.FtpUtil;
import com.sun.tools.javac.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.dc.path.PathException;

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

    public static void main(String[] args) throws PathException, SQLException, UnsupportedEncodingException {

        //1.读取参数配置
        SftpConfig sftpConfig = new SftpConfig();
        Properties props = new Properties();

        try {
            //项目相对路径
            props.load(new FileInputStream("src/main/resources/config/application.properties"));
        } catch (IOException e) {
            logger.error("读取系统配置文件报错！");
        }
        //配置类赋值
        sftpConfig.setHostName(props.getProperty("host-name"));
        sftpConfig.setPort(Integer.parseInt(props.getProperty("port")));
        sftpConfig.setUsername(props.getProperty("user-name"));
        sftpConfig.setPassword(props.getProperty("password"));
        sftpConfig.setLocalPath(props.getProperty("local-path"));
        sftpConfig.setRemotePath(props.getProperty("remote-path"));
        //断言校验
        Assert.checkNonNull(sftpConfig.getLocalPath(), "本地下载路径必输");
        Assert.checkNonNull(sftpConfig.getRemotePath(), "NAS下载路径必输");
        Assert.checkNonNull(sftpConfig.getHostName(), "Hostname必输");
        Assert.checkNonNull(sftpConfig.getPort(), "port必输");
        Assert.checkNonNull(sftpConfig.getUsername(), "username必输");
        Assert.checkNonNull(sftpConfig.getPassword(), "password必输");

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
            dateString = "2023-03-14";
            String localPath = URLDecoder.decode(sftpConfig.getLocalPath() + "/" + dateString, "UTF-8");
            file = new File(localPath);

            if (!file.exists()) {
                //不存在则创建文件夹
                file.mkdir();
            }

            //2.读取sftpParam参数表，获取当前状态正常的记录
            List<ThreadParamBean> threadParamBeans = sftpDao.selectSftpParam(dateString);

            //3.读取远程服务文件列表
            ftpUtil.loginSFTP("localhost", 22, sftpConfig.getUsername(), "src/main/resources/id_rsa", 2000);
            List<String> pathList = ftpUtil.listFiles(sftpConfig.getRemotePath() + "/" + dateString);
            //4.远程列表过滤出.check结尾的文件,只取表名
            String finalDateString = dateString;
            newFileList = pathList.stream()
                    .filter(str -> str.endsWith(".check"))
                    .map(str -> str.substring(0, str.indexOf(finalDateString) - 1))
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
                try {
                    downLoadThread = new DownLoadThread(threadParamBeans.get(i), ftpUtil,sftpConfig,dateString);
                    // 将任务交给线程池管理
                    newFixedThreadPool.execute(downLoadThread);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
