package com.lesson4;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.*;

/**
 * @author ilnaz-92@yandex.ru
 * Created on 26/08/2019
 */
public class LessonLauncher
{
  public static void main(String[] args)
  {
    //firstMethod();
    //secondMethod();
    //specialStreamCreation();

    Double avgLength = getStringStream().collect(Collectors.averagingInt(s -> s.length()));
    Set<String> mySet = getStringStream().collect(Collectors.toSet());
    List<String> myList = getStringStream().collect(Collectors.toList());
    //myList.stream().forEach(System.out::println);


    //  getStringStream().filter(s -> s.contains("A")).distinct().map(s -> s.concat("GeekBrains")).sorted().forEach(System.out::println);

/*
    List<String> result = getStringStream().filter(s -> s.contains("A")).distinct().map(s -> {
      return s.concat("GeekBrains");
    }).sorted().collect(Collectors.toList());
    System.out.println(result);
*/

    getStringStream().filter(s -> {
      return s.startsWith("A");
    }).map(String::toLowerCase).limit(3).forEach(System.out::println);

  }

  private static void specialStreamCreation()
  {
    IntStream intStream = IntStream.of(1, 2, 3, 4);
    LongStream longStream = LongStream.of(1, 2, 3, 4);
    DoubleStream doubleStream = DoubleStream.of(1.3, 1.4, 1.5);
    Stream simpleStream = Stream.of("dasda", "sadasd");
  }

  private static void secondMethod()
  {
    String[] myArray = {"A", "C", "D"};
    Stream<String> stream = Arrays.stream(myArray);
  }

  private static void firstMethod()
  {
    List<String> simpleList = Arrays.asList("M", "B", "c");
    Stream<String> myStream = simpleList.stream();
  }

  private static Stream<String> getStringStream()
  {
    return Stream.of("MA", "BC", "CDD", "A", "A", "A");
  }
}
