package com.example.demo.service.impl;

import com.example.demo.common.Utils;
import com.example.demo.entity.TransactionEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.model.TransactionDto;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public void addTransaction(UserEntity user, Float amount, Boolean type, String desc) {
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setUser(user);
        transactionEntity.setAmount(amount);
        transactionEntity.setType(type);
        transactionEntity.setDesc(desc);
        transactionRepository.save(transactionEntity);
    }

    @Override
    public List<TransactionDto> filter() {
        UserEntity userEntity = userRepository.findByUsername(Utils.getCurrentUser().getName());
        List<TransactionEntity> transactionEntities = transactionRepository.findByUserOrderByCreatedAtDesc(userEntity);
        List<TransactionDto> transactionDtos = new ArrayList<>();
        for (TransactionEntity transactionEntity : transactionEntities){
            TransactionDto transactionDto = new TransactionDto();
            transactionDto.setId(transactionEntity.getId());
            transactionDto.setAmount(transactionEntity.getAmount(),transactionEntity.getType());
            transactionDto.setDesc(transactionEntity.getDesc());
            transactionDto.setCreatedAt(transactionEntity.getCreatedAt());
            transactionDtos.add(transactionDto);
        }
        return transactionDtos;
    }
}
