package com.arcln.pattern.factory;

/**
 * @author HXS
 * @copyright
 * @since 2019-07-02
 */
public abstract class Pizza {
    public void prepare(){
        System.out.println(this.getClass()+" prepare..");
    };
    public void bake(){
        System.out.println(this.getClass()+" bake..");
    };
    public void cut(){
        System.out.println(this.getClass()+" cut..");
    };
    public void box(){
        System.out.println(this.getClass()+" box..");
    };
}
