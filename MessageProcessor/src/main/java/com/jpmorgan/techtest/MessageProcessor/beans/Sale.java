package com.jpmorgan.techtest.MessageProcessor.beans;

import java.math.BigDecimal;

import com.jpmorgan.techtest.MessageProcessor.Utils.BigDecimalUtils;

public class Sale {

	private Product product;
	private int occurances;
	private BigDecimal adjustment;
	private Operation operation;
	private boolean isAdjustmentApplied;
	private BigDecimal transactionValue;

	public static enum Operation {
		add, subtract, multiply;
	}
	
	public Sale(Product product) {
		this.product = product;
		isAdjustmentApplied = false;
		this.occurances = 1;
		transactionValue = product.getValue();
	}

	public Sale(Product product, int occurances) {
		this.product = product;
		this.occurances = occurances;
		transactionValue =  product.getValue().multiply(new BigDecimal(occurances));
		isAdjustmentApplied = false;		
	}

	public Sale(Product product, BigDecimal adjustment, Operation operation) {
		this.product = product;
		this.adjustment = adjustment;
		this.operation = operation;
		this.occurances = 1;
		
		if(adjustment.compareTo(BigDecimal.ZERO) >= 1) {
			isAdjustmentApplied = true;
		} else {
			isAdjustmentApplied = false;
		}		
	}

	public Product getProduct() {
		return product;
	}

	public int getOccurances() {
		return occurances;
	}
	
	public BigDecimal getAdjustment() {
		return adjustment;
	}
	
	public Operation getOperation() {
		return operation;
	}

	public BigDecimal getTransactionValue() {
		return transactionValue;
	}

	public boolean isAdjustmentApplied() {
		return isAdjustmentApplied;
	}
	
	public void performOperation(Operation opt, BigDecimal adj) {
		switch(opt) {
		case add:
			product.setValue(product.getValue().add(adj));
			transactionValue = product.getValue().multiply(new BigDecimal(occurances));			
			break;
			
		case subtract:
			product.setValue(product.getValue().subtract(adj));
			transactionValue = product.getValue().multiply(new BigDecimal(occurances));			
			break;
			
		case multiply:
			product.setValue(product.getValue().multiply(adj));
			transactionValue = product.getValue().multiply(new BigDecimal(occurances));			
			break;			
		}
	}	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Product type: ");
		builder.append(product.getProductType() + "\n");
		builder.append("Product value: ");
		builder.append(BigDecimalUtils.roundValue(product.getValue()) + "\n");
		builder.append("Adjustment: ");
		builder.append(adjustment + "\n");
		builder.append("Operation: ");
		builder.append(operation + "\n");
		builder.append("This sale total value: ");
		builder.append(BigDecimalUtils.roundValue(transactionValue) + "\n");
		return builder.toString();
	}
	
}


