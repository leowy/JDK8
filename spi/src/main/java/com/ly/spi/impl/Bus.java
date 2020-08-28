package com.ly.spi.impl;

import com.ly.spi.IVehicle;

/**
 * @Auther: Leowy Zhuang
 * @Date: 2020/8/20 10:36
 * @Description:
 */
public class Bus implements IVehicle {
    public String desc(String test) {
        System.out.println( "I'm a bus," +  test);
        return "I'm a bus," +  test;
    }
}
