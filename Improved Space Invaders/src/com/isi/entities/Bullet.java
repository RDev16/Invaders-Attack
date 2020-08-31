package com.isi.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.isi.core.Game;
import com.isi.states.GameState;
import com.isi.states.PlayState;

public class Bullet extends Entity {

	private Entity owner;
	
	public Bullet(Game game, GameState state, int x, int y, int width, int height, Entity owner) {
		super(game, state, x, y, width, height);
		
		this.owner = owner;
	}

	public void collisionDetection() {
		PlayState s = (PlayState) state;
		
		if (owner instanceof Player) {
			Player player = (Player) owner;
			ArrayList<Alien> aliens = s.getAlienGroup().getAliens();
			
			for (int i = 0; i < aliens.size(); i++) {
				Alien alien = aliens.get(i);
			
				if (hitbox.intersects(alien.getHitbox())) {
					// Killing the alien
					alien.setAlive(false);
					
					// Destroying the bullet that killed the alien
 					s.getEntityManager().remove(this);
					player.setBulletToNull();
					break;
				}
			}
		} else {
			if (hitbox.intersects(s.getPlayer().getHitbox())) {
				Alien alien = (Alien) owner;
				
				// The alien's bullet hurting the player
				s.getPlayer().hurt();
			
				// Destroying the bullet that hurting the player
				s.getEntityManager().remove(this);
				alien.setBulletToNull();
			}
		}
	}
	
	@Override
	public void tick() {
		PlayState s = (PlayState) state;
		
		collisionDetection();
		
		if (owner instanceof Player) {
			yVel = -4;
			y += yVel;	
		} else {
			yVel = 4;
			y += yVel;
			yVel = 0;
		}
		hitbox.x = x;
		hitbox.y = y;
		
		if (y < 0 || y + height > game.getWidth()) {
			s.getEntityManager().remove(this);

			if (owner instanceof Player) ((Player) owner).setBulletToNull(); else ((Alien) owner).setBulletToNull();
		}
	}

	@Override
	public void draw(Graphics2D g) {	
		g.setColor(Color.white);
		g.fillRect(x, y, width, height);
	} 
}