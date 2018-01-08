package fr.unice.miage.projetpa;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Plugin {
		String Name();
		Type Type();
		static enum Type { Graphique, Attaque, Deplacement};
		boolean actif();
}
