package TP.TPRegLogistique;

import java.util.Random;

/**
 * classe permettant un apprentissage simple par régression logistique.
 * Supposons 2 valeurs x1 et x2, chaque couple de valeur est classé dans un groupe 1 ou 0 (y=1 ou y=0)
 * LE but de l'algorithme est d'apprendre à classer automatiquement les couples.
 * pour cela, l'algo applique des coefficients theta aux valeurs de x et prédit une sortie :
 * theta0 + theta1.x1 + theta2.x2 = yp
 *
 * Puis l'algo mesure son erreur et modifie les thetas pour la corriger à partir de cette mesure d'erreur.
 *
 * @author E. ADAM
 * */
public class LogisticRegression {

    /**
     * dimension des données
     */
    int dim = 2;
    /**
     * thetas coef (last is theta0 in the equation)
     */
    double[] thetas;
    /**
     * nb of samples
     */
    int nb = 2;
    /**
     * nb of learning steps
     */
    int nbSteps = 100;

    /**
     * initialise l'algo de régression logistique
     * @param  _dim : length of a sample
     * @param  _nbSteps :nb of learning Steps """
     */
    LogisticRegression(int _dim, int _nbSteps) {
        this.dim = _dim;
        this.nbSteps = _nbSteps;
        Random hasard = new Random();
        this.thetas = hasard.doubles(dim+1).toArray();
    }


    /**
        corrige les coefficients thetas (les "paramètres du classifieur") sur nbSteps etapes
     @param X (tableau (m,n)): m exemples de n valeurs
     @param  y (tableau(m)): un vecteur de Y espérés (idéaux, de valeurs 0 ou 1)
        */
    void fit(double[][] X, double[] y) {
        double[] yp; // va contenir les predictions
        double dif; // va contenir les differences entre yi et yp
        double alpha = 0.1; // coef de reduction, peut etre mmodifie
        for (int s = 0; s < nbSteps; s++) {
            //on corrige les thetas :
            //thetas[0] = thetas[0] + alpha * dif;
            //thetas[1] = thetas[1] + alpha * dif * X[j][0];
            //thetas[2] = thetas[2] + alpha * dif * X[j][1];
            // ........etc pour avoir un code adaptable à différentes dimensions de données (bonus)
            }
        }




    /**
    genere une prédiction
    @param xs (tableau (m,n)): m exemples de n valeurs
    @return  un tableau (m) de prédiction de y  dans [0,1]
    */
    double[] predict(double[][] xs) {
        //TODO:
        //retourner un tableau de y où chaque y est calculé par
        //y = f(theta[0] + theta[1]*x[0] + theta[2]*x[1])
        // f = sigmoide
        return null;
    }



}
