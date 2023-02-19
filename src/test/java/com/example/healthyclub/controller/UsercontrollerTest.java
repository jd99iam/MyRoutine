package com.example.healthyclub.controller;

import com.example.healthyclub.entity.UserEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UsercontrollerTest {
    @Autowired
    Usercontroller usercontroller;

    @Test
    @DisplayName("ㅇㅇ")
    @Transactional
    void get(){
        ResponseEntity<?> list = usercontroller.getUserByName("조종원");

        System.out.println("=========================");
        System.out.println(list);
        System.out.println("==========================");

    }

}