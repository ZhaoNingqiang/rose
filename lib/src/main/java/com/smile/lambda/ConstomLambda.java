package com.smile.lambda;

/**
 * @author 赵凝强
 * @version 1.0.0
 * @Time 17/1/20/下午12:14
 */

public class ConstomLambda {
    public static void main(String[] args) {
        ConstomLambda consumLambda = new ConstomLambda();
        consumLambda.test();

    }

    void test() {
        Person p = new Person(() -> System.out.println("hello consum lambda"));
        p.applay();

    }

    class Person<T> {
        Transform t;

        public Person(Transform transform) {
            t = transform;
        }

        void applay(){
            t.apply();
        }


    }

}
