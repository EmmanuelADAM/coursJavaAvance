package TD.TDJeuDeLaVie.VieSwing;

import java.util.Arrays;

/**classe représentant une matrice de cellules pouvant être actives (vivantes) ou non.
 * Cette matrice simule le jeu de la vie.
 * @author emmanueladam
 * @version 1.0
 */
public class Matrice {
    /**la grille au temps actuel*/
    Cellule[][] grille;
    /**la grille au temps précédent*/
    Cellule[][] grilleAncienne;
    /**pourcentage initial entre [0,1] de cellules actives*/
    double densite;
    /**la taille de la matrice (carrée)*/
    int taille;

    public Matrice(int taille, double densite) {
        this.densite = densite;
        this.taille = taille;
        grille = new Cellule[taille][taille];
        grilleAncienne = new Cellule[taille][taille];
        init();
        initHasard();
    }

    /**cree des cellules inactives dans la grille
     * et les recopie dans la grilleAncienne*/
    void init() {
        for(int i=0; i<taille; i++)
            for(int j=0; j<taille; j++) {
                grille[i][j] = new Cellule(grille, i, j, false);
                grilleAncienne[i][j] = new Cellule(grille[i][j]);
//                grilleAncienne[i][j] = grille[i][j].clone();
//                grilleAncienne[i][j] = new Cellule(grille, i, j, false);
            }
    }


    /**active ± densité% de cellule dans la grille et dans la grilleAncienne*/
    void initHasard(){
        for(int i=0; i<taille; i++)
            for(int j=0; j<taille; j++)
            {
                if(Math.random()<densite)
                {
                    grille[i][j].vivante = true;
                    grilleAncienne[i][j].vivante = true;
                }
            }
    }

    /**recopie grille dans ancienne grille*/
    void copieGrille(){
        for(int i=0; i<taille; i++)
            for(int j=0; j<taille; j++)
            {
                grilleAncienne[i][j].vivante = grille[i][j].vivante;
                grilleAncienne[i][j].etatPrecedent = grille[i][j].etatPrecedent;
            }
    }
    /**demande à chaque cellule d’évoluer*/
    void animGrille(){
        for(int i=0; i<taille; i++)
            for(int j=0; j<taille; j++)
                grille[i][j].compterVoisinesVivantes();
        for(int i=0; i<taille; i++)
            for(int j=0; j<taille; j++)
                grille[i][j].evoluer();

    }

    @Override
    public String toString() {
        return "Matrice{" +
                "grille=" + Arrays.deepToString(grille) +
                '}';
    }
}
