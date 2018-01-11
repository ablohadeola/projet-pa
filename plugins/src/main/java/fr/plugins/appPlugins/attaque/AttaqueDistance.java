package fr.plugins.appPlugins.attaque;

import fr.plugins.appPlugins.Plugin;
import fr.plugins.appPlugins.PluginInfos;
import fr.plugins.appPlugins.Plugin.Type;

/**
 * Ce plugin permet a un robot d'effectuer une attaque a distance sur un autre robot
 * 
 * @author Melvin
 *
 */
@Plugin(Nom = "AttaqueDistance", Type = "attaque", TypeOf = Type.Attaque)
public class AttaqueDistance {

	private int degat = 2;
	private int energyUse = 25;
	private int distanceAtk = (int) Math.abs(Math.ceil(10 - (10 / 3)));
	
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
