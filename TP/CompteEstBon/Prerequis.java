/**interface pour test de validite d'une opération artithémtique sur 2 entiers
 * @author emmanuel adam*/
public interface Prerequis {
	/**test de validite d'une opération artithémtique sur 2 entiers a et b,
	 * @param a entier
	 * @param b second entier qui sera appliqué sur a (par addition, soustraction, multiplication ou division)
	 * @return true si l'operation de a par b est valide*/
	boolean execute(int a, int b);
}
