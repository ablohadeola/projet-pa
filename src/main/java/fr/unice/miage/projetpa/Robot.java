package fr.unice.miage.projetpa;

import java.awt.Color;
import java.util.Random;

import javax.swing.JComponent;

public class Robot extends JComponent{

	/**
	 * 
	 */
	private static final long serialVersionUID = -426502312760795279L;

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
