package com.isi.tools;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import com.isi.uicomponents.UIComponent;

public class Text {

	private Font font;
	private Color color;
	private String string;
	
	private int x;
	private int y;
	
	public Text(Font font, Color color, String string, int x, int y) {
		this.font = font;
		this.color = color;
		this.string = string;
		
		this.x = x;
		this.y = y;
	}

	public Text(Font font, Color color, String string) {
		this.font = font;
		this.color = color;
		this.string = string;
		
		x = 0;
		y = 0;
	}

	public Text(Font font, String string) {
		this.font = font;
		this.color = Color.black;
		this.string = string;
		
		x = 0;
		y = 0;
	}
	
	public Text(Color color, String string) {
		font = new Font("Arial", Font.BOLD, 16);
		this.color = color;
		this.string = string;
		
		x = 0;
		y = 0;
	}
	
	public Text(String string) {
		font = new Font("Arial", Font.BOLD, 16);
		color = Color.black;
		this.string = string;
		
		x = 0;
		y = 0;
	}

	public Text() {
		font = new Font("Arial", Font.BOLD, 16);
		color = Color.black;
		string = null;		
		
		x = 0;
		y = 0;
	}
	
	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public String getString() {
		return string;
	}
	
	public void setString(String string) {
		this.string = string;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void centerOnComponent() {
		
	}
	
	public void centerOnComponent(UIComponent component, Graphics2D g) {	
		g.setFont(font);
		FontMetrics fm = g.getFontMetrics();
		
		x = component.getX() + component.getWidth() / 2 - fm.stringWidth(string) / 2;
		y = component.getY() + component.getHeight() / 2 + fm.getDescent();
	}
	
	public void draw(Graphics2D g) {
		if (string != null) {
			g.setFont(font);
			g.setColor(color);
			g.drawString(string, x, y);
		}
	}
}