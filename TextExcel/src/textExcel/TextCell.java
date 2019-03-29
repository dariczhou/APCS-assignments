package textExcel;

public class TextCell implements Cell {
	
	private String text;
	
	public TextCell(String text) {
		this.text = text;
	}
	@Override
	public String abbreviatedCellText() {
		String abbrv = text + "          ";
		return abbrv.substring(0, 10);
	}

	@Override
	public String fullCellText() {
		return "\"" + text + "\"";
	}

}