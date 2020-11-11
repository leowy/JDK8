package com.ly.thread;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: Leowy Zhuang
 * @Date: 2020/9/20 21:20
 * @Description:
 */
public class ThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            System.out.println("I'm a thread !");
        }, "thread-1");
        t.start();
        System.out.println(t.getName());
        t.join();
        System.out.println("ok");

    }
}
