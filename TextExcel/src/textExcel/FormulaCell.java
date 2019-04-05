package textExcel;
/*
 * @author Daric Zhou
 * @version March 2019
 * This class handles formulas and equations from the client
 */
import java.util.ArrayList;

public class FormulaCell extends RealCell implements Cell{
	private Spreadsheet s;
	
	public FormulaCell(String value, Spreadsheet s) {
		super(value);
		this.s = s;
	}
	// text for spreadsheet cell display, must be exactly length 10
	public String abbreviatedCellText() {
		String s = getDoubleValue() + "          ";
		return s.substring(0, 10);
	}
		// text for individual cell inspection, not truncated or padded
	public String fullCellText() {
		return "( " + super.fullCellText() + " )";
	}
	public double getDoubleValue() {
		String[] valArr = super.fullCellText().split(" ");
		ArrayList<Double> operands = new ArrayList<Double>();
		ArrayList<Character> operators = new ArrayList<Character>();
		
		if(valArr[0].equalsIgnoreCase("AVG") || valArr[0].equalsIgnoreCase("SUM")) {
			String[] range = valArr[1].split("-");
			
			SpreadsheetLocation start = new SpreadsheetLocation(range[0]);
			SpreadsheetLocation end = new SpreadsheetLocation(range[1]);
			ArrayList<Cell> cellArr = Spreadsheet.rangeBetween(start, end, s);
				
			// Converts cells into RealCells
			ArrayList<RealCell> realCellArr = new ArrayList<RealCell>();
			for(Cell c : cellArr) {
				realCellArr.add((RealCell)c);
			}
			// Gets result
			double result = 0;
			for(int i = 0; i < realCellArr.size(); i++) {
				result += realCellArr.get(i).getDoubleValue();
			}
			//returns either average or sum
			if(valArr[0].equalsIgnoreCase("AVG")) {
				return result/((double)realCellArr.size());
				} else {
				return result;
			}
			
		} else {
			for(int i = 0; i < valArr.length; i++) {
		
			if(i%2 == 0) {
				if(valArr[i].matches("([A-Za-z])[0-9]*")) { //determines if the input is a cell of numeric value
					operands.add(((RealCell) s.getCell(new SpreadsheetLocation(valArr[i]))).getDoubleValue());
				} else {
				operands.add(Double.parseDouble(valArr[i]));
				}
			} else {
				operators.add(valArr[i].charAt(0));
			}
		}
			
			// Performs operations on formula
			double value = operands.get(0);
			for(int i = 0; i < operators.size(); i++) {
				char c = operators.get(i);

					if(c == '+')  	   value += operands.get(i+1);
					else if(c == '-')  value -= operands.get(i+1);
					else if(c == '*')  value *= operands.get(i+1);	
					else if(c == '/')  value /= operands.get(i+1);
								
			}
			return value;
		}	
	}
	
}
