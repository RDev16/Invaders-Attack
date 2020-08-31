package com.isi.tools;

import java.awt.image.BufferedImage;

public class Animation {
	
	private BufferedImage[] frames;
	private BufferedImage currentFrame;
	
	private int index;
	private int timer;
	private int speed;
	
	private boolean animationChanged;
	
	public Animation(BufferedImage[] frames, int speed) {
		this.frames = frames;
		currentFrame = frames[0];
		
		index = 0;
		timer = 0;
		this.speed = speed;
		
		animationChanged = false;
	}
	
	public Animation(BufferedImage[] frames, int speed, boolean animationChanged) {
		this.frames = frames;
		currentFrame = frames[0];
		
		index = 0;
		timer = 0;
		this.speed = speed;
		
		this.animationChanged = animationChanged;
	}
	
	public BufferedImage getCurrentFrame() {
		return currentFrame;
	}
	
	public int getIndex() {
		return index;
	}
	
	public int getTimer() {
		return timer;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public boolean isAnimationChanged() {
		return animationChanged;
	}

	public void setAnimationChanged(boolean animationChanged) {
		this.animationChanged = animationChanged;
	}
	
	public void animate() {
		timer++;
		
		if (timer == speed) {
			timer = 0;
			nextFrame();
		}
	}
	
	public void nextFrame() {
		index++;
		
		if (index > frames.length - 1) {
			index = 0;
			currentFrame = frames[index];
		} else {
			currentFrame = frames[index];
		}
	}
}