package fr.unice.miage.projetpa;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import fr.unice.miage.projetpa.plugins.core.PluginInfos;

/**
* Il s'agit d'un JPanel, qui affiche une quantite entre 0 et 100
* dans la forme d'une barre. Peut etre utilise pour representer la vie
* ou bien l'energie d'un robot.
*/
public class Barre extends JPanel {
	
	private Color color;
	/* Les cellules de barre (nb : 100) pour representer this.number */
	private CellForBar[] cases; 
	/** A quantite a representer */
	private int number;

	public Barre(Color color, int n) {
		this.setLayout(new GridLayout(1, 100));
		cases = new CellForBar[100];
		this.number = n;
//		this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		this.color = color;
		for(int i=0; i<100; i++) {
			CellForBar jp = new CellForBar();
			jp.setColor(color);
			CellForBar blank = new CellForBar();
			blank.setColor(Color.WHITE);
			if(i>n) {
				cases[i] = blank;
				this.add(blank);
			} else {
				cases[i] = jp;
				this.add(jp);
			}
		}
	}
	
	/** Remplir le number premier cellules de la barre avec color
	* et les autres avec blanc
	*/
	public void update() {
		this.removeAll();
		for(int i=0; i<100; i++) {
			CellForBar jp = new CellForBar();
			jp.setColor(color);
			CellForBar blank = new CellForBar();
			blank.setColor(Color.WHITE);
			if(i>number) {
				cases[i] = blank;
				this.add(blank);
			} else {
				cases[i] = jp;
				this.add(jp);
			}
		}
    }

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
}
