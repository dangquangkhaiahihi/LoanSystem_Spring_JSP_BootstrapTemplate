package com.example.demo.repository;

import com.example.demo.entity.LoanEntity;
import com.example.demo.entity.RequestEntity;
import com.example.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends JpaRepository<RequestEntity,Long> {
    List<RequestEntity> findByLoaner(UserEntity loaner);
    RequestEntity findByLoanAndDebtor(LoanEntity loan, UserEntity debtor);
}
