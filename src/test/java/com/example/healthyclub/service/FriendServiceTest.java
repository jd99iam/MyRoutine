package com.example.healthyclub.service;

import com.example.healthyclub.entity.FriendEntity;
import com.example.healthyclub.entity.UserEntity;
import com.example.healthyclub.repository.FriendRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FriendServiceTest {

    @Autowired
    FriendService service;

    @Autowired
    FriendRepository repository;
    @Test
    @DisplayName("친구 추가가 잘 되었나?")
    void addFriend(){
        //given
        FriendEntity friendEntity = new FriendEntity();
        UserEntity user = new UserEntity();
        user.setId(18L);
        friendEntity.setFriend("조종기기2");
        friendEntity.setUser(user);
        //when
        service.addFriend(friendEntity);
        //then
        System.out.println(friendEntity);
    }

    @Test
    @DisplayName("id 18에 해당되는 두 개의 friendentity 리스트를 잘 반환하는가")
    void showAll(){
        //given
        UserEntity user = new UserEntity();
        user.setId(18L);
        FriendEntity friend = new FriendEntity();
        friend.setUser(user);
        //when
        List<FriendEntity> friendEntities = service.showAllFriends(18L);
        //then
        for (FriendEntity friendEntity : friendEntities) {
            System.out.println("friendEntity : " + friendEntity);
        }
    }

//    @Test
//    @DisplayName("조종기기2가 잘 지워졌는가")
//    void deleteFriends(){
//        //given
//        FriendEntity entity = service.deleteFriends("조종기기2", 18L);
//        //when
//        //then
//        System.out.println(entity);
//    }


}