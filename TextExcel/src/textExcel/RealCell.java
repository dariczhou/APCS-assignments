package textExcel;
/*
 * @author Daric Zhou
 * @version March 2019
 * This superclass handles cells that are not text (string) nor empty
 */
public class RealCell implements Cell {
		
	private String value;
	
	public RealCell(String value) {
		this.value = value;
	}
	// text for spreadsheet cell display, must be exactly length 10
	public String abbreviatedCellText() {
		String str = value + "          ";
		return str.substring(0, 10);
	}
	// text for individual cell inspection, not truncated or padded
	public String fullCellText() {
		return value;
	}
	public double getDoubleValue() {
		return Double.parseDouble(value);
	}
	public int compareTo(Object o) {
		if(o instanceof RealCell) {
			RealCell r = (RealCell) o;
			if(this.getDoubleValue() == r.getDoubleValue()) {
				return 0;
			} else if(r.getDoubleValue() > this.getDoubleValue()){
				return -1;
			}
		}
		return 1;
	}
}
