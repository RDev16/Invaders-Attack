package com.isi.tools;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Hashtable;

public class AssetsHolder {

	public static Hashtable<String, ArrayList<BufferedImage>> images;
	
	static {
		images = new Hashtable<>();
	}
	
	public static void loadAssets() {
		loadPlayerAssets();
		loadAlienAssets();
		loadBackground();
	}
	
	private static void loadPlayerAssets() {
		ArrayList<BufferedImage> player = new ArrayList<>();
		
		player.add(ImageLoader.load("Player.png"));	
		images.put("Player", player);
	}
	
	private static void loadAlienAssets() {
		// First type of alien animation (Green)
		ArrayList<BufferedImage> alien1 = new ArrayList<>();
		
		alien1.add(ImageLoader.load("Aliens Sprite Sheet.png").getSubimage(4, 0, 20, 18));
		alien1.add(ImageLoader.load("Aliens Sprite Sheet.png").getSubimage(26, 0, 20, 18));			

		// Second type of alien animation (Blue)
		ArrayList<BufferedImage> alien2 = new ArrayList<>();
		
		alien2.add(ImageLoader.load("Aliens Sprite Sheet.png").getSubimage(2, 22, 22, 18));
		alien2.add(ImageLoader.load("Aliens Sprite Sheet.png").getSubimage(26, 22, 22, 18));
			
		// Third type of alien animation (Red)
		ArrayList<BufferedImage> alien3 = new ArrayList<>();
		
		alien3.add(ImageLoader.load("Aliens Sprite Sheet.png").getSubimage(0, 44, 24, 20));
		alien3.add(ImageLoader.load("Aliens Sprite Sheet.png").getSubimage(26, 44, 24, 20));
		
		// Death animation
		ArrayList<BufferedImage> death = new ArrayList<>();
		
		death.add(ImageLoader.load("Alien Death.png").getSubimage(0, 0, 19, 19));
		death.add(ImageLoader.load("Alien Death.png").getSubimage(64, 14, 25, 25));
		death.add(ImageLoader.load("Alien Death.png").getSubimage(11, 61, 31, 31));
		death.add(ImageLoader.load("Alien Death.png").getSubimage(58, 58, 36, 36));	
		death.add(ImageLoader.load("Alien Death.png").getSubimage(3, 103, 41, 41));
		death.add(ImageLoader.load("Alien Death.png").getSubimage(50, 100, 50, 50));
		
		images.put("Green Alien", alien1);
		images.put("Blue Alien", alien2);
		images.put("Red Alien", alien3);
		
		images.put("Alien Death", death);		
	}
	
	private static void loadBackground() {
		ArrayList<BufferedImage> playBackground = new ArrayList<>();
		
		playBackground.add(ImageLoader.load("PlayBG.jpg"));
		
		images.put("Play Background", playBackground);
	}
}