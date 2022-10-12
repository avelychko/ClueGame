import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;  
import clueGame.*;

public class testing {  
	public static void main(String[] args) throws Exception  {  
		Map<Character, Room> roomMap = new HashMap<Character, Room>(); //map for board rooms
		FileReader reader = new FileReader("ClueSetup306.txt"); //reads file
		Scanner in = new Scanner(reader);
		String line;
		in.useDelimiter(", ");

//		while (in.hasNext()) {
//			line = in.next();
//			if (!line.contains("//")) {
//				System.out.println(line);
//				line = Character.toString(in.next().charAt(0));
//				System.out.println(line);
//				in.next();
//			}
//		} in.close();
		
 		while (in.hasNext()) {
 			line = in.next();
 			if (!line.contains("//")) {
 				Room room = new Room();
				room.setName(line);
				line = in.next();
 				roomMap.put(line.charAt(0), room);
 			}
 		} in.close();
		System.out.println(roomMap);
	}  
}