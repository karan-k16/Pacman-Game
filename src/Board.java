import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

//This class creates the board. The board is made of a 2D array of JLabels that are cells. 
//This class also processes any logic for the game.
public class Board extends JPanel implements KeyListener, ActionListener {
	
	//Timers that control 
	public Timer gameTimer = new Timer(250, this);
	public Timer ghostTimer = new Timer(250, this);
	public Timer pacmanTimer = new Timer(250, this);
	public Timer abilityTimer = new Timer(2000, this);
	
	// Instance Variables 
	
	//A 2D array of cell objects
	private Cell[][] mazeArray = new Cell[25][27];
	
	private Mover pacman;
	
	//An array for 3 ghosts.
	private Mover[] ghostArray = new Mover[3];
	
	private int totalPellets = 0;
	
	private int pelletsEaten = 0;
	
	private int level = 0;
	
	private int lives = 3;
	
	private int score = 0;
	
	private int highScore = 0;
	
	private int map;
	
			
	//This method sets the board layout
	public Board() {
		
		setLayout(new GridLayout(25,27));
		setBackground(Color.BLACK);
		
		//Call the load board method
		loadBoard();
		
	}

	//This method will load the board frame with cells. 
	//This method also updates the board and processes any logic for the game.
	private void loadBoard() {
		
		//Increment the level
		level++;
		
		ghostTimer.start();
		pacmanTimer.start();
		
		// This sets the first row to 0
		int row = 0;
		
		// Initializes the scanner
		Scanner input;
		
		
		try {
			
			
			// Lets the scanner read the maze.txt file.
			input = new Scanner(new File("maze0.txt"));
			
			// Checks if the file has more inputs
			while (input.hasNext()) {
				
				// Converts row of texts into characters and places it into an array
				char[] lineArray = input.nextLine().toCharArray();
				
				//Loop through the number of columns in the maze.txt file.
				for (int column = 0; column < lineArray.length; column++) {
					
					//Create a 2D array with a cell object in each index.
					mazeArray[row][column] = new Cell(lineArray[column]);
					
					//Check if there are any 'C's (Cherry's) in that column of the text file.
					if (lineArray[column] == 'C') 
						
						//If there is, then add 100 to the total number of possible food in the game.
						totalPellets+=100;	
	
					
					
					//Check if there are any 'F's (Food) in that column of the text file.
					else if (lineArray[column] == 'F') 
						
						//If there is, then add 10 to the total number of possible food in the game.
						totalPellets+=10;	

					
					
					//Check if there are any 'P's (Pacman) in that column of the text file.
					else if (lineArray[column] == 'P') {
						
						//If there is, then create a new mover object called pacman at that index of the 2D array.
						pacman = new Mover(row, column);
						
						//Set the icons and direction for pacman.
						pacman.setIcon(Icons.PACMAN[1]);
						pacman.setDirection(1);
					}
					
					//Check if there are any numbers (Ghost) in that column of the text file.
					else if (lineArray[column] == '0' || 
							lineArray[column] == '1' || 
							lineArray[column] == '2' ) {
						
						// If there is, then create 3 new mover objects and place them in a mover array and at that index of the 2D array.
						int gNum = Character.getNumericValue(mazeArray[row][column].getItem());
						ghostArray[gNum] = new Mover(row, column);
						ghostArray[gNum].setIcon(Icons.GHOST[gNum]);
						
					}
					
								
					//Add that cell to the board
					add(mazeArray[row][column]);
						
				}
				
				//Increment the row
				row++;	
			
			}
				
			//Close the scanner
			input.close();
			
		//Inform if there's an exception error if reading the maze.txt file goes wrong.
		} catch (FileNotFoundException exception) {
			System.out.println(exception.getMessage());
		}
		
	}
	
	//This method performs any moves for both pacman and the ghosts.
	private void performMove(Mover mover) {
		
		//Sets the current position of the mover to a cell variable.
		//Sets the next position of the mover to a cell variable.
		
		Cell currentCell = mazeArray[mover.getRow()][mover.getColumn()];
		Cell nextCell = mazeArray[mover.getNextRow()][mover.getNextColumn()];
		
				//Handling doorways
				if (mover.getColumn() == 1) {
					
					//Teleport the mover to that column.
					mover.setColumn(25);
					mazeArray[12][1].setIcon(Icons.DOOR);
					
				}
				else if (mover.getColumn() == 25) {
					
					//Teleport the mover to that column.
					mover.setColumn(1);
					mazeArray[12][25].setIcon(Icons.DOOR);
				}
							
				
	
				//Make sure the mover doesn't collide into walls
				if (nextCell.getIcon() != Icons.WALL ) {
					
					//If the next cell isn't the gate, and the user is pacman, then allow mover to move.
					if (nextCell.getItem() != 'G' && mover == pacman)  { //In this case, if the mover is a ghost, and it's a gate, then it'll only allow the ghost to enter pass the gate.
						mover.move();
						
					}
					
							
					//Determine if's a ghost passing over any food item
					if ((mover != pacman && (currentCell.getItem() == 'F' || currentCell.getItem() == 'C')) || 
							(mover != pacman && (currentCell.getItem() == 'S' || currentCell.getItem() == 'T')) || 
							(mover != pacman && currentCell.getItem() == 'H')) {
						
						//Set the icon at that position back to the original food item if a ghost passes it.
						if (currentCell.getItem() == 'F')
							currentCell.setIcon(Icons.FOOD);
						
						else if (currentCell.getItem() == 'C')
							currentCell.setIcon(Icons.CHERRY);
						
						else if (currentCell.getItem() == 'S')
							currentCell.setIcon(Icons.SPEED);
						
						else if (currentCell.getItem() == 'T')
							currentCell.setIcon(Icons.FREEZE);
						
						else if (currentCell.getItem() == 'H')
							currentCell.setIcon(Icons.LIVES);
						
						
					}
					
					else {
						//Set the icon at that position to blank since pacman passed over it.
						currentCell.setIcon(Icons.BLANK);
					
					}
					
					//Reset the icon at that cell back to gate if a ghost passes over it.
					for (Mover ghost : ghostArray) {
						if (mover == ghost && (currentCell.getItem() == 'G')) 
							currentCell.setIcon(Icons.GATE);
						
					}
					
					
					//Move the ghost.
					if (mover!=pacman)
						mover.move();
					
					
					//Update the current cell
					currentCell = mazeArray[mover.getRow()][mover.getColumn()];
					
					//Check if pacman collided with a ghost
					if (collide()) {
						death();
					
					}
					//If not then update the current cell with the mover icon.
					else 
						currentCell.setIcon(mover.getIcon());
						
		
					//Handling the score
					if (mover == pacman && currentCell.getItem() == 'F' || mover == pacman && currentCell.getItem() == 'C'){
						
						//If it's a cherry add 100
						if(currentCell.getItem() == 'C') {
							pelletsEaten += 100;
							score+=100;
						}
						
						//If it's the normal pellet add 10
						else {
							pelletsEaten += 10;
							score+=10;
						}
						
						//Set that cell to empty
						currentCell.setItem('E');
					
						//If the score is equal to the total food, then inform user they've won.
						if(pelletsEaten == totalPellets) {
							
							//Set the high score to the score
							highScore = score;
							
							//Stop the game timer
							gameTimer.stop();
							pacmanTimer.stop();
							ghostTimer.stop();
							
							//Ask user if they want to play again.
							int answer = JOptionPane.showConfirmDialog(null, "Go to next level?", "You Won!", JOptionPane.YES_NO_OPTION);
							
							//If the answer is yes:
							if (answer == JOptionPane.YES_OPTION) {
								
								//Remove all the cells and GUI objects in this frame.
								removeAll();
								
								//Recalculate the layout of the frame
								revalidate();
								
								//Reset the stats
								pelletsEaten = 0;
								totalPellets = 0;
								
								//Reload the board
								loadBoard();
						
							}
							//If the answer is no:
							else {
								
								//Close the frame and reopen the menu frame
								setVisible(false);
								new PacmanMenu();
							}
							
						}	
									
					}
					
					//Handling the speed buff
					if (mover == pacman && currentCell.getItem() == 'S' ) {
						
						//Set the text at that cell to empty.
						currentCell.setItem('E');
						
						//Speed up pacman's moving timer
						pacmanTimer.setDelay(100);
		
						//Start the ability timer
						abilityTimer.start();
					}
					
					//Handling the freeze buff
					if (mover == pacman && currentCell.getItem() == 'T' ) {
						
						//Set the text at that cell to empty.
						currentCell.setItem('E');
						
						//Stop the ghost timer.
						ghostTimer.stop();
						
						//Start the ability timer
						abilityTimer.start();
					}
					
					//Handling the health buff
					if (mover == pacman && currentCell.getItem() == 'H' ) {
						
						//Set the text at that cell to empty.
						currentCell.setItem('E');
						
						//Increase number of lives by 1
						lives++;
						
					}
					
					//Helping ghost get out of the house
					
					//For each ghost
					for (Mover ghost : ghostArray) {
						
						//Checks if the current cell is either U (conveyer belt) or G (gate)
						if (currentCell.getItem() == 'U' || currentCell.getItem() == 'G') {
							
							//Set the direction to up
							ghost.setDirection(1);
							
							//Move ghost
							performMove(ghost);
						}
						
					}
					
					
				}
			
			}
		

	//This method tests for collisions between pacman and the ghost.
	private boolean collide() {
		
		//Loops for each of the ghosts
		for (Mover ghost : ghostArray) {
			
			//If pacman and the ghost's position is the same, then return true.
			if (ghost.getRow() == pacman.getRow() && ghost.getColumn() == pacman.getColumn()) {
				return true;
			}
				
		}
		//If it isn't, then return false.
			return false;
		
	}

	//This method controls what happens when pacman dies.
	private void death() {
		
		//Decrement the lives
		lives--;

		
		//If lives is 0 then: 
		//Stop the game and update high score if necessary
		//Inform user they've lost with a JOptionPane
		//Ask user if they want to play again.
		//If yes, then refresh the board, score, and lives, if not, then return to the main menu.
		if (lives == 0) {
		
			//Stop the game timer
			gameTimer.stop();
			pacmanTimer.stop();
			ghostTimer.stop();
			
			//Check if the curremt score is higher than the high score, if it is, then replace it.
			if (score > highScore) 
				highScore = score;
			
			//Set pacman to dead
			pacman.setDead(true);
			
			//Ask user if they want to try again.
			int answer = JOptionPane.showConfirmDialog(null, "Try Again?", "You Lost!", JOptionPane.YES_NO_OPTION);
			
			//If the answer is yes then:
			if (answer == JOptionPane.YES_OPTION) {
				
				//Remove all the cells and GUI objects in this frame.
				removeAll();
				
				//Recalculate the layout of the frame
				revalidate();
				
				//Reset the stats
				score = 0;
				lives = 3;
				totalPellets = 0;
				pelletsEaten = 0;
				
				//Reload the board
				loadBoard();
		
			}
			else {
				
				//Close the frame and reopen the menu frame
				setVisible(false);
				new PacmanMenu();
			}
	
		}
		
		//If pacman still has lives left, then teleport him and the ghosts back to their original positions.
		else {
			
			//Set pacman's previous position icon to blank.
			//teleport pacman
			mazeArray[pacman.getRow()][pacman.getColumn()].setIcon(Icons.BLANK);
			pacman.setDirection(1);
			pacman.setIcon(Icons.PACMAN[1]);
			
			//Different for each map
			if (map == 0){
				pacman.setColumn(13);
				pacman.setRow(15);
			}
			else if (map == 1) {
				pacman.setColumn(13);
				pacman.setRow(8);
			}
			else if (map == 2) {
				pacman.setColumn(22);
				pacman.setRow(13);
			}
			
			//Set the ghost's previous position icon to blank.
			//teleport the ghost
			for(Mover ghost: ghostArray) {
				mazeArray[ghost.getRow()][ghost.getColumn()].setIcon(Icons.BLANK);
			
				if (map == 0){
					ghost.setColumn(12);
					ghost.setRow(13);
				}
				else if (map == 1) {
					ghost.setColumn(24);
					ghost.setRow(22);
				}
				else if (map == 2) {
					ghost.setColumn(7);
					ghost.setRow(8);
				}
				
			}
			
		}
		
	}
	

	//This method detects for any timer events.
	@Override
	public void actionPerformed(ActionEvent event) { 

	        //Check if the pacmanTimer goes off
	        if(event.getSource() == pacmanTimer)
	        	
	            //Move Pacman
	            performMove(pacman);
	  
	        //Check if the ghostTimer goes off
	        if (event.getSource() == ghostTimer)
	        	
	        	//Move ghosts
	            moveGhosts();
	        
	      //Check if the abilityTimer goes off
	        if (event.getSource() == abilityTimer) {
	        	
	        	//Set pacmanTimer back to 250. (because of the speed buff)
	        	pacmanTimer.setDelay(250);
	        	
	        	//Start the ghost timer. (because of the freeze buff)
	        	ghostTimer.start();
	        	
	        	//Stop the ability timer. 
	        	abilityTimer.stop();
	        }
	        
	


	    }
		
	//This method performs a move for all the ghosts.
	private void moveGhosts() {
		
		//Loops for all of the ghosts
		for (Mover ghost: ghostArray) {

			//This variable controls the direction the ghost move in.
            int direction;
            
            if (ghost.getColumn() == 25) {
            	direction = 0;
            	continue;
            } else if (ghost.getColumn() == 1) {
            	direction = 2;
            	continue;
            } else

            //If the ghost is in the same column:
            if (ghost.getColumn() == pacman.getColumn()) {
            	
            	//If the ghost is above pacman
            	if (ghost.getRow() < pacman.getRow()) 
            		direction = 3;
            	else
            		direction = 1;
            		
            }
            //If the ghost is in the same row:
            else if (ghost.getRow() == pacman.getRow()) {
            	
            	//If the ghost is to the left of pacman
            	if (ghost.getColumn() < pacman.getColumn()) 

            		direction = 2;
            	else

            		direction = 0;
            }
            //If the ghost is neither in the same row or column, then make it move randomly.
            else {
            
            	do {
            		
            		//Get a random number from 0-3 and set it to direction
            		direction = (int)(Math.random() * 4);

            		//Check if the direction is not the opposite of the current direction.
            	} while (Math.abs(ghost.getDirection() - direction) == 2);
            }
            
            //Set the direction of the ghost
            ghost.setDirection(direction);

            //If pacman is not dead, then continue perform move for ghosts.
            if (!pacman.isDead())
                performMove(ghost);

        }
		
	}

	//This method doesn't have anything, but is required for the key listener.
	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	//This method controls pacman's movement based on the keys pressed for the arrow keys. 
	@Override
	public void keyPressed(KeyEvent key) {
		
		//Checks if the game is running
		if (gameTimer.isRunning() == false && pacman.isDead() == false)

			//Start the game timer
            gameTimer.start();

		//Will continue while the score doesn't equal to the pellets
        else if (pacman.isDead() == false && pelletsEaten != totalPellets) {

            int direction = key.getKeyCode() - 37; //based on the ASCII code for arrows

            //Checks if the move is valid (pacman won't move into a wall).
            if ((direction == 0 && mazeArray[pacman.getRow()][pacman.getColumn() - 1].getIcon() != Icons.WALL)
                    || (direction == 1 && mazeArray[pacman.getRow() - 1][pacman.getColumn()].getIcon() != Icons.WALL)
                    || (direction == 2 && mazeArray[pacman.getRow()][pacman.getColumn() + 1].getIcon() != Icons.WALL)
                    || (direction == 3 && mazeArray[pacman.getRow() + 1][pacman.getColumn()].getIcon() != Icons.WALL)) {

            	//Update pacman's icon based on the direction
                pacman.setIcon(Icons.PACMAN[direction]);
                
                //Set the direction for pacman.
                pacman.setDirection(direction);
            }
        }
		
	}

	//This method doesn't have anything, but is required for the key listener.
	@Override
	public void keyReleased(KeyEvent event) {
		
	}
	
	//This method returns the score to the PacmanGUI class.
	public int getScore() {
		return score;
	}
	
	//This method returns the lives to the PacmanGUI class.
	public int getLives() {
		return lives;
	}
	
	//This method returns the high score to the PacmanGUI class.
	public int getHighScore() {
		return highScore;
	}
	
	public int getLevel() {
		return level;
	}
	

}
