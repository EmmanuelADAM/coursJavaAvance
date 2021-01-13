package processus.gestionStock;

class ProductConsom {
	public static void main(String[] args) {
		Entrepot tab = new Entrepot(20);
		//creation et lancement des producteurs
		ThreadGroup groupeProd = new ThreadGroup("producteurs");
		new Producteur(groupeProd, "product1", tab).start();
		new Producteur(groupeProd, "product2", tab).start();

		//creation et lancement des consommateurs
		ThreadGroup groupeCons = new ThreadGroup("consommateurs");
		new Consommateur(groupeCons, "consom 1", tab).start();
		new Consommateur(groupeCons, "consom 2", tab).start();
		new Consommateur(groupeCons, "consom 3", tab).start();

		//on les laisse travailler 10 secondes
		try { Thread.sleep(10000); }
		catch (InterruptedException ignored) {}

		//on recupere la liste des producteurs a partir du groupe
		Producteur[] lesProds = new Producteur[groupeProd.activeCount()];
		groupeProd.enumerate(lesProds);
		//et on leur demande d'arreter
		for(Producteur p:lesProds) p.halte();

		//on recupere la liste des consommateurs a partir du groupe
		Consommateur[] lesCons = new Consommateur[groupeCons.activeCount()];
		groupeCons.enumerate(lesCons);
		//et on leur demande d'arreter
		for(Consommateur c:lesCons) c.halte();


		System.out.println("voici le contenu de l'entrepot : ");
		System.out.println(tab);

		System.exit(0);

	}
}
