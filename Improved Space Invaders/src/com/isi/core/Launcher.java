package com.isi.core;

import javax.swing.JFrame;

public class Launcher extends JFrame {

	private static final long serialVersionUID = 1L;

	public Launcher() {
		add(new Game());
		
		setTitle("Improved Space Invaders");
		
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		Launcher launcher = new Launcher();
		launcher.setVisible(true);
	}
}