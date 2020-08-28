package com.ly.proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Auther: Leowy Zhuang
 * @Date: 2020/8/21 09:35
 * @Description: 代理类
 */
public class FirstClassProxy implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("==========>FirstClassProxy before method invoke");
        methodProxy.invokeSuper(o, objects);
        System.out.println("==========>FirstClassProxy after method invoke");
        return o;
    }
}
