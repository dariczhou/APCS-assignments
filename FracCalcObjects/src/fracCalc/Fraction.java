package fracCalc;

public class Fraction {
	
	private int whole, numer, denom, sign;
	
	//Constructor that creates a Fraction from integers
	public Fraction(int sign, int whole, int numerator, int denominator) {
		
		this.sign = sign;
		this.whole = whole;
		this.numer = numerator;
		this.denom = denominator;
		
	}
	//Constructor that converts String element into an integer fraction
	public Fraction(String cutExpression) {
		this.whole = 0;
		this.numer = 0; //initial initialization
		this.denom = 1;
		//changes string to integers based on the string
			//when the String is in the complete +- a_b/c fraction
			if(cutExpression.contains("_")) {
				String[] mixedNum = cutExpression.split("_");
				String[] fraction = mixedNum[1].split("/");
				this.whole = Integer.parseInt(mixedNum[0]);
				this.sign = this.whole > 0 ? 1 : -1;
				this.whole = Math.abs(this.whole);
				this.numer = Integer.parseInt(fraction[0]);
				this.denom = Integer.parseInt(fraction[1]);
			}
			 //fraction strings with only a numerator and denominator
			else if(cutExpression.contains("/")) {
				String[] fraction = cutExpression.split("/");
				this.numer = Integer.parseInt(fraction[0]);
				this.sign = this.numer > 0 ? 1 : -1;
				this.numer = Math.abs(this.numer);

				this.denom = Integer.parseInt(fraction[1]);
			}
			//if fraction is a whole number
			else { 
				this.whole = Integer.parseInt(cutExpression);	
				this.sign = this.whole > 0 ? 1 : -1;
				this.whole = Math.abs(this.whole);
			}
	}
	//returns the result of the calculation as a string
	public String toString() {
		if(this.numer == 0){ 
			return this.sign*this.whole + "";
		} else if(this.whole == 0 && this.numer!= 0){ 
			return this.sign*this.numer+"/"+this.denom;
		} else {
			return this.sign*this.whole+"_"+this.numer+"/"+this.denom;
		}
	}
	//adds two operands to make a sum
	public static Fraction add(Fraction x, Fraction y) {
		x = Fraction.toImproperFrac(x);
		y = Fraction.toImproperFrac(y);
		
		int num1 = x.getNumer()*y.getDenom();
		int num2 = y.getNumer()*x.getDenom();
		int denom = x.getDenom()*y.getDenom();
		
		int numer = x.getSign()*num1 + y.getSign()*num2;
		int sign = numer > 0 ? 1 : -1;
		
		return new Fraction(sign, 0, absValue(numer), denom);
		
	}
	//subtracts two operands to make a difference
	public static Fraction subtract(Fraction x, Fraction y) {
		x = Fraction.toImproperFrac(x);
		y = Fraction.toImproperFrac(y);	
		
		int num1 = x.getNumer()*y.getDenom();
		int num2 = y.getNumer()*x.getDenom();
		int denom = x.getDenom()*y.getDenom();
		
		int numer = x.getSign()*num1 - y.getSign()*num2;
		int sign = numer > 0 ? 1 : -1;
		
		return new Fraction(sign, 0, Math.abs(numer), denom);
	}
	//multiplies two operands to make a product
	public static Fraction multiply(Fraction x, Fraction y) {
		x = toImproperFrac(x); y = toImproperFrac(y);	
		return new Fraction(x.getSign()*y.getSign(), 0, 
							x.getNumer() * y.getNumer(),
							absValue(x.getDenom() * y.getDenom()));
	}
	//divides two operands to make a quotient
	public static Fraction divide(Fraction x, Fraction y) {
		x = toImproperFrac(x); y = toImproperFrac(y);	
		return new Fraction(x.getSign()*y.getSign(), 0, 
							x.getNumer() * y.getDenom(), 
							x.getDenom() * y.getNumer());
	}

	//changes mixed number into an improper fraction 
	public static Fraction toImproperFrac(Fraction x) {
		return new Fraction(x.getSign(), 0, x.getNumer() + (x.getWhole()*x.getDenom()), absValue(x.getDenom()));
	}
	//simplifies fraction to smallest common values
	public void toReducedFrac() {
		//turns frac into improper so that only two numbers will be used
		this.numer = whole * denom + numer;
		this.whole = 0;
		int gcf = gcf(this.numer,this.denom); 

		this.numer = numer / gcf;
		this.denom = denom / gcf;
	}
	//turns an improper answer into a mixed number
	public void toMixedNum() {
		int mixedNumer = numer % denom;
		int mixedWhole = (numer - absValue(mixedNumer))/ denom;
		
		this.whole = mixedWhole;
		this.numer = mixedNumer;
	}
	

	//Accessors
	public int getSign() {
		return this.sign;
	}
	public int getWhole() { 
		return this.whole;
	}
	public int getNumer() {
		return this.numer;
	}
	public int getDenom() {
		return this.denom;
	}
	//returns absolute value of a number
	public static int absValue(int num) {
		int answer = 0;
		if(num < 0) {
			answer= -num;
			}else answer= num;{
			}
		return answer;
	}
	//gcf reduces fractions in operate
	public static int gcf(int x, int y) {
		int gcf = 1;
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

}
