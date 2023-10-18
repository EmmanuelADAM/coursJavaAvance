package TD.TDJeuDeLaVie.VieSwing;

/**
 * Cellule dans  un monde sphérique.
 * ’taille’ étant la dimension de la grille carrée,
 * la case en haut à gauche de la case (0,0) est la case (taille-1, taille-1).
 */
public class Cellule implements Cloneable {
    boolean vivante;
    /**
     * indique si l’état précédent de la cellule
     */
    boolean etatPrecedent;
    /**
     * lien vers la matrice de cellules
     */
    Cellule[][] grille;
    /**
     * coordonnee x de la cellule
     */
    int x;
    /**
     * coordonnee y de la cellule
     */
    int y;
    int nbVoisinesVivantes;

    public Cellule(Cellule[][] grille, int x, int y, boolean vivante) {
        this.vivante = vivante;
        this.grille = grille;
        this.x = x;
        this.y = y;
    }

    public Cellule(Cellule originale) {
        this.vivante = originale.vivante;
        this.etatPrecedent = originale.etatPrecedent;
        this.grille = originale.grille;
        this.x = originale.x;
        this.y = originale.y;
    }

    int compterVoisinesVivantes() {
        int nb = 0;
        int taille = grille.length;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i == 0 && j == 0) continue;
                int xx = x + i;
                if (xx == taille) xx = 0;
                if (xx == -1) xx = taille - 1;
                int yy = y + i;
                if (yy == taille) yy = 0;
                if (yy == -1) yy = taille - 1;
//                int xx = ((x + i) + grille.length) % grille.length;
//                int yy = ((y + j) + grille.length) % grille.length;
                if (grille[xx][yy].vivante) nb++;
            }
        }
        nbVoisinesVivantes = nb;
        return nb;
    }

    void evoluer() {
        if (vivante) {
            if (nbVoisinesVivantes < 2 || nbVoisinesVivantes > 3) vivante = false;
        } else if (nbVoisinesVivantes == 3) vivante = true;
    }

    @Override
    public Cellule clone() {
        try {
            Cellule clone = (Cellule) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public boolean isVivante() {
        return vivante;
    }

    @Override
    public String toString() {
        return vivante ? "1" : "0";
    }
}
