/* This class prints out an hourglass figure based off of nested for loops
 * @author Daric Zhou
 * @version 10/19/2018
 */

public class HourglassWarmup {
	public static void main(String[] args) {
		int size = -10;
		printBase(size);
		topHalf(size);
		printMiddle(size);
		bottomHalf(size);
		printBase(size);;
	}
	public static void printStringOfChars(String str, int count) {
		for (int i = 0; i < count; i++) {
			System.out.print(str);
		}
	}
	public static void printBase(int size) {
		System.out.print("|");
		printStringOfChars("\"",10);
		System.out.println("|");
	}
	public static void topHalf(int size) {
		for(int i = 1; i <= 4; i++) {
			printStringOfChars(" ", i);
			printStringOfChars("\\", 1);
			printStringOfChars(":", 10-2*i);
			printStringOfChars("/",1);
			System.out.println();
		}
	}
	public static void printMiddle(int size){
		printStringOfChars(" ", 5);
		printStringOfChars("||", 1);
		System.out.println();
	}
	public static void bottomHalf(int size) {
		for(int i = 4; i >= 1; i--) {
			printStringOfChars(" ", i);
			printStringOfChars("/", 1);
			printStringOfChars(":", -2*i+10);
			printStringOfChars("\\",1);
			System.out.println();
		}
	}
	
}