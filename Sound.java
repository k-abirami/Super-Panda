import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/*
Class:Sound class
Author: Swathi and Abirami 
Teacher: Mr.Anthony 
Course: ICS4U
Date: January 12, 2020
Description:This class plays sound when colliding with a coin and when its game_over
*/
public class Sound {
	
	/*
	 * play_coin_music
	 * pre:none
	 * post:plays the sound when the panda gets the coin
	 */
public static void play_coin_music() {
	
	//Abstract class declared
	InputStream music;
		
		try {
			//The music file is read
			music = new FileInputStream(new File("nsmb_coin.wav"));
			//The file is stored in AudiStream
			AudioStream audio = new AudioStream(music);
			//The music plays
			AudioPlayer.player.start(audio);
		}
		
		catch(Exception e) {
			System.out.println("Error");
		}
		
	}

/*
 * play_coin_music
 * pre:none
 * post:plays the sound when game is over
 */
public static void play_background_music() {
	
	//Abstract class declared
	InputStream music;
	
	try {
		//The music file is read
		music = new FileInputStream(new File("theme.wav"));
		//The file is stored in AudiStream
		AudioStream audio = new AudioStream(music);
		//The music plays
		AudioPlayer.player.start(audio);
	}
	
	catch(Exception e) {
		System.out.println("Error");
	}
	
}

	
	
}
