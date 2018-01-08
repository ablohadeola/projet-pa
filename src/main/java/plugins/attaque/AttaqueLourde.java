package plugins.attaque;

import fr.unice.miage.projetpa.Robot;
import fr.unice.miage.projetpa.plugins.core.Plugin;
import fr.unice.miage.projetpa.plugins.core.PluginInfos;

@Plugin(Nom = "AttaqueLourde", Type = Plugin.Type.Attaque)
public class AttaqueLourde {

	private int degat = 25;
	private int energyUse = 50;

	/**
	 * Attaque d'un robot par un autre
	 * 
	 * @param emetteur
	 * @param recepteur
	 */
	@PluginInfos(name = "attaque", who = "")
	public void attaque(Robot emetteur, Robot recepteur) {
		if (emetteur.getEnergy() >= energyUse) {
			System.out.println("Vie recepteur de l'attaque avant attaque : " + recepteur.getLife());
			emetteur.setEnergy(emetteur.getEnergy() - energyUse);
			recepteur.setLife(recepteur.getLife() - degat);
			System.out.println("Vie recepteur de l'attaque apres attaque : " + recepteur.getLife());
		} else {
			System.out.println("Energie de " + emetteur.getName() + " est insuffisante !");
		}

		// todo recepteur perd de l'energie que s'il est touché (dans le carre ou
		// legerement plus loin de emetteur
	}
}
