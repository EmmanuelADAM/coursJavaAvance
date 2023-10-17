package demoCours.jeuDeLaVie;

import java.util.Arrays;

/**
 * matrice qui represente un monde spherique pour le jeu de la vie
 */
public class Matrice {
    /**
     * la grille au temps actuel
     */
    Cellule[][] grille;
    /**
     * la grille au temps précédent
     */
    Cellule[][] grilleAncienne;
    /**
     * : pourcentage initial de cellules actives (entre 0 et 1)
     */
    double densite;
    /**
     * la taille de la matrice (carrée)
     */
    int taille;

    public Matrice(int taille, double densite) {
        this.densite = densite;
        this.taille = taille;
        grille = new Cellule[taille][taille];
        grilleAncienne = new Cellule[taille][taille];
        init();
        initHasard();
    }

    /**
     * cree des cellules inactives dans la grille et les recopie dans la grilleAncienne
     */
    void init() {
        for (int i = 0; i < taille; i++)
            for (int j = 0; j < taille; j++) {
                grille[i][j] = new Cellule(i, j, false, false, grille);
                grilleAncienne[i][j] = new Cellule(grille[i][j]);
            }
    }

    /**
     * active densité% de cellule dans la grille et dans la grilleAncienne
     */
    void initHasard() {
        for (int i = 0; i < taille; i++)
            for (int j = 0; j < taille; j++) {
                if (Math.random() <= densite) {
                    grille[i][j].vivante = true;
                    grilleAncienne[i][j].vivante = true;
                }
            }
    }

    /**recopie les etats des cellules de grille dans ancienne grille*/
    void copieGrille(){
        for (int i = 0; i < taille; i++)
            for (int j = 0; j < taille; j++) {
                grilleAncienne[i][j].vivante = grille[i][j].vivante;
                grilleAncienne[i][j].etatPrecedent = grille[i][j].etatPrecedent;
            }
    }

    /** demande à chaque cellule d’évoluer
     * */void animGrille(){
        for (int i = 0; i < taille; i++)
            for (int j = 0; j < taille; j++) {
                grille[i][j].compterVoisinageActif();
            }
        for (int i = 0; i < taille; i++)
            for (int j = 0; j < taille; j++) {
                grille[i][j].evoluer();
            }
    }

    void change(int i, int j){
         grille[i][j].vivante = !grille[i][j].vivante;
    }

    public int getTaille() {
        return taille;
    }
}

