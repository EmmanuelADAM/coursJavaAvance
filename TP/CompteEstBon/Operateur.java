
import java.util.function.BiFunction;

/**enumeration representant des opération entieres<br>
 * chaque operateur contient son signe, sa fonction de calcul et sa fonction de test de faisabilite (verification que le resultat sera un entier strictement positif )<br>
 * 
 * version avec surcharge de methode declaree en abstract dans l enumeration
 * @author emmanuel adam
 * */

public enum Operateur {
	PLUS("+"){ 
		int calcul(int a, int b) {return (a+b);}
		boolean test(int a, int b) {return (true);}
		},
	MINUS("-"){
			int calcul(int a, int b) {return (a-b);}
			boolean test(int a, int b) {return (b<a);}
		},
	TIMES("x"){
			int calcul(int a, int b) {return (a*b);}
			boolean test(int a, int b) {return (a!=1 && b!=1);}
		},
	DIV("/"){
			int calcul(int a, int b) {return (a/b);}
			boolean test(int a, int b) {return (b<a && b!=1 && b%a==0);}
		};

	String sign;
	abstract int calcul(int a, int b);
	abstract boolean test(int a, int b);
	
	Operateur(String _sign)
	{
		sign=_sign; 
	}
	
}
