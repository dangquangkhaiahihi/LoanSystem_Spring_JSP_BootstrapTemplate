package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;

import javax.persistence.*;
import java.time.Instant;

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
    private String phone;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "TOTAL_AMOUNT")
    private Long totalAmount;

    @ManyToOne
    @JsonIgnoreProperties(value = "", allowGetters = true)
    @JoinColumn(name = "USER_ID")
    private UserEntity user;

    @CreatedDate
    @Column(name = "CREATED_DATE", updatable = false)
    private Instant createdDate = Instant.now();

    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_DATE")
    private Instant lastModifiedDate = Instant.now();

    @Transient
    private String createdDateStr;

    @Transient
    private String lastModifiedDateStr;

}
