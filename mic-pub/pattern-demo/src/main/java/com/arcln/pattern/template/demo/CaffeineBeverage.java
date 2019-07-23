package com.arcln.pattern.template.demo;

/**
 * @author HXS
 * @copyright
 * @since 2019-07-16
 */
public abstract class CaffeineBeverage {

    /**
     * 模版方法
     * 工厂方法{@link com.arcln.pattern.factory.PizzaStore#orderPizza(String)} 和这里也有点类似
     */
    public final void prepareRecipe(){
        boilWater();//煮水
        brew();//冲泡
        pourInCup();//把咖啡倒入杯子
        addOther();//加其它
        hook();
    }

    void hook(){

    }

    protected abstract void addOther();

    protected abstract void brew();

    private void pourInCup(){
        System.out.println("Pouring into cup");
    }

    private void boilWater(){
        System.out.println("Boiling water");
    }
}
