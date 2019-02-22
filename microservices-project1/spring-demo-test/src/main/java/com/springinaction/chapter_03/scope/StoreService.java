package com.springinaction.chapter_03.scope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 超市
 * @author HXS
 * @copyright
 * @since 2019-02-21
 */
@Component
public class StoreService {
    private ShoppingCart shoppingCart;

    @Autowired
    public void setShoppingCart(ShoppingCart shoppingCart){
        this.shoppingCart = shoppingCart;
    }

    public void buy(){
        System.out.println(this.shoppingCart.getClass());
    }
}
