package com.example.demo.service;

import com.example.demo.model.RequestFilterRequest;
import com.example.demo.model.TraceDto;
import com.example.demo.model.TraceRequest;

import java.util.List;

public interface TraceService {
    List<TraceDto> filter(RequestFilterRequest traceFilterRequest);

    List<TraceDto> filterDebt(RequestFilterRequest traceFilterRequest);

    void payDebt(TraceRequest traceRequest) throws Exception;

    void updateTrace(TraceRequest traceRequest) throws Exception;
}
