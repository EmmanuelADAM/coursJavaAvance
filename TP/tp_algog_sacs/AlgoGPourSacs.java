import java.util.*;

import static java.lang.System.out;

/**
 * classe codant la recherche du sac le plus intéressant, et le moins lourd,
 * et ne dépassant pas  poids maximum autorisé<br>
 * Cette classe créé un panneau d'affichage des genes et de leurs résultats<br>
 * algo génétique, une séquence est un tableau binaire composé de 0 et de 1<br>
 * ex: [0,1,1,0] signifie que le sac contient les produits 1 et 2 (si le décompte des produits débute à 0)
 *
 * @author Emmanuel Adam
 */
@SuppressWarnings("serial")
public class AlgoGPourSacs {

    /** tableau des sequences*/
    private final Sequence[] tabSequences;

    /**nb de sequences a faire "vivre"*/
    private final int nbSequences = 50;
    /**nb de sequences devant muter */
    private final int nbSequenceAMuter;

    /** longueur d'une séquence*/
    private final int longSequence;


    /**
     * constructeur par défaut
     */
    private AlgoGPourSacs() {
        EnumSac.uniformiser();
        nbSequenceAMuter = (int) (nbSequences * EnumSac.MutationDesSequences);
        tabSequences = new Sequence[nbSequences];
        longSequence = EnumSac.values().length;
        construirePopulation();
    }

    /**
     * construit nbSequences sequences viables (d'utilite differente de Double.NEGATIVE_INFINITY)
     * et les place dans le tableau tabSequences
     */
    private void construirePopulation() {
        //TODO: A COMPLETER
    }

    /*** affiche les sequences, la meilleure, la pire
     * pour la meilleure, demande l'affichage du detail (appel a la fonction afficherDetail)*/
    private void afficherCodes() {
        //TODO: A COMPLETER
    }

    /**
     * affiche les noms des produits de la séquence, son poids totaln son interet total et son utilite*/
    private void afficherDetail(Sequence s) {
        int[] sequence = s.getSequence();
        double sommePoids = 0.0;
        double sommeInterets = 0.0;
        for (int j = 0; j < sequence.length; j++) {
            var elt = EnumSac.values()[j];
            if (sequence[j] == 1) sommePoids += elt.poids;
            if (sequence[j] == 1) sommeInterets += elt.interet;
        }
		out.println("poids de la meilleure solution : " + (sommePoids / 1000) + " kg.");
		out.println("interet de la meilleure solution :" + sommeInterets);
    }




    /**
     * croise nbElites entre eux.
     * Chaque resultat s'il est viable est classé en bas de tableau
     */
    private void croiserMeilleurs(int nbElites) {
        //TODO: A COMPLETER
    }

    /**
     * fonction effectuant des mutations dans le tableau des sequences : <br>
     * ici, on choisit des sequences au hasard, sauf la premiere, et demande la modification des genes
     */
    private void muter() {
        //TODO: A COMPLETER
    }

	/**
	 * fonction effectuant un cycle : tri du tableau des sequences, croisement des 2 meilleurs, mutation
	 */
	 void cycleDeVie()
	{
        //TODO: A COMPLETER
	}



    /**
     * fonction principale
     */
    public static void main(String[] args) {
    	var algo = new AlgoGPourSacs();
    	algo.afficherCodes();

    	Scanner sc = new Scanner(System.in);
    	String rep=null;
    	while (!Objects.equals(rep, "stop"))
        {
            for(int i=0; i<10; i++)
                algo.cycleDeVie();
            algo.afficherCodes();
            System.out.println("Encore 10 cycles de vie ? (oui ou stop) : ");
            rep = sc.nextLine();
        }
    }

}
