package com.study.web;
/**
 * ClassName: ${NAME}
 * Description:
 * date: 2021/8/2 17:55
 *
 * @author Quensty
 * @version
 * @since JDK 1.8
 */

import com.study.pojo.User;
import com.study.service.UserService;
import com.study.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Deprecated
public class LoginServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userService.login(new User(null, username, password, null));
        if(user == null){
            request.setAttribute("msg","用户名或密码错误!");
            request.setAttribute("username",username);
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
        }else {
            request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request,response);
        }
    }
}
