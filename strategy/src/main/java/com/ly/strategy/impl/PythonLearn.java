package com.ly.strategy.impl;

import com.ly.strategy.Intf.Learn;

public class PythonLearn implements Learn {
    @Override
    public void process(String type) {
        System.out.println("I learn Python");
    }
}
