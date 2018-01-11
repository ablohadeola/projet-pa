package fr.unice.miage.projetpa;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class Grille extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3313385453404797052L;
	private Cell[][] matrice;
	
	/**
	 * Constructeur d'une grille de taille App.arenaSize
	 */
	
	public Grille() {
		GridLayout gridLayout = new GridLayout(App.arenaSize, App.arenaSize);
		this.setLayout(gridLayout);
		matrice = new Cell[App.arenaSize][App.arenaSize];
		for (int i = 0; i < App.arenaSize; i++) {
			for (int j = 0; j < App.arenaSize; j++) {
				Cell cell = new Cell();
				cell.setColor(Color.WHITE);
				matrice[j][i] = cell;
				this.add(cell);
			}
		}
	}
	
	public Grille constructor() {
		return new Grille();
	}
	
	//Parcours toutes les cells afin de trouver l'emplacement des robots et d'affecter leurs couleurs aux cellules
	public void update() {
		for (int i = 0; i < getNbLignes(); i++) {
			for (int j = 0; j < getNbColonnes(); j++) {
				Cell cell = matrice[j][i];
				if(cell.hasRobotOnIt()){
					matrice[j][i].setColor(cell.getRobot().getColor());
					this.revalidate();
					this.repaint();
				}
			}
		}
	}
	
	//Si deux cellules se trouvent sur une meme ligne ou meme colonne
	public boolean isOnSameLineOrColumn(Cell c1, Cell c2) {
		if(c1.getX() == c2.getX() || c1.getY() == c2.getY()) {
			return true;
		} else return false;
	}
	
	public int getNbLignes() {
		return matrice.length;
	}

	public int getNbColonnes() {
		return matrice[0].length;
	}
	
	public Cell getCell(int ligne, int colonne) throws Throwable {
		return matrice[ligne - 1][colonne - 1];
	}

}
