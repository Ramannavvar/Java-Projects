package com.jpmorgan.techtest.MessageProcessor;

import java.math.BigDecimal;

import com.jpmorgan.techtest.MessageProcessor.Utils.BigDecimalUtils;
import com.jpmorgan.techtest.MessageProcessor.beans.Sale;
import com.jpmorgan.techtest.MessageProcessor.core.MessageInterpreter;

import junit.framework.TestCase;

public class TestMessageInterpreter extends TestCase {
	
	MessageInterpreter interpreter = new MessageInterpreter();
	
	// Message type 1
	public void testTnterpretMessage1() {
		
		try {
			Sale sale = interpreter.interpretMessage("T1|Apples|30p");
			
			assertTrue(sale.getProduct().getProductType().equals("Apples"));
			
			BigDecimal expected = BigDecimalUtils.roundValue(new BigDecimal("0.30"));
			BigDecimal actual = BigDecimalUtils.roundValue(sale.getProduct().getValue());
						
	    	assertEquals(expected, actual);
			
		} catch (Exception e) {	
			e.printStackTrace();
			fail();			
		}
	}
	
	// Message type 2
	public void testTnterpretMessage2() {
		
		try {
			Sale sale = interpreter.interpretMessage("T2|Apples|30p|5");
			
			assertTrue(sale.getProduct().getProductType().equals("Apples"));
			assertTrue( BigDecimalUtils.roundValue(sale.getProduct().getValue()).equals(BigDecimalUtils.roundValue(new BigDecimal("0.30"))));
			assertTrue(sale.getOccurances() == 5);
						
		} catch (Exception e) {
			e.printStackTrace();
			fail();			
		}
	}

	// Message type 3
	public void testTnterpretMessage3() {
	
		try {
			Sale sale = interpreter.interpretMessage("T3|Apples|30p|10p|add");
		
			assertTrue(sale.getProduct().getProductType().equals("Apples"));
			assertTrue( BigDecimalUtils.roundValue(sale.getProduct().getValue()).equals(BigDecimalUtils.roundValue(new BigDecimal("0.30"))));
			assertTrue( BigDecimalUtils.roundValue(sale.getAdjustment()).equals(BigDecimalUtils.roundValue(new BigDecimal("0.10"))));
			assertTrue(sale.getOperation().equals(Sale.Operation.add));
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();		
		}
	}
}
