package com.example.demo.service.impl;

import com.example.demo.common.StringUtils;
import com.example.demo.common.Utils;
import com.example.demo.entity.LoanEntity;
import com.example.demo.entity.RequestEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.model.LoanDto;
import com.example.demo.model.RequestDto;
import com.example.demo.model.RequestFilterRequest;
import com.example.demo.model.UserDto;
import com.example.demo.repository.RequestRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.RequestService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {
    @Autowired
    RequestRepository requestRepository;
    @Autowired
    UserRepository userRepository;
    @PersistenceContext
    private EntityManager entityManager;

    private void addCriteria(RequestFilterRequest requestFilterRequest, List<Predicate> predicates, CriteriaBuilder cb, Root<RequestEntity> root) {
        Join<RequestEntity, LoanEntity> loanJoin = root.join("loan");
        if (requestFilterRequest.getFromAmount() != null) {
            predicates.add(cb.greaterThanOrEqualTo(loanJoin.get("amount"), requestFilterRequest.getFromAmount()));
        }
        if (requestFilterRequest.getToAmount() != null) {
            predicates.add(cb.lessThanOrEqualTo(loanJoin.get("amount"), requestFilterRequest.getToAmount()));
        }
        if (requestFilterRequest.getFromCreatedDate() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("createdAt"), requestFilterRequest.getFromCreatedDate()));
        }
        if (requestFilterRequest.getToCreatedDate() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("createdAt"), requestFilterRequest.getToCreatedDate()));
        }
        if (!StringUtils.isEmpty(requestFilterRequest.getType())) {
            predicates.add(cb.equal(loanJoin.get("type"), requestFilterRequest.getType()));
        }
        if(!StringUtils.isEmpty(requestFilterRequest.getDuration())){
            predicates.add(cb.equal(loanJoin.get("duration"), requestFilterRequest.getDuration()));
        }
    }

    @Override
    public List<RequestDto> filter(RequestFilterRequest requestFilterRequest) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<RequestEntity> query = cb.createQuery(RequestEntity.class);
        Root<RequestEntity> root = query.from(RequestEntity.class);

        List<Predicate> predicates = new ArrayList<>();
        UserEntity currentUser = userRepository.findByUsername(Utils.getCurrentUser().getName());
        //get loan that this logged in user not own
        predicates.add(cb.equal(root.get("loaner"), currentUser.getId()));

        addCriteria(requestFilterRequest,predicates,cb,root);
        query.select(root).where(predicates.toArray(new Predicate[]{}));

        Order orderByCreateAt = cb.desc(root.get("createdAt"));
        query.orderBy(orderByCreateAt);
        TypedQuery<RequestEntity> typedQuery = entityManager.createQuery(query);
        List<RequestEntity> requestEntities = typedQuery.getResultList();

        List<RequestDto> requestDtos = new ArrayList<>();
        for (RequestEntity request : requestEntities) {
            RequestDto requestDto = new RequestDto();
            requestDto.setId(request.getId());

            LoanDto loanDto = new LoanDto();
            BeanUtils.copyProperties(request.getLoan(), loanDto);
            loanDto.setAmount(request.getLoan().getAmount());
            loanDto.setCreatedAt(request.getLoan().getCreatedAt());
            loanDto.setUser(null);
            requestDto.setLoanDto(loanDto);

            UserDto debtor = new UserDto();
            BeanUtils.copyProperties(request.getDebtor(), debtor);
            requestDto.setDebtorDto(debtor);

            requestDto.setCreatedAt(request.getCreatedAt());
            requestDtos.add(requestDto);
        }
        return requestDtos;
    }
}
