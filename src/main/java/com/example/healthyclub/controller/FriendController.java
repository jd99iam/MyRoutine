package com.example.healthyclub.controller;

import com.example.healthyclub.entity.FriendEntity;
import com.example.healthyclub.entity.UserEntity;
import com.example.healthyclub.error.ErrorDTO;
import com.example.healthyclub.service.FriendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friend")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin
public class FriendController {

    private final FriendService service;

    //해당하는 id의 모든 친구들을 불러오기
    @GetMapping("/{userId}")
    public ResponseEntity<?> showAll(@PathVariable Long userId){
        List<FriendEntity> friendEntities = service.showAllFriends(userId);

        return ResponseEntity.ok().body(friendEntities);
    }

    //userId인 녀석이 친구추가를 걸기
    @PostMapping("/{userId}")
    public ResponseEntity<?> addFriend(@PathVariable Long userId, @RequestBody FriendEntity entity){
        try {
            UserEntity user = new UserEntity();
            user.setId(userId);
            entity.setUser(user);
            FriendEntity entity1 = service.addFriend(entity);

            return ResponseEntity.ok().body(entity1);
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteFriend(@PathVariable Long userId,@RequestBody UserEntity userEntity){


        try{
            FriendEntity entity = service.deleteFriends(userEntity, userId);
            return ResponseEntity.ok().body(entity);

        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }
    }








}
