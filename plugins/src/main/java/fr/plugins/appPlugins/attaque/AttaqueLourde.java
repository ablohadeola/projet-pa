package fr.plugins.appPlugins.attaque;

import fr.plugins.appPlugins.Plugin;
import fr.plugins.appPlugins.PluginInfos;
import fr.plugins.appPlugins.Plugin.Type;

/**
 * Ce plugin permet a un robot d'effectuer une attaque lourde sur un autre robot
 * 
 * @author Melvin
 *
 */
@Plugin(Nom = "attaqueLourde", Type = "attaque", TypeOf = Type.Attaque)
public class AttaqueLourde {

	private int degat = 25;
	private int energyUse = 50;
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
