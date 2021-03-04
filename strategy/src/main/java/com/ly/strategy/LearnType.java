package com.ly.strategy;

import java.util.ServiceLoader;

public class LearnType {

    public static void main(String[] args) {

//        LearnType instance = new LearnType();
//        instance.learn("Java");
//        instance.learn("Python");

        ServiceLoader<Learn> loader = ServiceLoader.load(Learn.class);
        System.out.println(loader.toString());
        loader.forEach(System.out::println);
        for (Learn learn : loader) {
            System.out.println(learn);
            learn.process("Java");

        }
    }

    public void learn(String type) {
        if ("Java".equals(type)) {
            System.out.println("I learn Java");
        } else if ("Python".equals(type)) {
            System.out.println("I learn Python");
        } else if ("PHP".equals(type)) {
            System.out.println("I learn PHP");
        } else {
            System.out.println("I also can not know to what learn ");
        }

    }
}
