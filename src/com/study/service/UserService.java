package com.study.service;

import com.study.pojo.User;

/**
 * ClassName: UserService
 * Description:
 * date: 2021/8/2 16:25
 *
 * @author Quensty
 * @since JDK 1.8
 */
public interface UserService {

    /**
     * @Author Quensty
     * @Description //TODO 注册用户
     * @Date 16:29 2021/8/2
     * @param user
     * @return void
     **/
    public void registerUser(User user);
    /**
     * @Author Quensty
     * @Description //TODO 登录
     * @Date 16:30 2021/8/2
     * @param user
     * @return com.study.pojo.User
     **/
    public User login(User user);
    /**
     * @Author Quensty
     * @Description //TODO 检查用户名是否可用
     * @Date 16:31 2021/8/2
     * @param username
     * @return boolean 返回true表示用户名已存在，返回false表示用户名可用
     **/
    public boolean existsUsername(String username);
}
