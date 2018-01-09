package fr.unice.miage.projetpa.plugins.deplacement;

import fr.unice.miage.projetpa.plugins.core.Plugin;
import fr.unice.miage.projetpa.plugins.core.PluginInfos;
import fr.unice.miage.projetpa.*;

@Plugin(Nom="AvantEtArriereMove",Type=Plugin.Type.Deplacement)
public class AvantEtArriereMove {


	@PluginInfos(name="nextMove",who="Robot")
	public int move(Robot r, int maxWidth, int maxHeight) {
		if(r.getDepX() >= 0) {
			if(r.getPosX() + 1 > maxWidth) {
				r.setDepX(-1);
				return 3;
			}			
			return 4;
		} else {
			if(r.getPosX()  < 2) {
				r.setDepX(1);
				return 4;
			} 
			return 3;
		}
	}

}