package fr.unice.miage.projetpa.plugins.attaque;

import fr.unice.miage.projetpa.Robot;
import fr.unice.miage.projetpa.plugins.core.Plugin;
import fr.unice.miage.projetpa.plugins.core.PluginInfos;

/**
 * Ce plugin permet a un robot d'effectuer une attaque a distance sur un autre robot
 * 
 * @author Melvin
 *
 */
@Plugin(Nom = "AttaqueDistance", Type = Plugin.Type.Attaque)
public class AttaqueDistance {

	private int degat = 2;
	private int energyUse = 25;

	/**
	 * Le robot emetteur attaque le robot recepteur avec une attaque a distance
	 * 
	 * @param emetteur
	 * @param recepteur
	 */
	@PluginInfos(name = "attaque", who = "Robot")
	public boolean attaque(Robot emetteur, Robot recepteur) {
		if (emetteur.getEnergy() >= energyUse) {
			System.out.println(emetteur.getName() + " attaque a distance " + recepteur.getName());
			emetteur.setEnergy(emetteur.getEnergy() - energyUse);
			recepteur.setLife(recepteur.getLife() - degat);
			System.out.println("Vie de " + recepteur.getName() + " : " + recepteur.getLife());
			System.out.println("Energy de " + emetteur.getName() + " : " + emetteur.getEnergy() + "\n");
			return true;
		} else {
			System.out.println("Energie de " + emetteur.getName() + " est insuffisante !\n"
					+ "Passe son tour pour recharger");
			return false;
		}
	}
	
	@PluginInfos(name = "getEnergyUse", who = "Robot")
	public int getEnergyUse() {
		return energyUse;
	}
}
