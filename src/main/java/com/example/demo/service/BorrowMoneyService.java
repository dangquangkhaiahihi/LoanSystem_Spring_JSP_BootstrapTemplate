package com.example.demo.service;

import com.example.demo.entity.LoanEntity;
import com.example.demo.model.LoanDto;
import com.example.demo.model.TraceUserLoanRequest;

public interface BorrowMoneyService {
    void calculateInterest_TypeCurrentDebt(LoanEntity loanEntity, LoanDto loanDto);

    void calculateInterest_TypeInitialDebt(LoanEntity loanEntity, LoanDto loanDto);

    void borrowMoney(TraceUserLoanRequest borrowMoneyRequest) throws Exception;
}
