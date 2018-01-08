package fr.unice.miage.projetpa.plugins.core;

import java.lang.annotation.Retention;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PluginInfos {
	fr.unice.miage.projetpa.plugins.core.Plugin.Type Type();
	String who();
}
