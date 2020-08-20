package com.lz.spi.impl;

import com.lz.spi.IVehicle;

/**
 * @Auther: Leowy Zhuang
 * @Date: 2020/8/20 10:36
 * @Description:
 */
public class Car implements IVehicle {
    public String desc(String test) {
        System.out.println( "I'm a Car," +  test);
        return  "I'm a Car," +  test;
    }
}
