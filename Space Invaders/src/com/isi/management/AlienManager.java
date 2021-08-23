package com.isi.management;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.isi.core.Game;
import com.isi.entities.Alien;
import com.isi.enums.Difficulty;
import com.isi.states.GameState;

public class AlienManager {

	private Game game;
	private GameState state;
	
	// Array of aliens obviously
	private ArrayList<Alien> aliens;
	
	// The gap between every alien on the X axis (Horizontal)
	private int xGaps;
	// The gap between every alien on the Y axis (Vertical)
	private int yGaps;
	// The number of aliens on the X axis determined according to the horizontal gaps: windowWidth / (xGaps + alienSize)
	private int xLen;
	// The number of aliens on the Y axis determined according to the vertical gaps: windowHeight / (yGaps + alienSize)
	private int yLen;
	// Starting point on the X Axis
	private int xStart;	
	// Starting point on the Y Axis
	private int yStart;
		
	// Used to determine the velocity and the shooting rate of the aliens
	private Difficulty difficulty;
	// The velocity of aliens horizontally
	private int velocity;
	// The moving direction of the group
	private boolean direction;
	
	// The cooldown between each alien shot
	private int shootingCooldown;
	// The timer for the next shot
	private int shootingTimer;
	// All aliens are defeated?
	private boolean defeated = false;
	
	/** 
	 * Will Be used Later for optimization? (Line Completed)
	 * 
	 * private Alien mostRight;
	 * private Alien mostLeft;
	 */
		
	public AlienManager(Game game, GameState state, Difficulty difficulty) {		
		this.game = game;
		this.state = state;
		
		aliens = new ArrayList<Alien>();
		// The gaps between the aliens
 		xGaps = 20;
		yGaps = 20;
		// The number of aliens in one line is guided by the given horizontal gaps and alien size
		xLen = game.getWidth() / (xGaps + 50) - 1;
		yLen = 3;
		// Starting points on x/y axis
		xStart = 0;
		yStart = 50;
		autoGroupCreation();
		
		this.difficulty = difficulty;
		
		switch (difficulty) {
			case EASY:
				velocity = 1;
				shootingCooldown = 200;
				break;
			case MEDIUM:
				velocity = 2;
				shootingCooldown = 150;				
				break;
			case HARD:
				velocity = 4;
				shootingCooldown = 80;								
				break;
			case BRUTAL:
				velocity = 6;
				shootingCooldown = 40;												
				break;
		}
		shootingTimer = 0;
		direction = false;
	}
	
	public ArrayList<Alien> getAliens() {
		return aliens;
	}
	
	public Difficulty getDifficulty() {
		return difficulty;
	}
	
	public int getVelocity() {
		return velocity;
	}
	
	public boolean getDirection() {
		return direction;
	}
	
	public int getShootingCooldown() {
		return shootingCooldown;
	}

	public int getShootingTimer() {
		return shootingCooldown;
	}
	
	public void add(Alien a) {
		aliens.add(a);
	}

	public void remove(Alien a) {
		aliens.remove(a);
	}
	
	public boolean isDefeated() {
		return defeated;
	}
	
	/**
	 * Automatically creates a group of aliens with given gaps between them. 
	 * 
	 * The last alien location will be smaller than the window width and at least few pixels smaller than the window height.
	 */
	public void autoGroupCreation() {		
		for (int i = 0; i < yLen; i++) { 
			for (int j = 0; j < xLen; j++) {
				if (i == 1)
					aliens.add(new Alien(game, state, (50 * j + (xGaps * j)) + xStart, (50 * i + (yGaps * i)) + yStart, 1));
				else if (i == 2)
					aliens.add(new Alien(game, state, (50 * j + (xGaps * j)) + xStart, (50 * i + (yGaps * i)) + yStart, 2));
				else
					aliens.add(new Alien(game, state, (50 * j + (xGaps * j)) + xStart, (50 * i + (yGaps * i)) + yStart, 0));
			}
		}
	}
	
	/**
	 * Checks if the farthest or the closest alien X axis is equals to the window width in order to check
	 * and execute line completion 
	 * 
	 * @return
	 */
	public boolean lineCompleted() {
		if (direction) {
			// The most left alien
			Alien last = aliens.get(0);
			
			// Searching for the most right alien
			for (int i = 0; i < aliens.size(); i++) {
				Alien a = aliens.get(i);
				
				if (a.getX() > last.getX())
					last = a;
			}
			
			// Check if the most right alien is colliding with the window from the right
			if (last.getX() + last.getWidth() >= game.getWidth()) {
				direction = false;
			
				for (int i = 0; i < aliens.size(); i++) {
					Alien a = aliens.get(i);
					
					a.setxVel(-velocity);					
					a.setyVel(7);
				}
				return true;
			}
		} else {
			// The most left alien
			Alien last = aliens.get(0);
			
			// Searching for the most left alien
			for (int i = 0; i < aliens.size(); i++) {
				Alien a = aliens.get(i);
				
				if (a.getX() < last.getX())
					last = a;
			}

			// Check if the most left alien is colliding with the window from the left
			if (last.getX() <= 0) {
				direction = true;

				for (int i = 0; i < aliens.size(); i++) {
					Alien a = aliens.get(i);

					a.setxVel(velocity);
					a.setyVel(7);
				}
				return true; 
			}
		}
		return false;
	}
	
	public void tick() {
		if(aliens.size()>0) {
		shootingTimer++;

		if (shootingTimer == shootingCooldown) {
			int rand = (int) (Math.random() * aliens.size());
				
			aliens.get(rand).setShooting(true);
			shootingTimer = 0;
		}
		lineCompleted();
		
		for (int i = 0; i < aliens.size(); i++) aliens.get(i).tick();	
	} else if (aliens.size()==0) {
		defeated = true;
	}
		
	}

	public void draw(Graphics2D g) {
		for (int i = 0; i < aliens.size(); i++) aliens.get(i).draw(g);
	}
}