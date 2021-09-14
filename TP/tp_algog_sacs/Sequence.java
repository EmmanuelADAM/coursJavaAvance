import java.util.Arrays;

/**
 * sequence genetique composee de 'longSequence' entiers
 *
 * @author Emmanuel Adam
 */
public class Sequence implements Comparable<Sequence> {
    /**
     * sequence = tableau d'entiers
     */
    private final int[] genes;
    /**
     * longueur d'une sequence
     */
    private final int longueurSequence;
    /**
     * nb de genes devant muter
     */
    private final int nbGenesAMuter;
    /**
     * score
     */
    private final double tauxGenesMutants;
    /**
     * score
     */
    private double utilite;


    Sequence(int longueurSequence, double tauxGenesMutants) {
        this.longueurSequence = longueurSequence;
        this.tauxGenesMutants = tauxGenesMutants;
        nbGenesAMuter = (int) Math.round(longueurSequence * tauxGenesMutants);
        genes = new int[longueurSequence];
    }

    /**
     * construit et retourne une sequence initialisee au hasard <br>
     * modifie le tableau 'sequence' et demande le calcul de l'utilite
     */
    void construireSequence() {
        //TODO: A COMPLETER
    }

    /**
     * retourne la sequence resultant du croisement de la sequence actuelle avec une autre<br>
     * si sequence = a1b1c1d1 e1f1g1h1  et
     * si autre = a2b2c2d2 e2f2g2h2  <br>
     * alors retourne C = a1b1c1d1 e2f2g2h2
     *
     * @param autre Sequence avec laquelle la sequence courante doit se reproduire
     * @return la sequence issue du croisement
     */
    Sequence croiserSequence(Sequence autre) {
        //TODO: A COMPLETER
        Sequence result = new Sequence(longueurSequence, tauxGenesMutants);
        int demiLongSequence = longueurSequence / 2;
        System.arraycopy(genes, 0, result.genes, 0, demiLongSequence);
        System.arraycopy(autre.genes, demiLongSequence, result.genes, demiLongSequence, demiLongSequence);
        result.calculUtilite();
        return result;
    }



    /**
     * fonction effectuant des mutations dans les genes de la sequence.<br>
     * choix de nbGenesAMuter genes
     * une mutation est un changement de 0 en 1 ou inversement
     */
    void muterSequence() {
        //TODO: A COMPLETER
    }

    /**
     * calcul de l'utilité du sac codé par la séquence.
     * c-a-d la somme des interets unifies - la somme des poids unifies pour chaque objet present dans le sac<br>
     * l'utilité calculee = Double.NEGATIVE_INFINITY si le poids dépasse le poids maximal autorise
     */
    void calculUtilite() {
        //TODO: A COMPLETER
        //Fonction la plus longue du TP
    }


    /**
     * retourne une chaine de caractere representant la sequence + son score
     */
    public String toString() {
        //TODO: A COMPLETER
    }


    /**
     * comparaison de sequence par utilite décroissante <br>
     * deux scores sont identiques si egaux à +- 0.00000001 ou si tous deux égales à Double.NEGATIVE_INFINITY<br>
     * on cherche à placer en 1er les petits scores,
     * mais score  comp à  Double.NEGATIVE_INFINITY retournera une valeur <0
     * et Double.NEGATIVE_INFINITY comp à un score retournera une valeur >0
     */
    @Override
    public int compareTo(Sequence autre) {
        //TODO: A COMPLETER
    }

    /**
     * @return the sequence
     */
    int[] getSequence() {
        return genes;
    }

    /**
     * @return the score
     */
    double getUtilite() {
        return utilite;
    }

}
