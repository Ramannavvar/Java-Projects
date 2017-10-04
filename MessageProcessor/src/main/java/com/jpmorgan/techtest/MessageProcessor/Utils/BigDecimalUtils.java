package com.jpmorgan.techtest.MessageProcessor.Utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParsePosition;

public class BigDecimalUtils {
	
	public static BigDecimal roundValue(BigDecimal no) {
		return no.setScale(2, BigDecimal.ROUND_UP);
	}
	
	public static BigDecimal strToBigDecimal(String str) {
		DecimalFormat formatter = new DecimalFormat();
        formatter.setParseBigDecimal(true);
        BigDecimal value;
        
		StringBuilder builder = new StringBuilder();
		if(str.endsWith("p")) {
			builder.append(str.substring(0, str.length()-1));
			value = (BigDecimal) formatter.parse(builder.toString(), new ParsePosition(0));
	        value = value.divide(new BigDecimal("100"));
	        
		} else {
			value = (BigDecimal) formatter.parse(str, new ParsePosition(0));			
		}
		return value;
	}
}
