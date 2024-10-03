import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

//This class runs the help frame. 
public class PacmanHelp extends JFrame implements ActionListener {
	
	//GUI Objects:
	
	//JButton for closing frame
	private JButton back = new JButton("Back");
	
	// Creates border for button
	Border border = BorderFactory.createLineBorder(new Color(233, 255, 23), 8);
	
	public PacmanHelp() {
		
		//Set size for the frame
		setSize(600, 1000);

		//Set background for the frame
		setContentPane(new JLabel(new ImageIcon("images/help.png")));
		
		//Customize and set the bounds for the back button
		back.setBounds(450,50,100,100);
		back.setBackground(new Color(252, 255, 149));
		back.setBorder(border);
		back.addActionListener(this);

		//Add the back button to the frame
		add(back);
		
		//Set the frame visibility to true
		setVisible(true);
		
	}

	//This method checks for an action events.
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//If the back button is clicked:
		if (e.getSource() == back) {
			
			//Set the frame to invisible
			setVisible(false);
			
			//Open the pacman menu frame.
			new PacmanMenu();
		}
		
	}
	
	

}
