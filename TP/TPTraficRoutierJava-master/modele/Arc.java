package modele;

import java.util.List;

public class Arc
{
   /**noeud de depart*/
   private Noeud start;
   /**noeud d'arrivee*/
   private Noeud end;
   /**liste des voitures sur noeud*/
   List<Voiture> cars;
   /**nom de l'arc*/
   String name;

   public Arc() {   }


   public Arc(Noeud start, Noeud end) {
      super();
      this.start = start;
      this.end = end;
      this.name = ""+start.id+"-"+end.id;
   }

   public Noeud getStart() { return start; }
   public Noeud getEnd() { return end; }

   public void setStart(Noeud start) { this.start = start; }
   public void setEnd(Noeud end) { this.end = end; }


}
