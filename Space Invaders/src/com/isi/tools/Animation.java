package com.isi.tools;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.isi.enums.AnimationType;

public class Animation {
	
	// Every frame of the animation
	private ArrayList<BufferedImage> frames;

	// Current frame rendered to the screen
	private BufferedImage currentFrame;
	// Current frame index in the array
	private int index;
	
	// Animation speed - time between each frame
	private int speed;
	// Animation timer - count time to swap frames when it reaches the 'speed' number
	private int timer;
	
	// Indicates whether the animation should be looped - When the animation is over it will start again
	private boolean loop;
	// Indicates whether the animation has ended
	private boolean ended;
	
	// Marks that only one image is used
	private boolean single;
	
	private AnimationType animationType;
	
	public Animation(ArrayList<BufferedImage> frames, int speed, boolean loop, AnimationType animationType) {		
		this.frames = frames;

		currentFrame = frames.get(0);
		index = 0;
		
		timer = 0;
		this.speed = speed;
		
		this.loop = loop;
		ended = false;
		
		single = false;

		this.animationType = animationType;

		switch (animationType) {
			case RETRO_FLASHING:
				frames.add(null);
				frames.add(frames.get(0));
				frames.add(null);
				break;
			default:
				break;
		}
	}
	
	public Animation(ArrayList<BufferedImage> frames, int speed, boolean loop) {		
		this.frames = frames;

		currentFrame = frames.get(0);
		index = 0;
		
		timer = 0;
		this.speed = speed;
		
		this.loop = loop;
		ended = false;
		
		single = false;

		animationType = null;
	}
	
	public Animation(BufferedImage currentFrame) {		
		frames = null;
		
		this.currentFrame = currentFrame;
		index = 0;
		
		timer = 0;
		speed = 0;
		
		loop = false;
		ended = false;
		
		single = true;
		
		animationType = null;
	}
	
	public ArrayList<BufferedImage> getFrames() {
		return frames;
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
	
	public boolean isLoop() {
		return loop;
	}
	
	public void setLoop(boolean loop) {
		this.loop = loop;
	}
	
	public boolean isEnded() {
		return ended;
	}
	
	public AnimationType getAnimationType() {
		return animationType;
	}
	
	public void setAnimationType(AnimationType animationType) {
		this.animationType = animationType;
	}

	/**
	 * Renew is called after an unlooped animation is over for 
	 * allowing the animation to be animated again after it's over.
	 */
	public void renew() {
		ended = false;
	}

	/**
	 * Running and managing the animation
	 */
	public void animate() {	
		if (single || ended) return;
		
		if (speed == 0 || ++timer == speed) {
			timer = 0;
			nextFrame();
		}
	}
	
	/**
	 * called internally to move to the next frame
	 */
	private void nextFrame() {					
		if (index > frames.size() - 1) {		
			if (loop) {
				index = 0;
				currentFrame = frames.get(index);
			} else {
				index = 0;
				timer = 0;
				
				ended = true;
				return;
			}
		} else {
			currentFrame = frames.get(index);
		}
		index++;
	}
}