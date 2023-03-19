package com.example.demo.model;

import com.example.demo.common.StringUtils;
import com.example.demo.common.Utils;
import lombok.Data;

import java.text.DecimalFormat;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String username;
    private String password;

    public void validateInput() throws Exception{
        if(StringUtils.isEmpty(this.name)
//                || StringUtils.isEmpty(this.phone)
                || StringUtils.isEmpty(this.email)){
            throw new Exception("Vui lòng điền tất cả các trường");
        }

        if(!Utils.validateEmail(this.email)){
            throw new Exception("Email không đúng định dạng");
        }
    }
}
