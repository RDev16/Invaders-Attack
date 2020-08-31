package com.isi.uicomponents;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.isi.core.Game;
import com.isi.states.GameState;

public abstract class UIComponent {

	public final static Color DEFAULT_BOUNDS_COLOR = Color.white;
	public final static Color DEFAULT_FILL_COLOR = Color.gray;
	
	protected Game game;
	protected GameState state;
	
	protected int x;
	protected int y;
	
	protected int width;
	protected int height;
	
	protected Rectangle bounds;
	
	protected Color boundsColor;
	protected Color fillColor;
	
	protected boolean cursorOn;
	
	public UIComponent(Game game, GameState state, int x, int y, int width, int height) {
		this.game = game;
		this.state = state;
		
		this.x = x;
		this.y = y;
		
		this.width = width;
		this.height = height;
		
		bounds = new Rectangle(x, y, width, height);
		
		boundsColor = DEFAULT_BOUNDS_COLOR;
		fillColor = DEFAULT_FILL_COLOR;
		
		cursorOn = false;
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

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
	
	public Color getBoundsColor() {
		return boundsColor;
	}

	public void setBoundsColor(Color boundsColor) {
		this.boundsColor = boundsColor;
	}

	public Color getFillColor() {
		return fillColor;
	}

	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}
	
	public void setCursorOn(boolean cursorOn) {
		this.cursorOn = cursorOn;
	}

	public abstract void tick();

	public abstract void draw(Graphics2D g);	
}