package com.example.healthyclub.service;

import com.example.healthyclub.entity.UserEntity;
import com.example.healthyclub.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;
import java.sql.Array;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService service;

    @Autowired
    PasswordEncoder encoder;
    @Test
    @DisplayName("회원가입이 정상적으로 이루어지는가")
    public void create(){
        ArrayList<String> objects = new ArrayList<>();
        objects.add("파워");
        objects.add("속도");
        LocalDate date = LocalDate.now();

        //given


//        user.setExerciseType(objects);

        //when
        UserEntity userEntity = service.create(new UserEntity(1L,"끝","a123","가나","1234","1@naver.com",170,60,"남자","010111",20,objects,date,date));

        System.out.println(userEntity.toString());

        //then
    }

    @Test
    @DisplayName("회원가입두명이 정상적으로 이루어지는가")
    public void create2(){
        ArrayList<String> objects = new ArrayList<>();
        objects.add("파워");
        objects.add("속도");
        LocalDate date = LocalDate.now();
        //given
        UserEntity user = new UserEntity(2L,"조종원","a123","가나","1234","1@naver.com",170,60,"남자","010111",20,objects,date,date);

//        user.setExerciseType(objects);

        //when
        UserEntity userEntity = service.create(user);

        //then
        assertEquals(user.toString(),userEntity.toString());
    }

    @Test
    @DisplayName("1L의 조종원의 나이가 25로 잘 변경이 됐는가, 날짜도 변경됨")
    public void update(){

        ArrayList<String> objects = new ArrayList<>();
        objects.add("파워");
        objects.add("속도");
        LocalDate date = LocalDate.now();
        //given
        UserEntity user = new UserEntity(2L,"조종원","a123","가나","1234","1@naver.com",170,60,"남자","010111",25,objects,date,date);

        UserEntity update = service.update(user);

        assertEquals(user.toString(), update.toString());

    }

    @Test
    @DisplayName("1L을 잘 보여주는가")
    @Transactional
    public void show(){
        Long id = 1L;
        UserEntity shows = service.show(id);
        assertEquals(shows.getUserId(),"a123");
        System.out.println(shows.toString());
    }

    @Test
    @DisplayName("2L이 잘 지워졌는가")
    public void delete(){
        Long id = 2L;
        UserEntity deletes = service.delete(id);


    }

    @Test
    @DisplayName("비밀번호 디코딩 전후가 같은가")
    void aboutpassword(){
        //given
        UserEntity user = service.getUser("six");

        System.out.println(user);

        String pw = user.getPassword();

        boolean matches = encoder.matches("12345", pw);
        System.out.println(matches);
    }


}