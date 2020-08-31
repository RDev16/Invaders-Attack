package com.isi.handlers;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.isi.core.Game;
import com.isi.states.PlayState;

public class KeyHandler extends KeyAdapter {
	
	private Game game;
	
	public KeyHandler(Game game) {
		this.game = game;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
				
		if (game.getState() instanceof PlayState) {
			PlayState state = (PlayState) game.getState();
			
			if (key == KeyEvent.VK_A) {
				state.getPlayer().setLeft(true);
			}
			
			if (key == KeyEvent.VK_D) {
				state.getPlayer().setRight(true);
			}
			
			if (key == KeyEvent.VK_SPACE) {
				state.getPlayer().setShooting(true);
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();		
	
		if (game.getState() instanceof PlayState) {
			PlayState state = (PlayState) game.getState();
			
			if (key == KeyEvent.VK_A) {
				state.getPlayer().setLeft(false);
			}
			
			if (key == KeyEvent.VK_D) {
				state.getPlayer().setRight(false);
			}
			
			if (key == KeyEvent.VK_SPACE) {
				state.getPlayer().setShooting(false);
			}
		}
	}
}