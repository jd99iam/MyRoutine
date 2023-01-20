package com.example.healthyclub.service;

import com.example.healthyclub.entity.FriendEntity;
import com.example.healthyclub.entity.RoutineEntity;
import com.example.healthyclub.entity.UserEntity;
import com.example.healthyclub.repository.FriendRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.TransactionScoped;
import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository repository;

    //유저 아이디를 입력 받아서 해당하는 아이디의 모든 friendentity를 불러온다.
    @Transactional
    public List<FriendEntity> showAllFriends(Long userId){
        return repository.findByUserId(userId);

    }

    //친구 추가하기 기능
    @Transactional
    public FriendEntity addFriend(FriendEntity entity){
        FriendEntity save = repository.save(entity);
        if(save == null){
            log.info("Error occurred - FriendEntity is null");
            throw new RuntimeException("FriendEntity is null");
        }
        return save;
    }

    //친구 삭제하기 기능
    @Transactional
    public FriendEntity deleteFriends(UserEntity userEntity, Long userId){

        String friend = userEntity.getUserId();
        if(repository.findByFriendName(friend) == null){
            throw new RuntimeException("삭제하려는 친구가 존재하지 않습니다.");
        }
        repository.deleteByFriendNameAndUserId(friend, userId);
        return repository.findByFriendNameAndUserId(friend,userId);
    }



}
