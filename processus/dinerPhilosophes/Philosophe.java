package processus.dinerPhilosophes;

/**
 * classe representant un Philosophe dans le diner des Philosophes.<br>
 *     n philosophes sont assis autour d'une table et mangent des spagethis.<br>
 *     Pour manger, un philosophe a besoin de deux fourchettes.<br>
 *     Cependant il n'y a que 5 fourchettes,
 *      les philosophes alternent donc entre manger quand ils ont leurs fourchettes
 *      et penser quand ils attendent.
 * @author emmanuel adam
 */
public class Philosophe extends Thread {
    /**
     * no du philosophe
     */
    private int no;
    /**
     * nb de bouchees restantes dans l'assiette
     */
    private int nbBouchees;
    /**
     * temps total du repas pour le philosophe
     */
    private double tempsTotalRepas;

    /**
     * liste de fourchettes
     */
    private Fourchettes lesFourchettes;
    /**
     * temps min d'une bouchee en milliseconde
     */
    private static final int TempsBaseBouchee = 100;
    /**
     * temps min d'une pensee en milliseconde
     */
    private static final int TempsMinPensee = 100;


    /**
     * initialise le no et nb de bouchees
     */
    Philosophe(int _no, int _nbBouchees, Fourchettes _lesFourchettes) {
        super("philo" + _no);
        no = _no;
        nbBouchees = _nbBouchees;
        lesFourchettes = _lesFourchettes;
    }

    /**
     * initialise le no et nb de bouchees
     */
    Philosophe(ThreadGroup groupe, int _no, int _nbBouchees, Fourchettes _lesFourchettes) {
        super(groupe, "philo" + _no);
        no = _no;
        nbBouchees = _nbBouchees;
        lesFourchettes = _lesFourchettes;
    }

    /**
     * fonction principale du philosophe : cycle sur manger, penser.
     * Pour manger, il prend la fourchette de droite et celle de gauche.
     * Donc i prend la fourchette i et i+1.<br>
     * Le philosophe garde les fourchettes un certains temps et les depose ensuite<br>
     * la boucle se termine lorsque le philosophe a termine ses bouchees.
     */
    @Override
    public void run() {
        long debut = System.currentTimeMillis();
        long debutAttenteFourchettes, tpsAttenteFourchettes, totalAttenteFourchettes;
        totalAttenteFourchettes = 0;

        while (nbBouchees > 0) {
            affiche("je demande les fourchettes, j'attends en pensant");
            debutAttenteFourchettes = System.currentTimeMillis();
            lesFourchettes.prendre(no);
            tpsAttenteFourchettes = System.currentTimeMillis() - debutAttenteFourchettes;
            totalAttenteFourchettes += tpsAttenteFourchettes;

            if (LeDiner.VERBOSELEVEL >= 2)
                System.err.printf("%s : j'ai attendu les fourchettes pendant %d ms.%n", this.getName(), tpsAttenteFourchettes);

            nbBouchees--;
            affiche("j'ai obtenu les fourchettes, je mange, il me reste " + nbBouchees + " bouchees.");

            try { Thread.sleep(Philosophe.TempsBaseBouchee); } catch (InterruptedException ignored) { }

            lesFourchettes.deposer(no);

            affiche("je pense un peu apres ma bouchee...");
            try { Thread.sleep(Philosophe.TempsMinPensee); } catch (InterruptedException ignored) { }
        }

        long fin = System.currentTimeMillis();
        tempsTotalRepas = (fin - debut) / 1000d;
        if (LeDiner.VERBOSELEVEL >= 1)
            System.out.printf("%s : j'ai fini en %.3f s.%n", this.getName(), tempsTotalRepas);
        if (LeDiner.VERBOSELEVEL >= 2)
            System.err.printf("---> %s : au total j'ai attendu les fourchettes pendant %d ms.%n", this.getName(), totalAttenteFourchettes);

    }

    /**
     * @return temps total du repas pour le philosophe
     */
    double getTempsTotalRepas() {
        return tempsTotalRepas;
    }

    private void affiche(String msg) {
        if (LeDiner.VERBOSELEVEL >= 2)
            System.out.println(this.getName() + " : " + msg);
    }

}
