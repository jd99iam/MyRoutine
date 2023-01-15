package com.example.healthyclub.dto;

import com.example.healthyclub.entitiy.UserEntitiy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class UserDto {

    private Long id;
    private String name;
    private String userId;
    private String nickName;
    private String password;
    private String email;
    private String gender;
    private String phone;
    private int age;
    private int height;
    private int weight;
    private List<String>exerciseType;

    //dto를 entity로 변환하는 메소드
    public UserEntitiy toEntity() {
        return new UserEntitiy(id,name,userId,nickName,password,email,gender,phone,age,height,weight,exerciseType);
    }
}
