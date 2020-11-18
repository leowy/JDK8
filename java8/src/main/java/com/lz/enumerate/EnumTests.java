package com.lz.enumerate;

/**
 * 枚举类测试
 *
 * @author Leowy Zhuang
 * @create 2020-05-08 11:19
 **/
public class EnumTests {
    public static void main(String[] args) {

        SeasonEnum season = SeasonEnum.SPRING;
        System.out.println(season);
        Color color = Color.BLANK;
        System.out.println(color);

    }

}

enum SeasonEnum {
    SPRING(1), SUMMER(2), AUTUMN(3), WINTER(4);
    private int seq;

    SeasonEnum(int seq) {
        this.seq = seq;
    }

    public int getSeq() {
        return seq;
    }
}

enum Color {
    RED("红色", 1), GREEN("绿色", 2), BLANK("白色", 3), YELLOW("黄色", 4);
    private String name;
    private int index;

    // 构造方法  
    private Color(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法  
    public static String getName(int index) {
        for (Color c : Color.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    // get set 方法  
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}