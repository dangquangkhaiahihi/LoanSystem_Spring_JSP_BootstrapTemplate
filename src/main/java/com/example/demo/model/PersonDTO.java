package com.example.demo.model;

import com.example.demo.common.StringUtils;
import lombok.Data;

import java.time.Instant;

@Data
public class PersonDTO {
    private Long id;

    private String name;

    private String address;

    private String phone;

    private String email;

    private Long totalAmount = 0L;

    public void validateRequest () throws Exception{
        if(StringUtils.isEmpty(this.name) || StringUtils.isEmpty(this.phone)){
            throw new Exception("Vui lòng không bỏ trống các trường bắt buộc.");
        }
    }
}
