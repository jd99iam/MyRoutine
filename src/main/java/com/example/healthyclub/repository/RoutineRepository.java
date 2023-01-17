package com.example.healthyclub.repository;

import com.example.healthyclub.entity.RoutineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoutineRepository extends JpaRepository<RoutineEntity,Long> {

    //user_id가 userId인 루틴을 찾아서 리스트로 반환
    @Query(value = "SELECT * FROM routine WHERE user_id = :userId", nativeQuery = true)
    List<RoutineEntity> findByUserId(Long userId);

}
