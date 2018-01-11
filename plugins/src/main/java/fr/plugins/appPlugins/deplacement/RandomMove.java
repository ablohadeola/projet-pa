package fr.plugins.appPlugins.deplacement;

import java.util.Random;

import fr.plugins.appPlugins.Plugin;
import fr.plugins.appPlugins.PluginInfos;
import fr.plugins.appPlugins.Plugin.Type;

@Plugin(Nom = "RandomMove", Type = "move", TypeOf = Type.Deplacement)
public class RandomMove {
	
	@PluginInfos(name = "nextMove")
	public int nextMove() {
		Random rnd = new Random();
        int value = rnd.nextInt(4) + 1;
        return value;
	}
}
