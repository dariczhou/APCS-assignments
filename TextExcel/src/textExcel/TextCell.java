
package textExcel;

// cell that holds text
public class TextCell implements Cell {
	private String text;
	
	public TextCell(String t) {
		text = t;
	}
	
	// returns the value displayed on the grid
	public String abbreviatedCellText() {
		String abrvText = text;
		if (text.contains("\"")) {
			abrvText = text.substring(1, text.length()-1);
		}
		if (abrvText.length() > 10) {
			return abrvText.substring(0, 10);
		} else {
			String numSpaces = "";
			for (int i = 0; i < 10 - abrvText.length(); i++) {
				numSpaces += " ";
			}
			return abrvText + numSpaces;
		}
	}
	
	// returns the actual value
	public String fullCellText() {
		return text;
	}
}
