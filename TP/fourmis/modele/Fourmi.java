package modele;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.function.Function;

/**
 * cette classe represente une fourmis
 *
 * @author Emmanuel Adam
 */
public class Fourmi {
    // des couleurs
    static final public Color coulVide = Color.color(0.3, 0.3, 0.3);
    static final public Color coulPleine = Color.color(1, 0, 0);

    /**coeff de degradation de la dose de pheromone entre chaque depot */
    static final double degradationDosePhero = 0.95;

    /**parcelle ou se trouve la fourmi*/
    Parcelle parcelle;

    /**direction actuelle de la fourmis*/
    Direction d;

    /**reference a l'environnement*/
    Environnement evt;

    /**indique si la fourmis est vide ou si elle porte de la nourriture*/
    boolean vide;
    /**indique la dose de pheromone que peut deposer la fourmis*/
    double dosePhero;

    /**sa representation graphique associee (simple cercle)*/
    Circle circle;

    /**constructeur par defaut, inutilise */
    public Fourmi() { }

    /**
     * constructeur le lien vers l'environnement, la parcelle en x,y et une direction aleatoire
     * initialement la fourmi est vide et peut deposer une dose de 1 pheromone
     */
    public Fourmi(Environnement  evt, int x, int y) {
        this.evt = evt;
        this.parcelle = evt.getGrille()[x][y];
        this.d = Direction.getRandom();
        vide = true;
        dosePhero = 1;
    }

    /**
     * si la fourmi est vide, elle cherche de la nourriture, sinon elle revient au nid
     */
    void evoluer() {
        if(vide) chercherNourriture();
        else revenir();
    }

    /**la fourmi tire une direction aleatoire autour de l'actuelle
     * calcule les coordonnees de la prochaine case
     * demande a l'environnement de l'y deplacer
     * et tate le terrain*/
    void errer()
    {
        d = d.nextDirection();
        parcelle = getParcelleDir(d);
        evt.bouger(this);
        tater();
    }

    /**recherche la case voisine possédant la plus grande valeur on nulle retournee par la fonction f
     * si une telle case existe, y deplace la fourmi et lui fait tater le terrain
     * @param f fonction retournant un reel a partir d'une parcelle
     *          exemple fonction qui retourne la pheromone a partir d'une parcelle..
     * @return vrai si au moins une parcelle retourne a retourne une valeur > 0
     */
    boolean chercherElement(Function<Parcelle, Double> f)
    {
        boolean trouve = false;
        //recupere les directions dans le demi-cercle frontal
        Direction maxiDir = chercherMeilleureDirection(f);
        //si un maxi existe
        if(maxiDir!=null)
        {
            trouve = true;
            //prend la direction trouvee
            d = maxiDir;
            //se place dans la parcelle
            parcelle = getParcelleDir(d);
            //en informe l'environnement qui en informera l'application graphique
            evt.bouger(this);
            //tate pour voir ce qui s'y trouve
            tater();
        }
        return trouve;
    }

    /**recherche la case voisine possédant la plus grande valeur non nulle retournee par la fonction f
     * @param f fonction retournant un reel a partir d'une parcelle
     * @return la direction menant a la meilleur case de valeur >0; ou null
     */
    private Direction chercherMeilleureDirection(Function<Parcelle, Double> f)
    {
        //TODO:COMPLETER
        //NB: Ici, on peut créer à la place 3 fonctions : chercherMeilleureDirectionNourriture,
        // chercherMeilleureDirectionPheromone, chercherMeilleureDirectionNid
        //qui retourne la direction vers la case ayant le plus de nourriture, ou pheromone, ou odeur de nid,
        // il faudra alors légerement modifier le reste du code (chercherElement)
        // L'avantage de la programmation fonctionnelle est la compacité du code, à vous de choisir
        return this.d;
    }

    /**@return la parcelle accessible a partir de la parcelle courante dans la direction donnee
     * */
    private Parcelle getParcelleDir(Direction dir)
    {
        int x = Math.max(0, Math.min(evt.taille - 1, parcelle.x + dir.x));
        int y = Math.max(0, Math.min(evt.taille - 1, parcelle.y + dir.y));
        return evt.grille[x][y];
    }

    /**la fourmi cherche de la nourriture dans son voisinage
     * si non trouve, cherche de la pheromone
     * si non trouve erre...*/
    void chercherNourriture()
    {
        //TODO: COMPLETER POUR QUE LA FOURMI CHERCHE AVANT DE ERRER
        errer();
    }



    /**pour revenir,
     * la fourmis depose une dose de pheromone dans la parcelle,
     * degrade sa dose de pheromone restante,
     * la fourmis prend la direction du nid
     * */
    void revenir()
    {
        //TODO: COMPLETER
    }


    /**si la fourmi est vide, et si le nb de nourriture de la parcelle est >0, la fourmis mange
     * si la fourmi porte de la nourriture et si la case est une parcelle nid, la fourmis depose */
    void tater()
    {
        if(vide) {
            if(parcelle.getNbNourriture()>0) manger();
        }
        else {
            if(parcelle.type == TypeParcelle.Nid) deposer();
        }
    }

    /**la fourmis indique a l'environnement qu'elle mande en x,y, change son etat et demande le changement de couleur*/
    private void manger() {
        evt.manger(parcelle);
        vide = false;
        d = d.inverse();
        changerCouleur();
    }

    /**la fourmis  change son etat, demande le changement de couleur,
     * fait demi tour (prend une direction oposee a l'actuelle et restaure sa dose de pheromone a 1*/
    private void deposer() {
        vide = true;
        changerCouleur();
        d = d.inverse();
        dosePhero  =1;
    }



    /**modifie la couleur de la représentation graphique associee selon l'etat*/
    public void changerCouleur() {
        Color c = (vide?coulVide:coulPleine);
        circle.setFill(c);
    }


    /**
     * affecte un dessin à la cellule
     */
    public void setCircle(Circle circle) {
        this.circle = circle;
    }


}