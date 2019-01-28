package fracCalc;
import java.util.*;
/* This class will take in at least 2 values and perform at least one mathematical operation on them
 * @author Daric Zhou
 * @version 11/19/2018
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
    //
    // input is a fraction string that needs to be evaluated.  For this program, it will be the user input.
    //      e.g. input ==> "1/2 + 3/4"
	   public static String produceAnswer(String input) { 
   		   
		   String[] cutExpression = input.split(" ");
		 //Creates two new arrays to contain the operators and the operands
			String[] operands = new String[round(cutExpression.length/2.0 + 0.5)];
			String[] operators = new String[round(cutExpression.length/2.0 - 0.5)];
		   //Valid expressions for the scanner to take in
		   char[] allowedOperands = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '_', '/', '-'};
		   char[] allowedOperators = {'+', '-', '*', '/'}; 

		   String result = null;
			for(int i = 0; i < cutExpression.length;  i ++) {
				if(i % 2 == 0) {
					if(checkIfValid(cutExpression[i], allowedOperands)) { 		
						operands[i/2] = cutExpression[i];
					}
					else {
						return "ERROR: Input is in an invalid format";
					}
				}
				else {
					if(checkIfValid(cutExpression[i], allowedOperators) && cutExpression[i].split("").length < 2) {
						operators[i/2] = cutExpression[i];
					
					//If any are invalid, return an error immediately
					}
					if(checkIfValid(cutExpression[i], allowedOperators) && cutExpression[i].split("").length < 2) {
						return "ERROR: \"" + cutExpression[i+1] + "\" is not a valid format" ;
					}
				}
				result = toReducedFrac(operator(operators[i/2], toRealFrac(cutExpression[i]), toRealFrac(cutExpression[2])));
			}
			return result;
		
	   }
	   //parses the string array into integers
		public static int[] toRealFrac(String operand) {
			int[] intFrac = {0, 0, 1};
				if(operand.contains("_")) {
					String[] mixedNum = operand.split("_");
					String[] fraction = mixedNum[1].split("/");
					intFrac[0] = Integer.parseInt(mixedNum[0]);
					intFrac[1] = Integer.parseInt(fraction[0]);
					intFrac[2] = Integer.parseInt(fraction[1]);
				}
				else if(operand.contains("/")) {
					String[] fraction = operand.split("/");
					intFrac[1] = Integer.parseInt(fraction[0]);
					intFrac[2] = Integer.parseInt(fraction[1]);
				}
				else {
					intFrac[0] = Integer.parseInt(operand);
				}
				return intFrac;
		}
		//turns into an improper fraction as an array
		public static void toImproper(int[] fraction) {
			fraction[1] = fraction[1] + fraction[2] * Math.abs(fraction[0]);
			if(fraction[0] < 0) { //deals with negative fractions
				fraction[1] = -fraction[1];
			}
			fraction[0] = 0;
		}
		//This method executes the expression entered through the operators and operand
			public static int[] operator(String operator, int[] operand, int[] nextoperand) {
				int[] answer = new int[3];
				toImproper(operand);
				toImproper(nextoperand);
				answer[2] = (operand[2]*nextoperand[2]); //set common denominator
				if(operator.equals("+")) {
					answer[1] = (operand[1]*nextoperand[2]) + (nextoperand[1]*operand[2]);
					}
				if(operator.equals("-")) {
					answer[1] = (operand[1]*nextoperand[2]) - (nextoperand[1]*operand[2]);
					}
				if(operator.equals("/")) {
					answer[1] = (operand[1]*nextoperand[2]);
					answer[2] = (operand[2]*nextoperand[1]);
					}
				else if(operator.equals("*")) {
					answer[1] = (operand[1]*nextoperand[1]);
					}
				//changes the sign to be in front of the final value
				if(answer[2] < 0) {
					answer[1] = -answer[1];
					answer[2] = Math.abs(answer[2]);
				}
				return answer;
			}

				//after going through the operators, this method simplifies the result produced by the operator methods
			public static String toReducedFrac(int[] result) {
				if(result[2] == 0) {
					return "ERROR: Cannot divide by 0";
				}
					int gcf = gcf(Math.abs(result[1]),Math.abs(result[2])); 

					if(gcf >= 1 && Math.abs(result[1])%Math.abs(result[2]) != 0 && Math.abs(result[1]) > Math.abs(result[2])) {
						return toMixedNum(result[1]/gcf,result[2]/gcf);
					}
					else if(Math.abs(result[1])%Math.abs(result[2]) == 0) {
						return "" + result[1]/result[2] + "";
					}
					else {
						return result[1]/gcf + "/" + result[2]/gcf;
					}
			}
			//These methods from calculate allow the operate method to work
			//In the case of FracCalc, gcf can be employed to reduce fractions in operate
			public static int gcf(int x, int y) {
				int gcf = 0;
				if (x>y) {
					for (int i = y; i >= 1; i--) {
						if((x%i == 0) && (y%i == 0)) {
							return i;
						}
					}
				}else {
					   for(int j = x; j >=1; j--) {
				            if((x%j == 0) && (y%j == 0)) {
				                return j;
				            }
				       }
				}
				return gcf;
			}	
			//turns an improper answer into a mixed number
			public static String toMixedNum(int numer, int denom) {
				int newnum= numer%denom;
				int wholenum= (numer-newnum)/denom;
				return wholenum + "_" + Math.abs(newnum) + "/" + Math.abs(denom);
			} 
		

	   //This function checks if the operator entered is valid based off the given String array(EC)
		public static boolean checkIfValid(String check, char[] expression) {
			char[] str = check.toCharArray();
			for(char c : str) {
				if(!contains(expression, c)) {
					return false;
				}
			}
			
			return true;
		}
		//Returns if the target char is contained within an array 
		public static boolean contains(char[] arr, char target) {
	    	for(char curChar : arr) {
	    		if(curChar == target) {
	    			return true;
	    		} 
	    	}	
	    	return false;
	    }
		//This method solves the fencepost problem when declaring the arrays for the expressions
		public static int round(double n) {
			return (int)(n +  0.5);
		}
		//takes an improper fraction array and changes it to a mixed num
		public static int[] toOriginal(int[] improperFrac) {
			int num = improperFrac[1] % improperFrac[2];
			improperFrac[0] = (improperFrac[1] - num) / improperFrac[2];
			improperFrac[1] = num;
			return improperFrac;
		}
}