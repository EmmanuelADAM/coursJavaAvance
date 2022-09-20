package TP.TPNN;

import java.util.Arrays;


/**gestion des valeurs pour l'apprentissage machine par r�seau de neurones
 * @author emmanuel adam
 * */
public class GestionData {
    /**valeurs X uniquement*/
    double[][] Xs;

    /**valeurs y attendues, id�ales uniquement*/
    double[] Y;

    /**nb d'exemples*/
    int nbExemples;
    /**nb de valeurs dans chaque exemple, y compris le '1' ajoute en tete*/
    int nbValeurs;


    GestionData(){
        nbExemples = Data.data.length;
        nbValeurs = Data.data[0].length;
    }

    /**@return Xs ne contenant que les valeurs x de Data, en ajoutant une valeur '1' en t�te de chaque ligne
     *  (cr�e Xs s'iln'est pas cr��)
     *  */
    public  double[][] getXs() {
    //TODO:
    return Xs;
    }

    /**@return Y ne contenant que les valeurs Y id�ales (cr�e le tableau Y s'il n'est pas cr��)*/
    public double[] getY() {
        //TODO:
        return Y;
    }

    /**retourne une chaine de la forme "l'exemple contient ...."
     * contenant les mots cl�s relatifs � l'exemple
     * */
    public String toStringExemple(double[] exemple)
    {
        StringBuilder sb = new StringBuilder("l'exemple contient ");
        for(int i=1; i<nbValeurs; i++)
            if(exemple[i]==1) sb.append(MotsCles.values()[i-1]).append(", ");
        return sb.toString();
    }

}
