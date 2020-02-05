
/**
 * classe qui es une simple tache qui calcule la somme de nb <br>
 * le calcul peut être interrompu
 * @author emmanuel adam
 **/
class MaTacheCalcule extends Thread
{
	long taille, somme;
	boolean arret;
	
	MaTacheCalcule(String nom, long _taille) 
	{ super(nom); taille = _taille; arret = false;} // appel au constructeur de Thread
	
	/** lance le calcul*/
	public void run()
	{
		System.out.println("Mon nom est : " + getName());
		calcul();
		System.out.println(getName() + " ::: Je ne suis plus en vie, result=" +somme);
	}
	
	/**fonction activant l'arret*/
	public void arrete()	{ arret = true;	}
	
	/**calcule la somme des nb de 0 à taille. Peut s'arreter des que arret est active*/
	public void calcul()
	{
		int i=0;
		for(; i<taille && !arret; i++)
		{
			somme= somme + i;
			Thread.yield();	
			}
		if(!arret) System.out.println(getName() + " ::: J'ai reussi à finir !!!");
		else System.out.println(getName() + " ::: J'ai été arrété, il me restait " + (taille-i) + " calculs à faire !" );
	}
}


/**
 * classe lancant 2 taches de calcul et les interrompant avant la fin du calcul
 * @author emmanuel adam
 **/
public class TestProcess
{
	public static void main(String args[])
	{
		MaTacheCalcule tache1 = new MaTacheCalcule("tache 1", 1234567);
		MaTacheCalcule tache2 = new MaTacheCalcule("tache 2", 9124667);
		tache1.start();		
		tache2.start();
		System.out.println("Je suis la tâche principale");
		try
		{
			Thread.sleep(300);
		}
		catch(Exception e){System.out.println("pb dans le sommeil " + e);}
		System.out.println("Je suis la tâche principale et j'arrete les taches");
		tache2.arrete();
		tache1.arrete();
	}	}
