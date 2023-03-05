package com.example.demo.exception;

public class AddBalanceNotMinException extends Exception {
    public AddBalanceNotMinException(String errorMessage) {
        super(errorMessage);
    }
}
