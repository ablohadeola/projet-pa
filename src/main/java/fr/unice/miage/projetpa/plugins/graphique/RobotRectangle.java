//package fr.unice.miage.projetpa.plugins.graphique;
//
//import fr.unice.miage.projetpa.Robot;
//import fr.unice.miage.projetpa.plugins.core.*;
//
//
//import java.awt.*;
//
///**
//* Plugin pour dessiner des Robots Rectangulaires
//* @author Chamir
//*/
//@Plugin(Nom ="RobotRectangle", Type=Plugin.Type.Graphique) 
//public class RobotRectangle{
//	
//	/** largeur par defaut */
//	public static final int DEFAULT_WIDTH = 30;
//
//	/** hauteur par defaut */
//	public static final int DEFAULT_HEIGHT = 40;
//
//	private int width;
//	private int height;
//
//	public RobotRectangle(int w,int h) {
//		width = w;
//		height = h;
//	}
//
//	public RobotRectangle() {
//		this(DEFAULT_WIDTH, DEFAULT_HEIGHT);
//	}
//
//	/** Definir la taille du robot */
//	@PluginInfos(name="setTaille",who="Robot")
//	public void setTaille(Robot r) {
//		Dimension d = new Dimension(width, height);
//		r.setSize(d);
//	}
//
//
//}