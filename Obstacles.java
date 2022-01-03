import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/*
Class: Obstacles Class 
Author: Swathi and Abirami 
Teacher: Mr.Anthony 
Date: January 12, 2020
Description:This creates an array of objects that will be drawn on the screen accordingly.
*/

public class Obstacles implements Runnable {
	private int xDirection, yDirection, x_obs, y_obs, array_num, length_image, height_image ;
	private Image obstacles[] = new Image[15];
	private Image venus_fly_trap_img, spikes, no_land;
	
	/*
	 * Constructor
	 * pre:none
	 * post:Gets all obstacle images and places them in an array
	 */
	public Obstacles() {
		//GET OBSTACLE IMAGES 
		ImageIcon venus_flytrap = new ImageIcon(getClass().getResource("venus_fly_trap.png"));
		venus_fly_trap_img = venus_flytrap.getImage();
	
		ImageIcon spikes_icon =new ImageIcon(getClass().getResource("Spikes-obstacle.png"));
		spikes = spikes_icon.getImage();
		
		ImageIcon no_land_icon = new ImageIcon(getClass().getResource("no_land.png"));
		no_land = no_land_icon.getImage();
		
		//PLACE THEM IN AN ARRAY IN THE ORDER WHICH THEY WILL APPEAR ON SCREEN 
		obstacles[0] = venus_fly_trap_img;
		obstacles[1] = no_land;
		obstacles[2] = no_land;
		obstacles[3] = spikes;
		obstacles[4] = venus_fly_trap_img;
		obstacles[5] = spikes;
		obstacles[6] = venus_fly_trap_img;
		obstacles[7] = no_land;
		obstacles[8] = spikes;
		obstacles[9] = venus_fly_trap_img;
		obstacles[10] = no_land;
		obstacles[11] = spikes;
		obstacles[12] = spikes;
		obstacles[13] = no_land;
		obstacles[14] = venus_fly_trap_img;
		x_obs = 500;
		y_obs = 315;
		array_num =0;
		length_image = 50;
		height_image = 70;
		
	}
	
	/*
	 * Name:get_x_coor_obs()
	 * Description: Get the x coordinate of the obstacle 
	 * pre:none 
	 * post:returns the current x coordinate of the obstacle 
	  */
	public int get_x_coor_obs() {
		return x_obs;
	}
	
	/*
	 * Name:get_ycoor_obs()
	 * Description: Get the y coordinate of the obstacle 
	 * pre:none 
	 * post:returns the current y coordinate of the obstacle 
	  */
	public int get_y_coor_ob() {
		return y_obs;
	}
	
	/*
	 * Name:get_height_obs()
	 * Description: Get the height of the image 
	 * pre:none 
	 * post:returns the height of the image  
	  */
	public int get_height_obs() {
		return height_image;
	}
	

	/*
	 * Name:get_length_obs()
	 * Description: Get the length of the image 
	 * pre:none 
	 * post:returns the length of the image  
	  */
	public int get_length_obs() {
		return length_image;
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
	 * Description: Set yDirection variable 
	 * pre:none 
	 * post: makes yDirection equal to the argument y_Direct 
	*/
	public void setYDirect(int y_Direct) {
		yDirection = y_Direct;
	}
	
	/*
	 * Name:get_array_num()
	 * Descriptions: Get the array num
	 * pre:none 
	 * post:returns the array num
	*/
	public int get_array_num() {
		return array_num;
	}
	
	/* Method:move_obstacles()
	 * Description:Controls the movement of the obstacles
	 * pre:none
	 * post: Displays and move the obstacles across the screen 
	 */
	public void move_obstacles() {
		//If xDirection is equal to -1 move the obstacle across the screen 
		x_obs += xDirection;
		
		//If the obstacles goes off the screen 
		if (x_obs<=-50) {
			//If the array_num reaches the end restart the array
			if(array_num == 10) {
				array_num = 0;
			}
			else {
				array_num ++;
			}
			//RESET THE X COORDINATE 
			x_obs = 700;
			
			//IF THE OBSTACLE IS THE EMPTY SPACE  DRAW IT LOWER 
			if (obstacles[array_num] == no_land) {
				y_obs = 375;
				height_image = 200;
			}
			//ELSE DRAW IT NORMALLY 
			else {
				height_image = 70;
				y_obs = 315;
			}
		}
	
		}
	
	
	/* Method:paintComponent()
	 * Description:Draws the obstacles onto the screen 
	 * pre:none
	 * post:Draws obstacles on to the screen 
	 */
	public void paintComponent(Graphics g) {
		g.drawImage(obstacles[array_num],x_obs,y_obs,length_image, height_image ,null);
	
	}
	
	/* Method:run()
	 * Description:Calls the move_obstacles method 
	 * pre:none
	 * post: Calls the move_obstacles method  
	 */
	public void run() {
		try {
			while(true) {
				move_obstacles();
				Thread.sleep(5);
			}
		}
		catch(Exception e) {
			System.out.println("ERROR");
		}
	
	}
}

