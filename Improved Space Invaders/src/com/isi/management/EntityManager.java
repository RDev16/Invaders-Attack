package com.isi.management;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.isi.entities.Entity;

public class EntityManager {

	private ArrayList<Entity> entities;
	
	public EntityManager() {
		entities = new ArrayList<Entity>();
	}
	
	public ArrayList<Entity> getEntities() {
		return entities;
	}
	
	public void add(Entity e) {
		entities.add(e);
	}

	public void remove(Entity e) {
		entities.remove(e);
	}
	
	public void tick() {
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).tick();
		}
	}
	
	public void draw(Graphics2D g) {
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).draw(g);
		}
	}
}