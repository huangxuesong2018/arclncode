package com.test.spi.factory;

import java.util.function.Supplier;

@FunctionalInterface
public interface Factory<T> extends Supplier<T> {
}
