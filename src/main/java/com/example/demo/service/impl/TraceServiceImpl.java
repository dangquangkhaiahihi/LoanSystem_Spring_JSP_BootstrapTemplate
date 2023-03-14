package com.example.demo.service.impl;

import com.example.demo.common.Constant;
import com.example.demo.common.EnvironmentObj;
import com.example.demo.common.Utils;
import com.example.demo.entity.LoanEntity;
import com.example.demo.entity.TraceUserLoanEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.model.*;
import com.example.demo.repository.LoanRepository;
import com.example.demo.repository.TraceUserLoanRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.TraceService;
import com.example.demo.service.TransactionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class TraceServiceImpl implements TraceService {
    @Autowired
    TraceUserLoanRepository traceRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private HttpSession session;

    @Autowired
    EnvironmentObj env;

    @Autowired
    TransactionService transactionService;
    @Override
    public List<TraceDto> filter(RequestFilterRequest traceFilterRequest) {
        List<TraceUserLoanEntity> traceEntities = traceRepository.findAllByLoanerUserNameOrderByNextDeadlineDesc(Utils.getCurrentUser().getName());
        return generateTraceDtos(traceEntities);
    }

    @Override
    public List<TraceDto> filterDebt(RequestFilterRequest traceFilterRequest) {
        List<TraceUserLoanEntity> traceEntities = traceRepository.findAllByDebtorUserNameOrderByNextDeadlineDesc(Utils.getCurrentUser().getName());
        return generateTraceDtos(traceEntities);
    }

    @Override
    public void payDebt(TraceRequest traceRequest) throws Exception {
        TraceUserLoanEntity trace = traceRepository.findById(traceRequest.getTraceId()).get();
        UserEntity debtor = userRepository.findByUsername(trace.getDebtorUserName());

        if (Constant.TRACE_STATUS_PAID.equals(trace.getStatus())) {
            throw new Exception("Bạn đã trả nợ kỳ này rồi.");
        }

        if (debtor.getBalance() < trace.getFinalAmountThisMonth()) {
            throw new Exception("Số dư không đủ để thanh toán nợ.");
        }

        UserEntity loaner = userRepository.findByUsername(trace.getLoanerUserName());
        debtor.setBalance(debtor.getBalance() - trace.getFinalAmountThisMonth());
        loaner.setBalance(loaner.getBalance() + trace.getFinalAmountThisMonth());

        trace.setFinalAmountThisMonth(0f);
        trace.setStatus(Constant.TRACE_STATUS_PAID);
        trace.setAmountThisMonth(0f);
        trace.setAmountInterestThisMonth(0f);
        trace.setRemain(trace.getRemain() - trace.getAmountPerMonth());

        LocalDateTime deadline = trace.getLoan().getCreatedAt();
        if (Constant.DURATION_ONE_YEAR.equals(trace.getLoan().getDuration())) {
            deadline = deadline.plus(12, ChronoUnit.MONTHS);
        } else if (Constant.DURATION_ONE_MONTH.equals(trace.getLoan().getDuration())) {
            deadline = deadline.plus(1, ChronoUnit.MONTHS);
        } else if (Constant.DURATION_TWO_MONTHS.equals(trace.getLoan().getDuration())) {
            deadline = deadline.plus(2, ChronoUnit.MONTHS);
        } else if (Constant.DURATION_THREE_MONTHS.equals(trace.getLoan().getDuration())) {
            deadline = deadline.plus(3, ChronoUnit.MONTHS);
        }
        Period period = Period.between(deadline.toLocalDate(), LocalDateTime.now().toLocalDate());
        int monthsLeft = Math.abs((int) period.toTotalMonths());
        System.out.println(monthsLeft);
        if (monthsLeft == 0) {
            trace.setStatus(Constant.TRACE_STATUS_COMPLETE);
            trace.setAmountInterestNextMonth(0f);
            trace.setAmountNextMonth(0f);
        }

        if (Constant.LOAN_TYPE_BASED_ON_CURRENT_DEBT.equals(trace.getLoan().getType())) {
            trace.setAmountInterestNextMonth((float) Math.ceil(trace.getRemain() / monthsLeft));
            trace.setAmountNextMonth(trace.getAmountPerMonth());
        }else {

        }

        if (trace.getRemain() < 0f) {
            trace.setRemain(0f);
        }
        userRepository.save(debtor);
        userRepository.save(loaner);
        transactionService.addTransaction(loaner,trace.getFinalAmountThisMonth(),true,"Được trả nợ");
        transactionService.addTransaction(debtor,trace.getFinalAmountThisMonth(),false,"Trả nợ");
        traceRepository.save(trace);

        UserDto currentUserDto = new UserDto();
        BeanUtils.copyProperties(debtor, currentUserDto);
        session.setAttribute("user-info", currentUserDto);
    }

    @Override
    public void updateTrace(TraceRequest traceRequest) throws Exception {
        TraceUserLoanEntity trace = traceRepository.findById(traceRequest.getTraceId()).get();

        if (Constant.TRACE_STATUS_PAID.equals(trace.getStatus())) {
            trace.setAmountThisMonth(trace.getAmountNextMonth());

            trace.setAmountInterestThisMonth(trace.getAmountInterestNextMonth());
            trace.setFinalAmountThisMonth(
                    trace.getAmountThisMonth() + trace.getAmountInterestThisMonth());
            trace.setStatus(Constant.TRACE_STATUS_NOT_YET);
            trace.setPrevDeadline(trace.getNextDeadline());
            trace.setNextDeadline(trace.getNextDeadline().plus(1, ChronoUnit.MONTHS));
            traceRepository.save(trace);
        } else if (Constant.TRACE_STATUS_COMPLETE.equals(trace.getStatus())) {
            traceRepository.delete(trace);
        } else if (Constant.TRACE_STATUS_OVERDUE.equals(trace.getStatus())) {
            //Code sau
        }
    }

    private List<TraceDto> generateTraceDtos(List<TraceUserLoanEntity> traceEntities) {
        List<TraceDto> traceDtos = new ArrayList<>();
        for (TraceUserLoanEntity traceUserLoanEntity : traceEntities) {
            TraceDto traceDto = new TraceDto();
            BeanUtils.copyProperties(traceUserLoanEntity, traceDto);
            traceDto.setRemain(traceUserLoanEntity.getRemain());
            traceDto.setAmountThisMonth(traceUserLoanEntity.getAmountThisMonth());
            traceDto.setAmountInterestThisMonth(traceUserLoanEntity.getAmountInterestThisMonth());
            traceDto.setFinalAmountThisMonth(traceUserLoanEntity.getFinalAmountThisMonth());
            traceDto.setInterest(env.getInterestRate(traceUserLoanEntity.getLoan().getDuration()));
            traceDto.setCreatedAt(traceUserLoanEntity.getCreatedAt());
            traceDto.setPrevDeadline(traceUserLoanEntity.getPrevDeadline());
            traceDto.setNextDeadline(traceUserLoanEntity.getNextDeadline());
            traceDto.setStatus(traceUserLoanEntity.getStatus());

            LoanDto loanDto = new LoanDto();
            BeanUtils.copyProperties(traceUserLoanEntity.getLoan(), loanDto);
            loanDto.setDuration(traceUserLoanEntity.getLoan().getDuration());
            loanDto.setAmount(traceUserLoanEntity.getLoan().getAmount());
            loanDto.setCreatedAt(traceUserLoanEntity.getLoan().getCreatedAt());
            loanDto.setUser(null);
            traceDto.setLoan(loanDto);
            traceDtos.add(traceDto);
        }
        return traceDtos;
    }
}
