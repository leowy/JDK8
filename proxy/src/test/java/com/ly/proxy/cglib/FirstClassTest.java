package com.ly.proxy.cglib;


import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.junit.Test;

public class FirstClassTest {


    @Test
    public void testCglib() {
        // cglib动态代理所有方法
        FirstClassProxy fcProxy = new FirstClassProxy();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(FirstClass.class);
        enhancer.setCallback(fcProxy);

        FirstClass fc = (FirstClass) enhancer.create();
        // 该方法是final，无法代理
        fc.firstMethod();
        // 该方法被FirstClassProxy代理
        fc.secondMethod();
        // 该方法被FirstClassProxy代理
        fc.thirdMethod();
        fc.fourthMethod();
    }

    @Test
    public void testCglib2Method() {
        // cglib动态代理单个方法
        FirstClassProxy fcProxy = new FirstClassProxy();
        SecondClassProxy scProxy = new SecondClassProxy();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(FirstClass.class);
        enhancer.setCallbacks(new Callback[]{fcProxy,scProxy, fcProxy,NoOp.INSTANCE});
        enhancer.setCallbackFilter(new FirstClassFilter());
        FirstClass fc = (FirstClass) enhancer.create();
        // 该方法是final无法代理
        fc.firstMethod();
        // 该方法被SecondClassProxy代理
        fc.secondMethod();
        // 该方法没有代理
        fc.thirdMethod();
        // 该方法被FirstClassProxy代理
        fc.fourthMethod();

    }

}