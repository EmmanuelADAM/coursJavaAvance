package TP.TPNN;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;


/**
 * classe permettant un apprentissage simple par réseau de neurone monocouche à 1 sortie.
 * Le but de l'algorithme est d'apprendre à classer automatiquement les exemples composés de 1 et 0, .
 * qui correspondent à des textes.
 * Pour cela, l'algo applique des coefficients w aux valeurs de x et prédit une sortie :
 * w0.x0 + w1.x1 + w2.x2 + .... + wn.xn = yp
 * Il applique une fonction sigmoide pour cadrer la sortie entre 0 & 1
 *
 *
 * Puis l'algo mesure son erreur et modifie les w pour la corriger à partir de cette mesure d'erreur.
 *
 * @author E. ADAM
 * */public class Perceptron1 {
	/**poids appliques aux signaux recus par le neurone de sortie*/
	double[] weights;

    /**nb de poids*/
    int nbWeights;



	/**initialise le tableau des n poids (weighs) avec des valeurs aléatoires entre -1 et 1*/
	Perceptron1(int n) {
        nbWeights = n;
		weights = new double[nbWeights];
		Arrays.setAll(weights, i->(2*Math.random()-1));
	}


	/**calcule la somme ponderee des entrees par les poids et applique la sigmoide sur le resultat
	 * @param inputs exemple sous la forme d'un tableau de nb valeurs
     * @return sigmoid(inputs[i]xweight[i])
     * */
	double feedForward(double[] inputs) {
        //TODO:
		return 0;
	}


	/**entraine le reseau
     * @param gd objet permettant de recuperer les entrees et les sorties attendues
     * */
	double[] train(GestionData gd)
	{
		int nbExemples = gd.nbExemples;
        double[][] entrees = gd.getXs();
        double[] sortieIdeale = gd.getY();
		double[] sortieCalculee = new double[nbExemples];
		double[] erreurs = new double[nbExemples];
		double[] corrections = new double[gd.nbValeurs];

        //nb d'etapes d'entrainement maxi
        int nbSteps = 1000000;
		double erreur = 0;
        //seuil d'erreur max acceptable
		double acceptation = 0.001;
		int iterations =0;
		double coefLearning = 0.1;

        //TODO: faire la suite...

        return sortieCalculee;
	}


	/**
     * pour chaque exemple des entrees, calcule une sortie, mesure l'erreur par rapport à la sortie idéale.
     * @param entrees exemples d'apprentissage
     * @param sortiesI sorties idéales
     * @param sortiesCalculees sortie calculees (modifiees par la fonction)
     * @param erreurs erreurs calculees (modifiees par la fonction)
	 * @return l'erreur moyenne*/
	private double computeOutputAndErrors(double[][] entrees, double[] sortiesI, double[] sortiesCalculees,
                                          double[] erreurs) {
		int nbSamples = entrees.length;
		double error  = 0;
        //TODO: completer
		return error;
	}


	/**programme principal pour tester l'apprentissag*/
	public static void main(String[] args)
	{
        GestionData gd = new GestionData();

        var xs = gd.getXs();
        var y = gd.getY();
        for (int i=0; i<gd.nbExemples; i++) {
            double[] exemple = xs[i];
            System.out.print(gd.toStringExemple(exemple));
            if (y[i]==0) System.out.println(" Ce n'est pas un danger");
            else System.out.println(" C'est un danger");
        }

//        Perceptron1 p1 = new Perceptron1(gd.nbValeurs);
//        p1.train(gd);
//
//        double[]essaiDePrediction = {1, 0,	1,	0,	0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	0};
//        var r = p1.feedForward(essaiDePrediction);
//        System.out.println(gd.toStringExemple(essaiDePrediction));
//        System.out.printf("il y a %.2f %% de chance  que cet exemple soit un danger", (r*100));


	}
}
