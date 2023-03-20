package com.example.demo.security;

import com.example.demo.entity.PersonEntity;
import com.google.gson.Gson;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {
    public static void main(String[] args) {
//        System.out.println(addNumericString("-5","10"));
//        System.out.println(addNumericString("-10","5"));
//        System.out.println(addNumericString("8","-9"));
//        System.out.println( addNumericString("69","-30"));
//        System.out.println(addNumericString("12","19"));
//        System.out.println(addNumericString("61","-1"));
//        System.out.println(addNumericString("-10","110"));

//        System.out.println(validateEmail("asdasdas"));
//        System.out.println(validateEmail("2142r32434321dsfv"));
//        System.out.println(validateEmail("danguqngakhair@gmail.com"));

//        String a = "2023-03-10T20:00:00Z";
//        String b = "2023-04-22T20:00";
//        String c = "2023-04-02T20:01";
//        String d = "2023-03-06T20:01";
//
//        try {
//            System.out.println(Instant.parse(a));
//        }catch (Exception ex){
//            System.out.println(ex);
//        }

        PersonEntity person0 = new PersonEntity();
        person0.setName("ahihi");
        person0.setAddress("ahihi");
        person0.setPhone("0123456789");
        person0.setEmail("0123456789@gmail.com");
        person0.setTotalAmount(1000000L);
        person0.setCreatedDate(Instant.now().minus(7, ChronoUnit.DAYS));
        person0.setLastModifiedDate(Instant.now());

        Gson gson = new Gson();
        String a = gson.toJson(person0);
    }

    public static boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile("[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static String addNumericString(String num1, String num2){
        // Check for negative signs
        String num1NoSign = "";
        String num2NoSign = "";
        boolean isNum1Negative = false;
        boolean isNum2Negative = false;
        if (num1.charAt(0) == '-') {
            num1NoSign = num1.substring(1);
            isNum1Negative = true;
        }
        if (num2.charAt(0) == '-') {
            num2NoSign = num2.substring(1);
            isNum2Negative = true;
        }

        if(!isNum1Negative && !isNum2Negative){
            return addPosStrings(num1,num2);
        }else if(isNum1Negative && isNum2Negative){
            return "-"+addPosStrings(num1NoSign,num2NoSign);
        }else if(!isNum1Negative && isNum2Negative){
            if(comparePosStrings(num1,num2NoSign) == -1){
                return "-" + subtractPosStrings(num2NoSign,num1);
            }else{
                return subtractPosStrings(num1,num2NoSign);
            }
        }else{
//            isNum1Negative && !isNum2Negative
//            -5 7
//            -5 3
            if(comparePosStrings(num1NoSign,num2) == -1){
                return subtractPosStrings(num2,num1NoSign);
            }else{
                return "-" + subtractPosStrings(num1NoSign,num2);
            }
        }
    }

    public static String addPosStrings (String s1, String s2) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        int i = s1.length() - 1;
        int j = s2.length() - 1;

        while (i >= 0 || j >= 0) {
            int sum = carry;
            if (i >= 0) {
                sum += s1.charAt(i) - '0';
                i--;
            }
            if (j >= 0) {
                sum += s2.charAt(j) - '0';
                j--;
            }
            sb.append(sum % 10);
            carry = sum / 10;
        }

        if (carry != 0) {
            sb.append(carry);
        }

        return sb.reverse().toString();
    }
    public static String subtractPosStrings(String num1, String num2) {
        // Check for negative signs
        boolean isNegative = false;
        if (num1.charAt(0) == '-') {
            num1 = num1.substring(1);
            isNegative = !isNegative;
        }
        if (num2.charAt(0) == '-') {
            num2 = num2.substring(1);
            isNegative = !isNegative;
        }

        // Pad the shorter number with leading zeros
        int length = Math.max(num1.length(), num2.length());
        num1 = String.format("%" + length + "s", num1).replace(' ', '0');
        num2 = String.format("%" + length + "s", num2).replace(' ', '0');

        // Subtract digit by digit
        StringBuilder result = new StringBuilder();
        int borrow = 0;
        for (int i = length - 1; i >= 0; i--) {
            int digit1 = num1.charAt(i) - '0';
            int digit2 = num2.charAt(i) - '0';
            int diff = digit1 - digit2 - borrow;
            if (diff < 0) {
                diff += 10;
                borrow = 1;
            } else {
                borrow = 0;
            }
            result.append(diff);
        }

        // Remove leading zeros
        while (result.length() > 1 && result.charAt(result.length() - 1) == '0') {
            result.deleteCharAt(result.length() - 1);
        }

        // Add negative sign if necessary
        if (isNegative) {
            result.append('-');
        }

        // Reverse the string and return it
        return result.reverse().toString();
    }

    public static int comparePosStrings(String a, String b) {
        int len1 = a.length();
        int len2 = b.length();

        // If the strings have different lengths, the one with the
        // longer length represents the larger numeric value
        if (len1 != len2) {
            return Integer.compare(len1, len2);
        }

        int maxLength = Math.max(a.length(), b.length());
        for (int i = 0; i < maxLength; i++) {
            char charA = i < a.length() ? a.charAt(i) : '0';
            char charB = i < b.length() ? b.charAt(i) : '0';
            if (charA > charB) {
                return 1;
            } else if (charA < charB) {
                return -1;
            }
        }
        return 0;
    }



}
