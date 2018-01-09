package fr.unice.miage.projetpa.plugins.attaque;

import fr.unice.miage.projetpa.Robot;
import fr.unice.miage.projetpa.plugins.core.Plugin;
import fr.unice.miage.projetpa.plugins.core.PluginInfos;

/**
 * Ce plugin permet a un robot d'absorber les points de degats d'un autre robot
 * 
 * @author Chamir
 *
 */
@Plugin(Nom = "AttaqueAbsorbeVie", Type = Plugin.Type.Attaque)
public class AttaqueAbsorbeVie {

	public static final int DEGATS_ABSORBES = 15;
	public static final int ENERGIE_UTILISE = 25;

	/**
	 * Le robot emetteur attaque le robot recepteur avec une attaque courte
	 * 
	 * @param emetteur
	 * @param recepteur
	 */
	@PluginInfos(name = "attaque", who = "Robot")
	public boolean attaque(Robot emetteur, Robot recepteur) {
		if (emetteur.getEnergy() >= ENERGIE_UTILISE) {
			System.out.println(emetteur.getName() + " absorbe la vie de " + recepteur.getName());
			emetteur.setEnergy(emetteur.getEnergy() - ENERGIE_UTILISE);
			recepteur.setLife(recepteur.getLife() - DEGATS_ABSORBES);
			emetteur.setLife(emetteur.getLife() + DEGATS_ABSORBES);
			System.out.println("Vie de " + recepteur.getName() + " : " + recepteur.getLife());
			System.out.println("Vie de " + emetteur.getName() + " : " + emetteur.getLife());
			System.out.println("Energy de " + emetteur.getName() + " : " + emetteur.getEnergy());
			return true;
		} else {
			System.out.println("Energie de " + emetteur.getName() + " est insuffisante !");
			return false;
		}
		// todo recepteur perd de l'energie que s'il est touché (dans le carre ou
		// legerement plus loin de emetteur
	}

	@PluginInfos(name = "getEnergyUse", who = "Robot")
	public int getEnergyUse() {
		return ENERGIE_UTILISE;
	}
}
