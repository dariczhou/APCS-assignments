package textExcel;

public class ValueCell extends RealCell {
	
	public ValueCell(String value) {
		super(value);
	}

	@Override 
	public String abbreviatedCellText() {
		String s = super.fullCellText();
		//If the string contains a decimal, remove all the following zeroes
		if(s.contains(".")) {
			s = s.replaceAll("0*$", ""); 
		}
		//If the string has a decimal point w/o anything after, it adds a "0"
		if(s.charAt(s.length()-1) == '.') {
			s += "0";
		}
		// If the string has no decimals, add suffix ".0"
		if(s.indexOf('.') == -1) {
			s += ".0"; 
		}
		//Sets length to 10 after truncation of following zeroes
		s = s + "          ";
		return s.substring(0, 10);
	}
	
	@Override
	public String fullCellText() {
		return super.fullCellText();
	}
	
	public double getDoubleValue() {
		return super.getDoubleValue();
	}

}
