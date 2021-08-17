package com.isi.handlers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class Mouse {

	private MouseHandler mouseHandler;
	
	private int x;
	private int y;
	
	private boolean[] pressedButtons;
	
	public Mouse() {
		mouseHandler = new MouseHandler();
		
		x = 0;
		y = 0;
		
		pressedButtons = new boolean[3];
	}
	
	public MouseHandler getMouseHandler() {
		return mouseHandler;
	}

	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public boolean pressedButton(int button) {
		return pressedButtons[button];
	}
	
	private class MouseHandler extends MouseAdapter implements MouseMotionListener {
		
		public MouseHandler() {}
		
		public void mousePressed(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON1)
				pressedButtons[0] = true;
		}
		
		public void mouseReleased(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON1)
				pressedButtons[0] = false;		
		}
		
		public void mouseMoved(MouseEvent e) {
			x = e.getX();
			y = e.getY();
		}
		
		public void mouseDragged(MouseEvent e) {
			x = e.getX();
			y = e.getY();
		}
	}
}