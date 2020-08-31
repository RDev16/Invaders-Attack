package com.isi.uicomponents;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.isi.core.Game;
import com.isi.states.GameState;
import com.isi.tools.ImageLoader;
import com.isi.tools.Text;

public class Button extends UIComponent {

	private static BufferedImage defaultImage = ImageLoader.load("DefaultButton.png");
	private static BufferedImage cursorOnImage = ImageLoader.load("CursorOnButton.png");;
	
	private Text text;
	
	public Button(Game game, GameState state, int x, int y, int width, int height, Text text) {
		super(game, state, x, y, width, height);
		
		this.text = text;
	}

	public Button(Game game, GameState state, int x, int y, int width, int height) {
		super(game, state, x, y, width, height);

		text = new Text();
	}
		
	public Text getText() {
		return text;
	}
	 
	@Override
	public void tick() {
		
	}

	@Override
	public void draw(Graphics2D g) {		
		if (cursorOn) {
			g.drawImage(cursorOnImage, x, y, width, height, null);
		} else {
			g.drawImage(defaultImage, x, y, width, height, null);
		}		
		text.centerOnComponent(this, g);
		text.draw(g);		

		/*
		g.setColor(fillColor);
		g.fillRect(x, y, width, height);
		
		g.setColor(boundsColor);
		g.draw(bounds);
		*/
	}	
}