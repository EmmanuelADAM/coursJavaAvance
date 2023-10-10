package TDCatalogueVoyages;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class TrajetCompose {
    Ville depart;
    Ville arrivee;
    int dureeTotale;
    double coutTotal;
    LocalTime dateDepart;
    LocalTime dateArrivee;
    List<TrajetSimple> trajets;

    public TrajetCompose() {
        trajets = new ArrayList<>();
    }

    private void calcule(){
        coutTotal = 0;
        for(TrajetSimple t:trajets)
            coutTotal += t.getCout();
        //depuis Java 21
//        depart = trajets.getFirst().getDepart();
//        arrivee = trajets.getLast().getArrivee();
//        dateArrivee = trajets.getLast().getDateArrivee();
//        dateDepart = trajets.getFirst().getDateDepart();
        //anciens Java
        depart = trajets.get(0).getDepart();
        dateDepart = trajets.get(0).getDateDepart();
        arrivee = trajets.get(trajets.size()-1).getArrivee();
        dateArrivee = trajets.get(trajets.size()-1).getDateArrivee();
        dureeTotale = (int)ChronoUnit.MINUTES.between(dateDepart, dateArrivee);
    }

    void add(TrajetSimple trajet){
        if(arrivee==trajet.getDepart() && dateArrivee.compareTo(trajet.dateDepart)<=0 || trajets.isEmpty()) {
            if(trajets.isEmpty()) {
                depart = trajet.getDepart();
                dateDepart = trajet.getDateDepart();
            }
            trajets.add(trajet);
            arrivee = trajet.getArrivee();
            dateArrivee = trajet.getDateArrivee();
            coutTotal += trajet.getCout();
            dureeTotale = (int)ChronoUnit.MINUTES.between(dateDepart, dateArrivee);
        }
    }

    void add(List<TrajetSimple> trajets){
        trajets.forEach(t->add(t));
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

    public String detailVoyage(){
        return trajets.toString();
    }
}
