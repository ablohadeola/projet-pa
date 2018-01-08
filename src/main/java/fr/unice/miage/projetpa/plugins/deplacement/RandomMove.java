package fr.unice.miage.projetpa.plugins.deplacement;

import java.util.Random;

import fr.unice.miage.projetpa.plugins.core.Plugin;
import fr.unice.miage.projetpa.plugins.core.Plugin.Type;
import fr.unice.miage.projetpa.plugins.core.PluginInfos;

@Plugin(Nom = "RandomMove", Type = Type.Deplacement)
public class RandomMove {
	
	@PluginInfos(Type = Plugin.Type.Deplacement, who = "Robot")
	public int nextMove() {
		Random rnd = new Random();
        int value = rnd.nextInt(4) + 1;
        System.out.println("nextMove : " + value);
        return value;
	}
}
