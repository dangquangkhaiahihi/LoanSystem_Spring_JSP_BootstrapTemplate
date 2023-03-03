package com.example.demo.service.impl;

import com.example.demo.entity.LoanEntity;
import com.example.demo.model.LoanDto;
import com.example.demo.service.BorrowMoneyService;

public class BorrowMoneyServiceImpl implements BorrowMoneyService {
    @Override
    public void calculateInterest_TypeCurrentDebt(LoanEntity loanEntity, LoanDto loanDto) {

    }

    @Override
    public void calculateInterest_TypeInitialDebt(LoanEntity loanEntity, LoanDto loanDto) {
        float interest = 0f;

    }
}
