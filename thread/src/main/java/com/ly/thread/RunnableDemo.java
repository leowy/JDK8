package com.ly.thread;

/**
 * @Auther: Leowy Zhuang
 * @Date: 2020/9/20 21:24
 * @Description:
 */
public class RunnableDemo implements Runnable{

    @Override
    public void run() {
        System.out.println("I'm a thread!!!");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new RunnableDemo(), "thread-2");
        t.start();
        t.join();
        System.out.println("ok");

    }
}
