package com.geekbrains.stream.api;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// Стримы
// - получение потоков из коллекции
// - операции filter и map
// - collect и forEach

public class MainClass {
    // Добавить примеры Supplier, Unary операторов

    static class Person {
        enum Position {
            ENGINEER, DIRECTOR, MANAGER;
        }

        private String name;
        private int age;
        private Position position;

        public int getAge() {
            return age;
        }

        public Person(String name, int age, Position position) {
            this.name = name;
            this.age = age;
            this.position = position;
        }
    }

    public static void main(String[] args) {
//        firstEx();
//        filterEx();
//        secondEx();
//        thirdEx();
//        matchEx();
//        findAnyEx();
//        mappingEx();
//        reduceEx();
//        intStreamsEx();
//        streamCreationEx();
//        streamFromFilesEx();
        try {
            Files.lines(Paths.get("text.txt"))
                    .map(line -> line.split("\\s"))
                    .distinct()
                    .forEach(arr -> System.out.println(Arrays.toString(arr)));
            System.out.println("----------------------");
            Files.lines(Paths.get("text.txt"))
                    .map(line -> line.split("\\s")) // arr[0] arr[1] arr[2] arr[3]
                    .map(Arrays::stream)
                    .distinct()
                    .forEach(System.out::println);
            System.out.println("----------------------");
            // верный вариант
            System.out.println(Files.lines(Paths.get("text.txt"))
                    .map(line -> line.split("\\s")) // arr[0] arr[1] arr[2] arr[3]
                    // получаем из массивов стримы и объединяем их в один общий стрим
                    .flatMap(Arrays::stream)
                    // делаем distinct в общем стриме и он выделит только уникальные
                    .distinct()
                    .collect(Collectors.joining(", ", "Уникальные слова: ", ".")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void simpleStringEx() {
        System.out.println(Arrays.stream("A B CC B C AA A A B CC C".split("\\s")).distinct().count());
    }

    private static void streamFromFilesEx() {
        try {
            Files.lines(Paths.get("123.txt")).map(String::length).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void streamCreationEx() {
        // создание stream
        Arrays.asList("A", "B", "C").stream().forEach(System.out::println);
        Stream.of(1, 2, 3, 4).forEach(System.out::println);
        Arrays.stream(new int[]{4, 3, 2, 1}).forEach(System.out::println);
    }

    private static void intStreamsEx() {
        IntStream myIntStream = IntStream.of(10, 20, 30, 40, 50);

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        list.stream().mapToInt(v -> v).sum();

        // создаем набор чисел от 2-х до 10-ти включительно, находим только четные числа и распечатываем их
        IntStream.rangeClosed(2, 10).filter(n -> n % 2 == 0).forEach(System.out::println);
    }

    private static void reduceEx() {
        // способ уменьшения размерности (вектор)
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        int sum = 0;
        for (Integer o : list) {
            sum += o;
        }

        // начальное значение, далее формула как из двух объектов получить один
        int streamSum = list.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum + " " + streamSum);
    }

    private static void mappingEx() {
        Function<String, Integer> _strToLen = String::length;
        Function<String, Integer> strToLen = s -> s.length();
        Predicate<Integer> evenNumberFilter = n -> n % 2 == 0;
        Function<Integer, Integer> cube = n -> n * n * n;

        Stream.of(1, 2, 3).map(n -> Math.pow(n, 3));
        Stream.of(1, 2, 3).map(cube);

        List<String> list = Arrays.asList("A", "BB", "C", "DDD", "EE", "FFFF");
        // List<Integer> wordsLength = list.stream().map(str -> str.length()).collect(Collectors.toList());
        List<Integer> wordsLength = list.stream().map(strToLen).collect(Collectors.toList());

        System.out.println(list);
        System.out.println(wordsLength);

        list.stream().map(strToLen).forEach(n -> System.out.println(n));
        list.stream().map(strToLen).forEach(System.out::println);
    }

    private static void findAnyEx() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        list.stream().filter(n -> n > 10).findAny().ifPresent(System.out::println);
        Optional<Integer> opt = list.stream().filter(n -> n > 10).findAny();
        if (opt.isPresent()) {
            System.out.println(opt.get());
        }
    }

    private static void matchEx() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        System.out.println(list.stream().allMatch(n -> n < 10));
        System.out.println(list.stream().anyMatch(n -> n == 4));
        System.out.println(list.stream().noneMatch(n -> n == 2));
    }

    private static void thirdEx() {
        // получаем поток данных из набора целых чисел, находим среди них только уникальные, и каждое
        // найденное значение выводим в консоль
        System.out.println("Первый вариант: ");
        Arrays.asList(1, 2, 3, 4, 4, 3, 2, 3, 2, 1).stream().distinct().forEach(n -> System.out.println(n));
        // делаем то же самое, что и в первом случае, только используем более короткую запись System.out::println
        System.out.println("Второй вариант: ");
        Arrays.asList(1, 2, 3, 4, 4, 3, 2, 3, 2, 1).stream().distinct().forEach(System.out::println);
    }

    private static void secondEx() {
        // Создаем список целых чисел
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        List<Integer> out = numbers.stream()                        // преобразуем список целых чисел в поток данных и начинаем обработку
                .filter(n -> n % 2 == 0)                            // фильтруем поток, оставляем в нем только четные числа
                .map(n -> n * n)                                    // преобразуем каждый элемент потока int -> int, умножая на 2
                .limit(2)                                           // оставляем в потоке только 2 первых элемента
                .collect(Collectors.toList());                      // собираем элементы потока в лист

/*
        // расширенный вариант Function
        Stream.of("Java", "Abc").map(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return s.length();
            }
        }).collect(Collectors.toList());
*/

        // сокращенная лямбда-форма  (Alt+Enter на Function)
//        Stream.of("Java", "Abc").map(s -> s.length()).collect(Collectors.toList());
        // еще один вариант
//        Stream.of("Java", "Abc").map(String::length).collect(Collectors.toList());

        System.out.println(numbers);
        System.out.println(out);
    }


    private static void filterEx() {
        // создаем поток
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8).filter(new Predicate<Integer>() {
            @Override
            // на вход числа, на выходе boolean
            // если false, данные дальше не пройдут
            public boolean test(Integer integer) {
                return integer % 2 == 0;
            }
        }).forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer * 10);
            }
        });

        Stream.of(1, 2, 3, 4, 5, 6, 7, 8)
                .filter(n -> n % 2 == 0)
                //.forEach(n->System.out.println(n))
                // если один аргумент
                .map(n->n * 2)
                .forEach(System.out::println);
    }

    private static void firstEx() {
        List<Person> persons = new ArrayList<>(Arrays.asList(
                new Person("Bob1", 35, Person.Position.MANAGER),
                new Person("Bob2", 44, Person.Position.DIRECTOR),
                new Person("Bob3", 25, Person.Position.ENGINEER),
                new Person("Bob4", 42, Person.Position.ENGINEER),
                new Person("Bob5", 55, Person.Position.MANAGER),
                new Person("Bob6", 19, Person.Position.MANAGER),
                new Person("Bob7", 33, Person.Position.ENGINEER),
                new Person("Bob8", 37, Person.Position.MANAGER)
        ));

        // отбираем из общего списка только ENGINEER
        List<Person> engineers = new ArrayList<>();
        for (Person o : persons) {
            if (o.position == Person.Position.ENGINEER) {
                engineers.add(o);
            }
        }
        // сортируем их по возрасту
        engineers.sort(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.age - o2.age;
            }
        });
        // формируем список имен отобранных людей
        List<String> engineersNames = new ArrayList<>();
        for (Person o : engineers) {
            engineersNames.add(o.name);
        }
        System.out.println(engineersNames);

        // выходной список имен
        List<String> engineersNamesShortPath = persons.stream()
                // отбираем ENGINEER
                .filter(person -> person.position == Person.Position.ENGINEER)
                // сортируем
                .sorted((o1, o2) -> o1.age - o2.age)
                // из сотрудника получаем строку
                .map((Function<Person, String>) person -> person.name)
                // результат положить в List
                .collect(Collectors.toList());
        System.out.println(engineersNamesShortPath);
    }
}