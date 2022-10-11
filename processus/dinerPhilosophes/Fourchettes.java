package processus.dinerPhilosophes;
import java.util.Arrays;

/** liste des Fourchettes que doivent se partager les philosophes
 * @author emmanuel adam */
public class Fourchettes {
	/** tableau d'occupation des fourchettes false = occupee, true = libre*/
    private boolean[] couverts;
    /** nb de fourchettes*/
    private int taille;
    
    /** constructeur initialisant la taille et le tableau des fourchettes a true*/
    Fourchettes(int _taille) {
        taille = _taille;
    	couverts = new boolean[taille];
    	Arrays.fill(couverts, true);
    }
    
    /** fonction appelee par un processus philosophe i. <br>
     * Si la fourchette de gauche (i) et de droite (i+1) est libre alors le philosophe les prend, 
     * sinon, il est mis en attente*/
    synchronized void prendre(int no) {
    	int gauche = no;
    	int droite = (no+1)%taille;
        while (!couverts[gauche] || !couverts[droite]) {            
            try   {  wait();  }
            catch (InterruptedException ignored) {}
        }
        if(LeDiner.VERBOSELEVEL>=3)
           System.err.println(Thread.currentThread().getName() + " a pris les couverts ..");
        couverts[gauche] = false;
        couverts[droite] = false;
    }
    
    /** fonction appelee par un processus philosophe i. <br>
     * libere la fourchette de gauche (i) et de droite (i+1) <br> 
     * et reveille les processus en attente sur les four    chettes*/
    synchronized void deposer(int no) {
    	int gauche = no;
    	int droite = (no+1)%taille;    	
        couverts[gauche] = true;
        couverts[droite] = true;
        if(LeDiner.VERBOSELEVEL>=3)
           System.err.println(Thread.currentThread().getName() + " a pose les couverts ..");
        notifyAll(); // reveille les processus en attente de fourchettes
    }       
    


    
}
