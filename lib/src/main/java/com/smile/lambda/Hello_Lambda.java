package com.smile.lambda;

import com.example.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author 赵凝强
 * @version 1.0.0
 * @Time 17/1/19/上午11:53
 */

public class Hello_Lambda {
    public static void main(String[] args) {
        //forEach();
        //runnable();
        sort();
    }

    private static void forEach() {
        String[] names = {"libai", "zhangsan", "jim", "dashan", "zhangwuji"};
        List<String> list = Arrays.asList(names);
        //以前的for循环
        for (String name : names) {
            System.out.println(name);
        }

        //使用lambad表达式
        list.forEach((name) -> System.out.println(name + "  ，"));
        //在java8中使用双冒号操作符
        list.forEach(System.out::println);
    }

    private static void runnable(){
        //使用匿名内部类
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("runnable run typical");
            }
        }).start();

        //使用lambda表达式
        new Thread(() -> System.out.println("runnable use lambda")).start();

        //使用匿名内部类2
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("runnable1 run");
            }
        };

        Runnable runnable2 = () -> System.out.println("runnable2 run");

        runnable1.run();

        runnable2.run();
    }

    private static void sort(){
        String[] names = {"libai", "zhangsan", "jim", "dashan", "zhangwuji"};
        //匿名内部类
        Arrays.sort(names, new Comparator<String>() {
            @Override
            public int compare(String s, String t1) {
                return s.compareTo(t1);
            }
        });
        for (String name : names){
            System.out.println("sort 1 : "+name);
        }
        //lambda expresssion
        Arrays.sort(names,(String s1,String s2) -> s2.compareTo(s1));
        List<String> sortedNames = Arrays.asList(names);
        sortedNames.forEach((name) -> System.out.println("sort 2 : "+name));

        //使用lambda表达式比较length
        Arrays.sort(names,(String s1 , String s2) -> s1.length() - s2.length() );
        for (String name : names){
            System.out.println("sort 3 : "+name);
        }

    }

    private static void streams(){
      String[] names = new String[]{"libai","xiaobai","yuege"};

    }


}
