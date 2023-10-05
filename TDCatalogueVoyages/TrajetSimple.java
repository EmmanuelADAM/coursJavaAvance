package TDCatalogueVoyages;

import java.time.LocalTime;

/**Trajet simple, direct entre 2 viles*/
public class TrajetSimple {
    Ville depart;
    Ville arrivee;
    double distance;
    Moyen moyen;
    double cout;
    LocalTime dateDepart;

    /**duree en minute*/
    double duree;
    LocalTime dateArrivee;

    public TrajetSimple(Ville depart, Ville arrivee, Moyen moyen, LocalTime dateDepart) {
        this.depart = depart;
        this.arrivee = arrivee;
        this.moyen = moyen;
        this.dateDepart = dateDepart;
        calcule();
    }

    /**calcule la distance, le cout, la duree et la date d'arrivee à partir des autres champs*/
    private void calcule(){
        distance = depart.getDist(arrivee);
        cout = distance * moyen.getCout();
        duree = (distance / moyen.getVitesse())*60d;
        dateArrivee = dateDepart.plusMinutes((int)duree);
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
}
