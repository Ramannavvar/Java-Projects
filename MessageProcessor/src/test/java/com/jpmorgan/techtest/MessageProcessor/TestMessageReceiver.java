package com.jpmorgan.techtest.MessageProcessor;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import com.jpmorgan.techtest.MessageProcessor.core.MessageReceiver;

import junit.framework.TestCase;

public class TestMessageReceiver extends TestCase {

	// This test tests the report logging
	public void testReceiveMessage() {
		MessageReceiver receiver = new MessageReceiver();
		Scanner scanner = null;
		try {
			// Get the test data file
			scanner = new Scanner(new FileReader(getClass().getClassLoader().getResource("messages.txt").getFile()));			
			while(scanner.hasNextLine()) {				
				receiver.receiveMessage(scanner.next());				
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}
		
	}	
}
