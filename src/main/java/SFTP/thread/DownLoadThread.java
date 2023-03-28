package SFTP.thread;

import SFTP.config.SftpConfig;
import SFTP.util.DruidUtil;
import SFTP.util.FtpUtil;
import SFTP.bean.ThreadParamBean;
import demo2.ToolMain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

/**
 * @author ：a123145
 * @date ：Created in 2023/3/13 21:18
 * @description：下载线程，涉及表更新等
 * @modified By：`
 * @version: 1.0
 */

public class DownLoadThread implements Runnable{

    private ThreadParamBean threadParamBean;
    private FtpUtil ftpUtil;
    private SftpConfig sftpConfig;
    private String remoteFilePath;
    private String localFilePath;
    private String fileName;
    private Date batchDate;
    private static final Logger logger = LoggerFactory.getLogger(DownLoadThread.class);


    public DownLoadThread(ThreadParamBean threadParamBean,FtpUtil ftpUtil,SftpConfig sftpConfig,String dateString) throws ParseException {
        this.threadParamBean = threadParamBean;
        this.ftpUtil = ftpUtil;
        this.sftpConfig = sftpConfig;
        this.remoteFilePath = sftpConfig.getRemotePath() + "/" + dateString + "/" + threadParamBean.getFileName() + "_" + dateString + ".csv";
        this.localFilePath = sftpConfig.getLocalPath() + "/" + dateString;
        this.fileName = "/" + threadParamBean.getFileName() + "_" + dateString + ".csv";
        this.batchDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
    }

    @Override
    public void run() {
        logger.info(Thread.currentThread().getName() + "开始处理：" + threadParamBean.getFileName());
        long startTime = System.nanoTime();
        Duration timeTakenToStarting = null;
//        Connection con = null;
//        PreparedStatement stmt = null;
        String sql = "load data infile '" + this.localFilePath + this.fileName + "'" + " into table " + this.threadParamBean.getTableName() +
                " FIELDS terminated by '" + this.threadParamBean.getFileSeparator() + "'" +
                " LINES TERMINATED BY '\\n'" +
                " IGNORE 1 LINES;";
        String insertSQL = "INSERT INTO sftp.sftp_detail " +
                "(file_name, workdate, batch_num, status, retry_count, file_size, local_path, remote_path, used_time, msg, create_date) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW())";
        FtpUtil ftp = new FtpUtil();

        try
                (
                //数据库连接池中获取链接
                Connection con = DruidUtil.getCon();
                //预编译
                PreparedStatement stmtLoadData = con.prepareStatement(sql);
                PreparedStatement stmtLInsertSuccess = con.prepareStatement(insertSQL);
                )
        {

            ftp.loginSFTP("localhost", 22, sftpConfig.getUsername(), "src/main/resources/id_rsa", 2000);
            String filePath = ftp.downloadFile(this.localFilePath,this.remoteFilePath);

            //取消自动提交
            con.setAutoCommit(false);
            //mysql load data
            stmtLoadData.execute(sql);
            //结果表SQL

            timeTakenToStarting = Duration.ofNanos(System.nanoTime() - startTime);
            double seconds = timeTakenToStarting.toMillis() / 100.00;
            stmtLInsertSuccess.setString(1,this.threadParamBean.getFileName());//file_name
            stmtLInsertSuccess.setDate(2,new java.sql.Date(this.batchDate.getTime()));//workdate
            stmtLInsertSuccess.setString(3,"");//batch_num
            stmtLInsertSuccess.setString(4,"1");//status 9-失败 1-成功
            stmtLInsertSuccess.setString(5,"");//retry_count
            stmtLInsertSuccess.setString(6,"");//file_size
            stmtLInsertSuccess.setString(7,this.localFilePath + this.fileName);//local_path
            stmtLInsertSuccess.setString(8,this.remoteFilePath);//remote_path
            stmtLInsertSuccess.setString(9,seconds +"");//used_tim
            stmtLInsertSuccess.setString(10,"");
            stmtLInsertSuccess.executeUpdate();
            //事务提交
            con.commit();

        } catch (Exception e) {
                try(//数据库连接池中获取链接
                    Connection con = DruidUtil.getCon();
                    //预编译
                    PreparedStatement stmtInsertFail= con.prepareStatement(insertSQL);
                    ) {
                    con.setAutoCommit(true);

                    timeTakenToStarting = Duration.ofNanos(System.nanoTime() - startTime);
                    double seconds = timeTakenToStarting.toMillis() / 100.00;
                    stmtInsertFail.setString(1,this.threadParamBean.getFileName());//file_name
                    stmtInsertFail.setDate(2,new java.sql.Date(this.batchDate.getTime()));//workdate
                    stmtInsertFail.setString(3,"");//batch_num
                    stmtInsertFail.setString(4,"9");//status 9-失败 1-成功
                    stmtInsertFail.setString(5,"");//retry_count
                    stmtInsertFail.setString(6,"");//file_size
                    stmtInsertFail.setString(7,this.localFilePath + this.fileName);//local_path
                    stmtInsertFail.setString(8,this.remoteFilePath);//remote_path
                    stmtInsertFail.setString(9,seconds +"");//used_tim
                    stmtInsertFail.setString(10,"");
                    stmtInsertFail.executeUpdate();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            e.printStackTrace();
        }
        logger.info(Thread.currentThread().getName() + "结束：" + threadParamBean.getFileName());
    }
}
