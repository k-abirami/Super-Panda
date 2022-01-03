
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;

/*
 * Class:Main_Menu
 * Author: Swathi Salil and Abirami Karthikeyan
 * Teacher: Mr.Anthony 
 * Date: January 12, 2020
 * Course: ICS4U
 * Description: This displays the main menu which includes a start and instruction button. 
 * If the user presses the start button it will take them to the game(Main Class). If the
 * user presses the instruction button it will take them to the instructions screen(Instructions Class).
 */

public class Main extends JFrame implements ActionListener{
	//VARIABLE DECLARATION
	//DOUBLE BUFFEREING
	private Image dbImage;
	private  Graphics dbGraphics;
	
	//CREATE AN INSTANCE OF THE WIN_SCREEN CLASS
	private  Win_Screen win ;
	
	//HOW MUCH TIME HAS PASSED IN THE GAME 
	//private static int second_passed ;

	//CREATE THREADS 
	private Thread character ;
	private Thread obstacles_thread ;
	private Thread front_screen;
	private Thread blocks_thread ;
	private Thread power_up_thread;
	private Thread coin_thread;
	
	//Variable to check coin count
	public static boolean jump;
	
	//SCREEN DIMENSIONS
	private int SCREEN_WIDTH , SCREEN_HEIGHT;
	Dimension screenSize;

	//Creating Character
	static Panda game ;

	
	/*
	 * Constructor
	 * pre:none
	 * post: A screen has been created titled "Super Panda Game"
	 */
	public Main() {
		//CREATING AN INSTANCE OF THE PANDA CLASS
		game = new Panda();
		
		//NAMING THE SCREEN 
		setTitle("Super Panda Game");
	
		//SCREEN WIDTH AND HEIGHT
		SCREEN_WIDTH = 700;
		SCREEN_HEIGHT = 500;
		screenSize = new Dimension (SCREEN_WIDTH, SCREEN_HEIGHT);
		
		
		setSize(screenSize);
		//DON'T ALLOW THE USER TO RESIZE 
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
		
		
		addKeyListener(new AL());
		//CREATING AN INSTANCE OF THE WIN CLASS
		win =  new Win_Screen();
		
		//THE NUMBER OF SECONDS THE GAME STARTS WITH 
		//second_passed = 300;
		
		//CREATE INSTANCES OF THREADS
		character = new Thread(game);
		obstacles_thread = new Thread(game.obstacles_1);
		power_up_thread = new Thread(game.power_up);
		blocks_thread = new Thread(game.blocks);
		front_screen = new Thread(game.front);
		//goomba_thread = new Thread(game.goomba);
		coin_thread = new Thread(game.coin);
		
		//START THREADS
		character.start();
		obstacles_thread.start();
		//goomba_thread.start();
		front_screen.start();
		blocks_thread.start();
		power_up_thread.start();
		coin_thread.start();
	
	}
	
	/* Method:paint()
	 * Description: create a double buffering stratgey 
	 * pre:none
	 * post: Pre-draws all images and then calls the paint Component Class 
	 */
	public void paint(Graphics g) {
		dbImage = createImage(getWidth(),getHeight());
		dbGraphics = dbImage.getGraphics();
		paintComponent(dbGraphics);
		g.drawImage(dbImage,0,0,this);
	}

	/* Method:paintComponent()
	 * Description:Draws the images of the panda objects and its objects
	 * pre:none
	 * post:Calls panda and all of its objects paintComponent Method
	 */
	public void paintComponent(Graphics g) {
		//If the player wins the game 
		if (game.blocks.get_win_screen() == true) {
			
			//Create and start the win thread
			Thread win_thread = new Thread(win);
			win_thread.start();
			
			//Draw the winning screen 
			game.front.paintComponent(g);
			game.paintComponent(g);
			win.paintComponent(g);
			}
		//Else if player has not won yet 
		else {
			
			//Draw Game Screens and its elements
			game.front.paintComponent(g);
			game.paintComponent(g);
			game.obstacles_1.paintComponent(g);
			game.blocks.paintComponent(g);
			//game.goomba.paintComponent(g);
			game.coin.paintComponent(g);
			game.power_up.paintComponent(g);
		}
	
		//If Player hits an obstacle and loses the game 
				if (game.get_hit_obstacle()==true) {
					setVisible(false);
					//restart thread
					obstacles_thread.stop();
					coin_thread.stop();
					try {
						obstacles_thread.join();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//Display the game over screen 
					Game_Over game_over = new Game_Over();
					game_over.paint(g);
		
	
	
	
	}
	repaint();
	}

	/* Author:Abirami Karthikeyan and Swathi Salil
	 * Date:January 12 2020
	 * Teacher:Mr.Anthony
	 * Class: AL
	 * Description: This class is the ActionListener class that corresponds with the user's choice to move right or up
	  */
	public class AL extends KeyAdapter{
		/* Method:keyPressed()
		 * Description:Accounts for all the users key activities(UP OR RIGHT)
		 * pre:none
		 * post:If user presses up, setYDirect for the character to -1. 
		 * If the user presses right setXDirect for the coins, background,obstacles,blocks, powerups to -1
		 */
		public void keyPressed(KeyEvent e) {
			//GET KEY CODE
			int keyCode = e.getKeyCode();
			
			//IF USER PRESSES UP 
			if(keyCode == e.VK_UP) {
				jump=true;
				//MAKE THE CHARACTER GO UP BY DECREASING THE Y COORDINATE OF THE CHARACTER 
				game.setYDirect(-1);
				//SET THAT THE PANDA IS WALKING
				game.set_walk_panda(true);
			}
			//IF USER PRESSES RIGH 
			if(keyCode == e.VK_RIGHT) {

				//IF THE USER IS NOT WALKING ON A BLOCK GO BACK TO THE GROUND 
				if (!(game.get_x_coor()+50 >= game.blocks.getXBlock() && game.blocks.getWidthCoor() >= game.get_x_coor()+25 )) {
					game.set_y_coor(315); 
				}
				
				//IF USER HAS WON THE GAME 
				if (game.blocks.get_win_screen() == true) {
					
					//Create and start the win thread
					Thread win_thread = new Thread(win);
					win_thread.start();

					//MAKE THE CHARACTER RUN ACROSS THE SCREEN 
					game.setXDirect(-1);
					
					//STOP THE BACKGROUND FROM MOVING 
					game.front.setXDirect(0);

				}
				
				//IF USER STILL HAS NOT WON THE GAME 
				else {
					
					//MAKE THE COIN, BACKGROUND, OBSTACLES, POWERUPS MOVE TO THE LEFT 
					game.coin.setXDirect(-1);
					game.setXDirect(-1);
					game.front.setXDirect(-1);
					game.obstacles_1.setXDirect(-1);
					game.blocks.setXDirect(-1);
					game.power_up.setXDirect(-1);
					
					//SET THE PANDA TO WALKING 
					game.set_walk_panda(true);
				}

			}
		}
	
			/* Method:keyReleased()
			 * Description:Accounts for after all the users key actitvities(UP OR RIGHT)
			 * pre:none
			 * post:Stop everything from moving(obstacles, background, character, powerups, coins)
			 */
		public void keyReleased(KeyEvent e) {
			int keyCode = e.getKeyCode();

			//IF THE USER HAS PRESSED UP 
			if(keyCode == e.VK_UP) {
				//THE USER HAS JUMPED 
				jump = true;
				
				//IF THE USER IS IN THE RANGE OF A BLOCK 
				while(true) {
					if (game.blocks.getHeightFromGround() > game.get_y_coor() &&( (game.get_x_coor()+50 >= game.blocks.getXBlock() && game.blocks.getWidthCoor() >= game.get_x_coor()+25))) {
						//ALLOW THEM TO LAND AND WALK ON THE BLOCK 
						game.set_y_coor (game.blocks.getHeightFromGround()-75); 
						break;
					}
					//ELSE FORCE THE USER TO LAND ON THE GROUND 
					else if (game.get_y_coor()==315) {
						break;
					}
					
					game.set_y_coor(game.get_y_coor()+1);

				}
				//STOP THE CHARACTER FROM MOVING 
				game.setYDirect(0);
				
				//SET AS FALSE AS CHARACTER HAS STOPPED WALKING
				game.set_walk_panda(false);
			}
			
			//IF THE USER HAS PRESSED RIGHT 
			if(keyCode == e.VK_RIGHT) {
				//IF THE USER IS NOT IN THE RANGE OF A BLOCK 
				if  (!(game.get_x_coor()+50 >= game.blocks.getXBlock() && game.blocks.getWidthCoor() >= game.get_x_coor()+25 )){
					//THE USER WILL LAND ON THE GROUND
					game.set_y_coor(315) ;
				}
				// IF THE USER WINS THE GAME DISPLAY THE WIN SCREEN 
				if (game.blocks.get_win_screen() == true) {

					win.setXDirect(-1);
					game.setXDirect(-1);
					game.front.setXDirect(0);
	
				}
				// SET ALL ELEMENTS OF THE GAME TO NOT MOVING 
				game.coin.setXDirect(0);
				game.setXDirect(0);
				game.front.setXDirect(0);
				game.obstacles_1.setXDirect(0);
				game.blocks.setXDirect(0);
				game.power_up.setXDirect(0);
				game.set_walk_panda(false);
			}

			//Helps Game Run Faster
			e.consume();
		}
	}



	/* Method:actionPerformed()
	 * Description:method necessary for running AL
	 * pre:none
	 * post:allows AL to function
	 */
	public void actionPerformed(ActionEvent e) {

		}


}



