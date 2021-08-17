package com.isi.states;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.isi.core.Game;
import com.isi.tools.ImageLoader;
import com.isi.tools.Text;
import com.isi.uicomponents.Button;
import com.isi.uicomponents.UIComponent;

public class MainMenuState extends GameState {
	
	private static BufferedImage bg = ImageLoader.load("MainMenuBackground.jpg");
	
	// Background coordinates for animation
	private int x;
	private int y;

	// MainMenu components array
	private ArrayList<UIComponent> components;
	
	public MainMenuState(Game game) {
		super(game);
	
		x = 0;
		y = 0;
		
		components = new ArrayList<UIComponent>();		

		components.add(new Button(game, this, 20, 20, 220, 90, new Text(Game.font, "Play")));	
		components.add(new Button(game, this, 20, 115, 220, 90, new Text(Game.font, "Exit")));	
	}

	public ArrayList<UIComponent> getComponents() {
		return components;
	}

	public void tick() {		
		y = y >= game.getHeight() ? 0 : y + 2;

		for (int i = 0; i < components.size(); i++)
			components.get(i).tick();
	}

	public void draw(Graphics2D g) {
		g.drawImage(bg, 0, -game.getHeight() + y, game.getWidth(), game.getHeight(), null);
		g.drawImage(bg, x, y, game.getWidth(), game.getHeight(), null);
	
		for (int i = 0; i < components.size(); i++)
			components.get(i).draw(g);
	}	
}