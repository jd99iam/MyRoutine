package com.example.healthyclub.repository;


import com.example.healthyclub.entitiy.UserEntitiy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntitiy,Long> {

}
