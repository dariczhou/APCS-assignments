package textExcel;

public class TextCell implements  Cell {
	
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
	@Override
	//compares the alphabetical sequence of the strings depending on the ascending or descending sort
	public int compareTo(Object o) {
		if(o instanceof TextCell) {
			
			// Casts cell to TextCell and stores values
			TextCell t = (TextCell) o;
			String s1 = t.fullCellText();
			String s2 = this.fullCellText(); 
			
			// If the letters are equivalent, return 0
			if(s1.equals(s2)) return 0;
			
			// This part of the code loops through each string to determine which comes first alphabetically
			String shorter;
			if(s1.length() > s2.length()) shorter = s2;
			else shorter = s1;
			
			for(int i = 0; i < shorter.length(); i++) {
				if(s1.charAt(i) > s2.charAt(i)) {
					return -1;
				} else if (s1.charAt(i) < s2.charAt(i)) {
					return 1;
				}
			}
			
			// If Strings are identical up to the length of the shorter String, this returns based on what String is shorter
			if(shorter.equals(s1)) {
				return -1;
			} else return 1;
		
		}
		return 1;
	}
}