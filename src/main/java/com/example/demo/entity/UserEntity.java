package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "USER")
public class UserEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", length = 50, nullable = false)
    private String name;

    @Column(name = "CCCD_NUM", unique = true, length = 12, nullable = false)
    private String cccdNum;

    @Column(name = "GENDER", nullable = false)
    private boolean gender;

    @Column(name = "PHONE", unique = true, length = 10, nullable = false)
    private String phone;

    @Column(name = "EMAIL", unique = true, length = 50, nullable = false)
    private String email;

    @Column(name = "USERNAME", unique = true, length = 30, nullable = false)
    private String username;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "STATUS", nullable = false)
    private boolean status = true;

    @Column(name = "CREDIT_POINT", nullable = false)
    private Integer creditPoint = 50;

    @Column(name = "BALANCE", nullable = false)
    private Float balance = 0f;

    //trong bảng ko có cột này
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<LoanEntity> loans = new ArrayList<>();

}
