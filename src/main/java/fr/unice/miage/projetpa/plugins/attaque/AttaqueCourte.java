package fr.unice.miage.projetpa.plugins.attaque;

import fr.unice.miage.projetpa.Plugin;
import fr.unice.miage.projetpa.Robot;
import fr.unice.miage.projetpa.Plugin.Type;

@Plugin(Nom="PluginAttTest",Type=Type.Attaque,actif=true)
public class AttaqueCourte {
	
	private int degat = 10;
	private int energyUse = 15;
	/**
	 * Attaque d'un robot par un autre
	 * @param emetteur
	 * @param recepteur
	 */
	public void attaque(Robot emetteur, Robot recepteur) {
		emetteur.setEnergy(emetteur.getEnergy() - energyUse);
		recepteur.setLife(recepteur.getLife() - degat);
		
		//todo recepteur perd de l'energie que s'il est touché (dans le carre ou legerement plus loin de emetteur
	}
}
