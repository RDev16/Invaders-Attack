package com.isi.uicomponents;

import java.awt.Color;
import java.awt.Graphics2D;

import com.isi.core.Game;
import com.isi.states.GameState;

public class Slider extends UIComponent {

	private int sliderX;
	private int sliderY;
	
	private int sliderDx;
	private int sliderDy;
	
	private int sliderWidth;
	private int sliderHeight;	
	
	public Slider(Game game, GameState state, int x, int y, int width, int height) {
		super(game, state, x, y, width, height);
	
		sliderX = x + width / 2;
		sliderY = y - 15 / 2;
		
		sliderDx = sliderX;
		sliderDy = sliderY;		
		
		sliderWidth = 5;
		sliderHeight = 25;
	}

	public int getSliderX() {
		return sliderX;
	}

	public void setSliderX(int sliderX) {
		this.sliderX = sliderX;
	}

	public int getSliderY() {
		return sliderY;
	}

	public void setSliderY(int sliderY) {
		this.sliderY = sliderY;
	}

	public int getSliderDx() {
		return sliderDx;
	}

	public int getSliderDy() {
		return sliderDy;
	}

	public void setSliderDx(int sliderDx) {
		this.sliderDx = sliderDx;
	}

	public void setSliderDy(int sliderDy) {
		this.sliderDy = sliderDy;
	}

	public int getSliderWidth() {
		return sliderWidth;
	}

	public void setSliderWidth(int sliderWidth) {
		this.sliderWidth = sliderWidth;
	}

	public int getSliderHeight() {
		return sliderHeight;
	}

	public void setSliderHeight(int sliderHeight) {
		this.sliderHeight = sliderHeight;
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(x, y, width, height);

		g.setColor(Color.gray);
		g.fillRect(sliderDx, sliderDy, sliderWidth, sliderHeight);	
	}
}