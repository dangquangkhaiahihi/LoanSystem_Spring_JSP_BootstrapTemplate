package com.example.demo.service;

import com.example.demo.model.LoanDto;
import com.example.demo.model.LoanRequest;

import java.util.List;

public interface LoanService {
    List<LoanDto> filter(LoanRequest loanRequest) throws Exception;

    void changeStatus(Long loanId, boolean b);
}
