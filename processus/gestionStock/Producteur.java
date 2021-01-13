package processus.gestionStock;

/**classe processus representant un producteur deposant un entier dans un tableau
 * @author emmanuel adam
 * */
class Producteur extends Thread {
	/** lien vers l'entrepot */
	Entrepot entrepot;

	/** indique si le processus doit s'arreter */
	private boolean arret = false;

	private static int nbObjets = 0;
	/** no du produit à déposer */
	private int noObjet ;

	/**constructeur incluant le processus dans un groupe en lui donnant un nom et un lien vers l'entrepot*/
	public Producteur(ThreadGroup groupe, String _nom, Entrepot _entrepot) {
		super(groupe, _nom);
		entrepot = _entrepot;
		noObjet = nbObjets++;
	}

	/**constructeur  donnant un nom et un lien vers l'entrepot*/
	public Producteur(String _nom, Entrepot _entrepot) {
		super(_nom);
		entrepot = _entrepot;
		noObjet = nbObjets++;
	}

	public void run() {
		while (!arret) {
			noObjet = nbObjets++;
			entrepot.depose(noObjet);
			System.out.println(getName() + " : j'ai depose l'objet " + noObjet);
			try { Thread.sleep((int) (Math.random() * 1000));} //pause de 1000ms maxi
			catch (InterruptedException ignored) {
			}
		}
	}

	public void halte() {
		arret = true;
	}

}
