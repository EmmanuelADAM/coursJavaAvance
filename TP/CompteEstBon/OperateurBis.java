import java.util.function.BiFunction;

/**enumeration representant des opération entieres<br>
 * chaque operateur contient son signe, sa fonction de calcul et sa fonction de test de faisabilite (verification que le resultat sera un entier strictement positif )<br>
 * 
 * version avec BiFunction
 * @author emmanuel adam
 * */
public enum OperateurBis {
	PLUS("+", (a,b)->(a+b), (a,b)->true),
	MINUS("-", (a,b)->(a-b), (a,b)->(b<a)), 
	TIMES("*", (a,b)->(a*b), (a,b)->(a!=1 && b!=1)), 
	DIV("/", (a,b)->(a/b), (a,b) -> (b<a && b!=1 && b%a==0));

	String sign;
	BiFunction<Integer, Integer, Integer>  calcul;
	BiFunction<Integer, Integer, Boolean>  test;
	
	OperateurBis(String _sign, BiFunction<Integer, Integer, Integer> _calcul, BiFunction<Integer, Integer, Boolean> _test)
	{
		sign=_sign; calcul = _calcul; test=_test;
	}
	
}
