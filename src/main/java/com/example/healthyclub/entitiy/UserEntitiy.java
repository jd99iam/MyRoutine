package com.example.healthyclub.entitiy;

import lombok.*;
import org.springframework.lang.Nullable;


import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="user") //테이블 이름을 지정해줌
public class UserEntitiy {

    @Id
    @GeneratedValue
    private Long id;

    //실명
    @Column
    private String name;

    //로그인아이디
    @Column
    private String userId;

    //닉네임
    @Column
    private String nickName;

    //비밀번호
    @Column
    private String password;

    //이메일
    @Column
    private String email;

    //성별
    @Column
    private String gender;

    //전화번호
    @Column
    private String phone;

    //나이
    @Column
    private int age;


    //키, 몸무게 (필수항목 아님)
    @Column
    @Nullable
    private int height;

    @Column
    @Nullable
    private int weight;

    //운동 종목
    @Column
    @Nullable
    @ElementCollection
    private List<String> exerciseType;

    public void patch(UserEntitiy userEntitiy) {
        //객체 수정
        this.nickName = userEntitiy.getNickName();
        this.weight = userEntitiy.getWeight();
        this.exerciseType = userEntitiy.getExerciseType();
        this.password = userEntitiy.getPassword();
        this.phone = userEntitiy.getPhone();
        this.height = userEntitiy.getHeight();
    }
}
