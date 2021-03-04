package com.lz.string;

/**
 * @Author: Leowy Zhuang
 * @Date: 2021/01/26 10:09
 * @Description: TODO
 */
public class FirstLetterUpperCase {
    public static void main(String[] args) {

        String t = "zaaaaaaaaaa";

        char[] chars = t.toCharArray();
        chars[0] -= 32;
        t = String.valueOf(chars);
        System.out.println(t);
    }
}
