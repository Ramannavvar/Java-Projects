package com.jpmorgan.techtest.MessageProcessor.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.jpmorgan.techtest.MessageProcessor.Utils.BigDecimalUtils;
import com.jpmorgan.techtest.MessageProcessor.beans.Product;
import com.jpmorgan.techtest.MessageProcessor.beans.Sale;

public class MessageInterpreter {
	String delimiter;
	String messageType1; 
	String messageType2;
	String messageType3;
	
	private static final Logger logger = Logger.getLogger(MessageInterpreter.class);
	
	public MessageInterpreter() {
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream("message.properties");
		Properties properties = new Properties();
		
		try {
			properties.load(inputStream);
			inputStream.close();
			delimiter = properties.getProperty("delimiter");
			logger.info("Delimiter: " + delimiter);
			messageType1 = properties.getProperty("message-type1");
			messageType2 = properties.getProperty("message-type2");
			messageType3 = properties.getProperty("message-type3");
			 
		} catch (IOException e) {
			logger.fatal("message.properties file error " + e.getMessage());			
		}
	}
	
	public Sale interpretMessage(String message) throws Exception {
	
		String[] messageArr = message.split("\\"+delimiter);
		
		if (messageArr[0].equals(messageType1)) {
			logger.info("Message type 1 received");
			return new Sale(new Product(messageArr[1], BigDecimalUtils.strToBigDecimal(messageArr[2])));			
		} else if (messageArr[0].equals(messageType2)) {
			logger.info("Message type 2 received");
			return new Sale(new Product(messageArr[1], BigDecimalUtils.strToBigDecimal(messageArr[2])), Integer.parseInt(messageArr[3]));
		} else if (messageArr[0].equals(messageType3)) {
			logger.info("Message type 3 received");
			return new Sale(new Product(messageArr[1], BigDecimalUtils.strToBigDecimal(messageArr[2])), 
					BigDecimalUtils.strToBigDecimal(messageArr[3]), Sale.Operation.valueOf(messageArr[4]));
		} else {
			throw new Exception("Message type not found");
		}		
	}
}
