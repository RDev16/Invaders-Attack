package com.isi.uicomponents;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.isi.core.Game;
import com.isi.enums.Difficulty;
import com.isi.states.GameState;
import com.isi.states.OptionsState;
import com.isi.states.PlayState;
import com.isi.tools.ImageLoader;
import com.isi.tools.Text;

public class Button extends UIComponent {
	
	private final static BufferedImage defaultImage = ImageLoader.load("DefaultButton.png");
	private final static BufferedImage cursorOnImage = ImageLoader.load("CursorOnButton.png");
		
	private Text text;
	
	public Button(Game game, GameState state, int x, int y, int width, int height, Text text) {
		super(game, state, x, y, width, height);
		
		this.text = text;
	}

	public Button(Game game, GameState state, int x, int y, int width, int height, boolean defaultLAF, Text text) {
		super(game, state, x, y, width, height, defaultLAF);
		
		this.text = text;
	}
	
	public Text getText() {
		return text;
	}
	 
	@Override
	public void tick() {
		checkMouseEvents(true, true);
		
		// Check if the left mouse button is pressed. WARNING: { Without this condition all the other conditions are met }
		if (pressedButtons[0]) {
			switch (text.getString()) {
				case "Play":
					game.getGameStateManager().push(new OptionsState(game));				
					break;
				case "Easy":
					game.getGameStateManager().push(new PlayState(game, Difficulty.EASY));					
					break;
				case "Medium":
					game.getGameStateManager().push(new PlayState(game, Difficulty.MEDIUM));										
					break;
				case "Hard":
					game.getGameStateManager().push(new PlayState(game, Difficulty.HARD));					
					break;
				case "Brutal":
					game.getGameStateManager().push(new PlayState(game, Difficulty.BRUTAL));					
					break;
				case "Restart":
					Difficulty d = ((PlayState) game.getState()).getDifficulty();
					game.getGameStateManager().pop();
					game.getGameStateManager().push(new PlayState(game, d));
					break;
				case "Main Menu":
					game.getGameStateManager().pop();
					game.getGameStateManager().pop();
					break;
				case "Exit":
					System.exit(0);			
					break;
			}
			cursorOn = false;
			pressedButtons[0] = false;
		}
	}

	@Override
	public void draw(Graphics2D g) {			
		if (!cursorOn) {
			// Check if the button's look is not set to default Look & Feel
			if (!defaultLAF) {
				g.drawImage(defaultImage, x, y, width, height, null);
			} else {
				g.setColor(fillColor);			
				g.fillRect(x, y, width, height);
				g.setColor(boundsColor);
				g.drawRect(x, y, width, height);
			}
		} else {
			if (!defaultLAF) {
				g.drawImage(cursorOnImage, x, y, width, height, null);				
			} else {
				g.setColor(Color.darkGray);				
				g.fillRect(x, y, width, height);
				g.setColor(boundsColor);
				g.drawRect(x, y, width, height);
			}
		}
		text.centerOnComponent(this, g);
		text.draw(g);	
	}	
}