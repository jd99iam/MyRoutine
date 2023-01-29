package com.example.healthyclub.service;

import com.example.healthyclub.entity.UserEntity;
import com.example.healthyclub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    //회원가입하기
    @Transactional
    public UserEntity create(UserEntity userEntity){
        if(userEntity == null){
            log.info("userEntity create is error!");
            throw new RuntimeException("UserEntity(Service) create Error");
        }
        String rawPw = userEntity.getPassword();
        userEntity.setPassword(encoder.encode(rawPw));

        return repository.save(userEntity);
    }

    //회원 정보 수정
    @Transactional
    public UserEntity update(UserEntity userEntity){
//        UserEntity target = repository.findById(userEntity.getId())
//                .orElseThrow(() -> new IllegalArgumentException("우저 수정 실패, 대상 유저가 없습니다."));
//
//        target.patch(userEntity);
        if(userEntity == null){
            log.info("userEntity update is error!");
            throw new RuntimeException("UserEntity(Service) update Error");
        }
        String rawPw = userEntity.getPassword();
        userEntity.setPassword(encoder.encode(rawPw));
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
                .orElseThrow(() -> new RuntimeException("Userservice delete error"));
        repository.delete(target);

        return target;
    }

    //loginId 통해서 userEntity 찾아내기
    @Transactional
    public UserEntity getUser(String loginId){
        return repository.getUserByUserId(loginId);
    }

    //로그인할 때 검증하기
    @Transactional
    public UserEntity validateLogin(final String loginId, final String password){
        //회원가입을 했는가?
        UserEntity user = getUser(loginId);
        log.info("user - {}",user);
        if (user == null) throw new RuntimeException("가입된 회원이 아닙니다.");

        //패스워드가 일치하는가?
        if (!encoder.matches(password, user.getPassword())){
            throw new RuntimeException("비밀번호가 틀립니다.");
        }
        return user; //로그인 성공시 그 회원의 정보를 보여준다.
    }

    //실명(name으로 회원 검색하기)
    @Transactional
    public List<UserEntity> getUserByName(String name){
        log.info("name - {}",name);
        List<UserEntity> userByName = repository.getUserByName(name);
        if (userByName.size() == 0) throw new RuntimeException("해당 이름을 가진 회원이 존재하지 않습니다.");
        return userByName;
    }
}
