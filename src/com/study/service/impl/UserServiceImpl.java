package com.study.service.impl;

import com.study.dao.impl.UserDao;
import com.study.dao.impl.UserDaoImpl;
import com.study.pojo.User;
import com.study.service.UserService;

/**
 * ClassName: UserServiceImpl
 * Description:
 * date: 2021/8/2 16:33
 *
 * @author Quensty
 * @since JDK 1.8
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public void registerUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {
        if(userDao.queryUserByUsername(username) == null){
            return false;
        }
        return true;
    }
}
