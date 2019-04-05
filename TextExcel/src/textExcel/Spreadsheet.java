package textExcel;
import java.util.ArrayList;
import java.util.Collections;
/*
 * @author Daric Zhou
 * @version March 2019
 * This class handles the creation of the spreadsheet and what's within the sheet
 */

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
			} else cell = new ValueCell(split[1].replaceAll(" ", ""));
			setCell(l, cell);
				
		} else {
			//Clears cells
			if(command.toLowerCase().indexOf("clear") != -1) {
				if(command.equalsIgnoreCase("clear")) {
					//Clears all cells
					clearCells();
				} else {
					//Clears a single cell
					String s = command.toLowerCase().replaceAll("clear", "");
					setCell(new SpreadsheetLocation(s.replaceAll("\\s+", "")), new EmptyCell());
				}
			//Sorts the range of Cells in command
			} else if (command.toLowerCase().contains("sort")) {
				String range = command.split(" ")[1];
				SpreadsheetLocation start = new SpreadsheetLocation(range.split("-")[0]);
				SpreadsheetLocation end = new SpreadsheetLocation(range.split("-")[1]);
				ArrayList<Cell> rangeArr = rangeBetween(start, end, this);
				
				ArrayList<Cell> sortedArr;
				if(command.toLowerCase().charAt(4) == 'a') {
					sortedArr = sort(rangeArr, true);
				} else {
					sortedArr = sort(rangeArr, false);
				}
				// Iterates through rows and columns of range
				for(int i = start.getRow(); i <= end.getRow(); i++) {
					for(int j = start.getCol(); j <= end.getCol(); j++) {
						int arrayIndex = (i-start.getRow())*(end.getCol()-start.getCol()+1)+(j-start.getCol());
						this.setCell(toSL(i, j), sortedArr.get(arrayIndex));
					}
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
	// Returns all cells in a rectangular area from the upper-left to lower-right corner
	public static ArrayList<Cell> rangeBetween(SpreadsheetLocation a, SpreadsheetLocation b, Spreadsheet s) {
		ArrayList<Cell> range = new ArrayList<Cell>();
		// Iterates through all the rows and columns within target range
		for(int i = a.getRow(); i <= b.getRow(); i++) {
			for(int j = a.getCol(); j <= b.getCol(); j++) {
				range.add(s.getCell(toSL(i, j))); 
			}
		}

		return range;
			
	}
	// Obtains string version of cell location
	public static SpreadsheetLocation toSL(int row, int col) {
		char column = (char)(col+65); // Converts column number into a column letter
		String str = String.format("%1$s%2$s", Character.toString(column), row+1);
		
		return new SpreadsheetLocation(str);
	}
	@Override
	// prints out entire spreadsheet grid
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
	// resets spreadsheet
	public void clearCells() {
		sheet = new Cell[getRows()][getCols()];
		for(int i = 0; i < numRows; i++) {
			for(int j = 0; j < numColumns; j++) {
				sheet[i][j] = new EmptyCell();
			}
		}
	}
	//sorts array in either ascending or descending order
	public static ArrayList<Cell> sort(ArrayList<Cell> arr, boolean ascending) {
		ArrayList<Cell> resultantArr = (ArrayList<Cell>) arr;
		
		int swaps;
		do {
			swaps = 0;

			for(int i = 1; i < resultantArr.size(); i++) {
				if(ascending) {
					if(resultantArr.get(i).compareTo(resultantArr.get(i-1))==-1) {
						Collections.swap(resultantArr, i, i-1);
						swaps++;
					}
				} else {
					if(resultantArr.get(i).compareTo(resultantArr.get(i-1))==1) {
						Collections.swap(resultantArr, i, i-1);
						swaps++;
					}
				}
			}
		} while(swaps != 0);
		
		
		return resultantArr;
	} 
	

}
