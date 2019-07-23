package com.arcln.pattern.composite;

import com.arcln.pattern.composite.adapter.Goose;
import com.arcln.pattern.composite.adapter.GooseAdapter;
import com.arcln.pattern.composite.decorator.CountDecorator;
import com.arcln.pattern.composite.impl.*;

/**
 * <<复合模式>>
 *   鸭鸣器
 * @author HXS
 * @copyright
 * @since 2019-07-23
 */
public class DuckSimulator {
    public static void main(String[] args) {
        DuckSimulator simulator = new DuckSimulator();
        simulator.simulate();
    }

    private void simulate() {
        /**
         * 想不改变鸭子代码的情况下，加入鸭子呱呱叫的次数，
         * 使用装饰器模式{@link CountDecorator}
         */
        Quackable redheadDuck = new CountDecorator(new RedHeadDuck());
        Quackable mallardDuck = new CountDecorator(new MallardDuck());
        Quackable duckCall = new CountDecorator(new DuckCall());
        Quackable rubberDuck = new CountDecorator(new RubberDuck());
        /**
         * 鹅想加入通用的接口，必须做适配才行
         * 通过把 {@link Goose}包装进 {@link GooseAdapter},就可以让鹅像鸭子一样
         */
        Quackable goose = new GooseAdapter(new Goose());
        System.out.println("Duck Simulator\n");
        simulate(redheadDuck);
        simulate(mallardDuck);
        simulate(duckCall);
        simulate(redheadDuck);
        simulate(goose);
        System.out.println("鸭子呱呱叫的总次数:"+CountDecorator.getCount());
    }

    private void simulate(Quackable quackable) {
        quackable.quack();
    }
}
