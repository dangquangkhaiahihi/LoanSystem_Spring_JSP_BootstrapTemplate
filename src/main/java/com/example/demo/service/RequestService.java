package com.example.demo.service;

import com.example.demo.model.RequestDto;
import com.example.demo.model.RequestFilterRequest;

import java.util.List;

public interface RequestService {
    List<RequestDto> filter(RequestFilterRequest requestFilterRequest);
}
