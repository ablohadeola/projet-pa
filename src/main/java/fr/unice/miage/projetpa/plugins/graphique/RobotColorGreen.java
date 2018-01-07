package fr.unice.miage.projetpa.plugins.graphique;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RobotColorGreen {

	int colorR() default 0;
	int colorG() default 255;
	int colorB() default 0;
	
}
