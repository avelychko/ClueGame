package clueGame;

import java.util.*;

import java.io.*;  

public class Layouttest {
	

	public static void main(String[] args) throws Exception {
		FileReader reader = new FileReader("ClueLayout.csv"); //reads file
		Scanner in = new Scanner(reader);
	
		in.useDelimiter(",");
		int i = 0;
		
		String thisLine = ""; 
		List<String[]> lines = new ArrayList<String[]>();
		
		while(in.hasNextLine()) {
			
			
			//System.out.print(in.nextLine() + '\n');
			lines.add(in.nextLine().split(","));
			
		
			}
		
		/*for (int k=0; k < lines.size(); k++) {
			System.out.print(lines.get(k).length + " ");
			i++;
		}
		System.out.println(i);*/
		
		//System.out.print(lines.get(0).length);
		System.out.print(lines.get(1)[4].charAt(1));
		/*for (int k=0; k < lines.size(); k++) {
			System.out.print(lines.get(k).length + " ");
		for(int j=0; j < lines.get(k).length; j++) {
			System.out.print(lines.get(k)[j] + " ");
		}
		System.out.println("");
		}*/
		
		
		
		/*String[][] array = new String[lines.size()][0];
		lines.toArray(array);
		
		thisLine = in.nextLine();
			lines.add(thisLine.split(","));
			
		//System.out.println(array[1][0]);
		System.out.println(array.length);
		System.out.println(array[0].length);*/
		in.close(); //close file after reading	
		 
		

		
		
		
		/*String data; 
		String[][] layout;
    	FileInputStream file = new FileInputStream("ClueLayout.csv");
 		DataInputStream in = new DataInputStream(file);
    	 
 		List<String[]> lines = new ArrayList<String[]>();
		while ((data = in.readLine()) != null) {
		     lines.add(data.split(","));
		}
		in.close();
		// convert our list to a String array.
		layout = new String[lines.size()][0];
		lines.toArray(layout); 
		
		System.out.println(layout.length);
		System.out.println(layout[0].length);*/
		
		
	
	}
	
}
