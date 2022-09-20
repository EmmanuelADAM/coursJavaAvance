package TP.TPNN;

import java.util.Arrays;

/**classe contenant des fonctions de calculs classiques utilisées pour l'apprentissage par régression, descente de
 * gradiants...
 * @author E.ADAM
 * */
public class Calcul {

    /**
    @param  x reel
    @return la sigmoide de x : 1/(1+e^-x)
     */
    public static  double sigmoid(double x) {
        //TODO:
        return 0;
    }



    /**            "
     calcul l'erreur moyenne entre des valeurs attendues et des valeurs predites binary cross entropy
     @param yi valeurs attendues (ideales)
     @param yp valeurs predites
     @return erreur en utilisant le calcul de la "Binary cross entropy" moyenne : | yi*log(yp) + (1-yi)*log(1-yp) |
     */
    public static  double  binaryCrossEntropy(double[] yi, double[] yp)
    {
        //TODO:
        return 0;
    }


    /**
     calcul la précision moyenne des valeurs prédites yp par rapport aux valeurs
     @param yi valeurs attendues (ideales)
     @param yp valeurs predites
     @param seuil valeur à partir de laquelle une valeur yp est consideree comme 0 ou 1
     @return le nombre moyen des bonnes prédictions
*/
    public static  double   binaryAccuracy(double[] yi, double[] yp, double seuil) {
        var ypSeuil = new double[yp.length];
        Arrays.setAll(ypSeuil, i -> yp[i] >= seuil ? 1 : 0);
        var corrects = new double[yp.length];
        Arrays.setAll(corrects, i -> ypSeuil[i] == yi[i] ? 1 : 0);
        var mean = 0d;
        for (double v : corrects) mean += v;
        return mean / yp.length;
    }
}

