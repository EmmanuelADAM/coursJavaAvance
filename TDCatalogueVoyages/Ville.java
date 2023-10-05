package TDCatalogueVoyages;

/**Villes (A,B,C,D,E,F).
 * cette énumération possède la matrice des distances entre les villes
 * */
public enum Ville {A,B,C,D,E,F;
    /**matrice d'adjacences représentant les distances entre les villes*/
    final static double[][] matDist =
            {{0.,10.,-1.,20.,-1.,35.},
             {10.,0.,10.,15.,-1.,-1.},
             {-1.,10.,0.,10.,15.,20.},
             {20.,15.,10.,0.,15.,20.},
             {-1.,-1.,15.,15.,0.,10.},
             {35.,-1.,20.,20.,10.,0.}};

    /**retourne la distance entre les villes start et end*/
    static double getDist(Ville start, Ville end){
        int i=start.ordinal();
        int j=end.ordinal();
        return matDist[i][j];
    }

    /**retourne la distance entre la ville utilisant cette fonction et la ville end*/
    double getDist(Ville end){
        int i=this.ordinal();
        int j=end.ordinal();
        return matDist[i][j];
    }

    /**retourne vrai s'il existe un chemin direct entre la ville utilisant la fonction et la ville end*/
    boolean existeChemin(Ville end){
        return (getDist(end) >= 0);
    }
}
