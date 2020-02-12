package exam.AgloGSac;

import java.util.Arrays;

/**
 * sequence genetique composee de 'longSequence' entiers
 *
 * @author Emmanuel Adam
 * @since 2013.03.22
 */
public class Sequence implements Comparable<Sequence> {
    /**
     * sequence = tableau d'entiers
     */
    private int[] genes;
    /**
     * longueur d'une sequence
     */
    private int longueurGene;
    /**
     * nb de genes devant muter
     */
    private int nbGenesAMuter;
    /**
     * score
     */
    private double tauxGenesMutants;
    /**
     * score
     */
    private double utilite;


    Sequence(int _longueurGene, double _tauxGenesMutants) {
        longueurGene = _longueurGene;
        tauxGenesMutants = _tauxGenesMutants;
        nbGenesAMuter = (int) Math.round(longueurGene * _tauxGenesMutants);
        genes = new int[longueurGene];
    }

    /**
     * construit et retourne une sequence initialisee au hasard <br>
     * modifie le tableau 'sequence' et demande le calcul de l'utilite
     */
    void construireSequence() {
        Arrays.setAll(genes, i -> (Math.random() < 0.5 ? 0 : 1));
        calculUtilite();
    }

    /**
     * retourne la sequence resultant du croisement de A et de B<br>
     * si A = a1b1c1d1 e1f1g1h1  et
     * si B = a2b2c2d2 e2f2g2h2  <br>
     * alors C = a1b1c1d1 e2f2g2h2 avec a1,b1,c1,d1,e2,f2,g2,h2
     *
     * @param autre Sequence avec laquelle la sequence courante doit se reproduire
     * @return la sequence issue du croisement
     */
    Sequence croiserSequence(Sequence autre) {
        Sequence result = new Sequence(longueurGene, tauxGenesMutants);
        int demiLongSequence = longueurGene / 2;
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
        for (int i = 0; i < nbGenesAMuter; i++) {
            int num = (int) (longueurGene * Math.random());
            genes[num] = 1 - genes[num];
        }
        calculUtilite();
    }

    /**
     * calcul de l'utilité du sac codé par la séquence.
     * c-a-d la somme des interets unifies - la somme des poids<br>
     * l'utilité calculee = Double.NEGATIVE_INFINITY si le poids dépasse le poids maximal autorise
     */
    void calculUtilite() {
        //somme des poids normalises
        double sommePoids = 0;
        //somme des interets normalises
        double sommeInterets = 0;
        for (int i = 0; i < longueurGene; i++) {
            if (genes[i] == 1) {
                sommePoids += CaracteristiquesSac.PoidsUniformes.get(i);
                sommeInterets += CaracteristiquesSac.InteretsUniformes.get(i);
            }
        }
        if (sommePoids > CaracteristiquesSac.MaxPoidsUnifie)
            utilite = Double.NEGATIVE_INFINITY;
        else
            utilite = sommeInterets - sommePoids;
    }


    /**
     * retourne une chaine de caractere representant la sequence + son score
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("[ ");
        for (int val : genes)
            sb.append(val).append(" ");
        sb.append("] : utilité = ");
        sb.append(utilite);
        return sb.toString();
    }


    /**
     * comparaison de sequence par utilite décroissante <br>
     * deux scores sont identiques si egaux à +- 0.00000001<br>
     * une séquence d'utilite  Double.NEGATIVE_INFINITY est apres l'autre
     * sauf si l'autre a aussi son utilité à  Double.NEGATIVE_INFINITY, auquel cas les deux séquences sont considérée égales
     */
    @Override
    public int compareTo(Sequence autre) {
        int retour;
        double precision = 10000000;
        if (utilite == Double.NEGATIVE_INFINITY || autre.utilite == Double.NEGATIVE_INFINITY) {
            if (utilite == Double.NEGATIVE_INFINITY && autre.utilite == Double.NEGATIVE_INFINITY) retour = 0;
            else if (utilite == Double.NEGATIVE_INFINITY) retour = 1;
            else retour = -1;
        } else retour = (int) Math.round(precision * (autre.utilite - utilite));
        return retour;
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
