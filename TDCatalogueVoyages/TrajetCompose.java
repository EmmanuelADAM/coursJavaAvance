package TDCatalogueVoyages;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.util.ArrayList;
import java.util.List;

public class TrajetCompose{
    Ville depart;
    Ville arrivee;
    double dureeTotale;
    double coutTotal;
    LocalTime dateDepart;
    LocalTime dateArrivee;
    List<TrajetSimple> trajets;

    public TrajetCompose() {
        trajets = new ArrayList<>();
    }

    /**alcule la durée totale et le coût total
     * à partir des trajets contenus dans le trajet composé.*/
    private void calcule(){
        coutTotal = trajets.stream().mapToDouble(TrajetSimple::getCout).sum();
//  ou      trajets.forEach(t->coutTotal += t.getCout());
//  ou      for(TrajetSimple t:trajets) coutTotal += t.getCout();
        dureeTotale = ChronoUnit.MINUTES.between(dateDepart, dateArrivee);
    }

    void add(TrajetSimple trajet){
        if(trajets.isEmpty() || (arrivee==trajet.getDepart() &&
                trajet.getDateDepart().compareTo(dateArrivee)>=0)){
            if(trajets.isEmpty()){
                depart = trajet.getDepart();
                dateDepart = trajet.getDateDepart();
            }
            trajets.add(trajet);
            arrivee = trajet.getArrivee();
            dateArrivee = trajet.getDateArrivee();
            coutTotal += trajet.getCout();
            dureeTotale = ChronoUnit.MINUTES.between(dateDepart, dateArrivee);
        }
    }

    @Override
    public String toString() {
        return "TrajetCompose{" +
                "depart=" + depart +
                ", arrivee=" + arrivee +
                ", dureeTotale=" + dureeTotale +
                ", coutTotal=" + coutTotal +
                ", dateDepart=" + dateDepart +
                ", dateArrivee=" + dateArrivee +
                '}';
    }

    public String detailToString(){
        return "detail du voyage : " + trajets;
    }

}
