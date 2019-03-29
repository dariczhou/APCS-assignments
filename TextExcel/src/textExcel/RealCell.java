package textExcel;

public class RealCell implements Cell{
	private String value;
	public RealCell(String value) {
		this.value = value.replaceAll("\\s+", "");
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
}
