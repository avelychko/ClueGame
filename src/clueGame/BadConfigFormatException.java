package clueGame;

/**
 * BadConfigException extends Exception class
 * 
 * @author Anastasia Velyhko
 * @author Gordon Dina
 *
 */

public class BadConfigFormatException extends Exception {
	public BadConfigFormatException() { super("Error: Incorrect Board Layout"); }
	public BadConfigFormatException(String message) { super(message); }
}
