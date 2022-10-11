package processus.dinerPhilosophes;

import java.util.Arrays;
import java.util.OptionalDouble;
import java.util.function.Supplier;
import java.util.stream.DoubleStream;

/** classe principale lancant les processus philosophes
 * @author emmanuel adam */
public class LeDiner {

	/**nombre de messages affiches par les philosophes<br>
	 * 0-> aucun; 1 -> temps de repas; 2-> detail du repas*/
	static final int VERBOSELEVEL= 2;


	public static void main(String[] args)
	{
		int dim = 5;
		Fourchettes fourchettes = new Fourchettes(dim);
		Philosophe[] mangeurs = new Philosophe[dim];

		ThreadGroup groupe = new ThreadGroup("philos");


		// ajoute un philosophe  numero i dans chaque case d'indice i du tableau
		Arrays.setAll(mangeurs, i->new  Philosophe(groupe, i, 10, fourchettes));

		long dateDepart = System.currentTimeMillis();

		for (Philosophe mangeur:mangeurs) mangeur.start();

		while( groupe.activeCount()!=0) Thread.yield();

		long dateFin = System.currentTimeMillis();

		double duree = (dateFin -  dateDepart) / 1000d;

		System.out.printf("Le repas est fini en %.3f s.\n", duree);

		//recupere le flux contenant la liste des temps de repas des philosophes
		Supplier<DoubleStream> fluxTemps = ()->Arrays.stream(mangeurs).mapToDouble(Philosophe::getTempsTotalRepas);
		DoubleStream flux = fluxTemps.get();

		//temps le plus long pris par un philosophe pour terminer son repas
		OptionalDouble plusLong = flux.max();
		plusLong.ifPresent(max-> System.out.println("temps de repas max =" + max));

		//temps le plus long pris par un philosophe pour terminer son repas
		flux = fluxTemps.get();
		OptionalDouble plusCourt = flux.min();
		plusCourt.ifPresent(min-> System.out.println("temps de repas min =" + min));

		//temps moyen pris par les philosophe pour terminer leurs repas
		flux = fluxTemps.get();
		OptionalDouble moyen = flux.average();
		moyen.ifPresent(moy-> System.out.printf("temps de repas moyen = %.3f s.\n",+ moy));
	}

}
