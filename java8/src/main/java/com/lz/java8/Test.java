package com.lz.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Author: Leowy Zhuang
 * @Date: 2020/12/25 22:50
 * @Description: TODO
 */
public class Test {
    public static void main(String[] args) {
        int[] sums1 = {1,3};
        int[] sums2 = {2};


        Integer[] s1 = Arrays.stream(sums1).boxed().toArray(Integer[]::new);
        Integer[] s2 = Arrays.stream(sums2).boxed().toArray(Integer[]::new);
        List<Integer> collect = Stream.concat(Stream.of(s1), Stream.of(s2)).sorted().collect(Collectors.toList());
        collect.forEach(System.out::println);
        int size = collect.size();
        if (size % 2 == 0) {
            double i1 = collect.get(size / 2 - 1);
            double i2 = collect.get(size / 2);
            System.out.println("-=========>" + i2);
            double d3 = (i2+i1) / 2;
            System.out.println(d3);
        } else {
            System.out.println((double)collect.get(size/2));
        }

    }
}
