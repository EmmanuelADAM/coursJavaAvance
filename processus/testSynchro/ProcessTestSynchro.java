package testSynchro;

import java.time.LocalTime;

/**classe créant des processus pour tester la notion de synchronisation
 * @author emmanuel adam
 * */
public class ProcessTestSynchro extends Thread{
	/**nb dont le processus tente de trouver la parite*/
	int i;
	/**classe contenant une methode synchronisee pour tester la parite*/
	ClasseSynchro classeTest;
	
	/**
	 * @param _name nom du processus
	 * @param _i nb dont il faut tester la parité
	 * @param _classeTest classe contenant la methode testant la parité
	 */
	ProcessTestSynchro(String _name, int _i, ClasseSynchro _classeTest){
		super (_name);  
		i = _i;
		classeTest = _classeTest; 
		}
	
	
	public void run()
	{
		classeTest.testPair(i);
	}

	
	/**crée 3 processus, 2 testant un nb impair, un dernier testant un nb pair..
	 * */
	public static void main(String[] args)
	{
		ClasseSynchro classeSynchro = new ClasseSynchro();
		Thread p1 = new Thread(()->classeSynchro.testPair(3), "p1");
		Thread p2 = new Thread(()->classeSynchro.testPair(5), "p2");
		Thread p3 = new Thread(()->classeSynchro.testPair(4), "p3");
		System.out.println(LocalTime.now()+ " lancement de p1");
		p1.start();
		System.out.println(LocalTime.now()+ " lancement de p2");
		p2.start();
		try { Thread.sleep(5000); } catch (InterruptedException e) {e.printStackTrace();}
		System.out.println(LocalTime.now()+ " lancement de p3");
		p3.start();
	}
}
