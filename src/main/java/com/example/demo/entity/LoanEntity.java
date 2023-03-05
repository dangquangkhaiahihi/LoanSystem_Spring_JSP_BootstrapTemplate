package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "LOAN")
public class LoanEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "AMOUNT")
    private Float amount;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "DURATION")
    private String duration;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "STATUS")
    private Boolean status = true;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private UserEntity user;

//    //trong bảng ko có cột này
//    @OneToMany(mappedBy = "loan", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<TraceUserLoanEntity> traces = new ArrayList<>();
}
