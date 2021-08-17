package com.isi.management;

import java.awt.Graphics2D;
import java.util.Stack;

import com.isi.states.GameState;

public class GameStateManager {

	private Stack<GameState> states;
	
	public GameStateManager() {
		states = new Stack<GameState>();
	}
	
	public void push(GameState state) {
		states.add(state);
	}
	
	public GameState pop() {
		return states.pop();
	}
	
	public GameState peek() {
		return states.peek();
	}
	
	public void tick() {
		states.peek().tick();
	}
	
	public void draw(Graphics2D g) {
		states.peek().draw(g);
	}
}