package tp_algog_sacs;

/**
 * classe codant les caracteristiques des objets possibles, leurs poids, leurs interets, et l'uniformasition de leurs valeurs
 * @author Emmanuel Adam*/
enum EnumSac {
	A0(697d,7d),B0(45d,3d),C0(980d,4d),D0(744d,4d),E0(204d,7d),F0(324d,1d),G0(591d,3d),H0(305d,3d),I0(228d,6d),J0(522d,9d),K0(506d,1d),L0(645d,6d),M0(685d,5d),N0(770d,3d),O0(787d,7d),P0(457d,4d),Q0(316d,3d),R0(985d,1d),S0(152d,1d),T0(427d,6d),U0(506d,2d),V0(37d,9d),W0(706d,1d),X0(946d,2d),Y0(681d,4d),Z0(910d,2d),A1(173d,0d),B1(600d,7d),C1(948d,7d),D1(264d,7d),E1(598d,4d),F1(912d,9d),G1(403d,7d),H1(702d,9d),I1(195d,0d),J1(743d,9d),K1(860d,9d),L1(392d,1d),M1(190d,8d),N1(270d,8d),O1(627d,0d),P1(845d,4d),Q1(818d,4d),R1(438d,1d),S1(650d,5d),T1(905d,1d),U1(203d,3d),V1(826d,5d),W1(177d,4d),X1(224d,9d),Y1(258d,3d),Z1(896d,5d),A2(991d,1d),B2(864d,1d),C2(439d,6d),D2(216d,4d),E2(238d,8d),F2(227d,4d),G2(692d,5d),H2(298d,2d),I2(434d,7d),J2(906d,5d),K2(400d,7d),L2(452d,3d),M2(290d,9d),N2(898d,3d),O2(729d,6d),P2(746d,5d),Q2(293d,9d),R2(659d,2d),S2(40d,4d),T2(715d,9d),U2(574d,5d),V2(915d,3d),W2(969d,4d),X2(946d,0d),Y2(48d,2d),Z2(854d,7d),A3(687d,5d),B3(506d,1d),C3(691d,4d),D3(249d,0d),E3(748d,4d),F3(505d,1d),G3(924d,4d),H3(114d,7d),I3(878d,2d),J3(941d,5d),K3(248d,2d),L3(290d,5d),M3(763d,9d),N3(145d,4d),O3(905d,0d),P3(786d,6d),Q3(558d,1d),R3(816d,9d),S3(710d,0d),T3(457d,6d),U3(214d,5d),V3(209d,6d),W3(879d,8d),X3(955d,2d),Y3(190d,2d),Z3(289d,3d);
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
