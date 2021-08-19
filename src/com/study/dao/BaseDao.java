package com.study.dao;

import com.study.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * ClassName: BaseDao
 * Description:
 * date: 2021/8/2 13:12
 *
 * @author Quensty
 * @since JDK 1.8
 */
public abstract class BaseDao {
    //使用DBUtils操纵数据库
    private QueryRunner queryRunner = new QueryRunner();

    /**
     * @Author Quensty
     * @Description //TODO 用来执行update，delete，insert语句
     * @Date 13:23 2021/8/2
     * @Param
     * @return 返回-1代表执行失败，其他表示影响的行数
     **/
    public int update(String sql, Object...params){
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.update(conn, sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * @Author Quensty
     * @Description //TODO 查询返回一个JavaBean的sql语句
     * @Date 13:37 2021/8/2 
     * @param clazz 返回的对象类型
     * @param sql   执行的sql语句
     * @param params sql对应的参数值
     * @return T 返回null表示未查询到值
     **/
    public <T> T queryForOne(Class<T> clazz,String sql,Object...params){
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn,sql,new BeanHandler<T>(clazz),params);
        }catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * @Author Quensty
     * @Description //TODO 查询返回多个JavaBean的sql语句
     * @Date 13:37 2021/8/2
     * @param clazz 返回的对象类型
     * @param sql   执行的sql语句
     * @param params sql对应的参数值
     * @return T 返回null表示未查询到值
     **/
    public <T> List<T> queryForList(Class<T> clazz, String sql, Object...params){
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn,sql,new BeanListHandler<T>(clazz),params);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * @Author Quensty
     * @Description 执行返回一行一列的sql语句(带聚合函数的SQL语句)
     * @Date 13:48 2021/8/2
     * @param sql   执行的sql语句
     * @param params    sql对应的参数值
     * @return java.lang.Object
     **/
    public Object queryFroSingleValue(String sql,Object...params){
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn,sql,new ScalarHandler(),params);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
