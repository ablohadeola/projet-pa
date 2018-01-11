package fr.plugins.appPlugins;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Plugin {
	String Nom();
	String Type();
	Type TypeOf();
	static enum Type { Graphique, Attaque, Deplacement};
//	boolean actif();
}
