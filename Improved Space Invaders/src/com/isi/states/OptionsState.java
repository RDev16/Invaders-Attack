package com.isi.states;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.isi.core.Game;
import com.isi.tools.ImageLoader;
import com.isi.uicomponents.Slider;
import com.isi.uicomponents.UIComponent;

public class OptionsState extends GameState {

	private static BufferedImage bg = ImageLoader.load("Space.jpg");
	
	private int x;
	private int y;
	
	private ArrayList<UIComponent> components;
	
	public OptionsState(Game game) {
		super(game);
		
		x = 0;
		y = 0;
		
		components = new ArrayList<UIComponent>();		
		components.add(new Slider(game, this, game.getWidth() / 2 - 120 / 2, 40, 120, 10));
	}
	
	public ArrayList<UIComponent> getComponents() {
		return components;
	}

	@Override
	public void tick() {
		x = x >= game.getWidth() ? 0 : x + 2;
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(bg, -game.getWidth() + x, 0, game.getWidth(), game.getHeight(), null);
		g.drawImage(bg, x, y, game.getWidth(), game.getHeight(), null);
		
		for (int i = 0; i < components.size(); i++) {
			components.get(i).draw(g);
		}
	}
}
