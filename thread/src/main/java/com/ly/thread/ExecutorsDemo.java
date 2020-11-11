package com.ly.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: Leowy Zhuang
 * @Date: 2020/9/20 21:44
 * @Description:
 */
public class ExecutorsDemo {
    public static void main(String[] args) {
        ExecutorService es = Executors.newCachedThreadPool();
        es.execute(()->{
            System.out.println("I'm a thread !!!!!");
        });
        es.shutdown();

    }
}
