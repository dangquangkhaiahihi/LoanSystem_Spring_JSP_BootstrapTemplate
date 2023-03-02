package com.example.demo.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Constant {
    public static final String TRACE_STATUS_OVERDUE = "OVERDUE";
    public static final String TRACE_STATUS_PAID = "PAID";
    public static final String TRACE_STATUS_NOT_YET = "NOT YET";

    public static final String LOAN_TYPE_BASED_ON_CURRENT_DEBT = "BASED_ON_CURRENT_DEBT";
    public static final String LOAN_TYPE_BASED_ON_INITIAL_DEBT = "BASED_ON_INITIAL_DEBT";

    public static final String DURATION_ONE_MONTH = "ONE_MONTH";
    public static final String DURATION_TWO_MONTHS = "TWO_MONTHS";
    public static final String DURATION_THREE_MONTHS = "THREE_MONTHS";
    public static final String DURATION_ONE_YEAR = "ONE_YEAR";

    public static final List<String> cantRedirectUrls = new ArrayList<>(Arrays.asList("update-profile","change-pass","add-balance"));
}
