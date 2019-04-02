package textExcel;

// Update this file with your own code.

public class Spreadsheet implements Grid
{
	private int numRows = 20;
	private int numColumns = 12;
	private Cell[][] sheet = new Cell[numRows][numColumns];
	//constructor
	public Spreadsheet() {
		clearCells();
	}
	@Override // processes a user command, returns string to display, must be called in loop from main
	public String processCommand(String command)
	{
		if(command.indexOf('=') != -1) { // If assign cell
			
			Cell cell;
			
			String[] split = command.split("=", 2);
			//split[0] is the cell location and split[1] is the assignment statement
			SpreadsheetLocation l = new SpreadsheetLocation(split[0].replaceAll("\\s+", ""));
			
			//Assigning a TextCell
			if(split[1].indexOf("\"") != -1 && split[1].lastIndexOf("\"") != -1) {
				cell = new TextCell(split[1].substring(split[1].indexOf("\"")+1, split[1].lastIndexOf("\"")));
				
			//Assigning a FormulaCell
			} else if(split[1].indexOf("(") < split[1].indexOf(")")) { 
				cell = new FormulaCell(split[1].substring(split[1].indexOf("(")+2, split[1].lastIndexOf(")")-1), this);
				
			//Assigning a PercentCell
			} else if(split[1].indexOf("%") != -1){ 
				String percent = Double.parseDouble((split[1].replaceAll("%", "")))/100 + "";
				cell = new PercentCell(percent);
				
			//Assigning a ValueCell
			} else cell = new ValueCell(split[1].replaceAll("%", ""));
			setCell(l, cell);
				
		} else {
			if(command.toLowerCase().indexOf("clear") != -1) {
				if(command.equalsIgnoreCase("clear")) {
					//Clears all cells
					clearCells();
				} else {
					//Clears a single cell;
					String s = command.toLowerCase().replaceAll("clear", "");
					setCell(new SpreadsheetLocation(s.replaceAll("\\s+", "")), new EmptyCell());
				}
			} else {
				//Inspect cells
				return getCell(new SpreadsheetLocation(command)).fullCellText();
			}
		}
		return getGridText();
	}
	@Override
	public int getRows()
	{
		return numRows;
	}

	@Override
	public int getCols()
	{
		return numColumns;
	}

	@Override
	public Cell getCell(Location loc)
	{
		return sheet[loc.getRow()][loc.getCol()];
	}
	public void setCell(Location loc, Cell cell) {
		sheet[loc.getRow()][loc.getCol()] = cell;
	}
	@Override
	public String getGridText() {
		String grid = "";
		// first row - column header
		String firstRow = "   |";
		for (int i = 'A'; i <= 'L'; i++) {
			firstRow += (char) i + "         |";
		}
		grid += firstRow + "\n";
		// the rest of the rows - table of values
		for (int i = 0; i < numRows; i++) {
			String row = "";
			row += String.format("%-3s", i+1) + "|";
			for (int j = 0; j < numColumns; j++) {
				row += sheet[i][j].abbreviatedCellText() + "|";
			}
			grid += row + "\n";
		}
		return grid;
		}
	public void clearCells() {
		sheet = new Cell[getRows()][getCols()];
		for(int i = 0; i < numRows; i++) {
			for(int j = 0; j < numColumns; j++) {
				sheet[i][j] = new EmptyCell();
			}
		}
	}

}
