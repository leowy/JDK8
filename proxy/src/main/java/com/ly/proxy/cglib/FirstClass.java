package com.ly.proxy.cglib;

/**
 * @Auther: Leowy Zhuang
 * @Date: 2020/8/21 09:33
 * @Description: 需要被代理的类
 */
public class FirstClass {

    public FirstClass() {
        System.out.println("this is Constructor");
    }


    public final void firstMethod() {
        System.out.println("this is firstMethod");
    }

    public void secondMethod() {
        System.out.println("this is secondMethod");
    }

    public void thirdMethod() {
        System.out.println("this is thirdMethod");
    }

    public void fourthMethod() {
        System.out.println("this is fourthMethod");
    }

}
