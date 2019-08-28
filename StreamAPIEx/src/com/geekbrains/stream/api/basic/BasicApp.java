package com.geekbrains.stream.api.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class BasicApp {
    public static <T> List<T> filter(Predicate<T> predicate, List<T> items) {
        List<T> result = new ArrayList<T>();
        for (T item : items) {
            if (predicate.test(item)) {
                result.add(item);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Integer> inputData = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> outputData = filter(n -> n % 2 == 0, inputData);
        System.out.println(outputData);

        // public class BasicApp$1 implements Runnable {
        //             @Override
        //            public void run() {
        //
        //            }
        // }
        // new Thread(new BasicApp$1()).start();

        // метод заворачивается в обьект и передается объект, а потом из него вызывается метод.
        // объект является контейнером для реализации метода
        // лямбда - сокращенный вариант написания внутреннего анонимного класса
        doSomething(() -> System.out.println("method1"));
        doSomething(() -> System.out.println("method2"));

    }

    public static void doSomething(Runnable r) {
        System.out.println("do something start");
        r.run();
        System.out.println("do something end");
    }
}
