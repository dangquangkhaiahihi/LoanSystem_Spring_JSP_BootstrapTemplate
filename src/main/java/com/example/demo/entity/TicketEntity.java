package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "TICKET")
public class TicketEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "AMOUNT")
    private Long amount;

    @Column(name = "IS_PLUS")
    private Boolean isPlus;

    @Column(name = "NOTE")
    private String note;

    @Column(name = "DATE_OF_TRANS")
    private Instant dateOfTrans;

    @Column(name = "IMAGE_URL")
    private String imgUrl;

    @ManyToOne
    @JsonIgnoreProperties(value = "", allowGetters = true)
    @JoinColumn(name = "PERSON_ID")
    private PersonEntity person;

    @Column(name = "CREATED_BY", updatable = false)
    private String createdBy;

    @CreatedDate
    @Column(name = "CREATED_DATE", updatable = false)
    private Instant createdDate = Instant.now();

    @LastModifiedBy
    @Column(name = "LAST_MODIFIED_BY")
    private String lastModifiedBy;

    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_DATE")
    private Instant lastModifiedDate = Instant.now();
}
