package com.lz.java8;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Leowy Zhuang
 * @Date: 2020/11/30 17:55
 * @Description: TODO
 */
public class IntStreamTest {

    public static void main(String[] args) {
        Map<String, Info> map = new HashMap<>();
        Info info = new Info();
        Hobby hobby = new Hobby();
        hobby.setInterseting("learn");
        info.setName("zhangw");
        info.setAge(30);
        info.setHobby(hobby);
        map.put("yes", info);


        for (Map.Entry<String, Info> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "=>" + entry.getValue());
            if (entry.getKey().equals("yes")) {
                map.get("yes").setName("test");
                map.get("yes").setAge(entry.getValue().getAge() + 1);
            }
            System.out.println(entry.getKey() + "=>" + entry.getValue());
        }
    }
}

class Info {
    private String name;
    private Integer age;
    private Hobby hobby;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Hobby getHobby() {
        return hobby;
    }

    public void setHobby(Hobby hobby) {
        this.hobby = hobby;
    }

    @Override
    public String toString() {
        return "Info{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", hobby=" + hobby +
                '}';
    }
}

class Hobby {
    private String interseting;

    public String getInterseting() {
        return interseting;
    }

    public void setInterseting(String interseting) {
        this.interseting = interseting;
    }

    @Override
    public String toString() {
        return "Hobby{" +
                "interseting='" + interseting + '\'' +
                '}';
    }
}
