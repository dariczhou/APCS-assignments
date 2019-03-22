package textExcel;

// Update this file with your own code.

public class Spreadsheet implements Grid
{
	private int numRows = 20;
	private int numColumns = 12;
	private Cell[][] sheet = new Cell[numRows][numColumns];
	//constructor
	public Spreadsheet() {
		for(int row = 0; row < numRows; row++) {
			for(int col = 0; col < numColumns; col++) {
				sheet[row][col] = new EmptyCell();
			}
		}
	}
	@Override // processes a user command, returns string to display, must be called in loop from main
	public String processCommand(String command)
	{
		String result = "";
		if (command.equals("")) {
			// does nothing in case of empty string being passed in 
		// clears either the entire grid or a single cell
		} else if (command.split(" ", 2)[0].toLowerCase().equals("clear")) {
			if (command.contains(" ")) {
				String cellName = command.split(" ")[1];
				SpreadsheetLocation cell = new SpreadsheetLocation(cellName);
				sheet[cell.getRow()][cell.getCol()] = new EmptyCell();
				result = getGridText();
			} else {
				for (int i = 0; i < numRows; i++) {
					for (char j = 0; j < numColumns; j++) {
					sheet[i][j] = new EmptyCell();
					}
				}
				result = getGridText();
			}
		// input entered is a cell location
		} else if (Character.isLetter(command.charAt(0)) && Character.isDigit(command.charAt(1))) {
			String cellName = command.split(" ", 3)[0];
			SpreadsheetLocation cell = new SpreadsheetLocation(cellName);
			if (command.contains("=")) {
				String value = command.split(" ", 3)[2];
				Cell target = sheet[cell.getRow()][cell.getCol()];
				// determines whether the cell contains text (TextCell or FormulaCell) or numbers (RealCell)
				target = new TextCell(value);
				result = getGridText();
			} else {
				result = sheet[cell.getRow()][cell.getCol()].fullCellText();
			}
		} else {
			System.out.println("Unknown command. Type 'quit' to exit the program.");
		}
		return result;
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

	@Override
	public String getGridText()
	{
		String grid = "   ";
	    	for(int col = 65; col < 77; col++) {
	    		grid += "|" + (char)(col) + "         ";
	    	}
	    	grid += "|";
	    	for(int row = 1; row <= 9; row++) {
	    		grid += "\n  " + row;
	    		for(int col = 1; col <= numColumns; col++) {
	    			grid += "|          ";
	       		}
	    		grid += "|";
	    	}
	    	for(int row = 10; row <= numRows; row++) {
	    			grid += "\n " + row;
	    		for(int col = 1; col <= numColumns; col++) {
	    			grid += "|          ";
	       		}
	    		grid += "|";
	    	}
	    	return grid;
	    }
	public void clearCells() {
		for(int row = 0; row < numRows; row++) {
			for(int col = 0; col < numColumns; col++) {
				sheet[row][col] = new EmptyCell();
			}
		}
	}

}
