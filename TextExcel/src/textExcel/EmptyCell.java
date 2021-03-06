package textExcel;
/*
 * @author Daric Zhou
 * @version March 2019
 * This class handles cells that contain nothing
 */
public class EmptyCell implements Cell{
	public EmptyCell() {
		
	}
	// text for spreadsheet cell display, must be exactly length 10
	public String abbreviatedCellText() {
		return "          ";
	}
	//text for individual cell inspection, not truncated or padded
	public String fullCellText() {
		return "";
	}
	@Override
	public int compareTo(Object o) {
		return 0;
	}
	
	
}
