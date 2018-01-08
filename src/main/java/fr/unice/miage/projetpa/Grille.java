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
	
	
	
	
	 /**
     * Constructeur par defaut d'une grille 10 par 10
     */
    
   public Grille() {
   	
   	  matrice= new Cellule[5][5];
   	  
   	  for (int i=0; i<5; i++)
   	     for (int j=0; j<5; j++) {
   	     
   	        try {matrice[i][j]= new Cellule(i+1,j+1);}
   	        catch(Throwable e){return;}
   	     }
   }


   public int getNbLignes() {
   		return matrice.length;
   }

   public int getNbColonnes() {
   		return matrice[0].length;
   }

	// --------------------------              *** Classe interne Cellule
	   
	class Cellule {
		private final int ligne;
		private final int colonne;
		private Object    symbole;
		private Object    couleur;
		private Object    marque;
		private int       poids;
	   
    
	    public Cellule() {
	        ligne= 1;
	        colonne= 1;
	    }
	      

	    public Cellule(int ligne, int colonne) throws Throwable {
	    	// Controler la validite des parametres
	        //
	        if (ligne<1 || ligne>getNbLignes()) throw new Throwable("-2.1");
	        if (colonne<1 || colonne>getNbColonnes()) 
	        	throw new Throwable("-2.2");
	          
		          // Memoriser la valeur des attributs
		          //	
		        this.ligne= ligne;
		        this.colonne= colonne;
	       }   
	   }
}
