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
	 * Constructeur par defaut d'une grille 10 par 10
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
	
	public Grille(int nbLignes, int nbColonnes) throws Throwable {
		// Controler la validite des parametres
		if (nbLignes < 5)
			throw new Throwable("-2.1");
		if (nbColonnes < 5)
			throw new Throwable("-2.2");
		// Initialiser l'attribut
		GridLayout gridLayout = new GridLayout(App.arenaSize, App.arenaSize);
		this.setLayout(gridLayout);
		matrice = new Cell[nbLignes][nbColonnes];
		for (int i = 0; i < nbLignes; i++) {
			for (int j = 0; j < nbColonnes; j++) {
				Cell cell = new Cell();
				cell.setColor(Color.WHITE);
				matrice[j][i] = cell;
				this.add(cell);
			}
		}
	}
	
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
	
	public boolean isOnSameLineOrColumn(Cell c1, Cell c2) {
		if(c1.getX() == c2.getX() || c1.getY() == c2.getY()) {
			return true;
		} else return false;
	}
	
	public int getDistance(Cell c1, Cell c2) {
		if(isOnSameLineOrColumn(c1, c2)) {
			if(c1.getX() == c2.getX()) {
				return Math.abs(c1.getY() - c2.getY());
			} else {
				if(c1.getY() == c2.getY()) {
					return Math.abs(c1.getX() - c2.getX());
				}
			}
		} 
		return -1;
	}
	
	public int getNbLignes() {
		return matrice.length;
	}

	public int getNbColonnes() {
		return matrice[0].length;
	}
	
	public Cell getCell(int ligne, int colonne) throws Throwable {
		// Controler la validite des parametres
//		if (ligne < 1 || ligne > getNbLignes())
//			throw new Throwable("-2.1");
//		if (colonne < 1 || colonne > getNbColonnes())
//			throw new Throwable("-2.2");
		return matrice[ligne - 1][colonne - 1];
	}

}
