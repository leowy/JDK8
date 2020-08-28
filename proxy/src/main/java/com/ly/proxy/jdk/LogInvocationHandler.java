package com.ly.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Auther: Leowy Zhuang
 * @Date: 2020/8/21 17:05
 * @Description: 代理类
 */
public class LogInvocationHandler implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("this is animal's sound");
        Object obj = method.invoke(proxy, args);
        System.out.println("yes, you are right~");
        return obj;
    }
}
