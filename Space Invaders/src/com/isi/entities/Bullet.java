package com.isi.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.isi.core.Game;
import com.isi.states.GameState;
import com.isi.states.PlayState;

public class Bullet extends Entity {

	// The entity who shot this bullet
	private Entity owner;
	
	public Bullet(Game game, GameState state, int x, int y, int width, int height, Entity owner) {
		super(game, state, x, y, width, height);
		
		this.owner = owner;
		
		if (owner instanceof Player) {
			yVel = -4;	
		} else {
			yVel = 4;
			y += yVel;
		}
	}
	
	public Entity getOwner() {
		return owner;
	}
	
	// Destroying the bullet that killed the alien
	private void destroy(PlayState s) {
		visible = false;
		s.getEntityManager().remove(this);		
	}

	public void collisionDetection() {
		PlayState s = (PlayState) state;
				
		// Checks if the bullet is still visible
		if (y < 0 || y + height > game.getWidth()) {
			// Destroying this bullet
			destroy(s);
			return;
		}
		
		// Checks if the player is hit by aliens
		if (owner instanceof Alien && hitbox.intersects(s.getPlayer().getHitbox())) {
			// The alien's bullet hitting the player
			s.getPlayer().hit();
			destroy(s);
		} else if (owner instanceof Player) {
			// Checks if an alien is shot by the player
			ArrayList<Alien> aliens = s.getAlienManager().getAliens();

			for (int i = 0; i < aliens.size(); i++) {
				Alien a = aliens.get(i);
			
				if (hitbox.intersects(a.getHitbox())) {
					// Killing the alien
					((Alien) a).setAlive(false);					
					// Destroying this bullet
					destroy(s);
					break;
				}
			}
		}
	}
	
	@Override
	public void tick() {
		// Moving the bullet
		y += yVel;
		hitbox.y = y;		
		
		// Collisions check
		collisionDetection();
	}

	@Override
	public void draw(Graphics2D g) {	
		g.setColor(Color.white);
		g.fillRect(x, y, width, height);
	} 
}