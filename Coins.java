import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/*
 * Class: Coins
 * Author: Abirami Karthikeyan and Swathi Salil 
 * Teacher: Mr. Anthony 
 * Date: January 12, 2020
 * Description: This class withh draw coins on the screen for the user to collect 
*/
public class Coins implements Runnable{


private int  xDirection, x_coin ;
	
	private int[]height_coin = {300,300,300,315,150,250,310,270,200,200,200,310,270,100,100,200,310,270,200,310};
	public static boolean []hit_coin = {false,false,false,false,false,false,false,false,false,false,false,false,
	false,false,false,false,false,false,false,false};
	private int  array_num;
	private Image coins_img;
	
	/*
	 * Constructor
	 * pre:none
	 * post:Gets the image for the coins and sets default values for array_num and x_coin
	 */
	public Coins() {
	
		ImageIcon coins = new ImageIcon(getClass().getResource("coin.jpg"));
		coins_img = coins.getImage();
		
		 array_num = 0;
		 x_coin = 700;
	
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
	 * Name:getXCoin()
	 * Description: get the x coordinate of the coin
	 * pre:none 
	 * post:Return the x coordinate of the current coin of the screen 
	*/
	public int getXCoin() {
		return x_coin;
	}
	
	/*
	 * Name:getHeightFromGround()
	 * Description: get the current height of the coin
	 * pre:none 
	 * post:Return the height of the current coin of the screen 
	*/
	public int getHeightFromGround() {
		return height_coin[array_num];
	}
	
	/*
	 * Name:getCoinNum()
	 * Description: get the current array_num
	 * post:Return the array_num of the current coin of the screen 
	*/
	public int getCoinNum() {
		return array_num;
	}
	
	/* Method:move_coin()
	 * Description:Controls the movement of coin
	 * pre:none
	 * post:Move the coin across the screen 
	 */
	public void move_coin() {
		//MOVE THE COIN ACROSS THE SCREEN 
		x_coin += xDirection;
	
		//IF THE COIN GOES OF THE SCREEN 
		if (x_coin <= -700) {
			//IF THE COIN REACHES THE END OF THE ARRAY RESTART FROM THE BEGINNING
			if (array_num == 19) {
				array_num = 0;
			}
			//MOVE TO THE NEXT COIN IN THE ARRAY 
			array_num ++;
		
			x_coin = 700;
		}

	
	}
	
	
	/* Method:paintComponent()
	 * Description:Draws the coin onto the screen based on a condition
	 * pre:none
	 * post:If coin has not been hit, coin is drawn on to the screen 
	 */
	
	public void paintComponent(Graphics g) {
	
		if (hit_coin[array_num]==false) {
			g.drawImage(coins_img,x_coin ,height_coin[array_num],30,30,null);
	
	}
	}
	
	
	
	/* Method:run()
	 * Description:Calls the move_coin method 
	 * pre:none
	 * post: Calls the move_coin method  
	 */
	public void run() {
		try {
			while(true) {
	
				move_coin();
				Thread.sleep(5);
			}
		}
		catch(Exception e) {
			System.out.println("ERROR");
		}
		}
	
	/* Method:hit_coin()
	 * Description:Sets if the coin in the array has been hit or not
	 * pre:none
	 * post:Sets if the coin in the array has been hit or not
	 */
	public void hit_coin(boolean hit) {
		hit_coin[array_num] = hit;
	
	}

}

