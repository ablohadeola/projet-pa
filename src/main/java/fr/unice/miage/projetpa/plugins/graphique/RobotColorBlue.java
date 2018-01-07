package fr.unice.miage.projetpa.plugins.graphique;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RobotColorBlue {

	int colorR() default 0;
	int colorG() default 0;
	int colorB() default 255;
	
}
