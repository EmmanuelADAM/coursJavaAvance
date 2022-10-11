package processus.gestionStock;

import java.util.Arrays;

/**classe representant un conteneur ayant des classes de dépot et retrait d'entiers synchronisées
 * @author emmanuel adam
 * */
class Entrepot {

	/**tableau où sont stockés des entiers*/
	private final int[] stockage;

	/**taille de l'entrepot*/
	private final int taille;
	/**nb d'entiers qui ont été deposés*/
	private int  nbDeposes;
	/**nb d'entiers qui ont été pris*/
	private int  nbPris;
	/**nb d'entiers actuellement dans l'entrepot*/
	private int nbObjetsCourants;


	public Entrepot(int _taille) {
		stockage = new int[_taille];
		taille = _taille;
		nbDeposes = nbPris = nbObjetsCourants = 0;
	}

	/**fonction synchronise de depot d'objet (entier)*/
	public synchronized void depose(int obj) {
		// tq que le tableau est plein, mettre en pause
		while (nbObjetsCourants >= taille) {
			// affiche le nom du processus appelant et mis en pause
			System.out.println(Thread.currentThread().getName() + "-- est mis en pause dans le depot d'objet");
			try { wait(); } catch (InterruptedException e) { }
			System.out.println(Thread.currentThread().getName() + "-- reprend la tentative de depot d'objet");
		}
		nbObjetsCourants++;
		stockage[nbObjetsCourants-1] = obj;
		nbDeposes++;
		//reveiller des autres processus au cas ou
		notifyAll();
	}

	/**fonction synchronise de retrait d'objet (entier)*/
	public synchronized int preleve() {
		// tq que le tableau est vide, mettre en pause
		while (nbObjetsCourants == 0) {
			// affiche le nom du processus appelant et mis en pause
			System.out.println(Thread.currentThread().getName() + "-- est mis en pause dans le retrait d'objet");
			try { wait(); } catch (InterruptedException e) { }
			System.out.println(Thread.currentThread().getName() + "-- reprend la tentative de retrait d'objet");
		}
		int obj = stockage[nbObjetsCourants - 1];
		nbObjetsCourants--;
		nbPris++;
		//reveiller des autres processus au cas ou
		notifyAll(); 
		return obj;
	}

	@Override
	public String toString() {
		String chaine = Arrays.toString(stockage);
		chaine = chaine + "\n nbObjetsProduits = " + nbDeposes + ", nbObjetsConsommes = " + nbPris;
		return chaine;
	}
}
