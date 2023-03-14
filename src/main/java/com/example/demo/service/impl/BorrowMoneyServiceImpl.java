package com.example.demo.service.impl;

import com.example.demo.common.Utils;
import com.example.demo.entity.LoanEntity;
import com.example.demo.entity.RequestEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.model.BrowMoneyRequest;
import com.example.demo.model.LoanDto;
import com.example.demo.repository.LoanRepository;
import com.example.demo.repository.RequestRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.BorrowMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BorrowMoneyServiceImpl implements BorrowMoneyService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    LoanRepository loanRepository;

    @Autowired
    RequestRepository requestRepository;

    @Override
    public void requestBorrowMoney(BrowMoneyRequest borrowMoneyRequest) {
        UserEntity currentUser = userRepository.findByUsername(Utils.getCurrentUser().getName());
        UserEntity loaner = userRepository.findById(borrowMoneyRequest.getLoanerId()).get();
        LoanEntity loan = loanRepository.findById(borrowMoneyRequest.getLoanId()).get();

        RequestEntity requestBorrow = new RequestEntity();
        requestBorrow.setLoaner(loaner);
        requestBorrow.setDebtor(currentUser);
        requestBorrow.setLoan(loan);
        requestRepository.save(requestBorrow);
    }

    @Override
    public void deleteRequest(Long loanId) {
        LoanEntity loanEntity = loanRepository.findById(loanId).get();
        UserEntity currentUser = userRepository.findByUsername(Utils.getCurrentUser().getName());

        RequestEntity requestEntity = requestRepository.findByLoanAndDebtor(loanEntity,currentUser);
        requestRepository.delete(requestEntity);
    }
}
