package TDCatalogueVoyages;

import java.io.Serializable;

/**Moyen de locomotion
 * Bus, Tram et Train ont des couts au km et des vitesses différents
 * @author emmanueladam */
public enum Moyen implements Serializable {Train(1d, 70d), Tram(.3, 50d), Bus(.1, 40d);
    final double cout;
    final double vitesse;

    Moyen(double cout, double vitesse) {
        this.cout = cout;
        this.vitesse = vitesse;
    }

    public double getCout() {
        return cout;
    }

    public double getVitesse() {
        return vitesse;
    }
}
