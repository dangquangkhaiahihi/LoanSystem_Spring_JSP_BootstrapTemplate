package com.example.demo.service.impl;

import com.example.demo.common.StringUtils;
import com.example.demo.common.Utils;
import com.example.demo.entity.LoanEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.model.LoanDto;
import com.example.demo.model.TraceUserLoanRequest;
import com.example.demo.repository.TraceUserLoanRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.BorrowMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BorrowMoneyServiceImpl implements BorrowMoneyService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    TraceUserLoanRepository traceUserLoanRepository;

    @Override
    public void calculateInterest_TypeCurrentDebt(LoanEntity loanEntity, LoanDto loanDto) {

    }

    @Override
    public void calculateInterest_TypeInitialDebt(LoanEntity loanEntity, LoanDto loanDto) {
        float interest = 0f;

    }

    @Override
    public void borrowMoney(TraceUserLoanRequest borrowMoneyRequest) throws Exception {
        UserEntity userEntity = userRepository.findByUsername(Utils.getCurrentUser().getName());
        if (userEntity.getBalance() < borrowMoneyRequest.getAmount()) {
            throw new Exception("Số dư không đủ để thực hiện vay : "
                    + StringUtils.convertFloatToString(userEntity.getBalance())
                    + " < " + StringUtils.convertFloatToString(borrowMoneyRequest.getAmount()));
        }
        userEntity.setBalance(userEntity.getBalance() - borrowMoneyRequest.getAmount());
        userRepository.save(userEntity);
    }
}
