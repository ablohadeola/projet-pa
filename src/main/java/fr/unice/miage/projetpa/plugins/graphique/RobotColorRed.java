package fr.unice.miage.projetpa.plugins.graphique;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RobotColorRed {

	int colorR() default 255;
	int colorG() default 0;
	int colorB() default 0;
	
}
