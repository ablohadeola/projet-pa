package fr.unice.miage.projetpa.plugins.graphique;

import java.awt.Color;

import fr.unice.miage.projetpa.Robot;
import fr.unice.miage.projetpa.plugins.core.Plugin;
import fr.unice.miage.projetpa.plugins.core.Plugin.Type;
import fr.unice.miage.projetpa.plugins.core.PluginInfos;

@Plugin(Nom = "RobotColor", Type = Type.Graphique)
public class RobotColor {
	
	@PluginInfos(name = "setColor", who = "Robot")
	public void setColor(Robot robot, Color c) {
		robot.setColor(c);
	}

}
