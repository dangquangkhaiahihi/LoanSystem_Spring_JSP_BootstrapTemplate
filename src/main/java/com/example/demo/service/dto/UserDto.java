package com.example.demo.service.dto;

import com.example.demo.common.StringUtils;
import lombok.Data;

import java.text.DecimalFormat;
import java.time.Instant;

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
    private Long amount;

    private String password;

    private String createdBy;

    private Instant createdDate;

    private String lastModifiedBy;

    private Instant lastModifiedDate;

    public void validateInput() throws Exception {
        if (StringUtils.isEmpty(this.username)
                || StringUtils.isEmpty(this.password)
                || StringUtils.isEmpty(this.name)
                || StringUtils.isEmpty(this.cccdNum)
                || StringUtils.isEmpty(this.phone)
                || StringUtils.isEmpty(this.email)) {
            throw new Exception("Vui lòng điền tất cả các trường");
        }
    }
}
