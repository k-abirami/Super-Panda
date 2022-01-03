import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

/*
Class:Power_Up Class 
Author: Swathi and Abirami 
Teacher: Mr.Anthony 
Course: ICS4U
Date: January 12, 2020
Description:This controls the movement of the panda. It creates an instance of Background class, Obstacles class, Ground_in_Sky class, 
Power_up class, Coins class. Checks if panda collides with any object. If the panda collides with the Obstacle object it will call 
the game_over class. If the panda collides with the ground in sky object the panda will walk on the block and will be unable to pass 
through the block 
*/
public class Power_Up implements Runnable{
	private Image power_up_img;
	private int xDirection, yDirection, x_powerup , y_powerup, array_num,x_mushroom ,y_mushroom, x_coor_block,x_obs_coor, direction , counter , x_star,y_star;
	boolean []if_hit = {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false};

	private Image mushroom_img,blank_block_img, star_img;
	private boolean mushroom_appear,star_appear, coin_appear;


/*
 * Constructor
 * pre:none
 * post:GETS ALL THE IMAGES AND SETS ALL THE POWERUPS TO FALSE AS THEY HAVE NOT BEEN ACTIVATED YET. SET TIMER AND SET PANDA'S 
 * BEGINNING LOCATION 
 */
	public Power_Up() {
		//GET THE POWERUP IMAGES 
		ImageIcon power_up = new ImageIcon(getClass().getResource("power_up_block.jpg"));
		ImageIcon mushroom = new ImageIcon(getClass().getResource("mushroom.jpg"));
		ImageIcon blank_block = new ImageIcon(getClass().getResource("blank_block.jpg"));
		ImageIcon star = new ImageIcon(getClass().getResource("star.png"));
		star_img = star.getImage();
		power_up_img = power_up.getImage();
		mushroom_img = mushroom.getImage();
		blank_block_img = blank_block.getImage();
		
		x_powerup = 700;
		
		counter = 0;
		direction = 1;
	
		array_num = 0;
		
		//SET ALL POWER UP VARIABLES TO FALSE
		mushroom_appear = false;
		star_appear = false;
		coin_appear = false;
	}
	
	/*
	 * Name:setXDirect()
	 * Description: Set xDirection variable 
	 * pre:none 
	 * post: makes xDirection equal to the argument x_Direct 
	*/

	public void setXDirect(int x_Direct) {
		xDirection = x_Direct;
	}
	
	/*
	 * Name:setYDirect()
	 * Description: Set xDirection variable 
	 * pre:none 
	 * post: makes yDirection equal to the argument y_Direct 
	*/
	public void setYDirect(int y_Direct) {
		yDirection = y_Direct;
	}
	/*
	 * Name:set_Object_Appear()
	 * Description: set if the powerup has been intersected with 
	 * pre:none 
	 * post: if argument == 1, set mushroom_appear to the boolean argument, If argument == 2, set star_appear to the Boolean argument 
	 * and if else set coin_appear to the Boolean argument 
	 * */

	public void set_Object_Appear(boolean if_intersect, int which_object) {
		//If the mushroom intersect with panda
		if(which_object == 1) {
			mushroom_appear = if_intersect;
		}
		else if(which_object == 2) {
			star_appear = if_intersect;
		}
		else
			coin_appear = if_intersect;
		}
	/*
	 * Name:get_array_num()
	 * Descriptions: Get the array num
	 * pre:none 
	 * post:returns the array num
	*/

	public int get_array_num() {
		//If the mushroom intersect with panda
		return array_num;
	}
	
	/*
	Name:set_blank_block()
	Description: set element in array depending on if block was hit or not 
	pre:none 
	post:set a section of the array equal to the Boolean argument
	 */
	public void set_blank_block(boolean b) {
		if_hit[array_num] = b;
	}
	
	/*
	Name:set_power_up_y_coor()
	Description: get and give y value to power up y ;
	pre:none 
	post: Give the argument value to the variable y_powerup
	 */
	public void set_power_up_y_coor(int y) {
		y_powerup = y;
	}
	
	/*
	 * Name:get_blank_block()
	 * Description: Get if the current element in the arra is true or false 
	 * pre:none 
	 * post:returns the currrent element in the array
	  */
	public boolean get_blank_block() {
		//System.out.println("if"+ if_hit[array_num]);
		return if_hit[array_num];
	}
	
	/*
	 * Name:set_Object_Fall_Off()
	 * Description:Sets the x coordinate 
	 * pre:none 
	 * post:Sets the x coordinate of the given powerup
	  */

	public void set_Object_Fall_Off(int x,int which_object) {
		x_coor_block = x;
		if(which_object == 1) {
			x_mushroom = x;
		}
		if(which_object ==2) {
			x_star = x;
		}
		}
	
	
	/*
	 * Name: set_Obstacle_coor()
	 * Description:Set the obstacle coordinate 
	 * pre:none 
	 * post:Set the argument to the x obstacle coordinate variable 
	  */
	public void set_Obstacle_coor(int x) {
		x_obs_coor = x;
		}
	
	/*
	 * Name: set_power_up_x_coor()
	 * Description:Set the x coordinate 
	 * pre:none 
	 * post:Set the argument to the x coordinate of the powerup block 
	  */
	public void set_power_up_x_coor(int x) {
		x_powerup =x;
	}
	
	/*
	 * Name: get_x_Object()
	 * Description:get the x coordinate of the given powerup 
	 * pre:none 
	 * post:set the argument to the given pwoerup's x coordinate 
	  */
	public int get_x_Object(int which_object) {
		if(which_object == 1) {
			return x_mushroom;
		}
		else if (which_object==2) {
			return x_star;
		}
		else
			return x_star;
		}
	

	

	public int get_y_Object(int which_object) {
		if(which_object == 1) {
			return y_mushroom;
		}
		else{
			return y_star;
		}
	
		}
	public void set_array_num(int x) {
		array_num = x;
	}
	public void move_power_ups() {
	
		//If mushroom appears on screen make it move
		if (mushroom_appear == true) {
			x_mushroom = x_mushroom-direction;
	
			//If it hits an object make it go the other direction
			if (x_obs_coor+50 == x_mushroom ) {
	
				direction = direction*-1;
				counter ++;
	
			}
			// If the mushroom goes of block fall onto ground
			if(x_coor_block>=x_mushroom+70) {
				y_mushroom = 325;
	
	
			}
			//Makes the mushroom start off on top of the block
			else if(counter == 0) {
				y_mushroom = y_powerup- 50;
			}
		}
		else if(star_appear == true) {
			x_star= x_star-direction;
			//If it hits an object make it go the other direction
			if (x_obs_coor+50 == x_star ) {
				direction = direction*-1;
				counter ++;
	
			}
			// If the mushroom goes of block fall onto ground
			if(x_coor_block>=x_star+100) {
				y_star = 325;
			}
			//Makes the mushroom start off on top of the block
			else if(counter == 0) {
	
				y_star = y_powerup - 50;
			}
		}
	
		//Make the powerup block move acroos the screen
		x_powerup += xDirection;
	
	
		}
	
	
	
		public int get_x_coor() {
			return x_powerup;
		}
		public void paintComponent(Graphics g) {
			if(if_hit[array_num]==true) {
				g.drawImage(blank_block_img, x_powerup,y_powerup,50,50,null);
			}
			else {
				g.drawImage(power_up_img, x_powerup,y_powerup,50,50,null);
			}
	
			if (mushroom_appear == true ) {
				g.drawImage(mushroom_img, x_mushroom,y_mushroom,50,50,null);
	
			}
			else if (star_appear == true) {
				g.drawImage(star_img, x_star,y_star,50,50,null);
			}
			else {
				direction = 1;
				counter =0;
			}
		}
	
	public void run() {
		try {
			while(true) {
				move_power_ups();
				Thread.sleep(5);
			}
		}
		catch(Exception e) {
			System.out.println("ERROR");
		}
	
	}
	

}
