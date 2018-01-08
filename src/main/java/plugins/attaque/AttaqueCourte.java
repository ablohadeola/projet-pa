package plugins.attaque;

import fr.unice.miage.projetpa.Plugin;
import fr.unice.miage.projetpa.Robot;
import fr.unice.miage.projetpa.Plugin.Type;


public class AttaqueCourte {
	
	private int degat = 10;
	private int energyUse = 15;
	/**
	 * Attaque d'un robot par un autre
	 * @param emetteur
	 * @param recepteur
	 */
	@Plugin(Name="plugins.attaque.AttaqueCourte",Type=Type.Attaque,actif=true)
	public void attaque(Robot emetteur, Robot recepteur) {
		System.out.println("Vie recepteur de l'attaque avant attaque : "+recepteur.getLife());
		emetteur.setEnergy(emetteur.getEnergy() - energyUse);
		recepteur.setLife(recepteur.getLife() - degat);
		System.out.println("Vie recepteur de l'attaque apres attaque : "+recepteur.getLife());
		
		//todo recepteur perd de l'energie que s'il est touché (dans le carre ou legerement plus loin de emetteur
	}
}
