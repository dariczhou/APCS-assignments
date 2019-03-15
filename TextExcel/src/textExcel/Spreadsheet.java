package textExcel;

// Update this file with your own code.

public class Spreadsheet implements Grid
{
	private Cell[][] sheet;
	private int numRows;
	private int numColumns;
	//constructor
	public Spreadsheet() {
		//initializes a 2D array of cells with all elements containing EmptyCell objects
		numRows = 20;
		numColumns = 12;
		sheet = new Cell[numRows][numColumns];
		for(int row = 0; row < numRows; row++) {
			for(int col = 0; col < numColumns; col++) {
				sheet[row][col] = new EmptyCell();
			}
		}
	}
	@Override // processes a user command, returns string to display, must be called in loop from main
	public String processCommand(String command)
	{	
		String[] arr = command.split(" ");
		
		Cell c = new TextCell(arr[2]);
		Location l = new SpreadsheetLocation(arr[0]);
		if(command.contains("clear")) {
			if(arr[0].equalsIgnoreCase("clear")) {
				clearCells();
			}
			else if(arr[0].length() <= 3){
			sheet[l.getRow()][l.getCol()] = c;
			return c.fullCellText();
			}
			else {
			sheet[l.getRow()][l.getCol()] = new EmptyCell();
			}
		}
		return "";
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
		char ch = 'A';
		for(int row = 0; row < numRows; row++) {
			for(int col = 0; col < numColumns; col++) {
				return "|" + (char)(ch + col) + "       |";
			} 
		}
		return null;
	}
	public void clearCells() {
		for(int row = 0; row < numRows; row++) {
			for(int col = 0; col < numColumns; col++) {
				sheet[row][col] = new EmptyCell();
			}
		}
	}

}
