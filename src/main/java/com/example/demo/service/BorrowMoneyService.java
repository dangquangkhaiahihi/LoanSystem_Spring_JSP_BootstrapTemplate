package com.example.demo.service;

import com.example.demo.entity.LoanEntity;
import com.example.demo.model.LoanDto;
import com.example.demo.model.BrowMoneyRequest;

public interface BorrowMoneyService {

    void requestBorrowMoney(BrowMoneyRequest borrowMoneyRequest);

    void deleteRequest(Long loanId);
}
