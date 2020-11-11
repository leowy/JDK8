package com.ly.fastjson;

import lombok.*;

import java.time.Instant;
import java.util.List;

/**
 * @Auther: Leowy Zhuang
 * @Date: 2020/11/11 08:58
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserInfo {

    private String name;
    private Integer age;
    private String gender;
    private Instant birth;
    @Singular
    private List<School> schools;

}

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
class School {
    private String schoolName;
    private String className;
    private Instant entryTime;
}
