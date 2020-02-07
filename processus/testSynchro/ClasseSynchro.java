package testSynchro;

import java.time.LocalTime;

/**classe permettant de tester la synchronisation
 * @author emmanuel adam
 * */
public class ClasseSynchro {
	ClasseSynchro(){}
	
	/**tout processus entrant est bloqué 2 secondes. <br>
	 * si i est impair, le processus est mis en pause<br>
	 * si i est pair, le processus réveille tout processus en attente*/
	public synchronized void  testPair(int i) 
	{
		System.out.println(Thread.currentThread().getName() + ", je suis entré à " + LocalTime.now());
		try { Thread.sleep(2000); } 
		catch (InterruptedException e1) {e1.printStackTrace(); }
		if ((i % 2) != 0)
		{
			System.out.println("le nb n'est pas pair, je mets en attente");
			try { wait(); } catch (InterruptedException e) { e.printStackTrace(); }
		}
		else
		{
			System.out.println("le nb est pair");
			notifyAll();
		}
		System.out.println(Thread.currentThread().getName() + " sort de la fonction à " + LocalTime.now());		
	}
}
