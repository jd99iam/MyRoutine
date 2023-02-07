package com.example.healthyclub.entity;

import com.example.healthyclub.dto.UserRequestDTO;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class UserEntity {
    //회원 개인 식별 번호
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

    //프로필 사진
    @Column
    @Nullable
    private String profileImg;

    //운동 종목
    @Column
    @Nullable
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "exercise_type")
    private List<String> exerciseType;

    @Column
    @Nullable
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "friend_table")
    private List<String> friends;

    @Column(name = "join_date")
   private LocalDate joinDate;

    @Column(name = "update_date")
    private LocalDate updateDate;

    public UserEntity(UserRequestDTO dto){
        this.name = dto.getName();
        this.userId = dto.getUserId();
        this.password = dto.getPassword();
        this.nickname = dto.getNickname();
        this.email = dto.getEmail();
        this.height = dto.getHeight();
        this.weight = dto.getWeight();
        this.gender = dto.getGender();
        this.phone = dto.getPhone();
        this.age = dto.getAge();
        this.exerciseType = dto.getExerciseType();

    }
}
