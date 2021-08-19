package com.study.dao;

import com.study.pojo.Order;

import java.util.List;

/**
 * ClassName: OrderDao
 * Description:
 * date: 2021/8/6 11:27
 * author Quensty
 * since JDK 1.8
 */
public interface OrderDao {
    int saveOrder(Order order);
    List<Order> queryMyOrder(Integer userId);
    List<Order> queryAllOrders();
    void changeOrderStatus(String orderId,Integer status);
}
