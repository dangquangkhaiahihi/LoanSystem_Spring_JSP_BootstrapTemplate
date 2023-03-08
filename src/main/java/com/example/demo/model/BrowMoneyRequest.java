package com.example.demo.model;

import lombok.Data;

@Data
public class BrowMoneyRequest {
    private String loanIdStr;
    private String loanerIdStr;
    private Long loanId;
    private Long loanerId;

    public void validateInput() {
        this.loanId = Long.parseLong(loanIdStr);
        this.loanerId = Long.parseLong(loanerIdStr);
    }
}