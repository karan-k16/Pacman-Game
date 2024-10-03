import javax.swing.*;

// Controls the movement for mover objects
public class Mover extends JLabel {
	
	//Instance variables
	private int row;
	private int column;
	
	private int dRow;
	private int dColumn;
	
	private boolean isDead;
	
	//Constructor class
	public Mover(int row, int column) {
		super();
		this.row = row;
		this.column = column;
	}

	//Getter method: returns the row of the mover.
	public int getRow() {
		return row;
	}

	//Setter method: sets the row of the mover.
	public void setRow(int row) {
		this.row = row;
	}

	//Same as getRow method.
	public int getColumn() {
		return column;
	}

	//Same as setRow method.
	public void setColumn(int column) {
		this.column = column;
	}

	//Same as getRow method.
	public int getdRow() {
		return dRow;
	}

	//Same as setRow method.
	public void setdRow(int ddRow) {
		this.dRow = ddRow;
	}

	//Same as getRow method.
	public int getdColumn() {
		return dColumn;
	}

	//Same as setRow method.
	public void setdColumn(int dColumn) {
		this.dColumn = dColumn;
	}

	//Same as getRow method.
	public boolean isDead() {
		return isDead;
	}
	
	//Same as setRow method.
	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}
	
	//Utility methods:
	
	//Moves the mover
	public void move() {
		
		//increment the row/column
		row += dRow;
		column += dColumn;
	}
	
	//Sets the direction of the mover depending on direction
	public void setDirection(int direction) {
		dRow = 0;
		dColumn = 0;
		
		if (direction == 0)
			dColumn = -1;
		else if (direction == 2)
			dColumn = 1;
		else if (direction == 1)
			dRow = -1;
		else if (direction == 3)
			dRow = 1;
	}
	
	//Gets the direction of the mover
	public int getDirection() {
		if (dRow == 1)
			return 3;
		else if (dRow == -1)
			return 1;
		else if (dColumn == 1)
			return 2;
		else 
			return 0;
	}
	
	//Gets the next row
	public int getNextRow() {
		return row + dRow;
	}
	
	//Gets the next column
	public int getNextColumn() {
		return column + dColumn;
	}
	
	
	

}
