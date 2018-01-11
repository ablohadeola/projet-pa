package fr.plugins.appPlugins.attaque;

import fr.plugins.appPlugins.Plugin;
import fr.plugins.appPlugins.Plugin.Type;
import fr.plugins.appPlugins.PluginInfos;

/**
 * Ce plugin permet a un robot d'effectuer une attaque courte sur un autre robot
 * 
 * @author Melvin
 *
 */
@Plugin(Nom = "attaqueCourte", Type = "attaque", TypeOf = Type.Attaque)
public class AttaqueCourte {

	private int degat = 10;
	private int energyUse = 5;
	private int distanceAtk = 1;

	@PluginInfos(name = "getDegat")
	public int getDegat() {
		return degat;
	}
	
	@PluginInfos(name = "getEnergyUse")
	public int getEnergyUse() {
		return energyUse;
	}
	
	@PluginInfos(name = "getDistanceAtk")
	public int getDistanceAtk() {
		return distanceAtk;
	}
}
