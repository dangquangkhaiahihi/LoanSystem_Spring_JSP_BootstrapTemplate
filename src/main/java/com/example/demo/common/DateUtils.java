package com.example.demo.common;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class DateUtils {
//	public static void main(String[] args) {  
//	    String str="2021-11-03";  
//	    Date date = convertStringToSQLDate(str);
//	    System.out.println(date);  
//	} 

	public Date convertStringToSQLDate(String dateString) {
		Date date=Date.valueOf(dateString);//converting string into sql date
		return date;
	}
	
	public String showDate(Timestamp date) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		return formatter.format(date);	
	}
	
	public Boolean durationValidate(String duration) {
		Boolean validator = false;
		if(Pattern.matches("\\d+ year", duration.toLowerCase())) {
			validator = true;
		}
		return validator;
	}
}

