package fracCalc;

public class Fraction {
	private int whole;
	private int numer;
	private int denom;
	private int sign;
	//Constructor that creates a Fraction from integers
	public Fraction(int sign, int whole, int numerator, int denominator) {
		this.sign = sign;
		this.whole = whole;
		this.numer = numerator;
		this.denom = denominator;
		
	}
	public Fraction(String cutExpression) {
		this.whole = 0;
		this.numer = 0; //initial initialization
		this.denom = 1;
		//changes string to integers based on the string
			if(cutExpression.contains("_")) { //when the String is in the complete +- a_b/c fraction
				String[] mixedNum = cutExpression.split("_");
				String[] fraction = mixedNum[1].split("/");
				this.whole = Integer.parseInt(mixedNum[0]);
				this.sign = this.whole > 0 ? 1 : -1;
				this.whole = Math.abs(this.whole);
				this.numer = Integer.parseInt(fraction[0]);
				this.denom = Integer.parseInt(fraction[1]);
			}
			else if(cutExpression.contains("/")) { //fraction strings with only a numerator and denominator
				String[] fraction = cutExpression.split("/");
				this.numer = Integer.parseInt(fraction[0]);
				this.sign = this.numer > 0 ? 1 : -1;
				this.numer = Math.abs(this.numer);

				this.denom = Integer.parseInt(fraction[1]);
			}
			else { //if fraction is a whole number
				this.whole = Integer.parseInt(cutExpression);	
				this.sign = this.whole > 0 ? 1 : -1;
				this.whole = Math.abs(this.whole);
			}
	}
	//adds two operands to make a sum
	public static Fraction add(Fraction x, Fraction y) {
		x = Fraction.toImproperFrac(x);
		y = Fraction.toImproperFrac(y);
		
		int a_num = x.getNumer()*y.getDenom();
		int b_num = y.getNumer()*x.getDenom();
		int den = x.getDenom()*y.getDenom();

		int r_num = x.getSign()*a_num + y.getSign()*b_num;
		int sign = r_num > 0 ? 1 : -1;
		
		return new Fraction(sign, 0, Math.abs(r_num), den);
		
	}
	//subtracts two operands to make a difference
	public static Fraction subtract(Fraction x, Fraction y) {
		x = Fraction.toImproperFrac(x);
		y = Fraction.toImproperFrac(y);
		
		int a_num = x.getNumer()*y.getDenom();
		int b_num = y.getNumer()*x.getDenom();
		int den = x.getDenom()*y.getDenom();

		int r_num = x.getSign()*a_num - y.getSign()*b_num;
		int sign = r_num > 0 ? 1 : -1;
		
		return new Fraction(sign, 0, Math.abs(r_num), den);
	}
	//multiplies two operands to make a product
	public static Fraction multiply(Fraction x, Fraction y) {
		x = toImproperFrac(x);
		y = toImproperFrac(y);
		return new Fraction(x.getSign()*y.getSign(), 0, x.getNumer() * y.getNumer(),absValue(x.getDenom() * y.getDenom()));
	}
	//divides two operands to make a quotient
	public static Fraction divide(Fraction x, Fraction y) {
		x = toImproperFrac(x);
		y = toImproperFrac(y);
		return new Fraction(x.getSign()*y.getSign(),0, x.getNumer() * y.getDenom(), x.getDenom() * y.getNumer());
	}

	//changes mixed number into an improper fraction (static version)
	public static Fraction toImproperFrac(Fraction x) {
		return new Fraction(x.getSign(), 0, x.getNumer() + (x.getWhole()*x.getDenom()), absValue(x.getDenom()));
	}
	public void toImproperFrac() {
		this.numer = this.whole * this.denom + this.numer;
		this.whole = 0;
		
	}
	//simplifies fraction to smallest common values
	public void toReducedFrac() {
		this.toImproperFrac();
		int gcf = gcf(this.numer,this.denom); 

		this.numer = this.numer / gcf;
		this.denom = this.denom / gcf;
	}
	//turns an improper answer into a mixed number
	public void toMixedNum() {
		int mixedNumer = numer % denom;
		int mixedWhole = (numer - absValue(mixedNumer))/ denom;
		
		this.whole = mixedWhole;
		this.numer = mixedNumer;
	}
	
	//returns the result of the calculation as a string
	public String toString() {
		//Decides how much to return based on the Fraction
		if(this.numer == 0){ 
			return this.sign*this.whole + "";
		} else if(this.whole == 0 && this.numer!= 0){ 
			return this.sign*this.numer+"/"+this.denom;
		} else {
			return this.sign*this.whole+"_"+this.numer+"/"+this.denom;
		}
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
			answer= -num;//whenever the number is negative, it gets canceled out by the negative in this statement
			}else answer= num;{//straightforward positive 
			}
		return answer;
	}
	//In the case of FracCalc, gcf can be employed to reduce fractions in operate
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
