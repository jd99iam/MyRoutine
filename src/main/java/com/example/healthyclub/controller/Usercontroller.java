package com.example.healthyclub.controller;

import com.example.healthyclub.dto.UserRequestDTO;
import com.example.healthyclub.dto.UserResponseDTO;
import com.example.healthyclub.entity.UserEntity;
import com.example.healthyclub.error.ErrorDTO;
import com.example.healthyclub.jwt.TokenProvider;
import com.example.healthyclub.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/auth")
public class Usercontroller {

    private final UserService service;
    private final TokenProvider provider;

    //회원가입하기
    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody UserRequestDTO dto){

        try {
            log.info("/auth/join - {}",dto);
            //입력받은 userRequestDTO를 userEntity 타입으로 변환해준다
            UserEntity userEntity = new UserEntity(dto);
            LocalDate date = LocalDate.now();
            userEntity.setJoinDate(date);
            userEntity.setUpdateDate(date);
            UserEntity user = service.create(userEntity);

            return ResponseEntity.ok().body(user);

        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }

    }

    //회원식별번호(id)를 @PathVariable로 보내주면 회원 정보를 보여주기
    @GetMapping("/show/{id}")
    public ResponseEntity<?> showOne(@PathVariable Long id){
        UserEntity entity = service.show(id);
        log.info("entity에 대한 정보 - {}", entity);
        String message = "해당 id과 일치하는 회원정보가 없습니다.";
        return (entity != null) ? ResponseEntity.ok().body(entity) : ResponseEntity.badRequest().body(new ErrorDTO(message));
    }

    //Userequestdto를 입력하면 정보를 바꿔주기
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody UserRequestDTO dto,@AuthenticationPrincipal String Id){

        if(Id.equals("anonymousUser")){
            String m = "접근 권한이 없습니다.";
            return ResponseEntity.badRequest().body(new ErrorDTO(m));
        }

        Long userId = Long.parseLong(Id);
        if(!userId.equals(service.show(id).getId())){
            String m = "접근 권한이 없습니다.";
            return ResponseEntity.badRequest().body(new ErrorDTO(m));
        }


        try {
            log.info("/auth/update- {}",dto);
            //입력받은 userRequestDTO를 userEntity 타입으로 변환해준다
            UserEntity userEntity = new UserEntity(dto);

            //joinDate 집어넣기
            LocalDate joinDate = service.show(id).getJoinDate();

            //updatedate 갱신
            LocalDate date = LocalDate.now();

            userEntity.setJoinDate(joinDate);
            userEntity.setUpdateDate(date);
            userEntity.setId(id);
            UserEntity user = service.update(userEntity);
            log.info("@AuthenticationPrincipal String userId : {}",userId);
            return ResponseEntity.ok().body(user);

        }catch(RuntimeException e){
            String message = "회원 update 실패";
            return ResponseEntity.badRequest().body(new ErrorDTO(message));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id, @AuthenticationPrincipal String Id){

        if(Id.equals("anonymousUser")){
            String m = "접근 권한이 없습니다.";
            return ResponseEntity.badRequest().body(new ErrorDTO(m));
        }

        Long userId = Long.parseLong(Id);
        if(!userId.equals(service.show(id).getId())){
            String m = "접근 권한이 없습니다.";
            return ResponseEntity.badRequest().body(new ErrorDTO(m));
        }

        try {
            UserEntity delete = service.delete(id);
            UserResponseDTO dto = new UserResponseDTO(delete);

            log.info("@AuthenticationPrincipal String userId : {}",userId);
            return ResponseEntity.ok().body(dto);
        }catch(Exception e){
            String message = "delete가 잘되지 않았습니다.";
            return ResponseEntity.badRequest().body(new ErrorDTO(message));

        }
    }
    //로그인하기
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequestDTO dto){
        log.info("/auth/login POST - login info : {}",dto);
        try{
            UserEntity user = service.validateLogin(dto.getUserId(), dto.getPassword());
            //토큰 발행하기
            final String token = provider.create(user);
            UserResponseDTO userResponseDTO = new UserResponseDTO(user);
            userResponseDTO.setToken(token);

            return ResponseEntity.ok().body(userResponseDTO);
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }
    }

    //실명(name)에 해당되는 모든 user를 리턴
    @GetMapping("/showname/{name}")
    public ResponseEntity<?> getUserByName(@PathVariable String name){
        log.info("/auth/show/name -{}",name);

        try{
            List<UserEntity> userByName = service.getUserByName(name);
            return ResponseEntity.ok().body(userByName);
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }
    }

    //운동타입에 해당하는 모든 user를 리턴
    @GetMapping("/showtype/{type}")
    public ResponseEntity<?> getUserByType(@PathVariable String type){
        log.info("/auth/show/type -{}",type);

        try{
            List<UserEntity> userByType = service.getUserByType(type);
            return ResponseEntity.ok().body(userByType);
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }
    }
}
