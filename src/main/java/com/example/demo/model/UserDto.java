package com.example.demo.model;

import lombok.Data;

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
    private Float balance;
}
