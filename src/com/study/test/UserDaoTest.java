package com.study.test;

import com.study.dao.impl.UserDao;
import com.study.dao.impl.UserDaoImpl;
import com.study.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * ClassName: UserDaoTest
 * Description:
 * date: 2021/8/2 16:17
 *
 * @author Quensty
 * @since JDK 1.8
 */
public class UserDaoTest {
    UserDao userDao = new UserDaoImpl();

    @Test
    public void queryUserByUsername() {
        if(userDao.queryUserByUsername("admin123") == null){
            System.out.println("用户名可用");
        }else{
            System.out.println("用户名已存在");
        }
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        if (userDao.queryUserByUsernameAndPassword("admin","admin") == null){
            System.out.println("用户名或密码错误，登录失败");
        }else {
            System.out.println("登录成功");
        }
    }

    @Test
    public void saveUser() {
        System.out.println(userDao.saveUser(new User(null,"quensty","123456","admin@163.com")));
    }
}