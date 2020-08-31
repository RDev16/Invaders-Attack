package com.isi.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.isi.core.Game;
import com.isi.enums.Level;
import com.isi.states.GameState;

public class AliensGroup extends Entity {

	private ArrayList<Alien> aliens;
	
	private Level level;
	private int xVel;
	private int shootingCooldown;
	
	private int shootingTimer;
	private boolean direction;
	
	public AliensGroup(Game game, GameState state, Level level) {
		super(game, state, 50, 50, game.getWidth() - 50 * 5 - 5, 50 * 4 - 35);
		
		aliens = new ArrayList<Alien>();
		createGruop();
		
		this.level = level;
		
		switch (level) {
			case EASY:
				xVel = 1;
				shootingCooldown = 200;
				break;
			case MEDIUM:
				xVel = 2;
				shootingCooldown = 150;				
				break;
			case HARD:
				xVel = 4;
				shootingCooldown = 80;								
				break;
			case BRUTAL:
				xVel = 6;
				shootingCooldown = 40;												
				break;
		}
		shootingTimer = 0;
		direction = false;
	}
	
	public ArrayList<Alien> getAliens() {
		return aliens;
	}
	
	public Level getLevel() {
		return level;
	}
	
	public int getShootingCooldown() {
		return shootingCooldown;
	}

	public int getShootingTimer() {
		return shootingCooldown;
	}
	
	public void add(Alien a) {
		aliens.add(a);
	}

	public void remove(Alien a) {
		aliens.remove(a);
	}
	
	public void createGruop() {
		for (int i = 1; i < 4; i++) {		
			for (int j = 1; j <= 10; j++) {
				if (i == 1) {
					if (j == 1) {
						aliens.add(new Alien(game, state, x, y * i, i - 1));
					} else {
						aliens.add(new Alien(game, state, x * j + 5 * j - 5, y * i, i - 1));
					}
				} else {
					if (j == 1) {
						aliens.add(new Alien(game, state, x, y * i + 5 * i, i - 1));
					} else {
						aliens.add(new Alien(game, state, x * j + 5 * j - 5, y * i + 5 * i, i - 1));
					}
				}
			}
		}
	}
	
	@Override
	public void tick() {
		int rand = (int) (Math.random() * aliens.size());
		shootingTimer++;

		for (int i = 0; i < aliens.size(); i++) {
			if (direction) aliens.get(i).setxVel(xVel);
			else aliens.get(i).setxVel(-xVel);
			
			if (shootingTimer == shootingCooldown && rand == i) {
				aliens.get(i).setShooting(true);
				shootingTimer = 0;
			}
			aliens.get(i).tick();
		}
		hitbox.x += direction ? xVel : -xVel;
		
		if (hitbox.x + hitbox.width >= game.getWidth()) {
			direction = false;
			
			for (int i = 0; i < aliens.size(); i++) {
				Alien a = aliens.get(i);
				
				a.setyVel(7);
				a.tick();
				a.setyVel(0);
			}
			hitbox.y += 7;
		} else if (hitbox.x <= 0) {
			direction = true;

			for (int i = 0; i < aliens.size(); i++) {
				Alien a = aliens.get(i);

				a.setyVel(7);
				a.tick();
				a.setyVel(0);
			}
			hitbox.y += 7;
		}
	}

	@Override
	public void draw(Graphics2D g) {
		for (Alien a : aliens) {
			a.draw(g);
		}	
		g.setColor(Color.red);
		//g.draw(hitbox);
	}
}