package com.study.web; /**
 * ClassName: ${NAME}
 * Description:
 * date: 2021/8/4 11:15
 *
 * @author Quensty
 * @version
 * @since JDK 1.8
 */

import com.study.pojo.Book;
import com.study.pojo.Page;
import com.study.service.BookService;
import com.study.service.impl.BookServiceImpl;
import com.study.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookServlet extends BaseServlet {
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
        page.setUrl("manager/bookServlet?action=page");
        //3、保存到request域中
        request.setAttribute("page",page);
        //4、请求转发到pages/manager/book_manager.jsp页面
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
    }



    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"),0);
        pageNo ++;
        Book book = WebUtils.CopyParamToBean(new Book(), request.getParameterMap());
        bookService.addBook(book);
        response.sendRedirect( request.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);
    }
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        bookService.deleteBookById(WebUtils.parseInt(request.getParameter("id"),-1));
        response.sendRedirect(request.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + request.getParameter("pageNo"));
    }
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Book book = WebUtils.CopyParamToBean(new Book(), request.getParameterMap());
        bookService.updateBook(book);
        response.sendRedirect(request.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + request.getParameter("pageNo"));
    }
    protected void getBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Book book = bookService.queryBookById(WebUtils.parseInt(request.getParameter("id"), 0));
        request.setAttribute("book",book);
        request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request,response);
    }
    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、通过BookService查询图书
        List<Book> books = bookService.queryBooks();
        //2、把全部图书保存到request域中
        request.setAttribute("books", books);
        //3、请求转发到/pages/manager/book_manager.jsp页面
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);

    }
}
