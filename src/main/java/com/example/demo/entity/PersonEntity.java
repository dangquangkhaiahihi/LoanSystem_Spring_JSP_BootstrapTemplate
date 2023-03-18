package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "PERSON")
public class PersonEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "PHONE")
    private boolean phone;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "TOTAL_AMOUNT")
    private String totalAmount;

    @Column(name = "CREATE_AT")
    private LocalDateTime createAt;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JsonIgnoreProperties(value = "", allowGetters = true)
    @JoinColumn(name = "USER_ID")
    private UserEntity user;
}
