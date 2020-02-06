import java.math.BigInteger;

/**
 * classe présentant des exemples de processus definis par lamba expression; donc sans classe dédiés
 * @author emmanuel adam
 * */
public class TestLambdaRun {

	public static void testPetitsRuns()
	{
		ThreadGroup groupe = new ThreadGroup("mon groupe");
		Thread p1 = new Thread(groupe, ()->{int j=0; for(int i=0; i<5; i++){j=i; System.err.println("p1->" + j);Thread.yield();}});
		Thread p2 = new Thread(groupe, ()->{for(int i=100; i<105; i++){System.err.println("p2->" + i);Thread.yield();}});
		Thread p3 = new Thread(groupe, ()->{for(int i=200; i<205; i++){System.err.println("p3->" + i);Thread.yield();}});
		p1.start(); p2.start(); p3.start(); 
		while(groupe.activeCount()!=0 ) Thread.yield();
		System.err.println("Ils ont fini !!!");
	}




	/**@return un big integer correspondant à start0 x start1 x .... x end*/
	static BigInteger factBI(int start, int end)
	{
		BigInteger res= BigInteger.ONE;
		BigInteger bi= BigInteger.valueOf(start);
		int i=start;
		while(i<=end)
		{
			res = res.multiply(bi);
			bi = bi.add(BigInteger.ONE);
			i++;
			Thread.yield();
		}
		return res;
	}
	
	/**calcul de la combinatoire (combien de groupes différent de p éléments parmi n))<br>
	 * utilise 2 processus places dans un groupe et effectue le calcul final à la fin de ceux-ci*/
	static void cnpBIQuick(int n, int p)
	{
		BigInteger[] combi = new BigInteger[2];
		int bas = p;
		int haut = (n-p);
		if(bas>haut) { bas = (n-p); haut = p;}
		final int petit = bas;
		final int grand = haut;
		ThreadGroup grp = new ThreadGroup("combi");
		Thread dessous = new Thread(grp, ()->combi[0]=factBI(1,petit));
		Thread dessus  = new Thread(grp, ()->combi[1]=factBI((grand+1),n));
		dessous.start(); dessus.start();

		while(grp.activeCount()!=0) Thread.yield();
		
		for(int i=0; i<2; i++) System.out.println("fact["+i+"] = " +  combi[i] );
		
		BigInteger res = combi[1].divide(combi[0]);
		System.out.println("combi de " + p + " elements parmi "+ n + " = " + res);
	}

	
	public static void main(String ...args)
	{
		testPetitsRuns();
		cnpBIQuick(10000, 30);
	}

}
