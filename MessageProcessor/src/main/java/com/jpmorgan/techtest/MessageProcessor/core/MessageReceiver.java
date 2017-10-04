package com.jpmorgan.techtest.MessageProcessor.core;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.jpmorgan.techtest.MessageProcessor.beans.Sale;

public class MessageReceiver {

	private Map<String, ArrayList<Sale>> map = new HashMap<String, ArrayList<Sale>>();
	private int count = 0;
	private MessageInterpreter interpretor = new MessageInterpreter();
	
	private static final Logger logger = Logger.getLogger(MessageReceiver.class);
	
	public void receiveMessage(String message) {
		if(count % 10 == 0 && count != 0) {
			logReport();
		}
		if(count == 50) {
			System.out.println("50 messages have been received. Application will pause for some time.");			
			logReportOfAdjustments();
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {				
				logger.error("Thread interrupted " + e.getMessage());
			}
			count = 0;
		} else {
			try {
				Sale sale = interpretor.interpretMessage(message);
				if (map.get(sale.getProduct().getProductType()) == null) {					
					map.put(sale.getProduct().getProductType(), new ArrayList<Sale>());						
				}
				map.get(sale.getProduct().getProductType()).add(sale);
				
				if (sale.isAdjustmentApplied()) {
					performOperation(sale);
				}				
				++count;
			} catch (Exception e) {				
				e.printStackTrace();
			}
		}
	}
	
	private void performOperation(Sale sale) {
		ArrayList<Sale> sales = map.get(sale.getProduct().getProductType());
		for(Sale s : sales) {
			logger.info("Perform adjustment " + sale.getAdjustment() + " operation: " + sale.getOperation());
			s.performOperation(sale.getOperation(), sale.getAdjustment());
		}		
	}

	private void logReportOfAdjustments() {
		logger.info("Log report - 50th meesage received");
		System.out.println("============= Log Adjustment Report ==============");
		Iterator<Entry<String, ArrayList<Sale>>> itr = map.entrySet().iterator();
		while(itr.hasNext()) {
			
			Entry<String, ArrayList<Sale>> entry = itr.next();
			
			for (Sale sale : entry.getValue()) {
				if(sale.isAdjustmentApplied()) {
					System.out.println("Product type: " + entry.getKey());					
					System.out.println("Adjustment of " + sale.getAdjustment() + " applied " + " Operation: " + sale.getOperation());
					System.out.println("*************************************");
				}
			}			
		}
		
		System.out.println("=======================================");		
	}

	private void logReport() {
		logger.info("Log report - 10th meesage received");
		System.out.println("============= Log Report ==============");
		Iterator<Entry<String, ArrayList<Sale>>> itr = map.entrySet().iterator();
		while(itr.hasNext()) {
			int noOfSales = 0;
			BigDecimal total = BigDecimal.ZERO;
			Entry<String, ArrayList<Sale>> entry = itr.next();
			System.out.println("Product type: " + entry.getKey());
			System.out.println("*************************************");
			System.out.println("Sales details:");
			for (Sale sale : entry.getValue()) {
				noOfSales += sale.getOccurances();
				total = total.add(sale.getTransactionValue());
			}
			System.out.println("Total number of sales: " + noOfSales);
			System.out.println("Total sale value: " + total);
			System.out.println("*************************************");
		}
		
		System.out.println("=======================================");
	}
}
