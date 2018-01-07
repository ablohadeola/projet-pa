package fr.unice.miage.projetpa;

import java.util.Random;

import fr.unice.miage.projetpa.plugins.graphique.RobotColorBlack;
import fr.unice.miage.projetpa.plugins.graphique.RobotColorBlue;
import fr.unice.miage.projetpa.plugins.graphique.RobotColorGreen;
import fr.unice.miage.projetpa.plugins.graphique.RobotColorRed;

public class PluginsCreator {

	@RobotColorBlack
	@RobotColorBlue
	@RobotColorRed
	@RobotColorGreen
	public static void randomColor() {
		System.out.println("Coloring a robot");
	}
	
}
