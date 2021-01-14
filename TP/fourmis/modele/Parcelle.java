package modele;

import gui.ImgParcelle;

/**
 * cette classe represente une parcelle de terrain
 * La parcelle peut etre de type herbe, nid, nourriture, et contenir de la pheromone
 *
 * @author Emmanuel Adam
 */
public class Parcelle {
    /**taux de dillution de la pheromone*/
    static final double tauxDillution = 0.02;
    /**taux d'evaporation de la pheromone*/
    static final double tauxEvaporation = 0.99;
    /**taux a partir duquel la pheromone est consideree nulle*/
    static final double tauxOubli = 0.01;

    /**type de la parcelle*/
    TypeParcelle type;
    /**coordonnee x de la parcelle dans la grille*/
    final int x;
    /**coordonnee y de la parcelle dans la grille*/
    final int y;

    /**dose de nourriture*/
    private double nbNourriture;
    /**dose de pheromone*/
    private double pheromone;
    /**odeur du nid*/
    private double odeurNid;
    /**dose de pheromone recue par dillution*/
    private double pheroDilluee;
    /** reference a la grille des parcelles*/
    Parcelle[][] grille;
    /**representation graphique associee*/
    ImgParcelle img;

    /**
     * constructeur par defaut, inutilise
     */
    public Parcelle() { x = y = 0;}

    /**
     * constructeur initialisant la grille, les coordonnees et le type de la parcelle
     */
    public Parcelle(Parcelle[][] grille, int x, int y, TypeParcelle type) {
        this.x = x;
        this.y = y;
        this.grille = grille;
        this.type = type;
    }

    /**
     * accumule la phero recue par dillution dans la pheromone
     * (la phero recue par dillution est ensuite annulee)
     * puis degrade la pheromone par evaporation
     * et la met a zero si < au taux d'oubli
     */
    void evaporer() {
        if(pheromone>0) {
            //TODO: completer
        }
    }

    /**
     * diffuse la pheromone aux 8 parcelles voisines :
     * chaque voisine recoit une goutte de phero diluee
     * la phero de cette parcelle est donc diminuee de 1-8xla dillution
     * */
    public void diffuser() {
        if(pheromone>0) {
            //TODO: completer
        }
    }



    /**prise de nourriture par une fourmis
     * decremente le nb de nourriture
     * si le nb de nourriture tombe a zero, le type de la parcelle devient un terrain
     * */
    public void takeNourriture()
    {
        nbNourriture--;
        if(nbNourriture<=0) setType(TypeParcelle.Terrain);
    }



    /**ajoute une dose de pheromone a la pacelle*/
    public void addPheromone(double phero) {
        pheromone += phero;
    }

    /**definit le type de la parcelle; si c'est une zone de nourriture, en ajoute 10 */
    public void setType(TypeParcelle type) {
        this.type = type;
        if(type==TypeParcelle.Nourriture) nbNourriture = 10;
        img.choisirCouleur();
    }


    public void setImg(ImgParcelle img) {
        this.img = img;
    }
    public double getPheromone() {  return pheromone; }
    public TypeParcelle getType() { return type; }
    public double getNbNourriture() { return nbNourriture; }
    public double getOdeurNid() { return odeurNid; }
    public void setOdeurNid(double odeurNid) { this.odeurNid = odeurNid; }

    @Override
    public String toString() {
        return "Parcelle{" +
                "type=" + type +
                ", x=" + x +
                ", y=" + y +
                ", nbNourriture=" + nbNourriture +
                ", pheromone=" + pheromone +
                ", pheroDilluee=" + pheroDilluee +
                ", odeurNid=" + odeurNid +
                '}';
    }
}