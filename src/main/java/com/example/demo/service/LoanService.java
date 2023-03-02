package com.example.demo.service;
import com.example.demo.model.LoanRequestAdd;
import com.example.demo.model.LoanDto;
import com.example.demo.model.LoanRequest;

import java.util.List;

public interface LoanService {
    List<LoanDto> filter(LoanRequestFilter loanRequestFilter) throws Exception;

    void addLoan(LoanRequestAdd loanRequestAdd);

    void changeStatus(Long loanId, boolean b);
}
