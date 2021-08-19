package com.study.web;

import com.google.gson.Gson;
import com.study.pojo.Book;
import com.study.pojo.Cart;
import com.study.pojo.CartItem;
import com.study.service.BookService;
import com.study.service.impl.BookServiceImpl;
import com.study.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: CartServlet
 * Description:
 * date: 2021/8/5 19:54
 *
 * @author Quensty
 * @since JDK 1.8
 */
public class CartServlet extends BaseServlet{
    private BookService bookService = new BookServiceImpl();
    /**
     * @Description 加入购物车
     * @param req
     * @param resp
     * @return void
     **/
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Book book = bookService.queryBookById(WebUtils.parseInt(id, 0));
        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart == null){
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);

        req.getSession().setAttribute("lastName",book.getName());
        //加入完成后跳转回原页面
        resp.sendRedirect(req.getHeader("Referer"));
    }
    /**
     * @Description 删除商品项
     * @param req
     * @param resp
     * @return void
     **/
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart != null){
            cart.deleteItem(WebUtils.parseInt(id,-1));
        }
        //删除完成后跳转回原页面
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * @Description 清空购物车
     * @param req
     * @param resp
     * @return void
     **/
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart != null){
            cart.clear();
        }
        //完成后跳转回原页面
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * @Description 修改数量
     * @param req
     * @param resp
     * @return void
     **/
    protected void updateItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        int id = WebUtils.parseInt(req.getParameter("id"),-1);
        int count = WebUtils.parseInt(req.getParameter("count"),1);
        if(cart != null){
            cart.updateCount(id,count);
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * @Description Ajax实现加入购物车功能
     * @param req
     * @param resp
     * @return void
     **/
    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Book book = bookService.queryBookById(WebUtils.parseInt(id, 0));
        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart == null){
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("totalCount",cart.getTotalCount());
        resultMap.put("lastName",book.getName());
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        resp.getWriter().print(json);
    }
}
