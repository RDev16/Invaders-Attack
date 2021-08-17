package com.isi.states;

import java.awt.Graphics2D;

import com.isi.core.Game;

public abstract class GameState {

	protected Game game;
	
	public GameState(Game game) {
		this.game = game;
	}
	
	public abstract void tick();
	
	public abstract void draw(Graphics2D g);
}