package com.lz.datatype;

/**
 * float类型测试
 *
 * @author Leowy Zhuang
 * @create 2020-04-28 10:31
 **/
public class FloatTest {
    public static void main(String[] args) {
        //条件判断超预期
        float f1 = 0.9999999f;
        float f2 = 0.99999999f;
        System.out.println(f1 == 1f);
        System.out.println(f2 == 1f);

        //数据转换超预期
        float f3 = 1.1f;
        double d1 = (double) f3;
        System.out.println(f3);
        System.out.println(d1);

        //基本运算超预期
        System.out.println(0.2 + 0.7);

        //数据自增超预期
        float f4 = 84556721f;
        for (int i = 0; i < 10; i++) {
            System.out.println(f4);
            f4++;
        }

    }
}
