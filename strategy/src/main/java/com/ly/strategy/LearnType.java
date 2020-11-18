package com.ly.strategy;

public class LearnType {

    public static void main(String[] args) {

        LearnType instance = new LearnType();
        instance.learn("Java");
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
