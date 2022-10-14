package clueGame;

import java.util.*;

import java.io.*;  

public class Layouttest {
	

	public static void main(String[] args) throws Exception {
		/*FileReader reader = new FileReader("ClueLayout306.csv"); //reads file
		Scanner in = new Scanner(reader);
	
		in.useDelimiter(",");
		//int i = 0;
		
		String thisLine = ""; 
		List<String[]> lines = new ArrayList<String[]>();
		
		while(in.hasNextLine()) {
			
			
			//System.out.print(in.nextLine() + '\n');
			lines.add(in.nextLine().split(","));
			
		
			}*/
		
		/*for (int k=0; k < lines.size(); k++) {
			System.out.print(lines.get(k).length + " ");
			i++;
		}
		System.out.println(i);*/
		
		//System.out.print(lines.get(0).length);
		//System.out.print(lines.get(4)[6].charAt(1));
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
		//in.close(); //close file after reading	
		 
		
		
		/*for (int i = 0; i < lines.size(); i++) {
			for (int j = 0; j <  lines.get(0).length; j++) {   			 
				
				if(lines.get(i)[j].length() == 2) {
					if(lines.get(i)[j].charAt(1) == '*') {
						System.out.println("Center of room");
					}
					else if(lines.get(i)[j].charAt(1) == '#') {
						System.out.println("Label of room");
					}
					else if(lines.get(i)[j].charAt(1) == '^') {
						System.out.println("Is door");
						System.out.println("Up door");
					}
					else if(lines.get(i)[j].charAt(1) == '>') {
						System.out.println("Is door");
						System.out.println("Right door");
					}
					else if(lines.get(i)[j].charAt(1) == '<') {
						System.out.println("Is door");
						System.out.println("Left door");
					}
					else if(lines.get(i)[j].charAt(1) == 'v') {
						System.out.println("Is door");
						System.out.println("Down door");
					}
					else {
						System.out.println("Secret at " + lines.get(i)[j].charAt(1));
					}
				}
			}
		}
		in.close();
		
		
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
		List<String[]> layoutLines = new ArrayList<String[]>();
		FileReader reader = null;
		Scanner in = null;
		String[] nextCell; //stores layout lines
		//read once to see how many rows and cols 
		//and then read 2nd time to store cell information in grid[][]
		try {
			reader = new FileReader("ClueLayout.csv"); //reads file
			in = new Scanner(reader);
			// layoutLines = new ArrayList<String[]>(); //holds file lines

			//reads each line and adds to array
			while(in.hasNextLine()) {
				nextCell = in.nextLine().split(","); //adds split cell to array
				layoutLines.add(nextCell); //stores each cell in array list
			}
		} catch (FileNotFoundException e) {
			e.getLocalizedMessage();
		} in.close(); //close file
		
	
		System.out.println(layoutLines.get(0).length);
		
	}
	
}
