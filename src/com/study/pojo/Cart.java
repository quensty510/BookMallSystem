package com.study.pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ClassName: Cart
 * Description:购物车对象
 * date: 2021/8/5 18:52
 * @author Quensty
 * @since JDK 1.8
 */
public class Cart {
//    private Integer totalCount;
//    private BigDecimal totalPrice;
    /**
     * @Description key 为商品编号
     *              value 为商品信息
     **/
    private Map<Integer,CartItem>items = new LinkedHashMap<Integer,CartItem>();

    /**
     * @Description 添加商品项
     * @param cartItem
     * @return void
     **/
    public void addItem(CartItem cartItem){
        //先查看购物车中是否已经添加该商品:
        // 如已添加，则数量累加，总金额更新，如未添加，直接放入集合中
        CartItem item = items.get(cartItem.getId());
        if( item == null){
            //之前未添加
            items.put(cartItem.getId(),cartItem);
        }else{
            //已经添加过
            item.setCount(item.getCount() + 1);//数量累加

            //更新总金额
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount() )) );
        }
    }
    
    /**
     * @Description 删除商品项
     * @param id
     * @return void
     **/
    public void deleteItem(Integer id){
        items.remove(id);
    }
    /**
     * @Description 清空购物车
     * @return void
     **/
    public void clear(){
        items.clear();
    }

    /**
     * @Description 修改商品数量
     * @param id
     * @param count
     * @return void
     **/
    public void updateCount(Integer id,Integer count){
        //先查看购物车中是否已经添加该商品: 如果有，修改商品数量，更新总金额
        CartItem cartItem = items.get(id);
        if(cartItem != null){
            //修改商品数量
            cartItem.setCount(count);
            //更新总金额
            cartItem.setTotalPrice( cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())) );
        }
    }
    

    public Cart() {
    }

    public Cart(Map<Integer, CartItem> items) {
//        this.totalCount = totalCount;
//        this.totalPrice = totalPrice;
        this.items = items;
    }

    public Integer getTotalCount() {
        Integer totalCount = 0;
        for(Map.Entry<Integer,CartItem>entry : items.entrySet()){
            totalCount += entry.getValue().getCount();
        }

//        for (CartItem value : items.values()) {
//            totalCount += value.getCount();
//        }
        return totalCount;
    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        for (CartItem value : items.values()) {
            totalPrice =  totalPrice.add(value.getTotalPrice());
        }
        return totalPrice;
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
