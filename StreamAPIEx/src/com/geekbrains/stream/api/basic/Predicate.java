package com.geekbrains.stream.api.basic;

@FunctionalInterface
public interface Predicate<T> {
    boolean test(T object);
}
