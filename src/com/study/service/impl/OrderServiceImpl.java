package com.study.service.impl;

import com.study.dao.BookDao;
import com.study.dao.OrderDao;
import com.study.dao.OrderItemDao;
import com.study.dao.impl.BookDaoImpl;
import com.study.dao.impl.OrderDaoImpl;
import com.study.dao.impl.OrderItemDaoImpl;
import com.study.pojo.*;
import com.study.service.OrderService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * ClassName: OrderServiceImpl
 * Description:
 * date: 2021/8/6 12:11
 * author Quensty
 * since JDK 1.8
 */
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) {
        String order_id = System.currentTimeMillis() +""+ userId;
        Order order = new Order(order_id,new Date(),cart.getTotalPrice(),0,userId);
        orderDao.saveOrder(order);
//        int i = 10 / 0;
        //遍历购物车中的商品
        for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) {
            //获取每个商品
            CartItem cartItem = entry.getValue();
            //cartItem转换为orderItem
            OrderItem orderItem = new OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(),order_id);
            //保存订单项到数据库
            orderItemDao.saveOrderItem(orderItem);

            //更新库存和销量
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales() + cartItem.getCount());
            book.setStock(book.getStock() - cartItem.getCount());
            bookDao.updateBook(book);
        }
        //清空购物车
        cart.clear();
        return order_id;
    }

    @Override
    public List<Order> myOrders(Integer userId) {
        return orderDao.queryMyOrder(userId);
    }

    @Override
    public List<OrderItem> orderDetails(String orderId) {
        return orderItemDao.queryOrderDetailByOrderId(orderId);
    }

    @Override
    public List<Order> allOrders() {
        return orderDao.queryAllOrders();
    }

    @Override
    public void sendOrder(String orderId) {
        orderDao.changeOrderStatus(orderId,1);
    }

    @Override
    public void receiveOrder(String orderId) {
        orderDao.changeOrderStatus(orderId,2);
    }
}
