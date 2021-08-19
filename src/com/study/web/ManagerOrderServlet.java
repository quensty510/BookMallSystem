package com.study.web;

import com.study.pojo.Order;
import com.study.service.OrderService;
import com.study.service.impl.OrderServiceImpl;
import com.study.utils.JdbcUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * ClassName: ClientOrderServlet
 * Description:
 * date: 2021/8/6 20:52
 * author Quensty
 * since JDK 1.8
 */
public class ManagerOrderServlet extends BaseServlet {
    private OrderService orderService = new OrderServiceImpl();

    /**
     * @param request
     * @param response
     * @return void
     * @Description 查看所有订单
     **/
    protected void allOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> orders = orderService.allOrders();
        request.getSession().setAttribute("allOrders", orders);
        response.sendRedirect(request.getContextPath() + "/pages/manager/order_manager.jsp");
    }
    /**
     * @Description 发货
     * @param request
     * @param response
     * @return void
     **/
    protected void sendOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        orderService.sendOrder(request.getParameter("orderId"));
        response.sendRedirect("orderServlet?action=allOrders");
    }
}

