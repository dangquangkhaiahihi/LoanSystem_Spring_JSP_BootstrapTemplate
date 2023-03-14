package com.example.demo.service.impl;

import com.example.demo.common.Constant;
import com.example.demo.common.EnvironmentObj;
import com.example.demo.common.StringUtils;
import com.example.demo.common.Utils;
import com.example.demo.entity.LoanEntity;
import com.example.demo.entity.RequestEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.model.LoanRequestAdd;
import com.example.demo.model.LoanDto;

import com.example.demo.model.LoanRequestFilter;
import com.example.demo.repository.LoanRepository;
import com.example.demo.repository.RequestRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.LoanService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {
    @Autowired
    LoanRepository loanRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RequestRepository requestRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    EnvironmentObj env;

    private void addCriteria(LoanRequestFilter loanRequestFilter, List<Predicate> predicates, CriteriaBuilder cb, Root<LoanEntity> root) {
        if (loanRequestFilter.getFromAmount() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("amount"), loanRequestFilter.getFromAmount()));
        }
        if (loanRequestFilter.getToAmount() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("amount"), loanRequestFilter.getToAmount()));
        }
        if (loanRequestFilter.getFromCreatedDate() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("createdAt"), loanRequestFilter.getFromCreatedDate()));
        }
        if (loanRequestFilter.getToCreatedDate() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("createdAt"), loanRequestFilter.getToCreatedDate()));
        }
        if (!StringUtils.isEmpty(loanRequestFilter.getType())) {
            predicates.add(cb.equal(root.get("type"), loanRequestFilter.getType()));
        }
        if (!StringUtils.isEmpty(loanRequestFilter.getDuration())) {
            predicates.add(cb.equal(root.get("duration"), loanRequestFilter.getDuration()));
        }
    }


    @Override
    public List<LoanDto> filter(LoanRequestFilter loanRequestFilter) throws Exception {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<LoanEntity> query = cb.createQuery(LoanEntity.class);
        Root<LoanEntity> root = query.from(LoanEntity.class);

        List<Predicate> predicates = new ArrayList<>();
        UserEntity userEntity = userRepository.findByUsername(Utils.getCurrentUser().getName());
        predicates.add(cb.equal(root.get("user"), userEntity.getId()));
        addCriteria(loanRequestFilter, predicates, cb, root);
        if (loanRequestFilter.getStatus() != null) {
            predicates.add(cb.equal(root.get("status"), loanRequestFilter.getStatus()));
        }

        query.select(root).where(predicates.toArray(new Predicate[]{}));
        Order orderByCreateAt = cb.desc(root.get("createdAt"));
        query.orderBy(orderByCreateAt);
        TypedQuery<LoanEntity> typedQuery = entityManager.createQuery(query);
        List<LoanEntity> loanEntities = typedQuery.getResultList();

        List<LoanDto> loanDtos = new ArrayList<>();
        for (LoanEntity loanEntity : loanEntities) {
            LoanDto loanDto = new LoanDto();
            BeanUtils.copyProperties(loanEntity, loanDto);
            loanDto.setAmount(loanEntity.getAmount());
            loanDto.setCreatedAt(loanEntity.getCreatedAt());
            loanDtos.add(loanDto);
        }
        return loanDtos;
    }

    @Override
    public void addLoan(LoanRequestAdd loanRequestAdd) {
        LoanEntity loanEntity = new LoanEntity();
        loanEntity.setUser(userRepository.findByUsername(Utils.getCurrentUser().getName()));
        loanEntity.setAmount(loanRequestAdd.getAmount());
        loanEntity.setType(loanRequestAdd.getType());
        loanEntity.setDuration(loanRequestAdd.getDuration());
        loanRepository.save(loanEntity);
    }

    @Override
    public void changeStatus(Long loanId, boolean b) {
        LoanEntity loanEntity = loanRepository.getById(loanId);
        loanEntity.setStatus(b);
        loanRepository.save(loanEntity);
    }

    @Override
    public List<LoanDto> filterForBorrow(LoanRequestFilter loanRequestFilter) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<LoanEntity> query = cb.createQuery(LoanEntity.class);
        Root<LoanEntity> root = query.from(LoanEntity.class);

        List<Predicate> predicates = new ArrayList<>();
        UserEntity userEntity = userRepository.findByUsername(Utils.getCurrentUser().getName());
        //get loan that this logged in user not own
        predicates.add(cb.notEqual(root.get("user"), userEntity.getId()));

        addCriteria(loanRequestFilter, predicates, cb, root);
        predicates.add(cb.equal(root.get("status"), true));
        query.select(root).where(predicates.toArray(new Predicate[]{}));

        Order orderByCreateAt = cb.desc(root.get("createdAt"));
        query.orderBy(orderByCreateAt);
        TypedQuery<LoanEntity> typedQuery = entityManager.createQuery(query);
        List<LoanEntity> loanEntities = typedQuery.getResultList();

        List<LoanDto> loanDtos = new ArrayList<>();
        for (LoanEntity loanEntity : loanEntities) {
            LoanDto loanDto = new LoanDto();
            BeanUtils.copyProperties(loanEntity, loanDto);
            loanDto.setAmount(loanEntity.getAmount());
            loanDto.setCreatedAt(loanEntity.getCreatedAt());
            loanDto.setDuration(loanEntity.getDuration());
            Float configInterest = env.getInterestRate(loanEntity.getDuration());
            loanDto.setInterest(configInterest);
            loanDto.setInterestStr(configInterest.toString() + "%");

            RequestEntity requestEntity = requestRepository.findByLoanAndDebtor(loanEntity, userEntity);
            if (requestEntity != null) {
                loanDto.setCanLoan(false);
            }
            loanDtos.add(loanDto);
        }
        return loanDtos;
    }


}
