package com.study.web; /**
 * ClassName: ${NAME}
 * Description:
 * date: 2021/8/3 17:26
 *
 * @author Quensty
 * @version
 * @since JDK 1.8
 */

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public abstract class BaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //解决响应中文乱码问题
        response.setContentType("text/html;charset=utf-8");
        //必须在获取请求参数之前调用
        //解决post请求中文乱码问题
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        try {
            Method method = this.getClass().getDeclaredMethod(action,HttpServletRequest.class,HttpServletResponse.class);
            method.invoke(this,request,response);
        } catch (Exception e) {
            e.printStackTrace();
            //若不抛出，filter无法捕获异常，会提交事务
            throw new RuntimeException(e);//把异常抛出给TransactionFilter,以回滚事务
        }
    }
}
