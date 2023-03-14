package com.example.demo.service;

import com.example.demo.entity.UserEntity;
import com.example.demo.model.TransactionDto;

import java.util.List;

public interface TransactionService {
    void addTransaction(UserEntity user, Float amount, Boolean type, String desc);

    List<TransactionDto> filter();
}
