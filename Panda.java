import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/*
Class:Panda Class 
Author: Swathi and Abirami 
Teacher: Mr.Anthony 
Course: ICS4U
Date: January 12, 2020
Description:This controls the movement of the panda. It creates an instance of Background class, Obstacles class, Ground_in_Sky class, 
Power_up class, Coins class. Checks if panda collides with any object. If the panda collides with the Obstacle object it will call 
the game_over class. If the panda collides with the ground in sky object the panda will walk on the block and will be unable to pass 
through the block 
*/
public class Panda extends JFrame implements Runnable {
	//VARIABLE DECLARATION
	private int x, y, xDirection, yDirection,num_coins,time_spent ;
	private Graphics dbg;
	private boolean walk_panda, mushroom_appear,star_appear,coins_appear, increase_size, star_power,hit_obstacle;;
	public boolean coin_hit_status[]={false,false,false,false,false,false,false,false,false,false,false,false,
			false,false,false,false,false,false,false,false};

	private Image panda, panda_standing_image, panda_colour_img, panda_standing_colour_img, coin_img;
    
	//CREATING INSTANCES OF ClASSES
	Obstacles obstacles_1 = new Obstacles();
	Background front = new Background();
	Ground_in_sky blocks = new Ground_in_sky();
	Power_Up power_up = new Power_Up();
	Coins coin = new Coins();
	
	/*
	 * Constructor
	 * pre:none
	 * post:GETS ALL THE IMAGES AND SETS ALL THE POWERUPS TO FALSE AS THEY HAVE NOT BEEN ACTIVATED YET. SET TIMER AND SET PANDA'S 
	 * BEGINNING LOCATION 
	 */
	public Panda() {
		//GET ALL THE IMAGES
		ImageIcon panda_1 = new ImageIcon(getClass().getResource("panda_run.gif"));
		ImageIcon panda_standing  = new ImageIcon(getClass().getResource("Capture.PNG"));
		ImageIcon panda_colour = new ImageIcon(getClass().getResource("panda_run_colour.gif"));
		ImageIcon panda_standing_colour = new ImageIcon(getClass().getResource("panda_standing_colour.png"));
		ImageIcon coin = new ImageIcon(getClass().getResource("coin.jpg"));
		coin_img = coin.getImage();
		panda = panda_1.getImage();
		panda_standing_image = panda_standing .getImage();
		panda_colour_img = panda_colour.getImage();
		panda_standing_colour_img = panda_standing_colour.getImage();
		
		//SET THE PANDA'S ORIGINAL LOCATION 
		x = 270;
		y = 315;
		
		//SET TIMER 
		time_spent = 5000;
		num_coins = 0;
		
		//SET POWERUPS AND COIN TO FALSE 
		star_appear=false;
		coins_appear=false;
		increase_size = false;
		star_power = false;
		hit_obstacle = false;

}
	
	/* Method:set_hit_obstacle()
	 * Description:sets if an obstacle has been hit 
	 * pre:none
	 * post: Set if the panda has collided with an obstacle 
	 */
	public void set_hit_obstacle(boolean if_hit) {
		hit_obstacle = if_hit;
	}

	/* Method:run()
	 * Description:Calls the move method set the path of execution for the panda class  
	 * pre:none
	 * post: Calls the move method  
	 */
	public void run() {
		try {
			while(true) {	
				//CALLS THE MOVE METHOD CLASS 
				move();
				Thread.sleep(5);
			}
		}
		catch(Exception e) {
			System.out.println("ERROR");
		}
	}

	/* Method:move()
	 * Description:Creates the movement for the panda character and accounts for all the characters's collisions 
	 * pre:none
	 * post:Moves the character,sets the timer, and accounts for all collsions
	 */
	public void move() {
		
		//IF USER JUMPS DECREASE Y COORDINATE
		y += yDirection;
		
		//IF USER WINS THE GAME MAKE PANDA RUN ACROSS SCREEN 
		if(blocks.get_win_screen() == true) {
			x -= xDirection;
		}
		
		//PASS THE BLOCK X COORDINATE TO THE POWER_UP CLASS 
		power_up.set_power_up_x_coor(blocks.getXBlock());
		
		//PASS THE BLOCK Y COORDINATE TO THE POWER_UP CLASS 
		power_up.set_power_up_y_coor(blocks.getHeightFromGround());
		
		//PASS THE BLOCK ARRAY NUMBER TO THE POWER_UP CLASS 
		power_up.set_array_num(blocks.get_array_num());
		
		
		//If it is between the range of block
		if (x+50 >= blocks.getXBlock() && blocks.getWidthCoor() >= x+25) {
			//If It touches the bottom of the block
			if (blocks.getHeightFromGround()+50 >= y&& y>=blocks.getHeightFromGround()) {
			//DON'T ALLOW IT TO GO PAST THAT POINT 
				y = blocks.getHeightFromGround()+50;
	
				//If it hits a powerup that was not hit before
				if(power_up.get_x_coor()<=x+50 &&power_up.get_x_coor()+50>=x+50 && power_up.get_blank_block()== false){
					
					//IF THE ARRAY_NUM IS DIVISIBLE BY TWO THE POWERUP IS A MUSHROOM 
					if(power_up.get_array_num()% 2.0 ==0.0) {
						//A mushroom will appear
						mushroom_appear = true;
						star_appear = false;
						power_up.set_Object_Appear(true,1);
						//It will fall ofF the block
						power_up.set_Object_Fall_Off(blocks.getXBlock(),1);
						//If it hits an obstacle it will move the other direction
						power_up.set_Obstacle_coor(obstacles_1.get_x_coor_obs());
						//Make sure that the powerup cannot be used again
						power_up.set_blank_block(true);
					}
					//IF THE ARRAY_NUM IS DIVISIBLE BY THREE THE POWERUP IS A STAR
					else if (power_up.get_array_num()%3.0 ==0 && power_up.get_array_num()!= 0) {
						
						//A STAR WILL APPEAR 
						star_appear = true;
						//THE MUSHROOM POWER WILL DISAPPEAR
						mushroom_appear = false;
						power_up.set_Object_Appear(true,2);
						//It will fall ofF the block
						power_up.set_Object_Fall_Off(blocks.getXBlock(),2);
						//If it hits an obstacle it will move the other direction
						power_up.set_Obstacle_coor(obstacles_1.get_x_coor_obs());
						//Make sure that the powerup cannot be used again
						power_up.set_blank_block(true);
	
	
					}
					// ANY OTHER BLOCK 
					else {
						//A COIN WILL APPEAR 
						coins_appear = true;
		
					}
				}
			}
	
		}
		//IF THE USER TOUCHES THE TOP OF THE SCREEN 
		if (y<=10) {
			while(true) {
				y+= 1 ;
				// IF THE USER IS OVER A BLOCK LAND ON TOP OF THE BLOCK 
				if (y <= blocks.getHeightFromGround() && ((blocks.getXBlock() <= x && x<= blocks.getWidthCoor()&& xDirection == 0)||(power_up.get_x_coor()<=x+50 &&power_up.get_x_coor()+50>=x+50))) {
					y = blocks.getHeightFromGround()-75 ;
	
					break;
				}
				// ELSE CAUSE THE USER TO LAND ON THE GROUND 
				else if(y>=315) {
					break;
				}
			}
	
		}
	
		}
	/* Method:get_x_coor()
	 * Description:Gets the x_coordinate of the character 
	 * pre:none
	 * post:Return x coordinate of the character
	 */
	public int get_x_coor() {
		return x;
	
	}
	
	/* Method:get_y_coor()
	 * Description:Gets the y_coordinate of the character 
	 * pre:none
	 * post:Return y coordinate of the character
	 */
	public int get_y_coor() {
		return y;
	
	}
	
	public void set_y_coor(int y_coor) {
		y = y_coor;
	}
	
	/* Method:set_walk_panda()
	 * Description:Set if the panda is walking 
	 * pre:none
	 * post:If the panda is walking set true else set false 
	 */
	public void set_walk_panda(boolean if_walk) {
		if (if_walk == true) {
			walk_panda = true;
	
		}
		else
			walk_panda = false;
	
	}

	/* Method:setXDirect()
	 * Description:set the direction of the x coordinates 
	 * pre:none
	 * post:Will set xDirection to the argument. If not moving will be set to 0. If moving to the right will be set to -1
	 */
	public void setXDirect(int x_Direct) {
		xDirection = x_Direct;
	}

	/* Method:setYDirect()
	 * Description:set the direction of the y coordinates 
	 * pre:none
	 * post:Will set yDirection to the argument. If not moving will be set to 0. If moving to the up will be set to -1
	 */
	public void setYDirect(int y_Direct) {
		yDirection = y_Direct;
	}
	
	/* Method:get_hit_obstacle()
	 * Description:Get if the user has hit an obstacle 
	 * pre:none
	 * post:Returns if a user has hit an obstacle
	 */
	public boolean get_hit_obstacle() {
		return hit_obstacle;
	}



	/* Method:paintComponent()
	 * Description:Draws the images of the panda objects and its objects
	 * pre:none
	 * post:Calls panda and all of its objects paintComponent Method
	 */
	public void paintComponent(Graphics g) {
		//CREATE A RECTANGLE OVER THE OBJECTS WE HAVE CREATED TO TRACK COLLLSIONS
		Rectangle rect_panda = new Rectangle(x,y,50,75);
		Rectangle rect_obs = new Rectangle(obstacles_1.get_x_coor_obs(),obstacles_1.get_y_coor_ob(),obstacles_1.get_length_obs(),obstacles_1.get_height_obs());
		Rectangle mushrooms_rect = new Rectangle(power_up.get_x_Object(1),power_up.get_y_Object(1),50,50);
		Rectangle stars_rect = new Rectangle(power_up.get_x_Object(2),power_up.get_y_Object(2),50,50);
		Rectangle rect_coin = new Rectangle(coin.getXCoin(),coin.getHeightFromGround(),30,30);
		
		//SET OBSTACLE COORDINATES FOR THE GOOMBA 
		//goomba.set_Obstacle__coor(obstacles_1.get_x_coor_obs());

		//IF CHARACTER IS MOVING RIGHT
		if (xDirection == -1 ) {
			//AND THE CHARACTER HITS THE POWERUP WITH COINS
			if (coins_appear == true) {
				
				//INCREASE THE TOTAL COUNT OF COINS BY 1000
				num_coins = num_coins+1000;
				
				//DRAW ON THE SCREEN THAT THEY COLLLECTED 1000 COINS
				g.drawString("1000",blocks.getXBlock()+10, blocks.getHeightFromGround()-100);
				g.drawImage(coin_img, blocks.getXBlock()+10, blocks.getHeightFromGround()-70,50,50,null);
				//SET THAT BLOCK TO TRUE SO IT CANNOT BE HIT AGAIN 
				power_up.set_blank_block(true);
				//SET COIN APPEAR TO FALSE
				coins_appear = false;
			}
			
			//IF THE CHARACTER HAS HIT THE MUSHROOM POWERUP 
			if (increase_size == true) {
				//THE PANDA SIZE WILL INCREASE
				g.drawImage(panda,x,y-20,75,90,null);
		
			}
			//IF THE USER HITS THE STAR PWOERUP AND IT HAS NOT BEEN 5 SECONDS YET 
			else if (star_power == true && time_spent >= 0) {
				//DRAW AN IMAGE OF A COLOURFUL PANDA 
				g.drawImage(panda_colour_img,x,y-20,75,90,null);
				//DECREASE ONCE SECOND 
				time_spent--;
				//IF TIME SPENT== 0
				if (time_spent == 0) {
					//END STAR POWERS 
					star_power = false;
				}
			}
			//ELSE DRAW REGULAR PANDA 
			else {
				g.drawImage(panda,x,y,50,75,null);
		
			}
		
		
			}

		//IF CHARACTER IS NOT RUNNING 
		else {
			//AND THE CHARACTER HITS THE POWERUP WITH COINS
			if (coins_appear == true) {
				
				//INCREASE THE TOTAL COUNT OF COINS BY 1000
				num_coins += 1000;
		
				//DRAW ON THE SCREEN THAT THEY COLLLECTED 1000 COINS
				g.drawString("1000",blocks.getXBlock()+10, blocks.getHeightFromGround()-100);
				g.drawImage(coin_img, blocks.getXBlock()+10, blocks.getHeightFromGround()-70,50,50,null);
				
				//SET THAT BLOCK TO TRUE SO IT CANNOT BE HIT AGAIN 
				power_up.set_blank_block(true);
				//SET COIN APPEAR TO FALSE
				coins_appear = false;
			}
			//IF THE CHARACTER HAS HIT THE MUSHROOM POWERUP 
			if (increase_size == true) {
				//THE PANDA SIZE WILL INCREASE
				g.drawImage(panda_standing_image,x,y-30,100,125,null);
			}
			//IF THE USER HITS THE STAR PWOERUP AND IT HAS NOT BEEN 5 SECONDS YET 
			else if (star_power == true&& time_spent >= 0) {
				//DRAW AN IMAGE OF A COLOURFUL PANDA 
				g.drawImage(panda_standing_colour_img ,x,y-30,100,125,null);
				time_spent--;
			}
			//ELSE DRAW A REGULAR PICTURE
			else {
				g.drawImage(panda_standing_image,x,y-5,75,90,null);

			}
		}
		
		//DISPLAY NUMBER OF COINS ON SCREEN NUMBER OF COINS
		g.drawString ("COINS:" + num_coins,350 ,70);
		
		//IF MUSHROOM HAS APPEARED ON SCREEN 
		if (mushroom_appear == true) {
			// AND IT INTERSECTS WITH THE PANDA 
			if (rect_panda.intersects(mushrooms_rect)) {
				
				//CAUSE THE POWERUP TO DISAPPEAR 
				power_up.set_Object_Appear(false,1);
				star_power = false;
				//INCREASE THE SIZE OF THE PANDA 
				increase_size = true;
				}
		}
		//IF STAR HAS APPEARED ON SCREEN 
		if(star_appear == true) {
			// AND IT INTERSECTS WITH THE PANDA 
			if (rect_panda.intersects(stars_rect)) {
				
				//CAUSE THE POWERUP TO DISAPPEAR 
				power_up.set_Object_Appear(false,2);
				
				//STOPS MUSHROOM POWER 
				increase_size = false;
				// IF USER HAS SPENT LESS THAN 5 SECOND IN START POWER 
				if(time_spent >= 0) {
					//SHOW STA POWER 
					star_power = true;
				}
				else 
					//ELSE IF 5 SECONDS HAS PASSED END STAR POWER
					star_power = false;
			}
		}
		//IF THE PANDA INTERSECTS WITH AN OBJECT 
		if (rect_panda.intersects(rect_obs) ) {
			//AND THE MUSHROOM POWER IS CURRENTLY BEING USED 
			if( increase_size == true) {
				//STOP THE POWER 
				increase_size = false;
				//MAKE THE USER APPEAR ON THE OTHER SIDE OF THE OBSTACLE
				x = obstacles_1.get_x_coor_obs()+51;
					
			}
			//IF THE USER IS REGULAR AND THE STAR POWER IN NOT BEING USED 
			else if (star_power == false && blocks.get_win_screen() == false){
				//DECLARE THAT THE OBSTACLE HAS BEEN HIT 
				hit_obstacle = true;
			}

		}
		
		//if panda gets the coin and panda is jumping and if coin in array is not hit yet
		if (rect_panda.intersects(rect_coin)&& Main.jump==true && Coins.hit_coin[coin.getCoinNum()]==false) {
			//passes the status to the coin class
			coin_hit_status[coin.getCoinNum()] = true;
			coin.hit_coin(true) ;	
			
			//plays collecting coin music
			Sound.play_coin_music();
			
		}

		if (coin_hit_status[coin.getCoinNum()]==true) {
			//number of coins increase by one
			num_coins = num_coins + 1;
			//changes jump boolean value to false
			Main.jump = false;
			coin_hit_status[coin.getCoinNum()] = false;
		}
		//updates screen with number of coins
		g.drawString ("COINS:" + num_coins,350 ,70);


		
		

}
}







