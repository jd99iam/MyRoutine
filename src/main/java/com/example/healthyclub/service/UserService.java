package com.example.healthyclub.service;

import com.example.healthyclub.entity.UserEntity;
import com.example.healthyclub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    //회원가입하기
    @Transactional
    public UserEntity create(UserEntity userEntity){
        if(userEntity.getId() == null || userEntity == null){
            log.info("userEntity create is error!");
            throw new RuntimeException("UserEntity(Service) create Error");
        }
        return repository.save(userEntity);
    }

    //회원 정보 수정
    @Transactional
    public UserEntity update(UserEntity userEntity){
//        UserEntity target = repository.findById(userEntity.getId())
//                .orElseThrow(() -> new IllegalArgumentException("우저 수정 실패, 대상 유저가 없습니다."));
//
//        target.patch(userEntity);
        if(userEntity == null || userEntity.getId() == null){
            log.info("userEntity update is error!");
            throw new RuntimeException("UserEntity(Service) update Error");
        }
        return repository.save(userEntity);
    }

    //회원 정보 읽기
    public UserEntity show(Long id){
        return repository.findById(id).orElse(null);
    }

    //회원 탈퇴
    @Transactional
    public UserEntity delete(long id){
        UserEntity target = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Userservice delete error"));
        repository.delete(target);

        return target;
    }



}
