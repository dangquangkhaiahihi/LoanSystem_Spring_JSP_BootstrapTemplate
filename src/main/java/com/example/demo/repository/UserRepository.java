package com.example.demo.repository;

import com.example.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    UserEntity findByUsername (String username);
    UserEntity findByPhone (String phone);
    UserEntity findByCccdNum (String cccdNum);
    UserEntity findByEmail (String email);
}
