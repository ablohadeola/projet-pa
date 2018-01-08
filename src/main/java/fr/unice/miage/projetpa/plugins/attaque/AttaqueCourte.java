package fr.unice.miage.projetpa.plugins.attaque;

import fr.unice.miage.projetpa.Robot;
import fr.unice.miage.projetpa.plugins.core.Plugin;
import fr.unice.miage.projetpa.plugins.core.Plugin.Type;
import fr.unice.miage.projetpa.plugins.core.PluginInfos;

@Plugin(Nom = "attaqueCourte", Type = Plugin.Type.Attaque)
public class AttaqueCourte {
	
	private int degat = 10;
	private int energyUse = 15;
	/**
	 * Attaque d'un robot par un autre
	 * @param emetteur
	 * @param recepteur
	 */
	@PluginInfos(Type = Plugin.Type.Attaque, who = "Robot")
	public void attaque(Robot emetteur, Robot recepteur) {
		System.out.println("Vie recepteur de l'attaque avant attaque : "+recepteur.getLife());
		emetteur.setEnergy(emetteur.getEnergy() - energyUse);
		recepteur.setLife(recepteur.getLife() - degat);
		System.out.println("Vie recepteur de l'attaque apres attaque : "+recepteur.getLife());
		
		//todo recepteur perd de l'energie que s'il est touché (dans le carre ou legerement plus loin de emetteur
	}
}
