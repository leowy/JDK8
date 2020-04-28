package com.lz.datatype;

/**
 * Integer类型测试
 *
 * @author Leowy Zhuang
 * @create 2020-04-28 14:04
 **/
public class IntegerTest {
    public static void main(String[] args) {
        Integer i1 = 1;
        Integer i2 = 1;
        Integer i3 = 127;
        Integer i4 = 127;
        Integer i5 = 128;
        Integer i6 = 128;
        Integer i7 = 100;
        Integer i8 = new Integer(100);
        System.out.println(i1 == i2);
        System.out.println(i3 == i4);
        System.out.println(i5 == i6);
        System.out.println(i5.equals(i6));
        System.out.println(i7 == i8);

    }
}
