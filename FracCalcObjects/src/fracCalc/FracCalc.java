package fracCalc;
import java.util.Scanner;
/* This class, using objects, will take in at least 2 values and perform at least one mathematical operation on them
 * @author Daric Zhou
 * @version 1/19/2019
 */
public class FracCalc {

	public static void main(String[] args) {
		//Creates scanner object
    		Scanner sc = new Scanner(System.in);
	    
    		//Asks for input, continues to ask and print the answer until 'quit' is entered
    		System.out.print("Enter your values and we will calculate the answer: ");
	   	String input = sc.nextLine();
	   	while(!input.equals("quit")) {
	   		//Calls produceAnswer to obtain the answer to the problem and print it out
	   		System.out.println("Answer: " + produceAnswer(input));
	   		System.out.print("Next expression (or type \"quit\" to stop): ");
	   		input = sc.nextLine();
	   	}
	    sc.close();
	 
	}
	 // This function takes a String 'input' and produces the result
    public static String produceAnswer(String input)
    { 
    	String[] cutExpression = input.split(" ");
    	String operator = cutExpression[1];
    	Fraction answer = new Fraction(cutExpression[0]);
        Fraction operand = new Fraction(cutExpression[2]);

       answer = doMath(answer, operator, operand);
       answer.toReducedFrac();
       answer.toMixedNum();      
        return answer.toString();
    } 
    //With a given operator, math is done on the 2 given operands
    public static Fraction doMath(Fraction a, String operator, Fraction b) {
		if(operator.equals("+")) return Fraction.add(a, b);
		if(operator.equals("-")) return Fraction.subtract(a, b);
		if(operator.equals("/")) return Fraction.divide(a, b);
		return Fraction.multiply(a, b);
	}

}

