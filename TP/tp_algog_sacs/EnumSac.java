package tp_algog_sacs;

/**
 * classe codant les caracteristiques des objets possibles, leurs poids, leurs interets, et l'uniformasition de leurs valeurs
 * @author Emmanuel Adam*/
enum EnumSac {
	A1(349d,9d), B1(163d,9d), C1(490d,8d), D1(182d,7d), E1(417d,5d), F1(1d,9d), G1(245d,0d), H1(166d,3d), I1(402d,6d), J1(363d,7d), K1(200d,7d), L1(205d,8d), M1(59d,2d), N1(199d,2d), O1(208d,0d), P1(404d,7d), Q1(280d,7d), R1(215d,2d), S1(8d,3d), T1(50d,5d), U1(43d,6d), V1(237d,6d), W1(229d,9d), X1(463d,9d), Y1(288d,6d), Z1(209d,6d), A2(69d,8d), B2(101d,2d), C2(436d,2d), D2(266d,4d), E2(404d,9d), F2(394d,5d), G2(273d,1d), H2(328d,9d), I2(377d,6d), J2(32d,1d), K2(448d,1d), L2(314d,0d), M2(108d,0d), N2(279d,4d), O2(372d,6d), P2(394d,6d), Q2(66d,2d), R2(305d,8d), S2(143d,9d), T2(0d,7d), U2(350d,5d), V2(28d,0d), W2(484d,4d), X2(108d,9d), Y2(288d,5d), Z2(232d,5d);
	double poids;
	double interet;
	double poidsUniforme;
	double interetUniforme;
	static double SEUILPOIDSMAXI = 10000;
	/** pourcentage de sequences touches par la mutation */
	static final double MutationDesSequences = 0.5;
	/** pourcentage de mutation dans une sequence*/
	static final double MutationDansSequence = 0.1;

	EnumSac(double poids, double interet)
	{
		this.poids = poids;
		this.interet = interet;
	}


	/**calcule les poids et intérêts uniformiser de chaque sac :
	 * leurs poids et intérêts divisés par les poids et intérêts les plus grands*/
	public static void uniformiser()
	{
		EnumSac[] tab = EnumSac.values();
		var maxPoid = tab[0].poids;
		var maxInteret = tab[0].interet;
    //TODO: à compléter
	}

	/**NOTE : main utilise juste pour le test, a oter au final*/
	public static void main(String[] args)
	{
		//TEST
		EnumSac.uniformiser();
		for(EnumSac e:EnumSac.values()) {
			System.out.println(e + ",poids="+e.poids + ",interet="+e.interet);
			System.out.printf("\tpoids uniforme=%.2f, interet uniforme=%.2f \n",e.poidsUniforme, e.interetUniforme);
		}
	}

}
