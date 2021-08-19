package com.study.test;

import com.study.dao.OrderItemDao;
import com.study.dao.impl.OrderItemDaoImpl;
import com.study.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * ClassName: OrderItemDaoTest
 * Description:
 * date: 2021/8/6 11:48
 * author Quensty
 * since JDK 1.8
 */
public class OrderItemDaoTest {
    OrderItemDao orderItemDao = new OrderItemDaoImpl();

    @Test
    public void saveOrderItem() {
        orderItemDao.saveOrderItem(new OrderItem(null,"阿萨德刚",2,new BigDecimal(12),new BigDecimal(12),"12135435"));
    }
    @Test
    public void queryOrderDetailByOrderIdTest() {
        System.out.println(orderItemDao.queryOrderDetailByOrderId("16282298218111"));
    }
}