package processus.gestionStock;

class ProductConsom {
	public static void main(String[] args) {
		Entrepot tab = new Entrepot(20);
		//creation et lancement des producteurs
		ThreadGroup groupeProd = new ThreadGroup("producteurs");
		int nbProducteurs = 4;
		for(int i=0; i<nbProducteurs; i++)
			new Producteur(groupeProd, "product"+i, tab).start();

		//creation et lancement des consommateurs
		ThreadGroup groupeConsom = new ThreadGroup("consommateurs");
		int nbConsommateurs = 3;
		for(int i=0; i<nbConsommateurs; i++)
			new Consommateur(groupeConsom, "consom"+i, tab).start();

		//on les laisse travailler 10 secondes
		try { Thread.sleep(10000); }
		catch (InterruptedException ignored) {}

		//on recupere la liste des producteurs a partir du groupe
		Producteur[] lesProds = new Producteur[groupeProd.activeCount()];
		groupeProd.enumerate(lesProds);
		//et on leur demande d'arreter
		for(Producteur p:lesProds) p.halte();

		//on recupere la liste des consommateurs a partir du groupe
		Consommateur[] lesCons = new Consommateur[groupeConsom.activeCount()];
		groupeConsom.enumerate(lesCons);
		//et on leur demande d'arreter
		for(Consommateur c:lesCons) c.halte();


		System.out.println("voici le contenu de l'entrepot : ");
		System.out.println(tab);

		System.exit(0);

	}
}
