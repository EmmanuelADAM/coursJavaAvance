package modele;

import java.util.ArrayList;
import java.util.List;
import application.AppliTrafic;

public class Noeud {
	/**colonne du noeud*/
	int x;
   /**ligne du noeud*/
   int y;
	/**liste des voisins accessibles*/
   List<Noeud> noeudsAccessibles;
   /**liste des arcs entrants*/
   List<Arc> arcEntrants;
   /**liste des arcs sortants*/
   List<Arc> arcSortants;
   /**liste des voitures sur noeud*/
   List<Voiture> cars;
   /**id du noeud double construit a partir des coordonnes = (x.y)*/
   double id;

	/**lien vers l'application graphique*/
	AppliTrafic appli;

	
   /**constructeur initialisant les coordonnées du noeud et son type*/
   Noeud(int _x, int _y)
   {
      x = _x;
      y = _y;
      double yf = y/((Math.floor(Math.log10(y))+1)*10);
      id = (double)x+yf;
      noeudsAccessibles = new ArrayList<>();
      arcEntrants = new ArrayList<>();
      arcSortants = new ArrayList<>();
   }


	
	/**affiche les coordonnees d'un noeud */
	public String toString()
	{		
		return "noeud (" + x +"," + y + ")" ;
	}
   
   /**deux noeuds sont egaux s'ils ont la meme id*/
   public boolean equals(Object o)
   {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Noeud autre = (Noeud)o;
      return id==autre.id;
   }

   public List<Noeud> getVoisins() { return noeudsAccessibles; }
   public int getX() { return x; }
   public int getY() { return y; }
   public List<Arc> getArcSortants() { return arcSortants; }	
   public double getId() { return id; }

}
