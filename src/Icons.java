import javax.swing.ImageIcon;

//This class creates the icons for the board
public class Icons {

	//Creates constants for the item icons.
	public static final ImageIcon WALL = new ImageIcon("images/Wall.bmp");
	public static final ImageIcon FOOD = new ImageIcon("images/Food.bmp");
	public static final ImageIcon BLANK = new ImageIcon("images/Black.bmp");
	public static final ImageIcon DOOR = new ImageIcon("images/Black.bmp");
	public static final ImageIcon SKULL = new ImageIcon("images/Skull.bmp");
	public static final ImageIcon CHERRY = new ImageIcon("images/Cherry.bmp");
	public static final ImageIcon GATE = new ImageIcon("images/door.png");
	public static final ImageIcon SPEED = new ImageIcon("images/speedBuff.png");
	public static final ImageIcon FREEZE = new ImageIcon("images/freezeBuff.png");
	public static final ImageIcon LIVES = new ImageIcon("images/heartBuff.png");
	
	// These are PacMan state icons, creates constants for these icons
	public static final ImageIcon[] PACMAN = { 
			new ImageIcon("images/Pacman0.gif"), 
			new ImageIcon("images/Pacman1.gif"),
			new ImageIcon("images/Pacman2.gif"), 
			new ImageIcon("images/Pacman3.gif"), 
		};
	
	//Creates constants for the ghost icons.
	public static final ImageIcon[] GHOST = { 
			new ImageIcon("images/Ghost0.bmp"), 
			new ImageIcon("images/Ghost1.bmp"),
			new ImageIcon("images/Ghost2.bmp"),  
		};
	
}