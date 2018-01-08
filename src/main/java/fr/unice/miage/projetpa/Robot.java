package fr.unice.miage.projetpa;

import java.awt.Color;
import java.util.Random;

public class Robot{
	
	private String name;
	private int life = 100;
	private int energy = 100;
	private Color color;
	private int posX;
	private int posY;
	private DepType depType;
	private AtkType atkType;
	
	public static enum DepType { ALEATOIRE, INTELLIGENT};
	public static enum AtkType { COURTE, LOURDE};
	
	private Random rnd = new Random();

	public Robot(String name, DepType depType, AtkType atkType) {
		this.name = name;
		this.posX = rnd.nextInt(App.arenaSize)+1;
		this.posY = rnd.nextInt(App.arenaSize)+1;
		this.depType = depType;
		this.atkType = atkType;
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
	
}
