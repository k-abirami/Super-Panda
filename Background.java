import java.awt.*;
import javax.swing.*;


/*
Class: Background Class 
Author: Swathi and Abirami 
Teacher: Mr.Anthony 
Date: January 12, 2020
Description:This draws the game background and clouds. It controls the movement of the clouds.
*/
public class Background extends JFrame implements Runnable{

	private int xDirection,x_clouds;
	private int timer = 0, timer_in_sec = 300;
	
	
	private Image game_back, game_clouds, win_back_img;
	private Font font ;
	
	
	
	/*
	 * Constructor
	 * pre:none
	 * post: Displays the background of the game screen 
	 */
	public Background() {
		//SETS THE FONT 
		font = new Font("Calibri", Font.BOLD, 30);
		
		// GETS THE BACK IMAGE AND THE CLOUD IMAGE 
		ImageIcon back = new ImageIcon(getClass().getResource("game_back_without_clouds.png"));
		game_back = back.getImage();
		
		ImageIcon clouds = new ImageIcon(getClass().getResource("Clouds.png"));
		game_clouds = clouds.getImage();
	
		//SET THE X COORDINATE OF THE CLOUDS 
		x_clouds = 0;
		
		
	}
	
	/* Method:setXDirect()
	 * Description:set the direction of the x coordinates 
	 * pre:none
	 * post:Will set xDirection to the argument.
	 */
	public void setXDirect(int x_Direct) {
		xDirection = x_Direct;
	}
	
	/* Method:move()
	 * Description:Controls the movement of the screen 
	 * pre:none
	 * post: Displays and move the bamboo to a quarter if the screen 
	 */
	public void move_screen() {
	
		x_clouds += xDirection;
	}
	
	/* Method:get_timer()
	 * Description:Get the current time
	 * pre:none
	 * post: Returns the the variable timer_in_sec
	 */
	public int get_timer() {
		return timer_in_sec;
	}
	
	/* Method:paintComponent()
	 * Description:Draws background 
	 * pre:none
	 * post:Draws game background and game clouds. 
	 */
	public void paintComponent(Graphics g) {
		//Draw the bottom half of the game 
		g.drawImage(game_back, 0, 60, 700, 500,null);
	
		//DRAW THE CLOUDS 
		g.drawImage(game_clouds, x_clouds, 0, 700, 150,null);
		
		//IF THE X_COORDINATE OF THE CLOUD IMAGE IS LESS THAN 0 LOAD IN THE NEXT IMAGE 
		if (x_clouds < 0) {
			g.drawImage(game_clouds, x_clouds+700, 0, 700, 150,null);
			//IF THAT X_COORDINATE IMAGE IS LESS THAN -700, REDRAW IMAGE AT 0 X COORDINATE
			if (x_clouds < -700) {
				x_clouds = 0;
				//DRAW CLOUDS 
				g.drawImage(game_clouds, x_clouds, 0, 700, 150,null);
			}
		}
	
		//TIMER
		//CONVERTING FROM LONG MILLISECONDS TO REGULAR SECONDS
		 timer_in_sec = (timer/1000);
		
		//SETTING FONT
		g.setFont(font);
		//DISPLAYING TIMER
		g.drawString("TIMER: "+ (300-timer_in_sec)+ " seconds", 15, 70);
		timer++;
	}
	
	
	/* Method:run()
	 * Description:Calls the move_screen method and sets the path of execution for the background class  
	 * pre:none
	 * post: Calls the move_screen method  
	 */
	public void run() {
		try {
			while(true) {
				move_screen();
				Thread.sleep(5);
			}
		}
	catch(Exception e) {
		System.out.println("ERROR");
	}
	
	}
}

