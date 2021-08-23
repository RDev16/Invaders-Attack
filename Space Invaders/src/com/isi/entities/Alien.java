package com.isi.entities;

import java.awt.Graphics2D;

import com.isi.core.Game;
import com.isi.states.GameState;
import com.isi.states.PlayState;
import com.isi.tools.Animation;
import com.isi.tools.AssetsHolder;

public class Alien extends Entity {
	
	private int alienType;

	private boolean shooting;
	private boolean alive;
	
	public Alien(Game game, GameState state, int x, int y, int alienType) {
		super(game, state, x, y, 50, 50);
			
		this.alienType = alienType;
		
		int animationSpeed = 20;
		
		switch (alienType) {
			case 0:
				currentAnimation = new Animation(AssetsHolder.images.get("Green Alien"), animationSpeed, true);	
				break;
			case 1:
				currentAnimation = new Animation(AssetsHolder.images.get("Blue Alien"), animationSpeed, true);	
				break;
			case 2:
				currentAnimation = new Animation(AssetsHolder.images.get("Red Alien"), animationSpeed, true);	
				break;
			default:
				System.err.println("Illigal Alien Type: " + alienType);
				System.exit(1);
		}	
		shooting = false;
		alive = true;
	}
	
	public int getAlienType() {
		return alienType;
	}
	
	public boolean isShooting() {
		return shooting;
	}

	public void setShooting(boolean shooting) {
		this.shooting = shooting;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	@Override
	public void tick() {
		
		PlayState s = (PlayState) state;

		// Moving the alien
		x += xVel;
		y += yVel;
		// Resting the yVel in case it is not 0 (Should be always zero because this game is going one line at a time)
		yVel = 0;
		
		// Moving the alien's hitbox for collision detection
		hitbox.x = x;
		hitbox.y = y;
		
		// Checks if the alien is still alive
		if (alive) {
			if (shooting) {
				s.getEntityManager().add(new Bullet(game, state, x + width / 2 - 8 / 2, y + 13, 8, 13, this));
				shooting = false;
			}					
		} else {
			// If the alien is dead, the death animation is triggered
			if (!currentAnimation.getFrames().equals(AssetsHolder.images.get("Alien Death"))) 
				currentAnimation = new Animation(AssetsHolder.images.get("Alien Death"), 0, false);
			
			// At the end of the animation the alien will be removed
			if (currentAnimation.isEnded())	s.getAlienManager().getAliens().remove(this);		
		}
		
		if (y >= game.getHeight() - game.getHeight() / 4)
			s.getPlayer().hit();
		currentAnimation.animate();				
	}

	@Override
	public void draw(Graphics2D g) {		
		g.drawImage(currentAnimation.getCurrentFrame(), x, y, width, height, null);
	}
}