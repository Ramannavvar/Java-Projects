package com.jpmorgan.techtest.MessageProcessor.beans;

import java.math.BigDecimal;

public class Product {
	
	private String productType;
	private BigDecimal value;
	
	void setValue(BigDecimal value) {
		this.value = value;
	}

	public Product(String productType, BigDecimal value) {		
		this.productType = productType;
		this.value = value;
	}
	
	public String getProductType() {
		return productType;
	}
	
	public BigDecimal getValue() {
		return value;
	}
	
}
