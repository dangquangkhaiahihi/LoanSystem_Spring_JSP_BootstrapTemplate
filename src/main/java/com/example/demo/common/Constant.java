package com.example.demo.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Constant {
    public static final String EMAIL_PATTERN = "[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static final List<String> cantRedirectUrls = new ArrayList<>(Arrays.asList("update-profile","change-pass","add-balance"));
}
