package com.study.web;

import com.study.pojo.Book;
import com.study.pojo.Page;
import com.study.service.BookService;
import com.study.service.impl.BookServiceImpl;
import com.study.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: ClientBookServlet
 * Description:
 * date: 2021/8/5 9:05
 *
 * @author Quensty
 * @since JDK 1.8
 */
public class ClientBookServlet extends BaseServlet{
    private BookService bookService =  new BookServiceImpl();
    /**
     * @Author Quensty
     * @Description 处理分页功能
     * @Date 15:44 2021/8/4
     * @param request
     * @param response
     * @return void
     **/
    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、获取请求的参数pageNo和pageSize
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        //2、调用BookService.page(pageNo,pageSize);
        Page<Book> page = bookService.page(pageNo,pageSize);
        page.setUrl("client/bookServlet?action=page");
        //3、保存到request域中
        request.setAttribute("page",page);
        //4、请求转发到pages/manager/book_manager.jsp页面
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request,response);
    }

    /**
     * @Author Quensty
     * @Description 处理按价格查询的分页功能
     * @Date 15:44 2021/8/4
     * @param request
     * @param response
     * @return void
     **/
    protected void pageByPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、获取请求的参数pageNo和pageSize
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtils.parseInt(request.getParameter("min"), 0);
        int max = WebUtils.parseInt(request.getParameter("max"),Integer.MAX_VALUE);
        //2、调用BookService.page(pageNo,pageSize);
        Page<Book> page = bookService.pageByPrice(pageNo,pageSize,min,max);

        StringBuilder url =  new StringBuilder("client/bookServlet?action=pageByPrice");
        if(request.getParameter("min") != null){
            url.append("&min=" + request.getParameter("min"));
        }
        if(request.getParameter("max") != null){
            url.append("&max=" + request.getParameter("max"));
        }

        page.setUrl(url.toString());
        //3、保存到request域中
        request.setAttribute("page",page);
        //4、请求转发到pages/manager/book_manager.jsp页面
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request,response);
    }
}
