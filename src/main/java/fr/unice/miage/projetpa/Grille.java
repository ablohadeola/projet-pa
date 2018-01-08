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


   public int getNbLignes() {
   		return matrice.length;
   }

   public int getNbColonnes() {
   		return matrice[0].length;
   }
   
   
   
   
   public Cellule getCellule(int ligne, int colonne) throws Throwable {
	   	
	      // Controler la validite des parametres
	      //
	      if (ligne<1 || ligne>getNbLignes()) throw new Throwable ("-2.1");
	      if (colonne<1 || colonne>getNbColonnes()) 
	                               throw new Throwable ("-2.2");
	         
	      return matrice[ligne-1][colonne-1];
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
		    
		      
		      
		     // ---            Methodes heritees de Object
		     
		     public Object clone() {
		    	   
		         try {
		             Cellule cel = new Cellule();
		             cel.setCouleur(this.getCouleur());
		             cel.setMarque(this.getMarque());
		             cel.setPoids(this.getPoids());
		             cel.setSymbole(this.getSymbole());
		             return cel;
		             }
		         catch (Throwable e){return null;}
		            }
		     
		     
		     public boolean equals (Object op2) {
		    	   	
		         // Controler la validite du parametre
		         //
		         if (op2 == null) return false;
		         
		         // Transtyper la valeur du second parametre
		         //
		         Cellule c= (Cellule)op2;
		         
		         // Controler l'egalite des positions
		         //
		         if (ligne != c.ligne) return false;
		         if (colonne != c.colonne) return false;
		         
		         // Controler la valeur du symbole
		         //
		         if (symbole != null && c.symbole != null) { 
		            if(!symbole.equals(c.symbole)) return false;
		         }
		         else {
		            if (symbole == null && c.symbole != null) return false;
		            if (symbole != null && c.symbole == null) return false;	
		         }
		         
		         // Controler la valeur de la couleur
		         //
		         if (couleur != null && c.couleur != null) { 
		            if(!couleur.equals(c.couleur)) return false;
		         }
		         else {
		            if (couleur == null && c.couleur != null) return false;
		            if (couleur != null && c.couleur == null) return false;	
		         }
		         
		         // Controler la valeur de la marque
		         //
		         if (marque != null && c.marque != null) { 
		            if(!marque.equals(c.marque)) return false;
		         }
		         else {
		            if (marque == null && c.marque != null) return false;
		            if (marque != null && c.marque == null) return false;	
		         }
		         
		         // Controler la valeur du poids
		         //
		         if (poids != c.poids) return false;
		   	  
		         return true;
		      }
		   
		      
		      
		     
		     public String toString () {
		    	   	
		         return "("+ ligne+ ", "+colonne+")";
		      }
		      
		     
		     
		     //Methode pour retourner cellule voisine 
		     
		     
		     public int nbVoisines (){
		         
		         // Traiter les cas particuliers des quatre coins
		         //
		         if (coin()) return 3;
		      
		         // Traiter les cas particuliers des quatre bordures
		         //
		         if (bordure()) return 5;
		      
		         // Traiter le cas general
		         //
		         return 8;
		      } 
		     
		     
		  // ---                                        Methode bordureNord

		     /**
		      *
		      * @return
		      */
		     
		       public boolean bordureNord () {return getLigne() == 1;}
		       
		    // ---                                         Methode bordureEst

		     /**
		      *
		      * @return
		      */
		     
		       public boolean bordureEst () {
		          return getColonne() == getNbColonnes();
		       } 
		       
		    // ---                                         Methode bordureSud

		     /**
		      *
		      * @return
		      */
		     
		       public boolean bordureSud () {
		          return getLigne() == getNbLignes();
		       }
		       
		    // ---                                         Methode bordureOuest

		     /**
		      *
		      * @return
		      */
		     
		       public boolean bordureOuest () {return getColonne() == 1;}
		       
		    // ---                                         Methode bordure

		     /**
		      *
		      * @return
		      */
		     
		       public boolean bordure () {
		    	
		          if (bordureNord())  return true;
		          if (bordureEst())   return true;
		          if (bordureSud())   return true;
		          if (bordureOuest()) return true;
		      
		          return false;
		       }
		     
		  // ---                                          Methode coinNordOuest

		     /**
		      *
		      * @return
		      */
		     
		       public boolean coinNordOuest () {
		          return bordureNord() && bordureOuest();
		       } 
		       
		    // ---                                          Methode coinNordEst

		     /**
		      *
		      * @return
		      */
		     
		       public boolean coinNordEst () {
		          return bordureNord() && bordureEst();
		       }
		    
		    // ---                                          Methode coinSudEst

		     /**
		      *
		      * @return
		      */
		     
		       public boolean coinSudEst () {
		          return bordureSud() && bordureEst();
		       }
		    
		    // ---                                          Methode coinSudOuest

		     /**
		      *
		      * @return
		      */
		     
		       public boolean coinSudOuest () {
		          return bordureSud() && bordureOuest();
		       }
		    
		    // ---                                          Methode coin

		     /**
		      *
		      * @return
		      */
		     
		       public boolean coin () {
		    	
		          if (coinNordOuest()) return true;
		          if (coinNordEst())   return true;
		          if (coinSudEst())    return true;
		          if (coinSudOuest())  return true;
		      
		          return false;
		       }
		        
		     
		     public Cellule[] voisines() throws Throwable {
		         
		         // Allouer le tableau de retour
		         //
		         Cellule[] resultat= new Cellule[nbVoisines()];
		      
		         // Restituer le resultat pour le cas particulier du coin NO
		         //
		         if (coinNordOuest()) {
		            resultat[0]= getCellule(1,2);
		            resultat[1]= getCellule(2,2);
		            resultat[2]= getCellule(2,1);
		         
		            return resultat;
		         }
		      
		         // Restituer le resultat pour le cas particulier du coin NE
		         //
		         if (coinNordEst()) {
		            resultat[0]= getCellule(2,colonne);
		            resultat[1]= getCellule(2,colonne-1);
		            resultat[2]= getCellule(1,colonne-1);
		         
		            return resultat;
		         }
		      
		         // Restituer le resultat pour le cas particulier du coin SE
		         //
		         if (coinSudEst()) {
		      	    resultat[0]= getCellule(ligne,colonne-1);
		      	    resultat[1]= getCellule(ligne-1,colonne-1);
		            resultat[2]= getCellule(ligne-1,colonne);
		         
		            return resultat;
		         }
		      
		         // Restituer le resultat pour le cas particulier du coin SO
		         //
		         if (coinSudOuest()) {
		            resultat[0]= getCellule(ligne-1,1);
		            resultat[1]= getCellule(ligne-1,2);
		            resultat[2]= getCellule(ligne,2);
		         
		            return resultat;
		         }
		      
		         // Restituer le resultat pour la bordure Nord
		         //
		         if (bordureNord()) {
		            resultat[0]= getCellule(ligne,colonne+1);
		            resultat[1]= getCellule(ligne+1,colonne+1);
		            resultat[2]= getCellule(ligne+1,colonne);
		            resultat[3]= getCellule(ligne+1,colonne-1);
		            resultat[4]= getCellule(ligne,colonne-1);
		         
		            return resultat;
		         }
		      
		         // Restituer le resultat pour la bordure Est
		         //
		         if (bordureEst()) {
		            resultat[0]= getCellule(ligne+1,colonne);
		            resultat[1]= getCellule(ligne+1,colonne-1);
		            resultat[2]= getCellule(ligne,colonne-1);
		            resultat[3]= getCellule(ligne-1,colonne-1);
		            resultat[4]= getCellule(ligne-1,colonne);
		         
		            return resultat;
		         }
		      
		         // Restituer le resultat pour la bordure Sud
		         //
		         if (bordureSud()) {
		      	    resultat[0]= getCellule(ligne,colonne-1);
		            resultat[1]= getCellule(ligne-1,colonne-1);
		            resultat[2]= getCellule(ligne-1,colonne);
		            resultat[3]= getCellule(ligne-1,colonne+1);
		            resultat[4]= getCellule(ligne,colonne+1);
		         
		            return resultat;
		         }
		      
		         // Restituer le resultat pour la bordure Ouest
		         //
		         if (bordureOuest()) {
		            resultat[0]= getCellule(ligne-1,1);
		            resultat[1]= getCellule(ligne-1,2);
		            resultat[2]= getCellule(ligne,2);
		            resultat[3]= getCellule(ligne+1,2);
		            resultat[4]= getCellule(ligne+1,1);
		         
		            return resultat;
		         }
		      
		         // Restituer le resultat pour le cas general
		         //
		         resultat[0]= getCellule(ligne-1,colonne-1);
		         resultat[1]= getCellule(ligne-1,colonne);
		         resultat[2]= getCellule(ligne-1,colonne+1);
		         resultat[3]= getCellule(ligne,colonne+1);
		         resultat[4]= getCellule(ligne+1,colonne+1);
		         resultat[5]= getCellule(ligne+1,colonne);
		         resultat[6]= getCellule(ligne+1,colonne-1);
		         resultat[7]= getCellule(ligne,colonne-1);
		         
		         return resultat;
		      }
		     
		     
		     
		     
		     
		  // ---                                            Methode distance
		     
		     /**
		      *
		      * @param cible
		      * @return
		      * @throws Throwable
		      */
		        
		       public int distance(Cellule cible) throws Throwable {
		       	
		          // Controler la validite des parametres
		          //
		          if (cible == null) throw new Throwable("-2.1");
		       	 
		          int ligneCible= cible.ligne;
		          int colonneCible= cible.colonne;
		       
		          int x= Math.abs(ligne - ligneCible);
		          int y= Math.abs(colonne - colonneCible);
		          
		          return Math.max(x, y);
		       }
		     
		     
		     
		     
		     
		     
		     
		     
		     
		     
		     
		     
		     
		   }
	
	
	
	
}
