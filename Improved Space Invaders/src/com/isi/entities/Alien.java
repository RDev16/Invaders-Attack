package com.isi.entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.isi.core.Game;
import com.isi.states.GameState;
import com.isi.states.PlayState;
import com.isi.tools.Animation;
import com.isi.tools.ImageLoader;

public class Alien extends Entity {
	
	private static BufferedImage[] alienImages;
	private static BufferedImage[] deathImages;
	
	private int alienType;
	
	private Animation animation;
	
	private Bullet bullet;

	private boolean shooting;
	private boolean alive;
	
	static {
		alienImages = new BufferedImage[] {
			ImageLoader.load("Aliens Sprite Sheet.png").getSubimage(4, 0, 20, 18),
			ImageLoader.load("Aliens Sprite Sheet.png").getSubimage(26, 0, 20, 18),			
						
			ImageLoader.load("Aliens Sprite Sheet.png").getSubimage(2, 22, 22, 18),
			ImageLoader.load("Aliens Sprite Sheet.png").getSubimage(26, 22, 22, 18),						
			
			ImageLoader.load("Aliens Sprite Sheet.png").getSubimage(0, 44, 24, 20),
			ImageLoader.load("Aliens Sprite Sheet.png").getSubimage(26, 44, 24, 20)	
		};	
		
		deathImages = new BufferedImage[] {
			ImageLoader.load("Alien Death.png").getSubimage(0, 0, 19, 19),
			ImageLoader.load("Alien Death.png").getSubimage(64, 14, 25, 25),
			ImageLoader.load("Alien Death.png").getSubimage(11, 61, 31, 31),
			ImageLoader.load("Alien Death.png").getSubimage(58, 58, 36, 36),	
			ImageLoader.load("Alien Death.png").getSubimage(3, 103, 41, 41),
			ImageLoader.load("Alien Death.png").getSubimage(50, 100, 50, 50)
		};
	}
	
	public Alien(Game game, GameState state, int x, int y, int alienType) {
		super(game, state, x, y, 50, 50);
		
		this.alienType = alienType;
						
		switch (alienType) {
			case 0:
				animation = new Animation(new BufferedImage[] { alienImages[0], alienImages[1] }, 20);	
				break;
			case 1:
				animation = new Animation(new BufferedImage[] { alienImages[2], alienImages[3] }, 20);	
				break;
			case 2:
				animation = new Animation(new BufferedImage[] { alienImages[4], alienImages[5] }, 20);	
				break;
			default:
				System.err.println("Illigal Alien Type: " + alienType);
				System.exit(1);
		}		
		bullet = null;
		
		shooting = false;
		alive = true;
	}
	
	public int getAlienType() {
		return alienType;
	}
	
	public Bullet getBullet() {
		return bullet;
	}
	
	public boolean isShooting() {
		return shooting;
	}

	public void setShooting(boolean shooting) {
		this.shooting = shooting;
	}

	public void setBulletToNull() {
		bullet = null;
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
		
		x += xVel;
		y += yVel;
	
		hitbox.x = x;
		hitbox.y = y;
	
		if (alive) {
			if (bullet == null && shooting) {
				bullet = new Bullet(game, state, x + width / 2 - 8 / 2, y + 13, 8, 13, this);
				s.getEntityManager().add(bullet);
			}		
			animation.animate();				
		} else {
			if (!animation.isAnimationChanged()) animation = new Animation(deathImages, 2, true);
			animation.animate();
			
			if (animation.getIndex() == deathImages.length - 1 && animation.getTimer() == animation.getSpeed() - 1) {
				s.getAlienGroup().getAliens().remove(this);
			}			
		}
	}

	@Override
	public void draw(Graphics2D g) {		
		g.drawImage(animation.getCurrentFrame(), x, y, width, height, null);
	}
}