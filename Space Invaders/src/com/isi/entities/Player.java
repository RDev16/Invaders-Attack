package com.isi.entities;

import java.awt.Graphics2D;

import com.isi.core.Game;
import com.isi.enums.AnimationType;
import com.isi.states.GameState;
import com.isi.states.PlayState;
import com.isi.tools.Animation;
import com.isi.tools.AssetsHolder;

public class Player extends Entity {

	private Animation playerAnimation;
	private Animation hitAnimation;
	
	// The number of hits remained that the ship can take
	private int hp;	
	// Indicates if the player got hit by an alien bullet for the "Hit" animation to 
	private boolean hit;

	// Indicates if player is shooting
	private boolean shooting;
	// The player can shoot only one bullet at a time
	private boolean ableToShoot;
	// The bullet shot by the player used to track the bullet state. If the bullet is destroyed, the player will be able to shoot
	private Bullet bullet;
	
	// Indicates what direction the player is moving
	private boolean left;
	private boolean right;
	
	private boolean alive;
	
	public Player(Game game, GameState state, int x, int y, int width, int height) {
		super(game, state, x, y, width, height);
	
		playerAnimation = new Animation(AssetsHolder.images.get("Player").get(0));
		hitAnimation = new Animation(AssetsHolder.images.get("Player"), 12, false, AnimationType.RETRO_FLASHING);
		
		currentAnimation = playerAnimation;

		hp = 3;
		hit = false;

		shooting = false;
		ableToShoot = true;
		bullet = null;
		
		left = false;
		right = false;
		
		alive = true;
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

	public boolean getAbleToShoot() {
		return ableToShoot;
	}
	
	public void setAbleToShoot(boolean ableToShoot) {
		this.ableToShoot = ableToShoot;
	}
	
	public boolean isAlive() {
		return alive;
	}
	
	public void hit() {
		hit = true;
		hp--;		
		
		if (hp <= 0)
			alive = false;
	}
	
	@Override
	public void tick() {
		if (hp <= 0) {
			alive = false;
			return;
		}
		PlayState s = (PlayState) state;
		
		if (left && right || !left && !right) xVel = 0;
		else if (left && !right) xVel = -7;
		else if (!left && right) xVel = 7;		

		// Checks if the bullet is still traceable
		if (bullet != null && !bullet.isVisible()) {
			bullet = null; 
			ableToShoot = true;
		}
		
		// Checks if the player is shooting and ableToShot
		if (shooting && ableToShoot) {
			// The player can't shoot anymore bullets until the current bullet is destroyed or untraceable
			ableToShoot = false;
			
			// Shooting a new bullet
			bullet = new Bullet(game, state, x + width / 2 - 8 / 2, y - 13, 8, 13, this);
			// Adding the bullet to the entity manager
			s.getEntityManager().add(bullet);
		}

		if (hit) {
			currentAnimation = hitAnimation;
			hit = false;
		} else if (currentAnimation.equals(hitAnimation) && currentAnimation.isEnded()) {
			currentAnimation.renew();
			currentAnimation = playerAnimation;
		}		
		x += xVel;
		y += yVel;
		
		hitbox.x = x;
		hitbox.y = y;
		
		clamp();
		
		currentAnimation.animate();
	}
	
	public void clamp() {
		if (x + width >= game.getWidth())
			x = game.getWidth() - width;
		else if (x <= 0)
			x = 0;
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(currentAnimation.getCurrentFrame(), x, y, width, height, null);
	}
}