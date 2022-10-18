package swing.jeuDeLaVie;


/**
 * cette classe represente une cellule du jeu de la Vie
 * si la cellule est entouree du bon nombre de cellules alors elle vit,
 * sinon, en cas de sous population ou de sur population, elle meurt...
 *
 * @author Emmanuel Adam
 */
public class Cellule implements Cloneable {
    /**
     * (facultatif) seuil a partir duquel la cellule meurt de sous population
     */
    static final int sousPopulation = 1;
    /**
     * (facultatif) seuil a partir duquel la cellule meurt de sur population
     */
    static final int surPopulation = 4;
    /**
     * (facultatif) seuil minimum a partir duquel la cellule vit
     */
    static final int minPopulationRegeneratrice = 3;
    /**
     * (facultatif) seuil maximum a partir duquel la cellule vit
     */
    static final int maxPopulationRegeneratrice = 3;
    //des couleurs

    /**
     * nature de la cellule
     */
    boolean etatPrecedent;
    /**
     * nature de la cellule
     */
    boolean vivante;

    /**
     * coordonnées de la cellule dans la grille
     */
    int x, y;

    /**
     * référence à la grille des cellules
     */
    Cellule[][] grille;


    /**
     * constructeur par defaut, inutilise
     */
    public Cellule() {
    }

    /**
     * constructeur initialisant la grille, les coordonnées et la nature de la cellule
     */
    public Cellule(Cellule[][] grille, int x, int y, boolean vivante) {
        this.grille = grille;
        etatPrecedent = false;
        this.vivante =vivante;
        this.x = x;
        this.y = y;
    }

    /**
     * détermine le prochain etat de la cellule en fonction des cellules voisines.<br>
     *  compte le nb de cellules actives dans les 8 cases autours (la grille est considérée sphérique)
     */
    void evoluer() {
        int taille = grille.length;
        int nbVivantes = 0;
        for (int i = -1; i < 2; i++) {
            int xx = ((x + i) + taille) % taille; // si x+i=-1, xx=taille-1. si x+i=taille, xx=0
            for (int j = -1; j < 2; j++) {
                if (i == 0 && j == 0) continue;
                int yy = ((y + j) + taille) % taille;
                if (grille[xx][yy].vivante) nbVivantes++;
            }
        }

        etatPrecedent = vivante;
        //verifie si la cellule doit se desactiver (en sous population, ou surpopulation)
        if (vivante && (nbVivantes < 2 || nbVivantes > 3)) vivante = false;
        else //verifie si la cellule doit etre active ou reactivée (population idéale)
            if (nbVivantes == 3) vivante = true;
    }



    /**
     * @return la valeur de la variable vivante
     */
    public boolean isVivante() {
        return vivante;
    }



    /**
     * retourne une copie de la cellule
     * fonction importante utilisée dans la classe Matrice
     * pour la recopie de grille
     *
     * @return une copie de la cellule
     */
    public Object clone() {
        Object o = null;
        try {
            o = super.clone();
        } catch (CloneNotSupportedException e) {
            System.err.println("Cellule impossible a cloner...");
        }
        return o;
    }

}