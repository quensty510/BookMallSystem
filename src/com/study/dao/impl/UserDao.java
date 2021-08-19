package com.study.dao.impl;

import com.study.pojo.User;

/**
 * ClassName: userDao
 * Description:
 * date: 2021/8/2 13:59
 *
 * @author Quensty
 * @since JDK 1.8
 */
public interface UserDao {

    /**
     * @Author Quensty
     * @Description //TODO 根据用户名查询用户信息
     * @Date 15:58 2021/8/2 
     * @param username 用户名
     * @return com.study.pojo.User 返回null说明没有此用户，反之亦然
     **/
    public User queryUserByUsername(String username);
    /**
     * @Author Quensty
     * @Description //TODO 根据用户名和密码查询用户
     * @Date 16:01 2021/8/2
     * @param username  用户名
    * @param password   密码
     * @return com.study.pojo.User  返回null说明用户名或密码错误，反之亦然
     **/
    public User queryUserByUsernameAndPassword(String username,String password);
    /**
     * @Author Quensty
     * @Description //TODO 保存用户信息
     * @Date 16:00 2021/8/2 
    * @param user
     * @return int 返回-1代表操作失败，其他是sql语句影响的行数
     **/
    public int saveUser(User user);
}
