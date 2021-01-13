package processus.gestionStock;

/**classe processus representant un client retirant un entier dans un tableau
 * @author emmanuel adam
 * */
class Consommateur extends Thread{
	/**lien vers l'entrepot*/
    Entrepot entrepot;

    /**indique si le processus doit s'arreter*/
    boolean arret = false;

    /**constructeur  donnant un nom et un lien vers l'entrepot
     * appel au contructeur de la classe Thread*/
    public Consommateur(String _nom, Entrepot _entrepot)
    { super(_nom); entrepot = _entrepot; }

    /**constructeur incluant le processus dans un groupe en lui donnant un nom et un lien vers l'entrepot
     * appel au contructeur de la classe Thread*/
    public Consommateur(ThreadGroup groupe, String _nom, Entrepot _entrepot)
    { super(groupe, _nom); entrepot = _entrepot; }

    public void run() {
        while (!arret) {
            int obj = entrepot.preleve();
            System.out.println(getName() + ": j'ai preleve l'objet " + obj);
            try	{	Thread.sleep((int)(Math.random()*2000));	} // pause de 2s maxi
            catch (InterruptedException ignored) {}
        }
    }
        
    public void halte() { arret = true; }
    
}


