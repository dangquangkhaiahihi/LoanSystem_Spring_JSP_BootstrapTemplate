package com.example.demo.repository;

import com.example.demo.entity.TraceUserLoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface TraceUserLoanRepository extends JpaRepository<TraceUserLoanEntity,Long> {

}
