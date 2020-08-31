package com.isi.entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.isi.core.Game;
import com.isi.states.GameState;

public abstract class Entity {

	protected Game game;
	protected GameState state;
	
	protected int x;
	protected int y;
	
	protected int xVel;
	protected int yVel;
	
	protected int width;
	protected int height;
	
	protected Rectangle hitbox;
	
	protected boolean visible;
	
	public Entity(Game game, GameState state, int x, int y, int width, int height) {
		this.game = game;
		this.state = state;
		
		this.x = x;
		this.y = y;
		
		xVel = 0;
		yVel = 0;
		
		this.width = width;
		this.height = height;

		hitbox = new Rectangle(x, y, width, height);
		
		visible = true;
	}
	
	public Entity(Game game, GameState state, int x, int y) {
		this.game = game;
		this.state = state;
		
		this.x = x;
		this.y = y;
		
		xVel = 0;
		yVel = 0;
		
		width = 10;
		height = 10;

		hitbox = new Rectangle(x, y, width, height);
		
		visible = true;
	}
	
	public Entity(Game game, GameState state) {
		this.game = game;
		this.state = state;
		
		x = 0;
		y = 0;
		
		xVel = 0;
		yVel = 0;
		
		width = 10;
		height = 10;

		hitbox = new Rectangle(x, y, width, height);
		
		visible = true;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getxVel() {
		return xVel;
	}

	public void setxVel(int xVel) {
		this.xVel = xVel;
	}

	public int getyVel() {
		return yVel;
	}

	public void setyVel(int yVel) {
		this.yVel = yVel;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Rectangle getHitbox() {
		return hitbox;
	}
	
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public abstract void tick();	
	
	public abstract void draw(Graphics2D g);
}