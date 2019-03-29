package textExcel;

import java.util.Scanner;

// Update this file with your own code.

public class TextExcel
{

	public static void main(String[] args)
	{
		//Creates scanner object
		Scanner sc = new Scanner(System.in);
		Spreadsheet s = new Spreadsheet();
	    // Add your command loop here
   		System.out.print("Input: ");
	   	String input = sc.nextLine();
	   	while(!input.equals("quit")) {
	   		//Calls produceAnswer to obtain the answer to the problem and print it out
	   		System.out.println(s.processCommand(input));
	   		System.out.print("Next input (or type \"quit\" to stop): ");
	   		input = sc.nextLine();
	   	}
	    sc.close();
	 
	}
}
