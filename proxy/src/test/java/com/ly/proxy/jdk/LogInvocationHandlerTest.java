package com.ly.proxy.jdk;

import jdk.internal.org.objectweb.asm.commons.Method;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class LogInvocationHandlerTest {

    @Test
    public void jdkProxy() throws Throwable {
        LogInvocationHandler logInvocationHandler = new LogInvocationHandler();
        logInvocationHandler.invoke(new Cat(), Animal.class.getMethod("say"), null);
        logInvocationHandler.invoke(new Dog(), Animal.class.getMethod("say"), null);
        // Horse 没有实现animal接口，所以无法被代理
//        logInvocationHandler.invoke(new Horse(), Animal.class.getMethod("say"), null);
    }

}