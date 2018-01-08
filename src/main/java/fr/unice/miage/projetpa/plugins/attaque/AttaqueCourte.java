package fr.unice.miage.projetpa.plugins.attaque;

import fr.unice.miage.projetpa.Robot;
import fr.unice.miage.projetpa.plugins.core.Plugin;
import fr.unice.miage.projetpa.plugins.core.PluginInfos;

/**
 * Ce plugin permet a un robot d'effectuer une attaque courte sur un autre robot
 * 
 * @author Melvin
 *
 */
@Plugin(Nom = "attaqueCourte", Type = Plugin.Type.Attaque)
public class AttaqueCourte {

	private int degat = 10;
	private int energyUse = 5;

	/**
	 * Le robot emetteur attaque le robot recepteur avec une attaque courte
	 * 
	 * @param emetteur
	 * @param recepteur
	 */
	@PluginInfos(name = "attaque", who = "Robot")
	public void attaque(Robot emetteur, Robot recepteur) {
		if (emetteur.getEnergy() >= energyUse) {
			System.out.println(emetteur.getName() + " attaque courte " + recepteur.getName());
			emetteur.setEnergy(emetteur.getEnergy() - energyUse);
			recepteur.setLife(recepteur.getLife() - degat);
			System.out.println("Vie de " + recepteur.getName() + " : " + recepteur.getLife());
			System.out.println("Energy de " + emetteur.getName() + " : " + emetteur.getEnergy());
		} else {
			System.out.println("Energie de " + emetteur.getName() + " est insuffisante !");
		}
		// todo recepteur perd de l'energie que s'il est touché (dans le carre ou
		// legerement plus loin de emetteur
	}
}
