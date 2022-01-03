import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;


/*
 * Class:Instructions
 * Author: Swathi and Abirami 
 * Teacher: Mr.Anthony 
 * Date: January 12, 2020
 * Description: This displays the instructions and backstory of the game to the user. Along with an exit button and an return to main menu button. If the user presses the exit button the game will end. If the user 
 * presses the return to main menu button it will return th user to the main menu(main menu class)
*/

public class Instructions extends JFrame implements ActionListener{
	private JButton exit;
	private JButton return_menu;
	private JLabel title ;
	private JLabel intro ;
	private JLabel story;
	private JLabel warnings;
	private JLabel time ;
	private JLabel power_ups;
	private JLabel power_up_type1 ;
	private JLabel power_up_type2;
	private JLabel power_up_type_3;
	private JLabel coins;
	private JLabel to_move;
	private JLabel go_up_instruct;
	private JLabel go_right_instruct;
	
	
	/*
	 * Constructor
	 * pre:none
	 * post:Create a screen and create and place all the labels onto the screen 
	 */
	public Instructions() {
		
		exit = new JButton("EXIT GAME");
		return_menu = new JButton("RETURN TO MAIN MENU");
		title = new JLabel("=======INSTRUCTIONS==========================================================================================");
		intro = new JLabel(" Welcome Player to SUPER PANDA!");
		story = new JLabel(" Oreo the Panda had slept walked and unfortunatley got lost from his home. Can you help bring him back safe and sound? ");
		warnings = new JLabel (" You must be careful of the traps laid in the path as if Name ends up in a trap he will die so to avoid the trap you must jump and move forward above it");
		time = new JLabel (" You also must keep in mind time. If Name does not get home in time he/she will also get in trouble with her/his mother");
		power_ups = new JLabel(" Along with the traps there are power ups(a box with a question mark on it) that will mainly help get Name home faster.\nAll you must is just hit the powerup at the bottom by jumping");
		power_up_type1 = new JLabel("One type of powerup in the game is mushrooms. This allows Oreo to grow bigger and pass through an obstacle once without loosing his life.");
		power_up_type2 = new JLabel(" Another type of powerup in the game is star. This allows Oreo to grow bigger and become colourful and pass through any obstacle for 5 seconds.");
		power_up_type_3 = new JLabel("The last powerup in the game is earning additional coins. Oreo will get 1000 coins if he gets this powerup");
		coins = new JLabel(" While Oero was sleepwalking coins in his pocket fell out. Try to collect the coins on his way back home");
		to_move = new JLabel(" To move Oreo you can use all of the mentioned keys below");
		go_up_instruct = new JLabel(" 1. PRESS THE UP ARROW KEY TO JUMP ");
		go_right_instruct = new JLabel(" 2. PRESS THE RIGHT ARROW KEY TO MOVE FORWARD");
		
		setTitle("INSTRUCTIONS"); 
		setSize(1200,500); 
		setResizable(false); 
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); 
		
		Container c = getContentPane();
		c.setLayout(new GridLayout(16,1));
		c.add(title);
		c.add(intro);
		c.add(story);
		c.add(coins);
		c.add(time);
		c.add(warnings);
		c.add(power_ups);
		c.add(power_up_type1);
		c.add(power_up_type2);
		c.add(power_up_type_3);
	
		c.add(to_move);
		
		c.add(go_up_instruct);
		c.add(go_right_instruct);
		c.add(exit);
		exit.addActionListener(this);
		
		c.add(return_menu);
		return_menu.addActionListener(this);
		setVisible(true);
		
	}
	
	/* Method: actionPerformedt()
	 * Description:Checks which button the user has pressed 
	 * pre:none
	 * post:If user presses return to main menu take them to the Main_Menu class.If user presses exit game , exit from game
	 */
	public void actionPerformed(ActionEvent e) {
		 setVisible(false);
			if (e.getSource() == return_menu) {

				Menu menu_start = new Menu();
			}
		
		
		
		
		
	}
}

