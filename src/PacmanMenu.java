import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

//This class runs the pacman menu frame.
public class PacmanMenu extends JFrame implements ActionListener {
	
	//Images for the pacman
	private JLabel pacmanTitle = new JLabel(new ImageIcon("images/pacman_logo.png"));
	private JLabel pacmanPicture = new JLabel(new ImageIcon("images/pacmanImage.png"));
	
	//ATTEMPTED: sound effects
	//private Sound menu = new Sound();
	
	//Buttons to navigate the menu
	private JButton play = new JButton("Play");
	private JButton help = new JButton("Help");
	private JButton exit = new JButton("Exit");
	
	//Colours and border for buttons
	private Color innerYellow = new Color(252, 255, 149);
	private Border border = BorderFactory.createLineBorder(new Color(233, 255, 23), 8);
	

	public PacmanMenu() {
		
		//Set size for the frame
		setSize(600, 1000);

		//Set background for the frame
		setContentPane(new JLabel(new ImageIcon("images/space.jpg")));
		
		//Set the bounds for the pacman title and image.
		pacmanTitle.setBounds(0,0,600,130);
		pacmanPicture.setBounds(0, 100, 600, 300);
		
		//Set the bounds and appearance for the play button.
		play.setFont(new Font("Arial", Font.BOLD, 40));
		play.setBounds(180, 450, 250,100);
		play.setBackground(innerYellow);
		play.setBorder(border);
		
		//Set the bounds and appearance for the help button.
		help.setFont(new Font("Arial", Font.BOLD, 40));
		help.setBounds(180, 600, 250,100);
		help.setBackground(innerYellow);
		help.setBorder(border);
		
		//Set the bounds and appearance for the exit button.
		exit.setFont(new Font("Arial", Font.BOLD, 40));
		exit.setBounds(180, 750, 250,100);
		exit.setBackground(innerYellow);
		exit.setBorder(border);
		
		//Add GUI objects to the frame along with action listeners.
		add(pacmanTitle);
		add(pacmanPicture);
		
		add(play);
		play.addActionListener(this);
		add(help);
		help.addActionListener(this);
		add(exit);
		exit.addActionListener(this);

		//Set the frame visibility to true.
		setVisible(true);
	
		//ATTEMPTED: sound effects
		//menu.getClip();
	
	}

	//This method checks for any action events.
	@Override
	public void actionPerformed(ActionEvent event) {
		
		//If the play button is pressed
		if (event.getSource() == play) {
			
			//Close the frame and open the board/actual game frame.
			setVisible(false);
			new PacmanGUI();
		}
		
		//If the help button is pressed
		else if (event.getSource() == help) {
			
			//Close the frame and open the help frame.
			setVisible(false);
			new PacmanHelp();
		}
		
		//If the exit button is pressed
		else if (event.getSource() == exit)
			
			//Close the frame.
			setVisible(false);
		
	}	


}
