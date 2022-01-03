import java.awt.Container;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/*
 * Class:Main_Menu
 * Author: Swathi and Abirami 
 * Teacher: Mr.Anthony 
 * Date: January 12, 2020
 * Description: This displays the main menu along with the start and instruction button. If the user presses the start button it will take them to the game(Main Class). 
 * If the user presses the instruction button it will take them to the instructions screen(Instructions Class).
*/

public class Menu extends JFrame implements ActionListener{
	private JButton press_start, instructions;
	private Image front_screen_img;
	
	/*
	 * Constructor
	 * pre:none
	 * post:Creates a screen with the two button(Start and Instructions)
	 */
	public Menu(){
			//GET IMAGE OF MAINE MENI
			ImageIcon front_screen = new ImageIcon(getClass().getResource("front_screen.png"));
			front_screen_img  = front_screen.getImage();
			
			//CREATE, SET SIZE AND PLACE BUTTONS ON SCREEN 
			press_start = new JButton("START");
			instructions = new JButton("INSTRUCTIONS");
			
			press_start.setSize(260, 60);
			press_start.setLocation(195, 23);
			
			instructions.setSize(260, 60);
			instructions.setLocation(193, 293);
			
			press_start.addActionListener(this);
			instructions.addActionListener(this);
			
			Container c = getContentPane();
			c.setLayout(null);
	
			c.add(instructions);
			c.add(press_start);
			
			//CREATE A SCREEN 
			setTitle("Different Screen");
			setSize(700,500);
			setResizable(false);
			setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		 setVisible(true);
		 
	}	
	/* Method:paintComponent()
	 * Description:Draws main menu screen 
	 * pre:none
	 * post:Draws main menu screen 
	 */
	public void paint(Graphics g) {
		g.drawImage(front_screen_img,0,0,700,500,null);

	}
	
	/* Method: actionPerformedt()
	 * Description:Checks which button he user has pressed 
	 * pre:none
	 * post:If user presses instructions take them to the instructions class. If the user presses start take them to the main class 
	 */
	public void actionPerformed(ActionEvent e) {
		
	 setVisible(false);
	
			if (e.getSource() == press_start) {
			
				 Main m = new Main();
				 m.game.set_hit_obstacle(false);
			}
			else{
				
				Instructions instruc = new Instructions();
				
			}
			
		
		
	}
}
