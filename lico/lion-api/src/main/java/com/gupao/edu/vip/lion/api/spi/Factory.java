package com.gupao.edu.vip.lion.api.spi;

import java.util.function.Supplier;

/**
 * 继承函数式接口的 默认get方法
 * @param <T>
 */
@FunctionalInterface
public interface Factory<T> extends Supplier<T> {
}
