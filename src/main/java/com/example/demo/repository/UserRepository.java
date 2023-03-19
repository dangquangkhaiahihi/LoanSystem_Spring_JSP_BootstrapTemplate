package com.example.demo.repository;

import com.example.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Override
    @Query(value = "select * from user where id = :id", nativeQuery = true)
    Optional<UserEntity> findById(@Param("id") Long id);

    @Query(value = "select * from user where username = :username", nativeQuery = true)
    UserEntity findByUsername(@Param("username") String username);

    @Query(value = "select * from user where email = :email", nativeQuery = true)
    UserEntity findByEmail(@Param("email") String email);

    @Query(value = "update `USER` set amount = :amount where id = :id", nativeQuery = true)
    @Modifying
    void updateAmount(@Param("amount") Long amount, @Param("id") Long id);
}
