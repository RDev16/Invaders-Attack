package com.isi.handlers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import com.isi.core.Game;
import com.isi.states.MainMenuState;
import com.isi.states.OptionsState;
import com.isi.uicomponents.Slider;
import com.isi.uicomponents.UIComponent;

public class MouseMotionHandler extends MouseMotionAdapter {

	private Game game;
	
	private int x;
	private int y;
	
	private int dx;
	private int dy;
	
	private UIComponent onComponent;
	
	public MouseMotionHandler(Game game) {
		this.game = game;
	}
	
	public UIComponent getOnComponent() {
		return onComponent;
	}
	
	public void onComponent() {
		boolean flag = false;
		
		if (game.getState() instanceof MainMenuState) {
			MainMenuState state = (MainMenuState) game.getState();

			for (UIComponent c : state.getComponents()) {
				if (x >= c.getX() && x <= c.getX() + c.getWidth() && y >= c.getY() && y <= c.getY() + c.getHeight()) {
					onComponent = c;
					c.setCursorOn(true);
					flag = true;
				} else {
					c.setCursorOn(false);
				}
			}
		} else if (game.getState() instanceof OptionsState) {
			OptionsState state = (OptionsState) game.getState();

			for (UIComponent c : state.getComponents()) {
				if (x >= c.getX() && x <= c.getX() + c.getWidth() && y >= c.getY() && y <= c.getY() + c.getHeight()) {
					onComponent = c;
					c.setCursorOn(true);
					flag = true;
				} else {
					c.setCursorOn(false);
				}
			}
		}
		
		if (!flag) {
			onComponent = null;
		}
	}
	
	public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		
		onComponent();
	}

	public void mouseDragged(MouseEvent e) {
		dx = e.getX();
		dy = e.getY();
		
		onComponent();
		
		if (onComponent instanceof Slider) {
			Slider slider = (Slider) onComponent;
			
			if (x >= slider.getSliderX() && x <= slider.getSliderX() + slider.getSliderWidth() && 
				y >= slider.getSliderY() && y <= slider.getSliderY() + slider.getSliderHeight()) {
				slider.setSliderDx(slider.getSliderX() + (dx - x));			
			}
		}
	}
}