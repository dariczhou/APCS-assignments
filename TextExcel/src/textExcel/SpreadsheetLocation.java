package textExcel;

//Update this file with your own code.

public class SpreadsheetLocation implements Location
{
	private int row;
	private int column;
	//constructor
    public SpreadsheetLocation(String cellName)
    {
        // TODO: Fill this out with your own code
    	cellName = cellName.toUpperCase();
    	column = cellName.charAt(0);
    	row = Integer.parseInt(cellName.substring(1));
    }
    @Override // gets row of this location
    public int getRow()
    {
        // TODO Auto-generated method stub
        return row - 1;
    }

    @Override // gets column of this location
    public int getCol()
    {
        // TODO Auto-generated method stub
    	
        return column - 65;
    }
    

}
