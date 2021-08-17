package com.isi.uicomponents;

import java.awt.Graphics2D;

import com.isi.core.Game;
import com.isi.states.GameState;
import com.isi.tools.Text;

public class Label extends UIComponent {

	private Text text;
	
	public Label(Game game, GameState state, int x, int y, int width, int height, Text text) {
		super(game, state, x, y, width, height);
	
		this.text = text;
	}
	
	public Label(Game game, GameState state, int x, int y, int width, int height, boolean defaultLAF, Text text) {
		super(game, state, x, y, width, height, defaultLAF);
		
		this.text = text;
	}
	
	public Text getText() {
		return text;
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void draw(Graphics2D g) {
		text.centerOnComponent(this, g);
		text.draw(g);	
	}
}