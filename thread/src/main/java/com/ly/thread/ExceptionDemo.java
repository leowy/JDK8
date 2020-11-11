package com.ly.thread;

/**
 * @Auther: Leowy Zhuang
 * @Date: 2020/10/27 10:24
 * @Description:
 */
public class ExceptionDemo {
    public static void main(String[] args) {
        System.out.println(Test.getDesc(1));
        System.out.println(Test.getValue("test3"));

        System.out.println(Test.getDesc(Test.TEST2.getValue()));
        System.out.println(Test.TEST2.getDesc());
    }
}

enum Test {

    TEST1(1, "test1"),
    TEST2(2, "test2"),
    TEST3(3, "test3");

    Test(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    private int value;
    private String desc;

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public static int getValue(String desc) {
        for (Test t : Test.values()) {
            if (desc.equals(t.desc)) return t.value;
        }
        return 0;
    }

    public static String getDesc(int value) {
        for (Test t : Test.values()) {
            if (value == t.value) return t.desc;
        }
        return "";

    }

}
