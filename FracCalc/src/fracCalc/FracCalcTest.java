package fracCalc;
public class FracCalcTest {
	public static void main(String[] args) {
		String equation = "0 + 34_543/19";
		System.out.println(produceAnswer(equation));
	}
	//return addition(toRealFrac(cutExpression[0]),toRealFrac(cutExpression[2]));
	
    public static String produceAnswer(String input)
    { 
    	String result = null;
		String[] cutExpression = input.split(" ");
		if(cutExpression[1].equals("+")) {
			result = operate(addition(toRealFrac(cutExpression[0]),toRealFrac(cutExpression[2])));
		}
		else if(cutExpression[1].equals("-")) {
			result = operate(subtraction(toRealFrac(cutExpression[0]),toRealFrac(cutExpression[2])));
		}
		else if(cutExpression[1].equals("*")) {		
			result = operate(multiplication(toRealFrac(cutExpression[0]),toRealFrac(cutExpression[2])));
		}
		else if(cutExpression[1].equals("/")) {		
			result = operate(division(toRealFrac(cutExpression[0]),toRealFrac(cutExpression[2])));
		}
		return result;
	}
    // TODO: Fill in the space below with any helper methods that you think you will need

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
	public static String operate(int[] result) {
		int gcf = gcf(absValue(result[1]),absValue(result[2]));
		if(gcf > 1 && absValue(result[1])%absValue(result[2]) != 0 && absValue(result[1]) > absValue(result[2])) {
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
			result[2] = result[2] / gcf;
			result[1] = result[1] / gcf;
			return result[1] + "/" + result[2];
		}
		
	}
	public static void toImproper(int[] fraction) {
		fraction[1] = fraction[1] + fraction[2] * absValue(fraction[0]);
		if(fraction[0] < 0) { //deals with negative fractions
			fraction[1] = -fraction[1];
		}
		fraction[0] = 0;
	}
	//adds the two operands together
	public static int[] addition(int[] intFrac, int[] intFrac2) {
		int [] answer = new int[3];
		toImproper(intFrac);
		toImproper(intFrac2);
		answer[1] = (intFrac[1]*intFrac2[2]) + (intFrac2[1]*intFrac[2]);
		answer[2] = intFrac[2] * intFrac2[2];
		return answer;
	}
	public static int[] subtraction(int[] intFrac, int[] intFrac2) {
		int [] answer = new int[3];
		toImproper(intFrac);
		toImproper(intFrac2);
		answer[1] = (intFrac[1]*intFrac2[2]) - (intFrac2[1]*intFrac[2]);
		answer[2] = intFrac[2] * intFrac2[2];
		return answer;
	}
	public static int[] multiplication(int[] intFrac, int[] intFrac2) {
		int [] answer = new int[3];
		toImproper(intFrac);
		toImproper(intFrac2);
		answer[1] = (intFrac[1]*intFrac2[1]);
		answer[2] = (intFrac[2]*intFrac2[2]);
		return answer;
	}
	public static int[] division(int[] intFrac, int[] intFrac2) {
		int [] answer = new int[3];
		toImproper(intFrac);
		toImproper(intFrac2);
		answer[1] = (intFrac[1]*intFrac2[2]);
		answer[2] = (intFrac[2]*intFrac2[1]);
		return answer;
	}
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