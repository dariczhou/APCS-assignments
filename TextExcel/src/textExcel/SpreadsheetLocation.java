
package textExcel;

// Creates a SpreadsheetLocation that holds the row and column of a cell's location

public class SpreadsheetLocation implements Location
{
	private String cellName;
    public SpreadsheetLocation(String cellName)
    {
    		
        this.cellName = cellName.toUpperCase();
    }
	@Override
    public int getRow()
    {
		return Integer.parseInt(cellName.substring(1)) - 1;     
    }

    @Override
    public int getCol()
    {
    		return cellName.charAt(0) - 'A';
    }
}
