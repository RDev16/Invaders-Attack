package com.isi.handlers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.isi.core.Game;
import com.isi.states.MainMenuState;
import com.isi.states.OptionsState;
import com.isi.states.PlayState;
import com.isi.uicomponents.Button;
import com.isi.uicomponents.Slider;

public class MouseHandler extends MouseAdapter {

	private Game game;
	
	public MouseHandler(Game game) {
		this.game = game;
	}
	
	public void mousePressed(MouseEvent e) {
		MouseMotionHandler mouseMotionHandler = game.getMouseMotionHandler();
			
		if (mouseMotionHandler.getOnComponent() != null && e.getButton() == 1 && mouseMotionHandler.getOnComponent() instanceof Button) {
			Button button = (Button) mouseMotionHandler.getOnComponent();
			
			if (game.getState() instanceof MainMenuState) {
				if (button.getText().getString().equals("Play")) {
					game.getGameStateManager().push(new PlayState(game));
				} else if (button.getText().getString().equals("Options")) {
					game.getGameStateManager().push(new OptionsState(game));
				} else if (button.getText().getString().equals("Exit")) {
					System.exit(0);
				}
			}
		}
	}

	public void mouseReleased(MouseEvent e) {
		MouseMotionHandler mouseMotionHandler = game.getMouseMotionHandler();

		if (mouseMotionHandler.getOnComponent() instanceof Slider) {
			Slider slider = (Slider) mouseMotionHandler.getOnComponent();
			
			slider.setSliderX(slider.getSliderDx());
		}
	}
}