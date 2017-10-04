package com.jpmorgan.techtest.MessageProcessor.core;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class App {
	
	private static final Logger logger = Logger.getLogger(App.class);
	
	public static void main(String[] args) {
		Scanner scanner = null;
		try{
			scanner = new Scanner(new FileReader(App.class.getClassLoader().getResource("messages.txt").getFile()));
			MessageReceiver receiver = new MessageReceiver();			
			while(scanner.hasNextLine()) {
				receiver.receiveMessage(scanner.next());				
			}			
		} catch (FileNotFoundException e) {
			logger.fatal("Test data file error " + e.getMessage());
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}
	}	
}
