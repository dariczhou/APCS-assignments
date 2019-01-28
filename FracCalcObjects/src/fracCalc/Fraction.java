package fracCalc;

public class Fraction {
	private int whole;
	private int numer;
	private int denom;
	//Constructor that creates a Fraction from integers
	public Fraction(int whole, int numerator, int denominator) {
		
		this.whole = whole;
		this.numer = numerator;
		this.denom = denominator;
		
	}
	public Fraction(String cutExpression) {
		//changes string to integers based on the string
			if(cutExpression.contains("_")) { //when the String is in the complete +- a_b/c fraction
				String[] mixedNum = cutExpression.split("_");
				String[] fraction = mixedNum[1].split("/");
				this.whole = Integer.parseInt(mixedNum[0]);
				this.numer = Integer.parseInt(fraction[0]);
				this.denom = Integer.parseInt(fraction[1]);
			}
			else if(cutExpression.contains("/")) { //fraction strings with only a numerator and denominator
				String[] fraction = cutExpression.split("/");
				this.numer = Integer.parseInt(fraction[0]);
				this.denom = Integer.parseInt(fraction[1]);
				
				this.whole = 0;
			}
			else { //returns a whole number 
				this.whole = Integer.parseInt(cutExpression);				
				this.numer = 0;
				this.denom = 1;
			}
	}
	public static Fraction add(Fraction x, Fraction y) {
		x = toImproperFrac(x);
		y = toImproperFrac(y);
		int denom = x.getDenom() * y.getDenom(); //local common denominator	
		int numer = (x.getNumer() * y.getDenom()) + (x.getDenom() * y.getNumer());
		return new Fraction(0, numer, denom);
	}
	public static Fraction subtract(Fraction x, Fraction y) {
		int denom = x.getDenom() * y.getDenom();
		int numer = (x.getNumer() * y.getDenom()) - (x.getDenom() * y.getNumer());
		return new Fraction(0, numer, denom);
	}
	public static Fraction divide(Fraction x, Fraction y) {
		int numer = x.getNumer() * y.getDenom();
		int denom = x.getDenom() * y.getNumer();
		return new Fraction(0, numer, denom);
	}
	public static Fraction multiply(Fraction x, Fraction y) {
		int numer = x.getNumer() * y.getNumer();
		int denom = x.getDenom() * y.getDenom();
		return new Fraction(0, numer, denom);
	}

	
	
	//changes mixed number into an improper fraction
	public static Fraction toImproperFrac(Fraction x) {
		return new Fraction(0, x.getNumer() + x.getWhole()*x.getDenom(), x.getDenom());
	}
	//simplifies fraction to smallest common values
	public void toReducedFrac() {

		int gcf = gcf(this.numer,this.denom); 

		this.numer = this.numer / gcf;
		this.denom = this.denom / gcf;
	}
	//turns an improper answer into a mixed number
	public void toMixedNum() {
		int newNumer = numer%denom;
		int mixedWholeNum= (numer-newNumer)/denom;
		
		this.whole = mixedWholeNum;
		this.numer = newNumer;
	} 
	
	//returns the result of the calculation as a string
	public String toString() {
		if(this.numer == 0){ 
			return this.whole + "";
		} else if(this.whole == 0 && this.numer != 0){ 
			return this.numer+"/"+this.denom;
		} else {
			return this.whole+"_"+this.numer+"/"+this.denom;
		}
	}
	//Accessors
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
	public double absValue(double num) {
		double answer = 0;
		if(num < 0) {
			answer= -num;//whenever the number is negative, it gets canceled out by the negative in this statement
			}else answer= num;{//straightforward positive 
			}
		return answer;
	}
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

}
