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
   	
   	  matrice= new Cellule[10][10];
   	  
   	  for (int i=0; i<5; i++)
   	     for (int j=0; j<5; j++) {
   	     
   	        try {matrice[i][j]= new Cellule(i+1,j+1);}
   	        catch(Throwable e){return;}
   	     }
   } 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

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
		      
		      //-- 		Methodes getter et setter 
		      /**
		      *
		      * @return
		      */
		        
		       public int getLigne() {return ligne;}

		     /**
		      *
		      * @return
		      */
		     public int getColonne() {return colonne;}
		       
		     /**
		      *
		      * @return
		      */
		     public Object getSymbole() {return symbole;}

		     /**
		      *
		      * @return
		      */
		     public Object getCouleur() {return couleur;}

		     /**
		      *
		      * @return
		      */
		     public Object getMarque() {return marque;}

		     /**
		      *
		      * @return
		      */
		     public int    getPoids() {return poids;}
		    
		    // ---                                    Accesseurs de modification
		    
		     /**
		      *
		      * @param symbole
		      */
		        
		       public void setSymbole(Object symbole) {this.symbole= symbole;}

		     /**
		      *
		      * @param couleur
		      */
		     public void setCouleur(Object couleur) {this.couleur= couleur;}

		     /**
		      *
		      * @param marque
		      */
		     public void setMarque (Object marque)  {this.marque= marque;}

		     /**
		      *
		      * @param poids
		      */
		     public void setPoids  (int    poids)   {this.poids= poids;}
		       
		     /**
		      *
		      */
		     public void resetSymbole() {symbole= null;}

		     /**
		      *
		      */
		     public void resetCouleur() {couleur= null;}

		     /**
		      *
		      */
		     public void resetMarque () {marque= null;}

		     /**
		      *
		      */
		     public void resetPoids  () {poids= 0;}
		    
		      
		      
		      
		      
		      
		      
		   }
	
	
	
	
}
