package com.study.web;

import com.study.pojo.Cart;
import com.study.pojo.Order;
import com.study.pojo.OrderItem;
import com.study.pojo.User;
import com.study.service.OrderService;
import com.study.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * ClassName: OrderServlet
 * Description:
 * date: 2021/8/6 12:28
 * author Quensty
 * since JDK 1.8
 */
public class OrderServlet extends BaseServlet{
    private OrderService orderService = new OrderServiceImpl();
    
    /**
     * @Description 去结算-->创建订单
     * @param request
     * @param response
     * @return void
     **/
    protected void createOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        User loginUser = (User) request.getSession().getAttribute("user");
        if(loginUser == null){
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
            return;
        }
        Integer user_id = loginUser.getId();
        String order_id = orderService.createOrder(cart, user_id);
        request.getSession().removeAttribute("cart");
//        request.setAttribute("order_id",order_id);
//        request.getRequestDispatcher("pages/cart/checkout.jsp").forward(request,response);
        request.getSession().setAttribute("order_id",order_id);
        response.sendRedirect(request.getContextPath() + "/pages/cart/checkout.jsp");
    }

    /**
     * @Description 查看当前用户订单
     * @param request
     * @param response
     * @return void
     **/
    protected void myOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            response.sendRedirect("userServlet?action=login");
            return;
        }
        Integer userId = user.getId();
        List<Order> orders = orderService.myOrders(userId);
        request.getSession().setAttribute("orders",orders);
        response.sendRedirect(request.getContextPath() + "/pages/order/order.jsp");
    }
    /**
     * @Description 查看订单详情
     * @param request
     * @param response
     * @return void
     **/
    protected void orderDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");
        List<OrderItem> orderItems = orderService.orderDetails(orderId);
        request.setAttribute("orderDetails",orderItems);
        request.setAttribute("orderId",orderId);
        request.getRequestDispatcher("/pages/order/orderDetails.jsp").forward(request,response);
    }

    /**
     * @Description 确认收货
     * @param request
     * @param response
     * @return void
     **/
    protected void receiveOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        orderService.receiveOrder(request.getParameter("orderId"));
        response.sendRedirect("orderServlet?action=myOrders");
    }

}
