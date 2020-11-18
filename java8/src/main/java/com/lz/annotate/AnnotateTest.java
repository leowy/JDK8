package com.lz.annotate;

/**
 * 注释是否执行
 * 1、java编译器编译代码
 * 2、编译器也会编译unicode字符
 * 3、文中注释的unicode为\u000a 换行符, \u000d 回车
 *
 * @author Leowy Zhuang
 */
public class AnnotateTest {
    public static void main(String[] args) {
        String name = "张三";
        // \u000a name = "李四";
        System.out.println(name);
        // \u000d name = "王五";
        System.out.println(name);

    }
}
