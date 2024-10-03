import javax.swing.*;

// The class that creates cell objects
public class Cell extends JLabel {
	
	//Instance variables
	private char item;
	
	//Constructor class 
	public Cell (char item) {
		
		super();
		this.item = item;
		
		//Call the setCodeIcon method
		setCodeIcon();
		
	}
	
	//Getter method - Returns the item
	public char getItem() {
		return item;
	}

	//Setter method - Sets the item
	public void setItem(char item) {
		this.item = item;
	}
	

	// This sets the item to an icon
	private void setCodeIcon() {
		if (item == 'P') 
			setIcon(Icons.PACMAN[0]);
		else if (item == '0') 
			setIcon(Icons.GHOST[0]);
		else if (item == '1') 
			setIcon(Icons.GHOST[1]);
		else if (item == '2') 
			setIcon(Icons.GHOST[2]);
		else if (item == 'W') 
			setIcon(Icons.WALL);
		else if (item == 'F') 
			setIcon(Icons.FOOD);
		else if (item == 'D') 
			setIcon(Icons.DOOR);
		else if (item == 'C')
			setIcon(Icons.CHERRY);
		else if (item == 'G')
			setIcon(Icons.GATE);
		else if (item == 'S')
			setIcon(Icons.SPEED);
		else if (item == 'T')
			setIcon(Icons.FREEZE);
		else if (item == 'H')
			setIcon(Icons.LIVES);
		
		
	}
	

}
