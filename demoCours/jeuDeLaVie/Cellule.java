package demoCours.jeuDeLaVie;

/**classe representant une cellule dans le jeu de la vie
 * On prendra un monde sphérique.
 * ’taille’ étant la dimension de la grille carrée,
 * la case en haut à gauche de la case (0,0) est la case (taille-1, taille-1).
 * */
public class Cellule {
    /**coordonnee x de la cellule*/
    int x;
    /**coordonnee y de la cellule*/
    int y;
    boolean vivante;
    /** : booléen qui indique si un l’état précédent de la cellule*/
    boolean etatPrecedent;
    /**un lien vers la matrice de cellules*/
    Cellule[][] grille;

    public Cellule(boolean vivante, boolean etatPrecedent, Cellule[][] grille) {
        this.vivante = vivante;
        this.etatPrecedent = etatPrecedent;
        this.grille = grille;
    }

    public Cellule(int x, int y, boolean vivante, boolean etatPrecedent, Cellule[][] grille) {
        this(vivante,  etatPrecedent, grille);
        this.x = x;
        this.y = y;
    }
    public Cellule(Cellule originale){
        this(originale.x, originale.y, originale.vivante, originale.etatPrecedent, originale.grille);
    }


    /**change la valeur de la cellule par rapport à son voisinage*/
    void evoluer(){
        int somme = compterVoisinageActif();
        if (vivante && (somme < 2 || somme > 3)) vivante = false;
        else if (!vivante && somme == 3) vivante = true;
    }

    int compterVoisinageActif(){
        int somme = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i == 0 && j == 0) continue;
//                int xx = ((x + i) + grille.length) % grille.length;
                int xx = x+i;
                if(xx<0) xx+=grille.length;
                if(xx>=grille.length) xx-=grille.length;
//                int yy = ((y + j) + grille.length) % grille.length;
                int yy = y+j;
                if(yy<0) yy+=grille.length;
                if(yy>=grille.length) yy-=grille.length;
                if (grille[xx][yy].vivante) somme++;
            }
        }
        return  somme;
    }
}
