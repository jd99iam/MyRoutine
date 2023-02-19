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

    @Query(value = "select * from User where name = :name order by follower desc",nativeQuery = true)
    List<UserEntity> getUserByName(String name);

    @Query(value = "select * from user inner join exercise_type on id = user_entity_id where exercise_type = :type" +
            " order by follower desc,name",nativeQuery = true)
    List<UserEntity> getUserByType(String type);

    @Query(value = "select count(*) from friend_table where user_entity_id=:id and friends = :friends",nativeQuery = true)
    int booleanFriends(String id, String friends);

    @Query(value = "select profile_img from User where id = :id", nativeQuery = true)
    String profileImg(Long id);

    //나의 팔로워 수 찾기
    @Query(value = "select count(*) from friend_table where friends = :friends",nativeQuery = true)
    int friendsCount(int friends);

    @Query(value = "select count(*) as count from user u join friend_table f on u.id = f.user_entity_id " +
            "where friends = :friends group by friends",nativeQuery = true)
    Integer myfollowers(int friends);
}
