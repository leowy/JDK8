package com.ly.strategy.impl;

import com.ly.strategy.Learn;

public class PhpLearn implements Learn {
    @Override
    public void process(String type) {
        System.out.println("I learn PHP");
    }
}
