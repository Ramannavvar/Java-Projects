package com.jpmorgan.techtest.MessageProcessor;

import java.math.BigDecimal;

import com.jpmorgan.techtest.MessageProcessor.Utils.BigDecimalUtils;

import junit.framework.Assert;
import junit.framework.TestCase;

public class TestBigDecimalUtils extends TestCase {

	public void testRoundValue() {
		BigDecimal expected = new BigDecimal("1.92");
		BigDecimal actual = BigDecimalUtils.roundValue(BigDecimalUtils.strToBigDecimal("1.9111122888000000000000000020000000000000011001234"));
		
		System.out.println("Expected: " + expected + "\nActual: " + actual);
    	Assert.assertEquals(expected, actual);    	
	}
	
	public void testStrToBigDecimal1() {
				
		BigDecimal expected = BigDecimalUtils.roundValue(new BigDecimal("0.10"));
    	BigDecimal actual = BigDecimalUtils.roundValue(BigDecimalUtils.strToBigDecimal("10p"));    	
    	System.out.println("Expected: " + expected + "\nActual: " + actual);
    	Assert.assertEquals(expected, actual);    	
	}

	public void testStrToBigDecimal2() {
		
		BigDecimal expected = BigDecimalUtils.roundValue(new BigDecimal("0.03"));
    	BigDecimal actual = BigDecimalUtils.roundValue(BigDecimalUtils.strToBigDecimal("3p"));
    	System.out.println("Expected: " + expected + "\nActual: " + actual);
    	Assert.assertEquals(expected, actual);
	}
	
	public void testStrToBigDecimal3() {
		
		BigDecimal expected = BigDecimalUtils.roundValue(new BigDecimal("100000"));
		BigDecimal actual = BigDecimalUtils.roundValue(BigDecimalUtils.strToBigDecimal("100,000.00"));
		System.out.println("Expected: " + expected + "\nActual: " + actual);
		Assert.assertEquals(expected, actual);
	}
	
	public void testStrToBigDecimal4() {
		
		BigDecimal expected = BigDecimalUtils.roundValue(new BigDecimal("1"));
		BigDecimal actual = BigDecimalUtils.roundValue(BigDecimalUtils.strToBigDecimal("100p"));
		System.out.println("Expected: " + expected + "\nActual: " + actual);
		Assert.assertEquals(expected, actual);
	}
}
