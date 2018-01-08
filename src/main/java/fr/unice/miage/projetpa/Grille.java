package fr.unice.miage.projetpa;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
*
* @author Samir Zennani
* @version 1.1.0
*/

public class Grille implements Cloneable {
	
	
	private Cellule[][] matrice;	

	// --------------------------              *** Classe interne Cellule
	   
	public class Cellule {
		   private final int ligne;
		   private final int colonne;
		   private Object    symbole;
		   private Object    couleur;
		   private Object    marque;
		   private int       poids;
		   
		      // ---                                  Constructeur par defaut

		    /**
		     *
		     */
		    
		      public Cellule() {
		         ligne= 1;
		         colonne= 1;
		      }
		      
		      // ---                                        Constructeur normal

		    
		   }
	
	
	
	
}
