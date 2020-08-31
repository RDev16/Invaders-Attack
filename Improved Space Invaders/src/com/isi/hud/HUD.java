package com.isi.hud;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.isi.core.Game;
import com.isi.states.GameState;
import com.isi.states.PlayState;

public class HUD {

	private Game game;
	private GameState state;
	
	private Font font;
	
	public HUD(Game game, GameState state) {
		this.game = game;
		this.state = state;
		
		font = new Font("Arial", Font.BOLD, 16);
	}
	
	public void tick() {}
	
	public void draw(Graphics2D g) {
		PlayState s = (PlayState) state;
		
		g.setFont(font);
		g.setColor(Color.white);
		g.drawString("Aliens: " + s.getAlienGroup().getAliens().size(), 8, 20);

		for (int i = 1; i <= s.getPlayer().getHp(); i++)
			g.drawImage(s.getPlayer().getImage(), game.getWidth() - 35 * i, 10, 30, 30, null);
	}
}