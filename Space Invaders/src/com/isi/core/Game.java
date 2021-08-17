package com.isi.core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.IOException;

import javax.swing.JPanel;

import com.isi.handlers.KeyHandler;
import com.isi.handlers.Mouse;
import com.isi.management.GameStateManager;
import com.isi.states.GameState;
import com.isi.states.MainMenuState;
import com.isi.tools.AssetsHolder;

public class Game extends JPanel implements Runnable {

    private static final long serialVersionUID = 1L;

    private final int WIDTH = 800, HEIGHT = 600;
    
    public static Font font = null;

    static {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, Game.class.getResourceAsStream("/com/isi/assets/Font.otf"));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        font = font.deriveFont(46f);
    }

    private GameStateManager gameStateManager;
    
    private Mouse mouse;
    
    private Thread thread;

    public Game() {   	
    	mouse = new Mouse();
    	
        addMouseListener(mouse.getMouseHandler());
    	addMouseMotionListener(mouse.getMouseHandler());
        addKeyListener(new KeyHandler(this));

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();

        AssetsHolder.loadAssets();
        
        gameStateManager = new GameStateManager();
        gameStateManager.push(new MainMenuState(this));

        thread = new Thread(this);
        thread.start();
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }

    public GameStateManager getGameStateManager() {
        return gameStateManager;
    }

    public GameState getState() {
        return gameStateManager.peek();
    }

    public Mouse getMouse() {
        return mouse;
    }

    public void tick() {
        gameStateManager.tick();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));

        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        gameStateManager.draw(g2d);
    }

    @Override
    public void run() {
        while (true) {
            tick();
            repaint();

            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}