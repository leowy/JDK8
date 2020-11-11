package com.ly.fastjson;

import com.alibaba.fastjson.JSON;

import java.time.Instant;

/**
 * @Auther: Leowy Zhuang
 * @Date: 2020/11/11 08:57
 * @Description:
 */
public class JsonTest {

    public static void main(String[] args) {
        UserInfo userInfo = UserInfo.builder()
                .name("test")
                .age(10)
                .gender("male")
                .birth(Instant.now())
                .school(School.builder()
                        .schoolName("school-test1")
                        .className("3y2c")
                        .entryTime(Instant.now())
                        .build())
                .school(School.builder()
                        .schoolName("school-test")
                        .className("3y3c")
                        .entryTime(Instant.now())
                        .build())
                .build();


        final String s = JSON.toJSONString(userInfo);
        System.out.println(s);
        final UserInfo userInfo1 = JSON.parseObject(s, UserInfo.class);
        System.out.println(userInfo1);

    }
}
