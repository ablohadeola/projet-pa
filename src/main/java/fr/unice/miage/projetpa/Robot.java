package fr.unice.miage.projetpa;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JComponent;

import fr.unice.miage.projetpa.plugins.core.Plugin.Type;

public class Robot extends JComponent{

	public static final int INITIAL_LIFE = 100;
	
	private String name;
	private int life = INITIAL_LIFE;
	private int energy = 100;
	private Color color;
	private int posX;
	private int posY;
	private int depX = 0;
	private int depY = 0;
	private DepType depType;
	private AtkType atkType;
	
	public static enum DepType { ALEATOIRE, INTELLIGENT};
	public static enum AtkType { COURTE, LOURDE, ABSORBE};
	
	private Random rnd = new Random();

	public Robot(String name, DepType depType, AtkType atkType) {
		this.name = name;
		this.posX = rnd.nextInt(700)+50;
		this.posY = rnd.nextInt(700)+50;
		this.depType = depType;
		this.atkType = atkType;
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

	public DepType getDepType() {
		return depType;
	}

	public void setDepType(DepType depType) {
		this.depType = depType;
	}

	public AtkType getAtkType() {
		return atkType;
	}

	public void setAtkType(AtkType atkType) {
		this.atkType = atkType;
	}

	public int getDepX() {
		return depX;
	}

	public void setDepX(int x) {
		depX = x;
	}

	public int getDepY() {
		return depY;
	}

	public void setDepY(int y) {
		depY = y;
	}
	
}
