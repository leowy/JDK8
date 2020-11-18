package com.lz.datatype;

/**
 * @Author: Leowy Zhuang
 * @Date: 2020/11/18 22:53
 * @Description: boolean类型占用多少字节
 *   1、boolean只有true与false 这两种状态， 理解使用1bit即可
 *   2、boolean单个使用时占用4个字节，因为boolean类型编译时使用int数据类型替代boolean
 *   3、boolean数组使用时占用1个字节，因为与byte数组共用baload与bastore指令，所以使用byte替代
 *   4、其实boolean类型占用字节多少取决于Java虚拟机的实现
 *
 *   for example：
 *      下面例子得到 integers 大小差不多是 booleans 4倍，故有boolean数组中boolean类型占用1个字节
 */
public class BooleanTest {

    public static final int SIZE = 100000;

    public static void main(String[] args) {
        BooleansType[] first = new BooleansType[SIZE];
        IntegersType[] second = new IntegersType[SIZE];

        System.gc();
        long startMem = getMemory();

        for (int i = 0; i < SIZE; i++) {
            first[i] = new BooleansType();
        }

        System.gc();
        long endMem = getMemory();

        System.out.println("Size for booleans: " + (endMem - startMem));
        System.out.println("Average size: " + ((endMem - startMem) / ((double) SIZE)));

        System.gc();
        startMem = getMemory();
        for (int i = 0; i < SIZE; i++) {
            second[i] = new IntegersType();
        }
        System.gc();
        endMem = getMemory();

        System.out.println("Size for integers: " + (endMem - startMem));
        System.out.println("Average size: " + ((endMem - startMem) / ((double) SIZE)));

        // Make sure nothing gets collected
        long total = 0;
        for (int i = 0; i < SIZE; i++) {
            total += (first[i].a0 ? 1 : 0) + second[i].a0;
        }
        System.out.println(total);
    }

    private static long getMemory() {
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }
}

class BooleansType {
    boolean a0, a1, a2, a3, a4, a5, a6, a7, a8, a9, aa, ab, ac, ad, ae, af;
    boolean b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, ba, bb, bc, bd, be, bf;
    boolean c0, c1, c2, c3, c4, c5, c6, c7, c8, c9, ca, cb, cc, cd, ce, cf;
    boolean d0, d1, d2, d3, d4, d5, d6, d7, d8, d9, da, db, dc, dd, de, df;
}

class IntegersType {
    int a0, a1, a2, a3, a4, a5, a6, a7, a8, a9, aa, ab, ac, ad, ae, af;
    int b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, ba, bb, bc, bd, be, bf;
    int c0, c1, c2, c3, c4, c5, c6, c7, c8, c9, ca, cb, cc, cd, ce, cf;
    int d0, d1, d2, d3, d4, d5, d6, d7, d8, d9, da, db, dc, dd, de, df;
}
