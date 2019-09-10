/**interface pour une opération arithmétique sur 2 entiers
 * @author emmanuel adam*/
public interface Calcul {
	/**opération artihmétique sur 2 entiers, fournissant un résultat entier
	 * @param a entier
	 * @param b second entier 
	 * @return resultation de l'opération de a par b (par addition, soustraction, multiplication ou division)
	 * */
	int execute(int a, int b);
}
