package com.study.test;

import com.study.pojo.User;
import com.study.service.UserService;
import com.study.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * ClassName: UserServiceTest
 * Description:
 * date: 2021/8/2 16:39
 *
 * @author Quensty
 * @since JDK 1.8
 */
public class UserServiceTest {
    UserService userService = new UserServiceImpl();
    @Test
    public void registerUser() {
        userService.registerUser(new User(null,"laidada","laidada","laidada@qq.com"));
    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null, "laidada", "laidada", "laidada@qq.com")));

    }

    @Test
    public void existsUsername() {
        System.out.println(userService.existsUsername("laidada111"));
    }
}