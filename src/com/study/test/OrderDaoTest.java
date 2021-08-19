package com.study.test;

import com.study.dao.OrderDao;
import com.study.dao.impl.OrderDaoImpl;
import com.study.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * ClassName: OrderDaoTest
 * Description:
 * date: 2021/8/6 11:36
 * author Quensty
 * since JDK 1.8
 */
public class OrderDaoTest {
    private OrderDao orderDao = new OrderDaoImpl();
    @Test
    public void saveOrder() {
        orderDao.saveOrder(new Order("12135435",new Date(),new BigDecimal(101),0,1));
        orderDao.saveOrder(new Order("1678979",new Date(),new BigDecimal(101),0,1));
        orderDao.saveOrder(new Order("256345677",new Date(),new BigDecimal(101),0,3));
        orderDao.saveOrder(new Order("684645346",new Date(),new BigDecimal(101),0,3));
    }

    @Test
    public void queryMyOrder() {
        List<Order> orders = orderDao.queryMyOrder(1);
        orders.forEach(System.out::println);
    }
}