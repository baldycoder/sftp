package SFTP.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import demo2.ToolMain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * @author ：a123145
 * @date ：Created in 2023/3/14 08:58
 * @description：数据库连接池工具类
 * @modified By：`
 * @version: 1.0
 */

public class DruidUtil {

    private static final Logger logger = LoggerFactory.getLogger(DruidUtil.class);

    public static Connection getCon() {

        Connection con = null;
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream("src/main/resources/config/druid.properties"));
            DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
            con = dataSource.getConnection();
        } catch (Exception e) {
            logger.error("获取数据库连接失败",e);
            throw new RuntimeException("获取数据库连接失败");
        }
        return con;
    }

}
