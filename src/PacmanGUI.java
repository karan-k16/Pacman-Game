import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

//This class runs the Pacman GUI frame.
public class PacmanGUI extends JFrame implements ActionListener {
	
	//Declares a new board object
	private Board board = new Board();
	
	//Panel that contains the board
	private JPanel gamePanel = new JPanel();
	
	//Panels that contain the JLabels for the current score, lives, and the high score.
	private JPanel pointsPanel = new JPanel();
	private JPanel livesPanel = new JPanel();
	private JPanel highScorePanel = new JPanel();
	private JPanel levelPanel = new JPanel();
	
	//JLabel for the current score
	private JLabel pointsTitle = new JLabel("Current Score: ");
	private JLabel pointsLabel = new JLabel();
	
	//JLabel for the current lives
	private JLabel livesTitle = new JLabel("Lives: ");
	private JLabel livesLabel = new JLabel();
	
	//JLabel for the high  score
	private JLabel highTitle = new JLabel("High Score: ");
	private JLabel highLabel = new JLabel();
	
	//JLabel for the current level
	private JLabel levelTitle = new JLabel("Level: ");
	private JLabel levelLabel = new JLabel();
	
	//JButton for exiting this frame
	private JButton exit = new JButton("Leave");
	
	//JLabel for pacman logo
	private JLabel pacmanPicture = new JLabel(new ImageIcon("images/pacman_logo.png"));
	
	//Timer that controls how frequently to update the score and lives.
	private Timer update = new Timer(10, this);
	
	//Border for the panels
	private Color yellow = new Color(233, 255, 23);
	private Border border = BorderFactory.createLineBorder(yellow, 8);

	public PacmanGUI() {
			
		//Set the layout manually.
		setLayout(null);
		
		//Start the update JLabel timer.
		update.start();
	
		//Set title of the frame
		setTitle("Karans' Pacman Game");
				
		//Closes the frame when the project closes
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Set the background of the frame
		setContentPane(new JLabel(new ImageIcon("images/space.jpg")));
		
		//Set the bounds for the GUI objects
		pacmanPicture.setBounds(0,0,600,130);
		gamePanel.setBounds(0, 200, 600, 580);
		pointsPanel.setBounds(400, 120, 180, 80);
		livesPanel.setBounds(5,120,180,80);
		highScorePanel.setBounds(200,120,180,80);
		levelPanel.setBounds(5,780,180,80);
		exit.setBounds(30,800,100,100);
	
		//Add the board panel to the frame and listen to any keyboard inputs on the board.		
		gamePanel.add(board);
		addKeyListener(board);
		
		//Setting how each of the panels look.
		pointsPanel.setBackground(new Color(252, 255, 149));
		pointsPanel.setBorder(border);
		pointsPanel.add(pointsTitle);
		pointsPanel.add(pointsLabel);
		pointsLabel.setFont(new Font("Arial", Font.BOLD, 40));
		pointsPanel.setLayout(new BoxLayout(pointsPanel, BoxLayout.Y_AXIS));
		
		livesPanel.setBackground(new Color(252, 255, 149));
		livesPanel.setBorder(border);
		livesPanel.add(livesTitle);
		livesPanel.add(livesLabel);
		livesLabel.setFont(new Font("Arial", Font.BOLD, 40));
		livesPanel.setLayout(new BoxLayout(livesPanel, BoxLayout.Y_AXIS));
		
		highScorePanel.setBackground(new Color(252, 255, 149));
		highScorePanel.setBorder(border);
		highScorePanel.add(highTitle);
		highScorePanel.add(highLabel);
		highLabel.setFont(new Font("Arial", Font.BOLD, 40));
		highScorePanel.setLayout(new BoxLayout(highScorePanel, BoxLayout.Y_AXIS));
		
		levelPanel.setBackground(new Color(252, 255, 149));
		levelPanel.setBorder(border);
		levelPanel.add(levelTitle);
		levelPanel.add(levelLabel);
		levelLabel.setFont(new Font("Arial", Font.BOLD, 40));
		levelPanel.setLayout(new BoxLayout(levelPanel, BoxLayout.Y_AXIS));
		
		
		//Setting how the exit button looks.
		exit.setBackground(new Color(252, 255, 149));
		exit.setFont(new Font("Arial", Font.BOLD, 20));
		exit.setBorder(border);
		exit.addActionListener(this);
		
		//Make the game panel opaque (doesn't have a background). 
		gamePanel.setOpaque(false);

		//Add the GUI objects to the frame.
		add(gamePanel);
		add(pacmanPicture);
		add(pointsPanel);
		add(livesPanel);
		add(levelPanel);
		add(highScorePanel);
		
		//ATTEMPTED: When adding the exit button to the frame, you're unable to move pacman.
		//add(exit);
				
		//Set the frame visibility to true.
		setVisible(true);
		
		//Set size for the frame
		setSize(600, 1000);
		
		
		
		
	}

	//This method controls what happens if any buttons are pressed + updates the labels.
	@Override
	public void actionPerformed(ActionEvent event) {
		
		//If the update timer is detected, then update the points, lives, and high score
        if(event.getSource() == update) {
        	pointsLabel.setText(""+board.getScore());
        	livesLabel.setText(""+board.getLives());
        	highLabel.setText(""+board.getHighScore());  
        	levelLabel.setText(""+board.getLevel());
          
        }
        
        //If the exit button is pressed then close the frame and reopen the menu frame.
        if(event.getSource() == exit) {
        	setVisible(false);
        	new PacmanMenu();
        }


		
	}


}