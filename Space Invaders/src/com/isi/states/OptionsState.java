package com.isi.states;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.isi.core.Game;
import com.isi.tools.ImageLoader;
import com.isi.tools.Text;
import com.isi.uicomponents.Button;

public class OptionsState extends GameState {

	private static BufferedImage bg = ImageLoader.load("MainMenuBackground.jpg");
	
	// Background coordinates for animation
	private int x;
	private int y;

	private Button easy;
	private Button med;
	private Button hard;
	private Button brutal;
	
	public OptionsState(Game game) {
		super(game);
		
		int w = game.getWidth();
		
		easy = new Button(game, this, w / 2 - 200 / 2, 50, 200, 100, new Text(Game.font, "Easy"));	
		med = new Button(game, this, w / 2 - 200 / 2, 50 + 130, 200, 100, new Text(Game.font, "Medium"));	
		hard = new Button(game, this, w / 2 - 200 / 2, 50 + (130 * 2), 200, 100, new Text(Game.font, "Hard"));
		brutal = new Button(game, this, w / 2 - 200 / 2, 50 + (130 * 3), 200, 100, new Text(Game.font, "Brutal"));
	}

	@Override
	public void tick() {
		y = y >= game.getHeight() ? 0 : y + 2;

		easy.tick();
		med.tick();
		hard.tick();
		brutal.tick();
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(bg, 0, -game.getHeight() + y, game.getWidth(), game.getHeight(), null);
		g.drawImage(bg, x, y, game.getWidth(), game.getHeight(), null);
		
		easy.draw(g);
		med.draw(g);
		hard.draw(g);
		brutal.draw(g);
	}
	
}