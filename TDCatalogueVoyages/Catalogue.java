package TDCatalogueVoyages;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**catalogue des trajets*/
public class Catalogue implements Serializable {
    /**map avec comme cle une ville et en valeur la liste des trajets partant de cette ville*/
    HashMap<Ville, List<TrajetSimple>> tableDepart;

    Catalogue(){
        tableDepart = new HashMap<>();
    }

    /**ajout d'un trajet simple au catalogue*/
    void addTrajetSimple(TrajetSimple trajet){
        //TODO: verifier que trajet n'est pas null!
        Ville depart = trajet.getDepart();
        List<TrajetSimple> trajetsDeDepart = tableDepart.get(depart);
        if(trajetsDeDepart==null) trajetsDeDepart = new ArrayList<>();
        trajetsDeDepart.add(trajet);
        tableDepart.put(depart, trajetsDeDepart);
    }

    /**creation du catalogue des voyages spécifiés dans le sujet de TD*/
    void creerCatalogue() {
        Ville[] lesVilles = Ville.values();
        int nb = lesVilles.length;

        for (int i = 0; i < nb - 1; i++) {
            var start = lesVilles[i];
            for (int j = i + 1; j < nb; j++) {
                var end = lesVilles[j];
                if (start.existeChemin(end)) {
                    Moyen m = Moyen.Bus;
                    if (start == Ville.A && end == Ville.F || start == Ville.F && end == Ville.A) m = Moyen.Train;
                    if (start == Ville.A && end == Ville.D || start == Ville.D && end == Ville.A) m = Moyen.Tram;
                    if (start == Ville.F && end == Ville.D || start == Ville.D && end == Ville.F) m = Moyen.Tram;
                    var tjAller = new TrajetSimple(start, end, m, LocalTime.of(5, 0));
                    var tjRetour = new TrajetSimple(end, start, m, LocalTime.of(5, 0));
                    addTrajetSimple(tjAller);
                    addTrajetSimple(tjRetour);
                }
            }
        }
    }


}
