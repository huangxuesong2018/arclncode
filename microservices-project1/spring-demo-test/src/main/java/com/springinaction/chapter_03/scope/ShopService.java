package com.springinaction.chapter_03.scope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author HXS
 * @copyright
 * @since 2019-02-21
 */
@Component
public class ShopService {
    private ShoppingCart shoppingCart;

    /**
     * @param shoppingCart 是一个作用域的代理类
     */
    @Autowired
    public void setShoppingCart(ShoppingCart shoppingCart){
        this.shoppingCart = shoppingCart;
    }
    public void buy(){
        System.out.println(this.shoppingCart.getClass());
    }
}
