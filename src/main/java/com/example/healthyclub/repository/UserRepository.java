package com.example.healthyclub.repository;


import com.example.healthyclub.entity.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "select * from User where user_id = :user_id", nativeQuery = true)
    UserEntity getUserByUserId(String user_id);

    @Query(value = "select * from User where id = :id", nativeQuery = true)
    UserEntity getUserById(Long id);

    @Query(value = "select * from User where name = :name",nativeQuery = true)
    List<UserEntity> getUserByName(String name);

    @Query(value = "select * from user inner join exercise_type on id = user_entity_id where exercise_type = :type",nativeQuery = true)
    List<UserEntity> getUserByType(String type);

    @Query(value = "select count(*) from friend_table where user_entity_id=:id and friends = :friends",nativeQuery = true)
    int booleanFriends(String id, String friends);


}
