package com.study.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * ClassName: JdbcUtils
 * Description:
 * date: 2021/8/2 12:38
 *
 * @author Quensty
 * @since JDK 1.8
 */
public class JdbcUtils {
    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> conns = new ThreadLocal<Connection>();
    static{
        try {
            Properties properties = new Properties();
            //读取jdbc.properties属性配置文件
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            //从流中加载数据
            properties.load(inputStream);
            //创建数据里连接池
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * @Author Quensty
     * @Description //TODO 获取数据库连接池中的连接
     * @Date 12:42 2021/8/2
     * @Param 
     * @return 
     **/
    public static Connection getConnection(){
        Connection conn = conns.get();
        try {
            if(conn == null){
                conn = dataSource.getConnection();
                conns.set(conn);
                conn.setAutoCommit(false);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }
    /**
     * @Description 提交事务并释放数据库连接
     * @return void
     **/
    public static void commitAndClose(){
        Connection conn = conns.get();
        if(conn != null){
            //不为空说明之前使用过此连接，操作过数据库
            try {
                conn.commit();  //提交事务
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                try {
                    conn.close();   //释放数据库连接
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        //一定要执行remove操作，否则就会出错。(因为Tomcat底层使用了线程池操作)
        conns.remove();

    }
    /**
     * @Description 回滚事务并释放数据库连接
     * @return void
     **/
    public static void rollbackAndClose(){
        Connection conn = conns.get();
        if(conn != null){
            //不为空说明之前使用过此连接，操作过数据库
            try {
                conn.rollback();  //回滚事务
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                try {
                    conn.close();   //释放数据库连接
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        //一定要执行remove操作，否则就会出错。(因为Tomcat底层使用了线程池操作)
        conns.remove();
    }
    /**
     * @Author Quensty
     * @Description //TODO 关闭数据库连接池中的连接
     * @Date 12:41 2021/8/2
     * @Param 
     * @return 
     **/
//    public static void close(Connection conn){
//        if(conn != null){
//            try {
//                conn.close();
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        }
//    }
}
