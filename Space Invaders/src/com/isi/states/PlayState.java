package com.isi.states;

import java.awt.Color;
import java.awt.Graphics2D;

import com.isi.core.Game;
import com.isi.entities.Player;
import com.isi.enums.Difficulty;
import com.isi.hud.HUD;
import com.isi.management.AlienManager;
import com.isi.management.EntityManager;
import com.isi.tools.AssetsHolder;
import com.isi.tools.Text;
import com.isi.uicomponents.Button;
import com.isi.uicomponents.Label;

public class PlayState extends GameState {

	private EntityManager entityManager;
	private AlienManager alienManager;
	
	private Player player;
	
	private HUD hud;
	
	private Label lost;
	private Button reset;
	private Button main;
	
	public PlayState(Game game, Difficulty dif) {
		super(game);
		
		entityManager = new EntityManager();
		alienManager = new AlienManager(game, this, dif);
		
		player = new Player(game, this, game.getWidth() / 2 - 80 / 2, game.getHeight() - 80, 80, 80);
		entityManager.add(player);
		
		hud = new HUD(game, this);	
		
		Text text = new Text("</ You Lost >");
		text.setColor(Color.white);
		lost = new Label(game, this, game.getWidth() / 2 - 400 / 2, game.getHeight() / 2 - 150 / 2, 400, 150, text);
		reset = new Button(game, this, game.getWidth() / 2 - 200 / 2, (game.getHeight() / 2 - 100 / 2) - 70, 200, 100, new Text("Restart"));
		main = new Button(game, this, game.getWidth() / 2 - 200 / 2, (game.getHeight() / 2 - 100 / 2) + 70, 200, 100, new Text("Main Menu"));
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public AlienManager getAlienManager(){
		return alienManager;
	}
	
	public Player getPlayer() {
		return player;
	}

	public Difficulty getDifficulty() {
		return alienManager.getDifficulty();
	}
	
	@Override
	public void tick() {
		if (player.isAlive()) {
			entityManager.tick();
			alienManager.tick();
			hud.tick();
		} else {
			reset.tick();
			main.tick();
		}
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(AssetsHolder.images.get("Play Background").get(0), 0, 0, game.getWidth(), game.getHeight(), null);
		entityManager.draw(g);
		alienManager.draw(g);
		hud.draw(g);
		
		if (!player.isAlive()) {
			lost.draw(g);
			reset.draw(g);
			main.draw(g);
		}
	}	
}