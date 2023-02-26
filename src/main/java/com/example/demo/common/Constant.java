package com.example.demo.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Constant {
    public static final String TRACE_STATUS_OVERDUE = "OVERDUE";
    public static final String TRACE_STATUS_PAID = "PAID";
    public static final String TRACE_STATUS_NOT_YET = "NOT YET";

    public static final String LOAN_TYPE_INSTALLMENT = "INSTALLMENT";
    public static final String LOAN_TYPE_ONE_TIME = "ONE TIME";

    public static final List<String> cantRedirectUrls = new ArrayList<>(Arrays.asList("update-profile","change-pass"));
}
