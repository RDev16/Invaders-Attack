package com.isi.entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.isi.core.Game;
import com.isi.states.GameState;
import com.isi.states.PlayState;
import com.isi.tools.ImageLoader;

public class Player extends Entity {

	private static BufferedImage image = ImageLoader.load("Player.png");

	private int hp;
		
	private boolean hurted;
	private int hurtedAnimationTimer;
	
	private Bullet bullet;
	private boolean shooting;
	
	private boolean left;
	private boolean right;
	
	public Player(Game game, GameState state, int x, int y, int width, int height) {
		super(game, state, x, y, width, height);
	
		hp = 3;
		hurted = false;
		hurtedAnimationTimer = 0;

		bullet = null;
		shooting = false;
		
		left = false;
		right = false;
	}
	
	public BufferedImage getImage() {
		return image;
	}

	public int getHp() {
		return hp;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
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
	
	public void hurt() {
		hurted = true;
		hp--;
	}
	
	@Override
	public void tick() {
		PlayState s = (PlayState) state;
		
		if (left && right || !left && !right) xVel = 0;
		else if (left && !right) xVel = -7;
		else if (!left && right) xVel = 7;		
		
		if (bullet == null && shooting) {
			bullet = new Bullet(game, state, x + width / 2 - 8 / 2, y - 13, 8, 13, this);
			s.getEntityManager().add(bullet);
		}
		
		if (hurtedAnimationTimer == 15) {
			hurted = false;
			hurtedAnimationTimer = 0;
		} else if (hurtedAnimationTimer >= 10) {
			hurted = true;
			hurtedAnimationTimer++;
		} else if (hurtedAnimationTimer >= 5) {
			hurted = false;
			hurtedAnimationTimer++;
		} else if (hurted) {
			hurtedAnimationTimer++;
		}
		x += xVel;
		y += yVel;
		
		hitbox.x = x;
		hitbox.y = y;			
	}

	@Override
	public void draw(Graphics2D g) {		
		if (!hurted) g.drawImage(image, x, y, width, height, null);
	}
}