package com.example.demo.service;

import com.example.demo.entity.LoanEntity;
import com.example.demo.model.LoanDto;
import com.example.demo.model.BrowMoneyRequest;

public interface BorrowMoneyService {
    void calculateInterest_TypeCurrentDebt(LoanEntity loanEntity, LoanDto loanDto);

    void calculateInterest_TypeInitialDebt(LoanEntity loanEntity, LoanDto loanDto);

    void requestBorrowMoney(BrowMoneyRequest borrowMoneyRequest);

    void deleteRequest(Long loanId);
}
