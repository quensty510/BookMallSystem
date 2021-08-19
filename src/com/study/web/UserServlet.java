package com.study.web; /**
 * ClassName: ${NAME}
 * Description:
 * date: 2021/8/3 16:52
 *
 * @author Quensty
 * @version
 * @since JDK 1.8
 */

import com.google.gson.Gson;
import com.study.pojo.User;
import com.study.service.UserService;
import com.study.service.impl.UserServiceImpl;
import com.study.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();
    /**
     * @Author Quensty
     * @Description //TODO 处理登录请求
     * @Date 17:04 2021/8/3
     * @param request
     * @param response
     * @return void
     **/
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userService.login(new User(null, username, password, null));
        if(user == null){
            request.setAttribute("msg","用户名或密码错误!");
            request.setAttribute("username",username);
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
        }else {
            request.getSession().setAttribute("user",user);
            request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request,response);
        }
    }

    /**
     * @Author Quensty
     * @Description //TODO 处理注册请求
     * @Date 17:04 2021/8/3
     * @param request
     * @param response
     * @return void
     **/
    protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String captcha = (String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        String username = request.getParameter("username");
//        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String code = request.getParameter("code");
        User user = WebUtils.CopyParamToBean(new User(), request.getParameterMap());
        if (captcha != null && captcha.equalsIgnoreCase(code)){
            if (userService.existsUsername(username)){
                System.out.println("用户名[" + username + "]已存在！");
                request.setAttribute("msg","用户名已存在！");
                request.setAttribute("username",username);
                request.setAttribute("email",email);
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
            }else{
                userService.registerUser(user);
                request.getSession().setAttribute("user",user);
                request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request,response);
            }

        }else{
            //跳回注册页面
            request.setAttribute("msg","验证码错误！");
            request.setAttribute("username",username);
            request.setAttribute("email",email);
            System.out.println("验证码[" + code + "]错误！");
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
        }
    }

    /**
     * @Author Quensty
     * @Description 退出登录
     * @Date 16:48 2021/8/5
     * @param request
     * @param response
     * @return void
     **/
    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、销毁session中用户数据或直接销毁session
        request.getSession().invalidate();
        //2、重定向到首页（或登录页面）
        response.sendRedirect(request.getContextPath());
    }

    /**
     * @Description 通过ajax检查用户名是否可用
     * @param request
     * @param response
     * @return void
     **/
    protected void ajaxExistUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        boolean existUsername = userService.existsUsername(username);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("existUsername",existUsername);
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        response.getWriter().print(json);
    }

}
