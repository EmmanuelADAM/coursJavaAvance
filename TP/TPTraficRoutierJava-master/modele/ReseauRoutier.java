package modele;

import java.util.ArrayList;
import java.util.List;

public class ReseauRoutier
{
   /**liste des carrefours constituants le reseau routier*/
   private static List<Noeud> noeuds = new ArrayList<>();
   /**liste des routes constituants le reseau routier*/
   private static List<Arc> arcs = new ArrayList<>();

   public ReseauRoutier(){}


   /**ajoute deux noeuds et un arc entre eux
    * @param xo x du noeud origine
    * @param yo y du noeud origine
    * @param xd x du noeud destination
    * @param yd x du noeud destination
    * */
   private static void addArcs(Noeud origine, Noeud destination)
   {
      Arc a = new Arc(origine,destination);
      origine.arcSortants.add(a);
      destination.arcEntrants.add(a);
      arcs.add(a);
   }
   
   /**retourne le noeud en coordonnee x,y s'il existe*/
   public static Noeud getNoeud(int x, int y)
   {
      Noeud result = null;
      boolean found = false;
      int i=0;
      int size= noeuds.size();
      Noeud n=null;
      while (i<size && !found)
      {
         n=noeuds.get(i++);
         found = (n.getX()==x && n.getY()==y);
      }
      if (found) result = n;
      return result;
   }
   
   /**creation du reseau routier*/
   public static void creerReseau()
   {
      for(int i=0; i<6; i++)
         for(int j=0; j<6; j++)
         {
            if(i==0 && (j==0 || j==5)) continue;
            if(i==5 && (j==0 || j==5)) continue;
            noeuds.add( new Noeud(i,j));
         }
      Noeud o=null;
      Noeud d=null;
      for(int x=0; x<5; x++)
         for(int y=5; y>0; y--)
         {
            o = getNoeud(x,y);
            if(o==null)continue;
            if (y!=0 && y!=5)
            {
               d = getNoeud(x+1,y);
               if(d==null)continue;
               addArcs(o,d);
            }
            if (x!=0 && x!=5)
            {
               d = getNoeud(x,y-1);
               if(d==null)continue;
               addArcs(o,d);
            }
         }
   }

   public static List<Noeud> getNoeuds() { return noeuds; }
   public static List<Arc> getArcs() { return arcs; }
}
