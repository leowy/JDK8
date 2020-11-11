package com.ly.thread;

import com.mysql.cj.util.TimeUtil;
import com.sun.jmx.snmp.tasks.Task;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: Leowy Zhuang
 * @Date: 2020/9/20 21:26
 * @Description:
 */
public class CallableDemo implements Callable {
    @Override
    public Object call() throws Exception {
        TimeUnit.SECONDS.sleep(10);
        return "I'm a thread!!!";
    }

    public static void main(String[] args) throws Exception {
        FutureTask<String> task = new FutureTask<String>(new CallableDemo());
        Thread t = new Thread(task, "Thread-3");
        t.start();
        System.out.println(task.get());
        System.out.println("ok");
    }
}