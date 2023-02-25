package com.example.demo.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class StringUtils {

	public boolean convertStringToBoolean(String gender) {
		return Boolean.parseBoolean(gender);
	}
	
	public Boolean emailValidate(String email) {
		Boolean validator = false;
		String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}";
		if(email != null && Pattern.matches(regex, email)) {
			validator = true;
		}
		return validator;
	}
	
	private boolean isPositiveInteger(String value) throws NumberFormatException {
		if (value == null || "".equals(value.trim())) {
			return false;
		}
		
		// Regex to check string contains only digits
        String regex = "[0-9]+";
  
        // Compile the ReGex
        Pattern p = Pattern.compile(regex);
  
        // Find match between given string and regular expression using Pattern.matcher()
        Matcher m = p.matcher(value);
  
        // Return if the string matched the ReGex
        if (m.matches() == false) return false;
        
        if(Integer.parseInt(value)<0 || Integer.parseInt(value)==0) return false;
        
        return true;
	}
	
	
	public boolean checkNumberInputWithLenght (String input, int length) {
		return (isPositiveInteger(input) && input.length() == length);
	}
}
