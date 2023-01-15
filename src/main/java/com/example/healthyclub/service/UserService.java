package com.example.healthyclub.service;

import com.example.healthyclub.dto.UserDto;
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

    //회원가입 CREATE
    @Transactional //throw로 예외를 던지면 @Transactional로 과정을 중단하고 롤백함 작동함
    public UserEntitiy create(UserDto dto){

        //dto -> enitity로 변환
        UserEntitiy userEntitiy = dto.toEntity();

        //create 메소드로 생성할 때는 Id가 자동생성이므로 Id는 null이어야함
        if (userEntitiy.getId()!=null){
            return null; //null을 반환하면 컨트롤러에서 사용자에게 bad request 응답코드 반환
        }

        //repository 이용해서 DB에 생성 + 생성한 엔티티 반환
        return userRepository.save(userEntitiy);
    }

    //회원 정보 수정 UPDATE
    @Transactional
    public UserEntitiy update(Long id,UserDto dto){ //매개변수 id는 컨트롤러에서 url의 id를 인자로 전달할것임

        //수정용 엔티티 생성
        UserEntitiy userEntitiy = dto.toEntity();

        //수정 대상 엔티티 DB에서 조회
        UserEntitiy target = userRepository.findById(id).orElse(null);

        //잘못된 요청 처리 (DB에서 url의 id에 해당하는 id를 못찾거나, url의 id와 수정을 위해 생성한 id가 다를경우)
        if (target==null||id!=target.getId()){
            return null; //null을 반환하면 컨트롤러에서 사용자에게 bad request 응답코드 반환
        }

        //DB에 업데이트, 결과 엔티티 반환
        target.patch(userEntitiy);
        UserEntitiy updated = userRepository.save(target);
        return updated;
    }


    //회원 정보 읽기 SHOW
    public UserEntitiy show(Long id){
        //id를 이용해서 찾음
        return userRepository.findById(id).orElse(null); //null을 반환하면 컨트롤러에서 사용자에게 bad request 응답코드 반환
    }

    //회원 삭제 DELETE
    public UserEntitiy delete(Long id){
        //대상 찾기
        UserEntitiy target = userRepository.findById(id).orElse(null);
        log.info(target.toString());

        //잘못된 요청 처리
        if (target==null){
            return null; //null을 반환하면 컨트롤러에서 사용자에게 bad request 응답코드 반환
        }

        //대상 삭제, 지워지는건 작동함 근데 반환코드가 잘못됨
        userRepository.delete(target);

        //데이터 반환
        return target;
    }
}
