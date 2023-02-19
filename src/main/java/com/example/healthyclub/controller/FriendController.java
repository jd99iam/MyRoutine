package com.example.healthyclub.controller;

import com.example.healthyclub.entity.UserEntity;
import com.example.healthyclub.error.ErrorDTO;
import com.example.healthyclub.repository.UserRepository;
import com.example.healthyclub.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/friend")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin
public class FriendController {

    private final UserRepository userRepository;



    //친구를 추가하기 @Pathvariable의 id는 내가 친구를 추가하려는 사람의 id, @Auth의 identifyId는 나 자신
    @GetMapping("/get/{id}")
    @Transactional
    public ResponseEntity<?> addFriends(@PathVariable Long id, @AuthenticationPrincipal String identifyId) {
        log.info("나 자신 {}",identifyId);
        log.info("친구 추가하려는 아이디: {}",id);
        long longId = Long.parseLong(identifyId);

        List<String> friends = userRepository.getUserById(longId).getFriends();
        String stringId = String.valueOf(id);
        UserEntity userById = userRepository.getUserById(id);
        log.info("{}",userById);
        if(userById == null){
            String message = "추가하려는 친구가 존재하지 않습니다.";
            return ResponseEntity.badRequest().body(new ErrorDTO(message));
        }
        friends.add(stringId);

        return ResponseEntity.ok().body(friends);
    }

    //친구를 삭제하기
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteFriends(@PathVariable Long id, @AuthenticationPrincipal String identifyId) {
        long longId = Long.parseLong(identifyId);
        List<String> friends = userRepository.getUserById(longId).getFriends();
        String stringId = String.valueOf(id);
        UserEntity userById = userRepository.getUserById(id);
        log.info("{}",userById);
        if(userById == null){
            String message = "추가하려는 친구가 존재하지 않습니다.";
            return ResponseEntity.badRequest().body(new ErrorDTO(message));
        }
        friends.remove(stringId);

        return ResponseEntity.ok().body(friends);

    }

    //나의 모든 친구 목록들을 보여주기
    @GetMapping("/show")
    @Transactional
    public ResponseEntity<?> showFriends(@AuthenticationPrincipal String identifyId) {
        long longId = Long.parseLong(identifyId);
        List<String> friends = userRepository.getUserById(longId).getFriends();
        List<UserEntity> friendslist = new ArrayList<>();
        for (String friend : friends) {
            UserEntity userByUserId = userRepository.getUserById(Long.parseLong(friend));
            friendslist.add(userByUserId);
        }
        return ResponseEntity.ok().body(friendslist);
    }

    //친구 숫자
    @GetMapping("/showcount")
    @Transactional
    public ResponseEntity<?> showFriendsCount(@AuthenticationPrincipal String identifyId) {
        long longId = Long.parseLong(identifyId);
        List<String> friends = userRepository.getUserById(longId).getFriends();
        List<UserEntity> friendslist = new ArrayList<>();
        for (String friend : friends) {
            UserEntity userByUserId = userRepository.getUserById(Long.parseLong(friend));
            friendslist.add(userByUserId);
        }
        int size = friendslist.size();
        return ResponseEntity.ok().body(size);
    }
    //나의 모든 친구 목록들을 보여주기
    @GetMapping("/showid")
    @Transactional
    public ResponseEntity<?> showFriendsid(@AuthenticationPrincipal String identifyId) {
        long longId = Long.parseLong(identifyId);
        List<String> friends = userRepository.getUserById(longId).getFriends();
        List<Long> friendslist = new ArrayList<>();
        for (String friend : friends) {
            Long id = userRepository.getUserById(Long.parseLong(friend)).getId();
            friendslist.add(id);
        }
        return ResponseEntity.ok().body(friendslist);
    }

    //그들이 이미 친구인지 아닌지 알려주기
    //그들이 이미 친구면 1(true)를 반환, 친구가 아직 아니라면 false(0)을 반환
    @PostMapping("/truefalse/{id}")
    @Transactional
    public ResponseEntity<?> booleanFriends(@PathVariable String id, @AuthenticationPrincipal String tokenId){
        int flag = userRepository.booleanFriends(tokenId, id);
        log.info("flag - {}",flag);
        return ResponseEntity.ok().body(flag);
    }

    //로그인되어 있는 나의 팔로워 수
    @GetMapping("/countfriends")
    @Transactional
    public ResponseEntity<?> countFriends(@AuthenticationPrincipal String tokenId){
        int inttokenId = Integer.parseInt(tokenId);
        int count = userRepository.friendsCount(inttokenId);

        return ResponseEntity.ok().body(count);
    }



}