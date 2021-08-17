package com.isi.uicomponents;

import java.awt.Color;
import java.awt.Graphics2D;

import com.isi.core.Game;
import com.isi.handlers.Mouse;
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
	
	protected boolean defaultLAF;

	protected Color boundsColor;
	protected Color fillColor;
	
	protected boolean cursorOn;
	protected boolean[] pressedButtons;
	
	public UIComponent(Game game, GameState state, int x, int y, int width, int height) {
		this.game = game;
		this.state = state;
		
		this.x = x;
		this.y = y;
		
		this.width = width;
		this.height = height;
		
		defaultLAF = false;

		boundsColor = DEFAULT_BOUNDS_COLOR;
		fillColor = DEFAULT_FILL_COLOR;
		
		cursorOn = false;
		pressedButtons = new boolean[3];
	}

	public UIComponent(Game game, GameState state, int x, int y, int width, int height, boolean defaultLAF) {
		this.game = game;
		this.state = state;
		
		this.x = x;
		this.y = y;
		
		this.width = width;
		this.height = height;
		
		this.defaultLAF = defaultLAF;

		boundsColor = DEFAULT_BOUNDS_COLOR;
		fillColor = DEFAULT_FILL_COLOR;
		
		cursorOn = false;
		pressedButtons = new boolean[3];
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
	
	public void checkMouseEvents(boolean onEvent, boolean pressedEvent) {
		if (onEvent)
			checkCursorOnEvent();
		if (pressedEvent)
			checkMousePressedEvent();
	}
	
	private void checkCursorOnEvent() {
		Mouse mouse = game.getMouse();
		
		if (mouse.getX() >= x && mouse.getX() <= x + width && mouse.getY() >= y && mouse.getY() <= y + height)
			cursorOn = true;
		else
			cursorOn = false;
	}
	
	private void checkMousePressedEvent() {
		Mouse mouse = game.getMouse();

		if (cursorOn) {
			for (int i = 0; i < pressedButtons.length; i++)
				pressedButtons[i] = mouse.pressedButton(i);
		}
	}
	
	public abstract void tick();

	public abstract void draw(Graphics2D g);	
}