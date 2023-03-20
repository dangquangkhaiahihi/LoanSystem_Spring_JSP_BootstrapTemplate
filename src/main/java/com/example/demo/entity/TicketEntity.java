package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.Instant;
import org.springframework.data.annotation.Transient;

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

    @CreatedDate
    @Column(name = "DATE_OF_TRANS")
    private Instant dateOfTrans;

    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_DATE")
    private Instant lastModifiedDate;

    @Transient
    private String dateOfTransStr;

    @Transient
    private String lastModifiedDateStr;

    @Column(name = "IMAGE_URL")
    private String imgUrl;

    @ManyToOne
    @JsonIgnoreProperties(value = "", allowGetters = true)
    @JoinColumn(name = "PERSON_ID")
    private PersonEntity person;
}
