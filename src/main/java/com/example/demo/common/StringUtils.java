package com.example.demo.common;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class StringUtils {
	public static boolean isEmpty(String input) {
		if (input == null || "".equals(input)) {
			return true;
		}
		return false;
	}

	public static boolean containsOnlyNumbers(String str) {
		return str.matches("[0-9]+");
	}

	public static String convertFloatToString(Float floatNum) {
		DecimalFormat decimalFormat = new DecimalFormat("#.###");
		String stringValue = decimalFormat.format(floatNum);
		return stringValue;
	}

}
