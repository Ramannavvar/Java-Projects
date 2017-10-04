package com.jpmorgan.techtest.MessageProcessor;

import java.math.BigDecimal;

import com.jpmorgan.techtest.MessageProcessor.Utils.BigDecimalUtils;
import com.jpmorgan.techtest.MessageProcessor.beans.Product;
import com.jpmorgan.techtest.MessageProcessor.beans.Sale;

import junit.framework.Assert;
import junit.framework.TestCase;


public class TestSale extends TestCase {
	
    public void testPerformOperationForAdd() {
    	Product product = new Product("Apples", new BigDecimal("0.2"));    	
    	Sale test = new Sale(product, new BigDecimal("0.5"), Sale.Operation.add);
    	test.performOperation(Sale.Operation.add, new BigDecimal("0.5"));
    	
    	BigDecimal expected = BigDecimalUtils.roundValue(new BigDecimal("0.70"));
    	BigDecimal actual = BigDecimalUtils.roundValue(test.getTransactionValue());
    	
    	Assert.assertEquals(expected, actual);
    }
	
    public void testPerformOperationForSustract() {
    	Product product = new Product("Mangoes", new BigDecimal("0.7"));    	
    	Sale test = new Sale(product, new BigDecimal("0.1"), Sale.Operation.subtract);
    	test.performOperation(Sale.Operation.subtract, new BigDecimal("0.1"));
    	
    	BigDecimal expected =  BigDecimalUtils.roundValue(new BigDecimal("0.6"));
    	BigDecimal actual = BigDecimalUtils.roundValue(test.getTransactionValue());
    	
    	Assert.assertEquals(expected,actual);
    }

    public void testPerformOperationForMultiply() {
    	Product product = new Product("Oranges", new BigDecimal("0.2"));    	
    	Sale test = new Sale(product, new BigDecimal("0.5"), Sale.Operation.multiply);
    	test.performOperation(Sale.Operation.multiply, new BigDecimal("0.5"));
    	
    	BigDecimal expected =  BigDecimalUtils.roundValue(new BigDecimal("0.1"));
    	BigDecimal actual = BigDecimalUtils.roundValue(test.getTransactionValue());
    	
    	Assert.assertEquals(expected,actual);
    }
    
    public void testToString() {
    	Product product = new Product("Oranges", new BigDecimal("0.2"));    	
    	Sale test = new Sale(product, new BigDecimal("0.5"), Sale.Operation.multiply);
    	test.performOperation(Sale.Operation.multiply, new BigDecimal("0.5"));
    	
    	Assert.assertTrue(test.toString().contains("Product type:"));
    }
}
