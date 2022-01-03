import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/*
 * Class:Game Over
 * Author: Swathi and Abirami
 * Teacher: Mr.Anthony 
 * Date: January 12, 2020
 * Course: ICS4U
 * Description: This displays the game over screen 
 */

public class Game_Over extends JFrame implements ActionListener{
	private JButton restart;
	private Image game_over_screen_img;
	
	/*
	 * Constructor
	 * pre:The user must intersect with an obstacle
	 * post: Displays the Game Over screen 
	 */
	public Game_Over() {
		//GETS THE GAME OVER IMAGE 
	ImageIcon game_over_screen= new ImageIcon(getClass().getResource("game_over_screen.jpg"));
	game_over_screen_img  = game_over_screen.getImage();
	//SET THE SCREEN SIZE 
	setSize(700,500);
	//DO NOT ALLOW THE USER TO TO REZ=SIZDE THE SCREEN 
	setResizable(false);
	setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	setVisible(true);
	restart = new JButton("RESTART");
	restart.setSize(200, 50);
	restart.setLocation(250, 250);
	restart.addActionListener(this);
	Container c = getContentPane();
	c.setLayout(null);
	c.add(restart);
	
	}
	
	/* Method:paint()
	 * Description:Draws game over image 
	 * pre:none 
	 * post:Draws a game over image 
	 */
public void paint(Graphics g) {
	g.drawImage(game_over_screen_img, 0, 0,700,500,null);
}


public void actionPerformed(ActionEvent e) {
	setVisible(false);
	Menu m = new Menu();
	
}

}