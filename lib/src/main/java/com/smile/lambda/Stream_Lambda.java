package com.smile.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 赵凝强
 * @version 1.0.0
 * @Time 17/1/19/下午5:17
 */

public class Stream_Lambda {
    public static void main(String[] args) {
        // new Stream_Lambda().stream();
        //new Stream_Lambda().flatMap();
        reduce();
    }

    private static void reduce() {
        Integer reduce = Stream.of(1, 2, 4, 5).reduce(10, (e1, e2) -> e1 + e2);
        System.out.println(reduce);
    }


    private void flatMap() {
        List<Person> javaProgrammer = new ArrayList<Person>() {
            {
                add(new Person("zhangsan", "java", "male", 1200, 23));
                add(new Person("xiaoyue", "java", "female", 1800, 26));
                add(new Person("dashan", "java", "female", 800, 25));
                add(new Person("lisi", "java", "male", 1300, 28));
                add(new Person("wangwu", "java", "male", 2300, 24));
            }
        };

        List<Person> phpProgrammer = new ArrayList<Person>() {
            {
                add(new Person("David", "php", "female", 1100, 34));
                add(new Person("xiongyingyang", "php", "female", 1000, 32));
                add(new Person("yijianmei", "php", "male", 3000, 22));
            }
        };
        List<Person> collect = Stream.of(javaProgrammer, phpProgrammer).flatMap(s ->
//      将java和php程序员的姓名放到一个集合内
//        {
//            List<String> persons = new ArrayList<String>();
//            s.forEach(person -> {persons.add(person.name);});
//            return persons.stream();
//        }
                        //合并集合
                        s.stream()
        ).collect(Collectors.toList());
        collect.forEach(p -> System.out.println(p));


    }

    private void stream() {
        List<Person> javaProgrammer = new ArrayList<Person>() {
            {
                add(new Person("zhangsan", "java", "male", 1200, 23));
                add(new Person("xiaoyue", "java", "female", 1800, 26));
                add(new Person("dashan", "java", "female", 800, 25));
                add(new Person("lisi", "java", "male", 1300, 28));
                add(new Person("wangwu", "java", "male", 2300, 24));
            }
        };

        List<Person> phpProgrammer = new ArrayList<Person>() {
            {
                add(new Person("David", "php", "female", 1100, 34));
                add(new Person("xiongyingyang", "php", "female", 1000, 32));
                add(new Person("yijianmei", "php", "male", 3000, 22));
            }
        };
        //使用foreach迭代输出上述列表
        System.out.println();
        System.out.println("所有程序员的名字：");

        javaProgrammer.forEach(person -> System.out.print(person.name + ", "));
        phpProgrammer.forEach(person -> System.out.print(person.name + ", "));

        //使用foreach为每个员工加工资 500刀
        System.out.println();
        System.out.println("所有程序员加薪500：");

        Consumer<Person> c = p -> p.salary = (p.salary + 500);
        javaProgrammer.forEach(c);
        phpProgrammer.forEach(c);

        javaProgrammer.forEach(person -> System.out.print(person.name + "：" + person.salary + ", "));
        phpProgrammer.forEach(person -> System.out.print(person.name + "：" + person.salary + ", "));

        //打印薪水大于2000的java程序员
        System.out.println();
        System.out.println("薪水大于2000的java程序员：");
        javaProgrammer.stream()
                .filter(person -> person.salary > 2000)
                .forEach(person -> System.out.print(person.name + " : " + person.salary + ", "));

        //自定义过滤器
        Predicate<Person> ageFilter = person -> person.age > 23;
        Predicate<Person> genderFilter = person -> "male".equals(person.gender);
        System.out.println();
        System.out.println("年龄大于23的男程序员：");
        javaProgrammer.stream()
                .filter(ageFilter)
                .filter(genderFilter)
                .forEach(person -> System.out.println(person.name));
        phpProgrammer.stream()
                .filter(ageFilter)
                .filter(genderFilter)
                .forEach(person -> System.out.println(person.name));
        //使用limit限制
        System.out.println();
        System.out.println("前三个java程序员：");
        javaProgrammer.stream()
                .limit(3)
                .forEach(person -> System.out.println(person.name));
        System.out.println("前1个java程序员：");
        phpProgrammer.stream()
                .limit(1)
                .forEach(person -> System.out.println(person.name));

        //根据年龄对程序员排序
        System.out.println();
        System.out.println("根据年龄对程序员排序：");
        List<Person> sortedJavaProgrammer = javaProgrammer.stream().sorted((p1, p2) -> p1.age - p2.age).collect(Collectors.toList());
        sortedJavaProgrammer.forEach(person -> System.out.println(person.name));
        phpProgrammer.stream().sorted((person, t1) -> person.age - t1.age).forEach(person -> System.out.println(person.name));

        //获取最高工资和最低工资的java程序员
        Person javaSalaryMax = javaProgrammer.stream().max((person, t1) -> person.salary - t1.salary).get();
        System.out.println("java 工资最高的是 ： " + javaSalaryMax.name);
        Person javaSalaryMin = javaProgrammer.stream().min((person, t1) -> person.salary - t1.salary).get();
        System.out.println("java 工资最低的是 ： " + javaSalaryMin.name);
    }


    class Person {
        String name, job, gender;
        int salary, age;

        public Person(String name, String job, String gender, int salary, int age) {
            this.name = name;
            this.job = job;
            this.gender = gender;
            this.salary = salary;
            this.age = age;
        }
    }
}
