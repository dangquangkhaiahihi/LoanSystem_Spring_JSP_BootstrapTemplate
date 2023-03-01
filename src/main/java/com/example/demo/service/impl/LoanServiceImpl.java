package com.example.demo.service.impl;

import com.example.demo.common.StringUtils;
import com.example.demo.common.Utils;
import com.example.demo.entity.LoanEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.model.LoanDto;
import com.example.demo.model.LoanRequest;
import com.example.demo.repository.LoanRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.LoanService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {
    @Autowired
    LoanRepository loanRepository;

    @Autowired
    UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private List<LoanEntity> performFilter(LoanRequest loanRequest) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<LoanEntity> query = cb.createQuery(LoanEntity.class);
        Root<LoanEntity> root = query.from(LoanEntity.class);

        List<Predicate> predicates = new ArrayList<>();

        UserEntity userEntity = userRepository.findByUsername(Utils.getCurrentUser().getName());
        predicates.add(cb.equal(root.get("id"), userEntity.getId()));

        if (loanRequest.getFromAmount() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("amount"), loanRequest.getFromAmount()));
        }
        if (loanRequest.getToAmount() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("amount"), loanRequest.getToAmount()));
        }
        if (loanRequest.getFromCreatedDate() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("createdAt"), loanRequest.getFromCreatedDate()));
        }
        if (loanRequest.getToCreatedDate() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("createdAt"), loanRequest.getToCreatedDate()));
        }
        if (loanRequest.getFromDeadline() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("deadline"), loanRequest.getFromDeadline()));
        }
        if (loanRequest.getToDeadline() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("deadline"), loanRequest.getToDeadline()));
        }
        if (loanRequest.getStatus() != null) {
            predicates.add(cb.equal(root.get("status"), loanRequest.getStatus()));
        }
        if (!StringUtils.isEmpty(loanRequest.getType())) {
            predicates.add(cb.equal(root.get("type"), loanRequest.getType()));
        }

        query.select(root).where(predicates.toArray(new Predicate[]{}));
        TypedQuery<LoanEntity> typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList();
    }


    @Override
    public List<LoanDto> filter(LoanRequest loanRequest) throws Exception {
        List<LoanEntity> loanEntities = performFilter(loanRequest);
        List<LoanDto> loanDtos = new ArrayList<>();
        for (LoanEntity loanEntity : loanEntities) {
            LoanDto loanDto = new LoanDto();
            BeanUtils.copyProperties(loanEntity, loanDto);
            loanDto.setAmount(loanEntity.getAmount());
            loanDto.setCreatedAt(loanEntity.getCreatedAt());
            loanDto.setDeadline(loanEntity.getDeadline());
            loanDtos.add(loanDto);
        }
        return loanDtos;
    }

    @Override
    public void changeStatus(Long loanId, boolean b) {
        LoanEntity loanEntity = loanRepository.getById(loanId);
        loanEntity.setStatus(b);
        loanRepository.save(loanEntity);
    }
}
