package clueGame;

import java.io.*;

/**
 * BadConfigException extends Exception class
 * 
 * @author Anastasia Velyhko
 * @author Gordon Dina
 *
 */

public class BadConfigFormatException extends Exception {
	public BadConfigFormatException() { super("Error: Incorrect Board Layout"); } //default message
	public BadConfigFormatException(String message) {
		super(message); 
	}
	
	public void logMessage() throws Exception {
		PrintWriter message = new PrintWriter(new FileWriter("logfile.txt", true));
		message.println(message);
		message.close(); //custom message
	}
}
