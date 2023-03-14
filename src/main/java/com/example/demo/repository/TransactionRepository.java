package com.example.demo.repository;

import com.example.demo.entity.TransactionEntity;
import com.example.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity,Long> {
    List<TransactionEntity> findByUserOrderByCreatedAtDesc (UserEntity userEntity);
}
