
/**enumeration representant des opération entieres<br>
 * chaque operateur contient son signe, sa fonction de calcul et sa fonction de test de faisabilite (verification que le resultat sera un entier strictement positif )<br>
 * 
 * version avec implementation d'interface Prerequis et Calcul qui ne possede qu'une methode
 * @author emmanuel adam
 * */
public enum OperateurTer {
	PLUS("+", (a,b)->(a+b), (a,b)->true),
	MINUS("-", (a,b)->(a-b), (a,b)->(b<a)), 
	TIMES("*", (a,b)->(a*b), (a,b)->(a!=1 && b!=1)), 
	DIV("/", (a,b)->(a/b), (a,b) -> (b<a && b!=1 && b%a==0));
	
	
	String sign;
	Prerequis test;
	Calcul calcul;
	
	OperateurTer(String _sign, Calcul _calcul, Prerequis _test)
	{
		sign=_sign; 
		calcul = _calcul;
		test = _test;
	}
	
}
