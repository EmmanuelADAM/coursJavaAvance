package util;

/**simple classe pour illustrer la recherche multi-criteres via programmation fonctionnelle en Java.
 * Une boite contient trois valeurs reelles 
 * @author emmanuel adam
 * */
public class Boite {
    /**largeur de la boite en cm*/
    double largeur;
    /**longueur de la boite en cm*/
    double longueur;
    /**hauteur de la boite en cm*/
    double hauteur;
    /**poids de la boite en kg*/
    double poids;

    /**constucteur*/
    public Boite(double largeur, double longueur, double hauteur, double poids) {
        this.largeur = largeur;
        this.longueur = longueur;
        this.hauteur = hauteur;
        this.poids = poids;
    }

    public double getLargeur() {
        return largeur;
    }

    public double getLongueur() {
        return longueur;
    }

    public double getHauteur() {
        return hauteur;
    }

    public double getPoids() {
        return poids;
    }

    public String  toString()
    {
        return "boite largeur=%.2f, longueur=%.2f, hauteur=%.2f, poids=%.2f .".formatted(largeur,  longueur, hauteur, poids);
    }

}
