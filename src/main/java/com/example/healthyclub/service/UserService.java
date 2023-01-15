package com.example.healthyclub.service;

import com.example.healthyclub.entitiy.UserEntitiy;
import com.example.healthyclub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor //@Autowired 대신 의존성 주입을 위해 사용함
public class UserService {

    // DI를 위해 @Autowired 대신에 @RequiredArgsConstructor 사용, 대신에 final 붙여야함
    private final UserRepository userRepository;

    //회원가입
    @Transactional //throw @Transactional이 작동함
    public UserEntitiy create(UserEntitiy userEntitiy){

        if (userEntitiy==null){
            log.info("UserService create exception");
            throw new RuntimeException("UserService create exception");
        }

        return userRepository.save(userEntitiy);

    }



    //회원 정보 수정
    @Transactional
    public UserEntitiy update(UserEntitiy userEntitiy){

        if (userEntitiy==null||userEntitiy.getId()==null){
            log.info("UserService update exception");
            throw new RuntimeException("UserService update exception");
        }

        return userRepository.save(userEntitiy);
    }

    //회원 정보 읽기
    public UserEntitiy show(Long id){
        return userRepository.findById(id).orElse(null);
    }



    //회원 삭제
    @Transactional
    public UserEntitiy delete(Long id){
        UserEntitiy target = userRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("UserService delete exception"));

        userRepository.delete(target);

        return target;
    }



}
