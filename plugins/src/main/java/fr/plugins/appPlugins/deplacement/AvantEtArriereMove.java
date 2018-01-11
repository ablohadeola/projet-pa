package fr.plugins.appPlugins.deplacement;

import java.util.Random;

import fr.plugins.appPlugins.Plugin;
import fr.plugins.appPlugins.PluginInfos;
import fr.plugins.appPlugins.Plugin.Type;

@Plugin(Nom="AvantEtArriereMove", Type = "move", TypeOf = Type.Deplacement)
public class AvantEtArriereMove {


	@PluginInfos(name="nextMove")
	public int nextMove() {
		int i = new Random().nextInt(2);
		if(i==0) {return 1;} else {return 4;}
	}

}