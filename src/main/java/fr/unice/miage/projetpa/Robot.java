package fr.unice.miage.projetpa;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JComponent;

public class Robot extends JComponent{
	
	private String name;
	private int life = 100;
	private int energy = 100;
	private Color color;
	private int posX;
	private int posY;
	
	private Random rnd = new Random();

	public Robot(String name) {
		this.name = name;
		this.posX = rnd.nextInt(700)+50;
		this.posY = rnd.nextInt(700)+50;
	}
	
	public void paint(Graphics g) {
		this.posX = rnd.nextInt(700)+50;
		this.posY = rnd.nextInt(700)+50;
		System.out.println(posX + " " + posY);
	    g.drawRect (posX, posY, 50, 50);  
	}
	
	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
}
