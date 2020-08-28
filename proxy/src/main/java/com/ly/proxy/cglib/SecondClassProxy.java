package com.ly.proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Auther: Leowy Zhuang
 * @Date: 2020/8/21 09:49
 * @Description: 代理类
 */
public class SecondClassProxy implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("==========>SecondClassProxy before method invoke");
        proxy.invokeSuper(obj, args);
        System.out.println("==========>SecondClassProxy after method invoke");
        return obj;
    }
}
