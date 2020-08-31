package com.isi.states;

import java.awt.Graphics2D;

import com.isi.core.Game;
import com.isi.entities.AliensGroup;
import com.isi.entities.Player;
import com.isi.enums.Level;
import com.isi.hud.HUD;
import com.isi.management.EntityManager;

public class PlayState extends GameState {

	private EntityManager entityManager;
	
	private Player player;
	private AliensGroup alienGroup;
	
	private HUD hud;
	
	public PlayState(Game game) {
		super(game);
		
		entityManager = new EntityManager();
		
		player = new Player(game, this, game.getWidth() / 2 - 80 / 2, game.getHeight() - 85, 80, 80);
		entityManager.add(player);

		alienGroup = new AliensGroup(game, this, Level.EASY);
		entityManager.add(alienGroup);
		
		hud = new HUD(game, this);
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public AliensGroup getAlienGroup(){
		return alienGroup;
	}
	
	public Player getPlayer() {
		return player;
	}

	@Override
	public void tick() {
		entityManager.tick();
		hud.tick();
	}

	@Override
	public void draw(Graphics2D g) {
		entityManager.draw(g);
		hud.draw(g);
	}	
}