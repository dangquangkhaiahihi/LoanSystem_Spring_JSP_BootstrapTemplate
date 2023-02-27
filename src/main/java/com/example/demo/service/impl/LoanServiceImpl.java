package com.example.demo.service.impl;

import com.example.demo.entity.LoanEntity;
import com.example.demo.model.LoanDto;
import com.example.demo.model.LoanRequest;
import com.example.demo.repository.LoanRepository;
import com.example.demo.service.LoanService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {
    @Autowired
    LoanRepository loanRepository;


    @Override
    public List<LoanDto> filter(LoanRequest loanRequest) throws Exception {
        List<LoanEntity> loanEntities = loanRepository.findAll();
        List<LoanDto> loanDtos = new ArrayList<>();
        for(LoanEntity loanEntity : loanEntities){
            LoanDto loanDto = new LoanDto();
            BeanUtils.copyProperties(loanEntity,loanDto);
            loanDto.setAmount(loanEntity.getAmount());
            loanDto.setCreatedAt(loanEntity.getCreatedAt());
            loanDto.setDeadline(loanEntity.getDeadline());
            loanDtos.add(loanDto);
        }
        return loanDtos;
    }
}
