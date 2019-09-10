
/**
 * classe pour test unitaires
 * @author emmanuel adam
 * */
public class Test {

	static void testOpeTer()
	{
		OperateurTer op = OperateurTer.MINUS;
		int a=6, b=5;
		boolean ok = op.test.execute(a,b);
		System.out.println("faisabilite de " + a + " " + op.sign + " " + b + " = " + ok);
		if (ok)
			System.out.println("resultat de " + a + " " + op.sign + " " + b + " = " + op.calcul.execute(a, b));;
	}

	static void testOpeBis()
	{
		OperateurBis op = OperateurBis.MINUS;
		int a=6, b=5;
		boolean ok = op.test.apply(a,b);
		System.out.println("faisabilite de " + a + " " + op.sign + " " + b + " = " + ok);
		if (ok)
			System.out.println("resultat de " + a + " " + op.sign + " " + b + " = " + op.calcul.apply(a,b));;
	}

	static void testOpe()
	{
		Operateur op = Operateur.MINUS;
		int a=6, b=5;
		boolean ok = op.test(a,b);
		System.out.println("faisabilite de " + a + " " + op.sign + " " + b + " = " + ok);
		if (ok)
			System.out.println("resultat de " + a + " " + op.sign + " " + b + " = " + op.calcul(a,b));;
	}

	public static void main(String[] args) {
		testOpeBis();
	}

}
