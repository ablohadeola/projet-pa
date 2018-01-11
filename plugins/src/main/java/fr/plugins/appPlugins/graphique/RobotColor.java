package fr.plugins.appPlugins.graphique;

import java.awt.Color;
import java.util.Random;

import fr.plugins.appPlugins.Plugin;
import fr.plugins.appPlugins.PluginInfos;
import fr.plugins.appPlugins.Plugin.Type;

@Plugin(Nom = "RobotColor", Type = "robotcolor", TypeOf = Type.Graphique)
public class RobotColor {
	
	@PluginInfos(name = "getColor")
	public Color getColor() {
		Random rand = new Random();
		float r = rand.nextFloat();
		float g = rand.nextFloat();
		float b = rand.nextFloat();
		return new Color(r, g, b);
	}

}
