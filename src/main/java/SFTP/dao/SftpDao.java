package SFTP.dao;

import SFTP.bean.ThreadParamBean;
import SFTP.util.DruidUtil;
import demo2.ToolMain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：a123145
 * @date ：Created in 2023/3/19 09:58
 * @description：sftp的sql处理
 * @modified By：`
 * @version: 1.0
 */

public class SftpDao {

    private static final Logger logger = LoggerFactory.getLogger(SftpDao.class);

    public List<ThreadParamBean> selectSftpParam(String workdate) throws SQLException {
        //获取连接

        //查询语句编译 当天失败的记录，或者当天未进行处理的记录
        String sql = "SELECT  p.* from sftp_file_param p \n" +
                "WHERE p.status = '1' \n" +
                "and p.file_name not in (select d.file_name from sftp_detail d where d.workdate = '" + workdate + "' and d.status = '1');";
        ResultSet rest = null;
        //建立list集合存放输出查询结果
        List<ThreadParamBean> book = new ArrayList<ThreadParamBean>();
        try(Connection con = DruidUtil.getCon();
            PreparedStatement per = con.prepareStatement(sql);) {

            //executeQuery()方法查询到数据库的相应结果存放到ResultSet
            rest = per.executeQuery();
            //rest.next()方法将指针下移直到没下一行返回FALSE。
            while (rest.next()) {
                //将查询结果依次储存
                int id = rest.getInt("id");
                String fileName = rest.getString("file_name");
                String fileSparator = rest.getString("file_separator");
                String dataBaseType = rest.getString("data_base_type");
                String tableName = rest.getString("table_name");
                String status = rest.getString("status");
                String beanName = rest.getString("bean_name");
                String fieldNames = rest.getString("field_names");
                String mapperName = rest.getString("mapper_name");
                String remoteUrl = rest.getString("remote_url");
                String loadScript = rest.getString("load_script");
                String partitionFlag = rest.getString("partition_flag");
                String partitionPeriod = rest.getString("partition_period");
                String partitionUnit = rest.getString("partition_unit");
                String partitionMaxValue = rest.getString("partition_max_value");
                String createDate = rest.getString("create_date");
                String createUser = rest.getString("create_user");
                String updDate = rest.getString("upd_date");
                String updUser = rest.getString("upd_user");
                //建立一个新对象放置结果
                ThreadParamBean book1 = new ThreadParamBean(id,fileName,fileSparator,dataBaseType,tableName,status,
                        beanName,fieldNames,mapperName,remoteUrl,loadScript,partitionFlag,partitionPeriod,partitionUnit,partitionMaxValue,createDate,createUser,updDate,updUser);
                //将结果增加到list集合中
                book.add(book1);
            }

        } catch (SQLException e) {
            throw new SQLException();
        }



        //返回查询结果
        return book;

    }

}
