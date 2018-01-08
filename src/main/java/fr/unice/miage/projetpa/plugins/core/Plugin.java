package fr.unice.miage.projetpa.plugins.core;

public @interface Plugin {
	String Nom();
	Type Type();
	static enum Type { Graphique, Attaque, Deplacement};
//	boolean actif();
}
