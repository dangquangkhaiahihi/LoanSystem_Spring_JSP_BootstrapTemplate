package com.example.demo.model;

import com.example.demo.common.StringUtils;
import lombok.Data;

import java.text.DecimalFormat;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String cccdNum;
    private boolean gender;
    private String phone;
    private String email;
    private String username;
    private Integer creditPoint;
    private String balance;

    public void setBalance(Float balance) {
        this.balance = StringUtils.convertFloatToString(balance);
    }
}
