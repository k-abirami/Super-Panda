import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;


/*
 * Class: Ground in the Sky Class 
 * Author: Swathi and Abirami 
 * Teacher: Mr.Anthony 
 * Date: January 12, 2020
 * Description: This creates an array of the height and width of each block and this will be drawn on the screen accordingly.
 */

public class Ground_in_sky implements Runnable{
	//VARIABLE DECLARATION
	private int[][] height_width_ground = {{350,200},{50,190},{200,195},{300,185},{400,190},{95,200},{100,200},{90,195},{260,200},{85,180},
	{65,200},{200,200},{100,190},{230,185},{300,200},{350,195},{50,200},{100,185},{150,200}};
	private boolean win_screen ;
	private int xDirection, x_block , array_num;
	private Image blocks_img;
	
	/*
	 * Constructor
	 * pre:none
	 * post:Gets the image for the block an gives default values to win_Screen, x_block and array_num
	 */
	public Ground_in_sky() {
		//GET THE BLOCKS IMAGE
		ImageIcon blocks = new ImageIcon(getClass().getResource("blockS.PNG"));
		blocks_img = blocks.getImage();
		
		//SET THE WIN SCREEN TO FALSE
		win_screen = false;
		
		//GIVE DEFAULT VALUE TO THEX COORDINATE OF THE BLOCK 
		x_block = 700;
		
		//START OF WITH THE FIRST ELEMENT IN THE ARRAY 
		array_num = 0;
	}
	
	/* Method:setXDirect()
	 * Description:set the direction of the x coordinates of the blocks 
	 * pre:none
	 * post:Will set the argument to xDirection.
	 */
	public void setXDirect(int x_Direct) {
	//Set the direction of the block 
	xDirection = x_Direct;
	}
	
	/* Method:get_win_screen()
	 * Description:Get the value of the win_screen 
	 * pre:none
	 * post:Return the value of the variable win_screen
	 */
	public boolean get_win_screen() {
		return win_screen;
	}
	
	/* Method:getXBlock()
	 * Description:Get the value of the x_coordinate of the block 
	 * pre:none
	 * post:Return the value of the variable x_block
	 */
	public int getXBlock() {
		//Returns x coordinate of the block 
		return x_block;
	}
	
	/* Method:getXBlock()
	 * Description:Get the current height of the blocks 
	 * pre:none
	 * post:Return the current height of the blocks
	 */
	public int getHeightFromGround() {
		//get the y coordinate of the block 
	return  height_width_ground[array_num][1];
	}
	
	/* Method:get_array_num()
	 * Description:Get the array number 
	 * pre:none
	 * post:Return the value of the variable array_num
	 */
	public int get_array_num() {
		//get the current array num
		return array_num;
	}
	
	/* Method:getWidthCoor()
	 * Description:Get the x coordinate of the end of the block  
	 * pre:none
	 * post:Return the x coordinate of the end of the block  
	 */
	public int getWidthCoor() {
		//Get the end coordinate of the block 
	return  height_width_ground[array_num][0] + x_block;
	}
	
	/* Method:getWidth()
	 * Description:get the width of the block(x coordinate at the front of the block to the x cooridante to back of the block 
	 * pre:none
	 * post:Return the current width of the block
	 */
	public int getWidth() {
		//get the width of the block 
	return  height_width_ground[array_num][0];
	}
	
	/* Method:move_block()
	 * Description:Controls the movement of the blocks
	 * pre:none
	 * post:If the xDirection = -1, the block move to the left and if the array_num is greater than 15 the user has reached the end of the game and can win_screen = true
	 */
	public void move_block() {
		//Move the block across the screen 
		x_block += xDirection;
		//if the x coordinate of the block is less than -700 draw a new block 
		if (x_block <=-700) {
		//If the user reaches the end of the game change the win_screen variable to trur
		if(array_num >= 15) {
			win_screen = true;
		}
		else {
			//Increase the array_num by one eveytime new_block is drawn 
		array_num ++;
		}
		//Reset the x coordinate of the block 
		x_block = 700;
		
		}
	
		
	}
	
	
	/* Method:paintComponent()
	 * Description:Draws the block
	 * pre:none
	 * post:Draws block image
	 */
	public void paintComponent(Graphics g) {
		//Draw the block 
		g.drawImage(blocks_img,x_block,height_width_ground[array_num][1],height_width_ground[array_num][0],50,null);
	
	}
	
	/* Method:run()
	 * Description:Calls the move_block method set the path of execution for the Ground_in_Sky class  
	 * pre:none
	 * post: Calls the move_block method  
	 */
	public void run() {
		try {
			while(true) {
		
				move_block();
				Thread.sleep(5);
			}
		}
		catch(Exception e) {
			System.out.println("ERROR");
		}
	
		}

}


