package com.study.dao.impl;

import com.study.dao.BaseDao;
import com.study.dao.OrderItemDao;
import com.study.pojo.OrderItem;

import java.util.List;

/**
 * ClassName: OrderItemDaoImpl
 * Description:
 * date: 2021/8/6 11:32
 * author Quensty
 * since JDK 1.8
 */
public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`name`,`count`,`price`,`totalPrice`,`orderId`) values(?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }

    @Override
    public List<OrderItem> queryOrderDetailByOrderId(String orderId) {
        String sql = "select * from t_order_item where orderId = ?";
        return queryForList(OrderItem.class, sql,orderId);
    }
}
