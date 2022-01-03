import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/*
 * Class:Win_Screen 
 * Author: Swathi and Abirami
 * Teacher: Mr.Anthony 
 * Date: January 12, 2020
 * Course: ICS4U
 * Description: This displays the game over screen 
 */
public class Win_Screen extends JFrame implements Runnable{
	private Image bamboo_img;
	private int x_coor, xDirection; 
	
	/*
	 * Constructor
	 * pre:The user must win the game 
	 * post:Gets the image for the bamboo  
	 */
	public Win_Screen() {
		ImageIcon bamboo= new ImageIcon(getClass().getResource("bamboo.png"));
		bamboo_img = bamboo.getImage();
		 x_coor = 701;
		
	}

	/* Method:run()
	 * Description:Calls the move method set the path of execution for the win class  
	 * pre:none
	 * post: Calls the move method  
	 */
	public void run() {
		try {
			while(true) {
			move();
			Thread.sleep(10);
			}
			}
			catch(Exception e) {
			System.out.println("ERROR");
			}
			}

	/* Method:move()
	 * Description:Controls the movement of the bamboo 
	 * pre:none
	 * post: Displays and move the bamboo to a quarter if the screen 
	 */
	public void move() {
		
		if (x_coor>520) {
		x_coor = x_coor -1;
		}
		else
			x_coor = 520;
	}
	
	/* Method:paintComponent()
	 * Description:Draws win screen 
	 * pre:none
	 * post:Draws bamboo image and draws congratualtions message 
	 */
	public void paintComponent(Graphics g) {
		
		g.drawImage(bamboo_img, x_coor,0,200,500,null);
		g.drawString(" CONGRATULATIONS ",10,250);
		g.drawString(" OREO GOT HOME IN TIME", 10, 300);
	}
	/*
	 *  Method:get_x_coor()
	 * Description:Gets the x_coordinate of the bamboo 
	 * pre:none
	 * post:Return x coordinate of the bamboo 
	 */
	public int get_x_coor() {
		return x_coor;
	}
	
	/* Method:setXDirect()
	 * Description:set the direction of the x coordinates 
	 * pre:none
	 * post:Will set xDirection to the argument.
	 */
	public void setXDirect(int x_Direct) {
		xDirection = x_Direct;

	}


	
}
