import java.io.*;  
import java.util.Scanner;  
public class testing {  
	public static void main(String[] args) throws Exception  {  
		FileReader reader = new FileReader("ClueSetup306.txt"); //reads file
		Scanner in = new Scanner(reader);
		String line;

		in.useDelimiter(", ");

		while (in.hasNext()) {
			line = in.next();
			if (!line.contains("//")) {
//				if (line.startsWith("Room")) line = Character.toString(line.charAt(0));
				System.out.println(line);
			}
		} in.close();
	}  
}