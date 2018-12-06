package fracCalc;
import java.util.*;
/* This class will take in at least 2 values and perform at least one operation on them
 * @author Daric Zhou
 * @version 11/19/2018
 */
public class FracCalc {

    public static void main(String[] args) {
    	Scanner console = new Scanner(System.in);
    	System.out.print("Enter your fractions, and we will calculate the answer: ");
    	String expression = console.next();
    	while(!expression.equals("quit")) {
    	produceAnswer(expression);
    	System.out.println("Answer: " + expression);
    	}
    	console.close();
    }
    // This function takes a String 'input' and produces the result
    //
    // input is a fraction string that needs to be evaluated.  For your program, this will be the user input.
    //      e.g. input ==> "1/2 + 3/4"
    //        
    // The function should return the result of the fraction after it has been calculated
    //      e.g. return ==> "1_1/4"
	   public static String produceAnswer(String input)
	    { 
	    	String result = null;
			String[] cutExpression = input.split(" ");
			if(cutExpression[1].contains("+")) {
				result = operate(add(toRealFrac(cutExpression[0]),toRealFrac(cutExpression[2])));
			}
			else if(cutExpression[1].contains("-")) {
				result = operate(subtract(toRealFrac(cutExpression[0]),toRealFrac(cutExpression[2])));
			}
			else if(cutExpression[1].contains("*")) {		
				result = operate(multiply(toRealFrac(cutExpression[0]),toRealFrac(cutExpression[2])));
			}
			else if(cutExpression[1].contains("/")) {		
				result = operate(divide(toRealFrac(cutExpression[0]),toRealFrac(cutExpression[2])));
			}
			return result;
		}
	    
	    //parses the operands into integers so that math operations may take place properly
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
		
		//THESE METHODS ARE REDUNDANT; WILL WORK ON IT TO BE MORE EFFICIENT
		
		//adds the two operands together
		public static int[] add(int[] intFrac, int[] intFrac2) {
			int [] answer = new int[3];
			toImproper(intFrac);
			toImproper(intFrac2);
			answer[1] = (intFrac[1]*intFrac2[2]) + (intFrac2[1]*intFrac[2]);
			answer[2] = intFrac[2] * intFrac2[2];
			return answer;
		}
		
		//this method subtracts the two methods
		public static int[] subtract(int[] intFrac, int[] intFrac2) {
			int [] answer = new int[3];
			toImproper(intFrac);
			toImproper(intFrac2);
			answer[1] = (intFrac[1]*intFrac2[2]) - (intFrac2[1]*intFrac[2]);
			answer[2] = intFrac[2] * intFrac2[2];
			return answer;
		}
		//this method multiplies the expressions together
		public static int[] multiply(int[] intFrac, int[] intFrac2) {
			int [] answer = new int[3];
			toImproper(intFrac);
			toImproper(intFrac2);
			answer[1] = (intFrac[1]*intFrac2[1]);
			answer[2] = (intFrac[2]*intFrac2[2]);
			return answer;
		}
		//this method divides the expressions
		public static int[] divide(int[] intFrac, int[] intFrac2) {
			int [] answer = new int[3];
			toImproper(intFrac);
			toImproper(intFrac2);
			answer[1] = (intFrac[1]*intFrac2[2]);
			answer[2] = (intFrac[2]*intFrac2[1]);
			if(answer[2] < 0) {
				answer[1] = -answer[1];
				answer[2] = absValue(answer[2]);
			}
			return answer;
		}
		
		
		//after going through the operators, this method simplifies the result produced by the operator methods
		public static String operate(int[] result) {
			
			int gcf = gcf(absValue(result[1]),absValue(result[2]));
			
			if(gcf >= 1 && absValue(result[1])%absValue(result[2]) != 0 && absValue(result[1]) > absValue(result[2])) {
				result[2] = result[2] / gcf;
				result[1] = result[1] / gcf;
				return toMixedNum(result[1],result[2]);
			}
			else if(absValue(result[1])%absValue(result[2]) == 0) {
				return "" + result[1]/result[2] + "";
			}
			else if(result[2] == 1) {
				return "" + result[1] + "";
			}
			else {
				return result[1]/gcf + "/" + result[2]/gcf;
			}
			
		}
		//returns the improper fraction of a fraction if it's mixed
		public static void toImproper(int[] fraction) {
			fraction[1] = fraction[1] + fraction[2] * absValue(fraction[0]);
			if(fraction[0] < 0) { //deals with negative fractions
				fraction[1] = -fraction[1];
			}
			fraction[0] = 0;
		}
		
		//These methods from calculate allow the operate method to simplify 
		
		public static boolean isDivisibleBy(int num1, int num2) {
			boolean answer;
			if(num1%num2== 0) {
				if(num2 == 0) {
					throw new IllegalArgumentException("undefined");
				}
				answer=true;//evenly divisible by one another
					}else {
					return false;
				}
				return answer;	
			}
		public static int gcf(int x, int y) {
			int gcf = 0;
			if (x>y) {
				for (int i = y; i >= 1; i--) {
					if(isDivisibleBy(x, i) && isDivisibleBy(y, i)) {
						return i;
					}
				}
			}else {
				   for(int j = x; j >=1; j--)
			        {
			            if(isDivisibleBy(x, j) && isDivisibleBy(y, j))
			            {
			                return j;
			            }
			       }
			}
			return gcf;
		}	
		public static int toWholeNum(int num1, int num2) {
			int ans = 0;
			if(num1%num2 == 0) {
				ans = num1/num2;
			}
			return ans;
		}
		public static int absValue(int num) {
			int answer = 0;
			if(num < 0) {
				answer= -num;//whenever the number is negative, it gets canceled out by the negative in this statement
				}else answer= num;{//straightforward positive 
				}
			return answer;
		}
		public static String toMixedNum(int numer, int denom) {
			int newnum= numer%denom;
			int wholenum= (numer-newnum)/denom;
			return wholenum + "_" + absValue(newnum) + "/" + absValue(denom);
		} 
	}