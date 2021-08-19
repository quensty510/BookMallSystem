package com.study.dao;

import com.study.pojo.OrderItem;

import java.util.List;

/**
 * ClassName: OrderItemDao
 * Description:
 * date: 2021/8/6 11:28
 * author Quensty
 * since JDK 1.8
 */
public interface OrderItemDao {
    int saveOrderItem(OrderItem orderItem);
    List<OrderItem> queryOrderDetailByOrderId(String orderId);
}
