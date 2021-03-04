package com.ly.proxy.cglib;

import net.sf.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @Auther: Leowy Zhuang
 * @Date: 2020/8/21 10:04
 * @Description: 代理回调过滤器
 */
public class FirstClassFilter implements CallbackFilter {
    @Override
    public int accept(Method method) {
        // 返回的数字对应Callback[]的顺序
        if (method.getName().equals("firstMethod")) {
            return 0;
        } else if (Objects.equals(method.getName(),"secondMethod")) {
            return 1;
        } else if (Objects.equals(method.getName(),"fourthMethod")) {
            return 2;
        }
        return 3;
    }

}
