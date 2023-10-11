package TDCatalogueVoyages;

import java.io.Serializable;
import java.time.LocalTime;

/**Trajet simple, direct entre 2 viles*/
public class TrajetSimple implements Serializable, Cloneable {
    Ville depart;
    Ville arrivee;
    double distance;
    Moyen moyen;
    double cout;
    LocalTime dateDepart;

    /**duree en minute*/
    double duree;
    LocalTime dateArrivee;

    TrajetSimple(){}
    public TrajetSimple(Ville depart, Ville arrivee, Moyen moyen, LocalTime dateDepart) {
        this.depart = depart;
        this.arrivee = arrivee;
        this.moyen = moyen;
        this.dateDepart = dateDepart;
        calcule();
    }

    public TrajetSimple(TrajetSimple original) {
        this.depart = original.depart;
        this.arrivee = original.arrivee;
        this.distance = original.distance;
        this.moyen = original.moyen;
        this.cout = original.cout;
        this.dateDepart = original.dateDepart;
        this.duree = original.duree;
        this.dateArrivee = original.dateArrivee;
    }

    public static TrajetSimple getTrajetSimple(TrajetSimple original) {
        TrajetSimple tj = new TrajetSimple();
        tj.depart = original.depart;
        tj.arrivee = original.arrivee;
        tj.distance = original.distance;
        tj.moyen = original.moyen;
        tj.cout = original.cout;
        tj.dateDepart = original.dateDepart;
        tj.duree = original.duree;
        tj.dateArrivee = original.dateArrivee;
        return tj;
    }

    /**Constructeur créant un trajet simple entre les villes _depart et _arrivee,
     * à partir de la date _dateDepart donnée sous la forme hh:mm et du moyen de locomotion _moyen*/
    TrajetSimple(String _depart, String _arrivee,  int _dateDepart, String _moyen)
    {
        this.depart = Ville.valueOf(_depart);
        this.arrivee = Ville.valueOf(_arrivee);
        this.moyen = Moyen.valueOf(_moyen);
        int hh = _dateDepart / 100;
        int mm = _dateDepart - hh*100;
        this.dateDepart = LocalTime.of(hh, mm);
        calcule();
    }




    /**calcule la distance, le cout, la duree et la date d'arrivee à partir des autres champs*/
    private void calcule(){
        distance = depart.getDist(arrivee);
        cout = distance * moyen.getCout();
        duree = (distance / moyen.getVitesse())*60d;
        dateArrivee = dateDepart.plusMinutes((int)duree);
    }



    @Override
    public String toString() {
        return "TrajetSimple{" +
                "depart=" + depart +
                ", arrivee=" + arrivee +
                ", distance=" + distance +
                ", moyen=" + moyen +
                ", cout=" + cout +
                ", dateDepart=" + dateDepart +
                ", duree=" + duree +
                ", dateArrivee=" + dateArrivee +
                '}';
    }

    public Ville getDepart() {
        return depart;
    }

    public Ville getArrivee() {
        return arrivee;
    }

    public double getDistance() {
        return distance;
    }

    public Moyen getMoyen() {
        return moyen;
    }

    public double getCout() {
        return cout;
    }

    public LocalTime getDateDepart() {
        return dateDepart;
    }

    public double getDuree() {
        return duree;
    }

    public LocalTime getDateArrivee() {
        return dateArrivee;
    }

    public void setMoyen(Moyen moyen) {
        this.moyen = moyen;
        calcule();
    }

    public void setDateDepart(LocalTime dateDepart) {
        this.dateDepart = dateDepart;
        calcule();
    }

    public void setDateArrivee(LocalTime dateArrivee) {
        this.dateArrivee = dateArrivee;
    }

    @Override
    public TrajetSimple clone() {
        try {
            TrajetSimple clone = (TrajetSimple) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
