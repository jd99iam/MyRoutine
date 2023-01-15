package com.example.healthyclub.controller;

import com.example.healthyclub.dto.UserDto;
import com.example.healthyclub.entitiy.UserEntitiy;
import com.example.healthyclub.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //Get 요청으로 id에 해당하는 회원 엔티티 반환
    @GetMapping("/user/{id}")
    public ResponseEntity<UserEntitiy> show(@PathVariable Long id){
        UserEntitiy toShow = userService.show(id);

        return toShow!=null?
                ResponseEntity.status(HttpStatus.OK).body(toShow):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //Post 요청으로 폼에 입력받은 내용으로 회원정보 생성
    @PostMapping("/user")
    public ResponseEntity<UserEntitiy> create(@RequestBody UserDto dto){

        log.info(dto.toString());

        UserEntitiy created = userService.create(dto);
        log.info(created.toString());

        return created!=null?
                ResponseEntity.status(HttpStatus.OK).body(created):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        //build는 내용 없이 반환하는것 . body(null)로 반환해도 무관함

    }


    //Patch 요청으로 id에 해당하는 회원정보 수정
    @PatchMapping("/user/{id}")
    public ResponseEntity<UserEntitiy> update(@PathVariable Long id, @RequestBody UserDto dto){
        UserEntitiy updated = userService.update(id,dto);

        return updated!=null?
                ResponseEntity.status(HttpStatus.OK).body(updated):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //Delete
    @DeleteMapping("/user/{id}")
    public ResponseEntity<UserEntitiy> delete(@PathVariable Long id){
        UserEntitiy deleted = userService.delete(id);
        log.info(deleted.toString());

        return deleted!=null?
                ResponseEntity.status(HttpStatus.OK).body(deleted):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
