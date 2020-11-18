package com.ly.strategy.impl;

import com.ly.strategy.Intf.Learn;

public class JavaLearn implements Learn {
    @Override
    public void process(String type) {
        System.out.println("I learn Java");
    }
}
