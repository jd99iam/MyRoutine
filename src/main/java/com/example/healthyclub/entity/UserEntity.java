package com.example.healthyclub.entity;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class UserEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //실명
    @Column
    private String name;

    //아이디
    @Column
    private String userId;

    //비밀번호
    @Column
    private String password;

    //닉네임
    @Column
    private String nickname;

    //이메일
    @Column
    private String email;

    //키, 몸무게
    @Nullable
    @Column
    private int height;

    @Nullable
    @Column
    private int weight;

    //성별
    @Column
    private String gender;

    //전화번호
    @Column
    private String phone;

    //나이
    @Column
    private int age;

    //운동 종목
    @Column(name = "exercise_type")
    @Nullable
    @ElementCollection
    private List<String> exerciseType;

    public UserEntity(String name, String userId, String password, String nickname, String email, int height, int weight,
                      String gender, String phone, int age,List<String> exerciseType){
        this.id = 0L;
        this.name = name;
        this.userId = userId;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
        this.phone = phone;
        this.age = age;
        this.exerciseType = exerciseType;
    }

//    public UserEntity patch(UserEntity userEntity) {
//        //예외처리
//        if (userEntity.getId() == null || this.getId() != userEntity.getId()){
//            return null;
//        }
//
//        //객체 수정
//        this.nickname = userEntity.getNickname();
//        this.weight = userEntity.getWeight();
//        this.exerciseType = userEntity.getExerciseType();
//        this.password = userEntity.getPassword();
//        this.phone = userEntity.getPhone();
//        this.height = userEntity.getHeight();
//
//        return this;
//    }
}
