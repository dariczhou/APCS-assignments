package textExcel;

public class FormulaCell extends RealCell implements Cell{
	public FormulaCell(String value) {
		super(value);
	}
	// text for spreadsheet cell display, must be exactly length 10
		public String abbreviatedCellText() {
		 return super.abbreviatedCellText();
		}
		// text for individual cell inspection, not truncated or padded
		public String fullCellText() {
		return super.abbreviatedCellText();
		}
		public double getDoubleValue() {
			return 0.0;
		}
}
