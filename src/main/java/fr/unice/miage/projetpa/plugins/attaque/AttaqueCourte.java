package fr.unice.miage.projetpa.plugins.attaque;

import fr.unice.miage.projetpa.Robot;
import fr.unice.miage.projetpa.plugins.core.Plugin;
import fr.unice.miage.projetpa.plugins.core.PluginInfos;

@Plugin(Nom = "attaqueCourte", Type = Plugin.Type.Attaque)
public class AttaqueCourte {

	private int degat = 10;
	private int energyUse = 5;

	/**
	 * Attaque d'un robot par un autre
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
