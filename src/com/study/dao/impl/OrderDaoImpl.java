package com.study.dao.impl;

import com.study.dao.BaseDao;
import com.study.dao.OrderDao;
import com.study.pojo.Order;

import java.util.List;

/**
 * ClassName: OrderDaoImpl
 * Description:
 * date: 2021/8/6 11:29
 * author Quensty
 * since JDK 1.8
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(`orderId`,`createTime`,`price`,`status`,`userId`) values(?,?,?,?,?)";
        return update(sql, order.getOrderId(), order.getCreateTime(), order.getPrice(), order.getStatus(), order.getUserId());
    }

    @Override
    public List<Order> queryMyOrder(Integer userId) {
        String sql = "select * from t_order where userId = ?";
         return queryForList(Order.class,sql,userId);
    }

    @Override
    public List<Order> queryAllOrders() {
        String sql = "select * from t_order";
        return queryForList(Order.class,sql);
    }

    @Override
    public void changeOrderStatus(String orderId,Integer status) {
        String sql = "update t_order set status = ? where orderId = ?";
        update(sql,status,orderId);
    }
}
