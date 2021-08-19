package com.study.test;

import com.study.pojo.Cart;
import com.study.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * ClassName: CartTest
 * Description:
 * date: 2021/8/5 19:33
 *
 * @author Quensty
 * @since JDK 1.8
 */
public class CartTest {

    @Test
    public void addItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"阿萨德刚",1,new BigDecimal(23), new BigDecimal(23)));
        cart.addItem(new CartItem(1,"阿萨德刚",1,new BigDecimal(23), new BigDecimal(23)));
        cart.addItem(new CartItem(2,"闪电发货",1,new BigDecimal(99), new BigDecimal(99)));
        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"阿萨德刚",1,new BigDecimal(23), new BigDecimal(23)));
        cart.addItem(new CartItem(1,"阿萨德刚",1,new BigDecimal(23), new BigDecimal(23)));
        cart.addItem(new CartItem(2,"闪电发货",1,new BigDecimal(99), new BigDecimal(99)));

        cart.deleteItem(1);

        System.out.println(cart);
    }

    @Test
    public void clear() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"阿萨德刚",1,new BigDecimal(23), new BigDecimal(23)));
        cart.addItem(new CartItem(1,"阿萨德刚",1,new BigDecimal(23), new BigDecimal(23)));
        cart.addItem(new CartItem(2,"闪电发货",1,new BigDecimal(99), new BigDecimal(99)));

        cart.deleteItem(1);

        cart.clear();

        System.out.println(cart);
    }

    @Test
    public void updateCount() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"阿萨德刚",1,new BigDecimal(23), new BigDecimal(23)));
        cart.addItem(new CartItem(1,"阿萨德刚",1,new BigDecimal(23), new BigDecimal(23)));
        cart.addItem(new CartItem(2,"闪电发货",1,new BigDecimal(99), new BigDecimal(99)));

        cart.deleteItem(1);

        cart.clear();

        cart.addItem(new CartItem(2,"闪电发货",1,new BigDecimal(99), new BigDecimal(99)));

        cart.updateCount(2,10);

        System.out.println(cart);
    }
}