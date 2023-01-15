package com.example.healthyclub.service;

import com.example.healthyclub.entitiy.UserEntitiy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    //테스트에서는 Autowired 사용함
    @Autowired
    UserService userService;

    @Test
    @DisplayName("UserService create 테스트")
    @Transactional
    public void testCreate(){

        //테스트 할 때 Transactional과는 상관없이 DB에서 세는 id 갑 자동 증가하는 것은 돌아가지 않음

        ArrayList<String> list = new ArrayList<>();
        list.add("턱걸이");
        list.add("푸쉬업");
        UserEntitiy expected = new UserEntitiy(
                1L,
                "장지담",
                "jidam03",
                "jidami",
                "pw123",
                "email@123.com",
                "남자",
                "01012345678",
                24,
                170,
                75,
                list

        );

        UserEntitiy real = userService.create(
                new UserEntitiy(
                        null,
                        "장지담",
                        "jidam03",
                        "jidami",
                        "pw123",
                        "email@123.com",
                        "남자",
                        "01012345678",
                        24,
                        170,
                        75,
                        list

                )
        );



        assertEquals(expected.toString(),real.toString());

    }

    @Test
    @Transactional
    public void updateTest(){
        ArrayList<String> list = new ArrayList<>();
        list.add("턱걸이");
        list.add("푸쉬업");
        UserEntitiy expected = new UserEntitiy(
                1L,
                "장지담",
                "jidam03",
                "jidami",
                "pw123",
                "email@123.com",
                "남자",
                "01012345678",
                24,
                170,
                75,
                list

        );
        expected.setName("수정이름");

        UserEntitiy real = userService.update(expected);

        assertEquals(expected.toString(),real.toString());
    }

    @Test
    @Transactional
    public void deleteTest(){
        ArrayList<String> list = new ArrayList<>();
        list.add("턱걸이");
        list.add("푸쉬업");
        UserEntitiy expected = new UserEntitiy(
                1L,
                "장지담",
                "jidam03",
                "jidami",
                "pw123",
                "email@123.com",
                "남자",
                "01012345678",
                24,
                170,
                75,
                list

        );

        UserEntitiy real = userService.delete(expected.getId());

        assertEquals(expected.toString(),real.toString());
    }

    @Test
    @Transactional
    public void showTest(){
        ArrayList<String> list = new ArrayList<>();
        list.add("턱걸이");
        list.add("푸쉬업");
        UserEntitiy expected = new UserEntitiy(
                1L,
                "장지담",
                "jidam03",
                "jidami",
                "pw123",
                "email@123.com",
                "남자",
                "01012345678",
                24,
                170,
                75,
                list

        );

        UserEntitiy real = userService.show(expected.getId());

        assertEquals(expected.toString(),real.toString());
    }







}