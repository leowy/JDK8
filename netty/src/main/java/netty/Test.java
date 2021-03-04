package netty;

import java.io.Serializable;

/**
 * @Author: Leowy Zhuang
 * @Date: 2021/01/15 08:59
 * @Description: TODO
 */
public class Test implements Serializable {

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
        return "Test{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", hobby=" + hobby +
                '}';
    }
}

class Hobby implements Serializable{
    private String test;

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    @Override
    public String toString() {
        return "Hobby{" +
                "test='" + test + '\'' +
                '}';
    }
}
