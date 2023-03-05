package com.example.demo.model;

import com.example.demo.entity.LoanEntity;
import lombok.Data;

@Data
public class TraceUserLoanRequest {
    private String amountStr;
    private Float amount;
    private String type;
    private String duration;
    private String interestStr;

    private Float interest;

    public void validateInput() {
        this.amount = Float.parseFloat(amountStr);
        this.interest = Float.parseFloat(interestStr);
    }
}