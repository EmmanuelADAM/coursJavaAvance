package modele;

import java.util.ArrayList;
import java.util.List;

public class Voiture
{
   /**identifiant de la voiture*/
   private int id;
   /**coordonnée x de la voiture*/
   int x;
   /**coordonnée y de la voiture*/
   int y;
   /**liste des noeuds du trajet prevu*/
   private List<Noeud> trajet;
   /**liste des restant a parcourir*/
   private List<Noeud> routeRestante;
   /**noeud de depart*/
   private Noeud origine;
   /**noeud de destination finale*/
   private Noeud destination;
   /**pause pendant le parcours*/
   private boolean pause;
   /**indique si la voiture est arrivee*/
   private boolean arrivee;
   
   
   Voiture(){}
   /**construit une voiture
    * @param id identifiant de la voiture
    * */
   Voiture(int id){
      this.id = id;
      trajet = new ArrayList<>();
      routeRestante = new ArrayList<>();
      }
   /**construit une voiture
    * @param id identifiant de la voiture
    * @param origine noeud de depart
    * @param destination noeud d'arrivee
    * */
   public Voiture(int no, Noeud origine, Noeud destination) {
      this(no);
      this.origine = origine;
      x = origine.x;
      y = origine.y;
      this.destination = destination;
      calculerRoute();
      for(Noeud n:trajet)routeRestante.add(n);
   }
   /**construit une voiture
    * @param id identifiant de la voiture
    * @param xo yo coordonnees du noeud d'origine
    * @param xd yd coordonnees du noeud de destinatin
    * */
   public Voiture(int no, int xo, int yo, int xd, int yd) {
      this(no);
      this.origine = ReseauRoutier.getNoeud(xo, yo);
      x = origine.x;
      y = origine.y;
      this.destination = ReseauRoutier.getNoeud(xd, yd);
      calculerRoute();
      for(Noeud n:trajet)routeRestante.add(n);
   }
   
   /**calcul la route entre origine et destination 
    * (suppose que ces points soient aient une meme abscisse ou ordonnee)*/
   public void calculerRoute()
   {
      boolean enLigne = (origine.getY()==destination.getY());
      if (enLigne)
      {
         int ligne = origine.getY();
         int sens = destination.getX() - origine.getX();
         sens = sens/Math.abs(sens);
         for(int i=origine.getX(); i!=destination.getX(); i+=sens)
         {
            Noeud n = ReseauRoutier.getNoeud(i, ligne);
            trajet.add(n);
         }
      }
      else
      {
         int colonne = origine.getX();
         int sens = destination.getY() - origine.getY();
         sens = sens/Math.abs(sens);
         for(int i=origine.getY(); i!=destination.getY(); i+=sens)
         {
            Noeud n = ReseauRoutier.getNoeud(colonne, i);
            trajet.add(n);
         }
      }
      trajet.add(destination);
   }
   
   /** retourne le prochain noeud de la route (depile routeRestante)
    * @return le prochain noeud de la route (null si non)*/
   public Noeud prochainNoeud()
   {
      Noeud suivant = null;
      if(!routeRestante.isEmpty()) suivant = routeRestante.remove(0);
      return suivant;
   }
   
   /**affecte les coordonnees x,y de la voiture*/
   public void setXY(int x, int y) 
   { 
      this.x = x; this.y = y; 
      if (destination!=null && x==destination.x && y==destination.y) arrivee = true;
   }

   /**retourne une chaine de caracteres contenant l'identfiant de la voiture et son chemin prevu*/
   public String toString()
   {
      StringBuilder sb = new StringBuilder("voiture ").append(id);
      sb.append(". chemin = ");
      for(Noeud n:trajet) sb.append(n).append("-");
      return sb.toString();      
   }

   public int getX() { return x; }
   public int getY() { return y; }
   public boolean isPause() {return pause; }
   public boolean isArrivee() { return arrivee; }
   public void setPause(boolean pause) { this.pause = pause; }
}
