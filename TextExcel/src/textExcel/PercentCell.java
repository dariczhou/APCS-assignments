package textExcel;

public class PercentCell extends RealCell implements Cell{
	public PercentCell(String value) {
		super(value);
	}
	@Override
	public String abbreviatedCellText() {
		//Corrects for length while converting value back into percent form
		String s = (int)(super.getDoubleValue()*100)+"%          ";
		return s.substring(0, 10);
	}

	@Override
	public String fullCellText() {
		//Returns the percent as a decimal value
		return super.getDoubleValue()+"";
	}
	
	@Override
	public double getDoubleValue() {
		//Returns the percent as a decimal value (in double form)
		return super.getDoubleValue();
	}
}