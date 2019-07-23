package com.arcln.pattern.state;

/**
 * @author HXS
 * @copyright
 * @since 2019-07-19
 */
public interface State {
    /**
     * 投入25分钱硬币
     */
    void insertQuarter();

    /**
     * 退出25分钱硬币
     */
    void ejectQuarter();

    /**
     * 旋转曲柄
     */
    void turnCrank();

    /**
     * 分配
     */
    void dispense();
}
