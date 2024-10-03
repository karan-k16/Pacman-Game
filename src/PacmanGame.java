/*
Title: PacMan Game Assignment
Description: This program runs the PacMan game with a couple of added features to enhance the performance of the basic PacMan game. 
			 The user can control the PacMan character by using the arrow keys.

Major Skills: 
Using multiple classes together, reading keyboard presses, reading text and image files, and using Java Swing GUI

Added Features:
1. Score
2. High score
3. Ghost A.I - Ghosts run away from PacMan if a certain power-up is used, otherwise they go toward Pacman
4. Different maps and levels
5. eBonus items (cherry and buffs)
6. Gate for house
7. Helping ghosts leave house
8. Main menu
9. Help frame
10. Lives, Power pellet (ability to eat ghosts)

 */

//This class runs the PacMan game
public class PacmanGame {
	
	public static void main(String[] args) {
			
		//Create the menu frame
		new PacmanMenu();
	}

}
