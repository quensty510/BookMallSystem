package com.study.service;

import com.study.pojo.Cart;
import com.study.pojo.Order;
import com.study.pojo.OrderItem;

import java.util.List;

/**
 * ClassName: OrderService
 * Description:
 * date: 2021/8/6 12:10
 * author Quensty
 * since JDK 1.8
 */
public interface OrderService {
    String createOrder(Cart cart,Integer userId);
    List<Order> myOrders(Integer userId);
    List<OrderItem> orderDetails(String orderId);
    List<Order> allOrders();
    void sendOrder(String orderId);
    void receiveOrder(String orderId);
}
