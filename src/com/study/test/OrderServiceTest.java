package com.study.test;

import com.study.pojo.Cart;
import com.study.pojo.CartItem;
import com.study.pojo.Order;
import com.study.service.OrderService;
import com.study.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * ClassName: OrderServiceTest
 * Description:
 * date: 2021/8/6 12:23
 * author Quensty
 * since JDK 1.8
 */
public class OrderServiceTest {
    private OrderService orderService = new OrderServiceImpl();
    @Test
    public void createOrderTest() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"阿萨德刚",1,new BigDecimal(23), new BigDecimal(23)));
        cart.addItem(new CartItem(1,"阿萨德刚",1,new BigDecimal(23), new BigDecimal(23)));
        cart.addItem(new CartItem(2,"闪电发货",1,new BigDecimal(99), new BigDecimal(99)));

        System.out.println(orderService.createOrder(cart, 1));
    }
    @Test
    public void myOrdersTest(){
        List<Order> orders = orderService.myOrders(1);
        for (Order order : orders) {
            System.out.println(order.getOrderId());
        }
    }

    @Test
    public void orderDetailsTest(){
        System.out.println(orderService.orderDetails("16282298218111"));
    }

    @Test
    public void allOrdersTest(){
        System.out.println(orderService.allOrders());
    }

    @Test
    public void receiveOrdersTest(){
        orderService.receiveOrder("16282390357911");
        System.out.println(orderService.allOrders());
    }
}