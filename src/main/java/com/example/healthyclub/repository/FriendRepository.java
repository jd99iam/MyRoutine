package com.example.healthyclub.repository;

import com.example.healthyclub.entity.FriendEntity;
import com.example.healthyclub.entity.RoutineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FriendRepository extends JpaRepository<FriendEntity,Long> {

    //user_id가 userId인 루틴을 찾아서 리스트로 반환
    @Query(value = "SELECT * FROM friends WHERE user_id = :userId", nativeQuery = true)
    List<FriendEntity> findByUserId(Long userId);

    //친구이름이 동일한 모든 리스트 추출하기
    @Query(value = "SELECT * FROM friends WHERE friend = :friend", nativeQuery = true)
    List<FriendEntity> findByFriendName(String friend);

    @Query(value = "select * from friends where friend = :friend and user_id = :userId", nativeQuery = true)
    FriendEntity findByFriendNameAndUserId(String friend, Long userId);

    @Query(value = "delete from friends where friend = :friend and user_id = :userId", nativeQuery = true)
    FriendEntity deleteByFriendNameAndUserId(String friend, Long userId);
}
