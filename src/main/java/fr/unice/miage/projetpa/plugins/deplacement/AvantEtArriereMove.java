package fr.unice.miage.projetpa.plugins.deplacement;

import fr.unice.miage.projetpa.plugins.core.Plugin;
import fr.unice.miage.projetpa.*;

@Plugin(Nom="AvantEtArriereMove",Type=Plugin.Type.Deplacement)
public class AvantEtArriereMove {


	public void move(Robot r, int maxWidth, int maxHeight) {
		if(r.getDepX() >= 0) {
			r.setPosX(r.getPosX() + 1);
			if(r.getPosX() > maxWidth) {
				r.setDepX(-1);
			}
		} else {
			r.setPosX(r.getPosX() - 1);
			if(r.getPosX() < 0) {
				r.setDepX(1);
			}
		}
	}

}