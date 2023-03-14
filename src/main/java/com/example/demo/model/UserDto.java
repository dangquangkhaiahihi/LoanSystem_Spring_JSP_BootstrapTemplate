package com.example.demo.model;

import com.example.demo.common.StringUtils;
import lombok.Data;

import java.text.DecimalFormat;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String cccdNum;
    private boolean gender = true;
    private String phone;
    private String email;
    private String username;
    private Integer creditPoint;
    private String balance;

    private String password;

    public void setBalance(Float balance) {
        this.balance = StringUtils.convertFloatToString(balance);
    }

    public void validateInput() throws Exception{
        if(StringUtils.isEmpty(this.username)
                || StringUtils.isEmpty(this.password)
                || StringUtils.isEmpty(this.name)
                || StringUtils.isEmpty(this.cccdNum)
                || StringUtils.isEmpty(this.phone)
                || StringUtils.isEmpty(this.email)){
            throw new Exception("Vui lòng điền tất cả các trường");
        }
    }
}
