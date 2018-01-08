package fr.unice.miage.projetpa;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JComponent;

import fr.unice.miage.projetpa.plugins.core.Plugin.Type;

public class Robot extends JComponent{
	
	private String name;
	private int life = 100;
	private int energy = 100;
	private Color color;
	private int posX;
	private int posY;
	private depType depType;
	
	public static enum depType { Aleatoire, Intelligent};
	
	private Random rnd = new Random();

	public Robot(String name, depType depType) {
		this.name = name;
		this.posX = rnd.nextInt(700)+50;
		this.posY = rnd.nextInt(700)+50;
		this.depType = depType;
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

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}


	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public String getName() {
		return name;
	}

	public int getEnergy() {
		return energy;
	}

	public depType getDepType() {
		return depType;
	}

	public void setDepType(depType depType) {
		this.depType = depType;
	}
	
}
