package tp_algog_sacs;

/**
 * classe codant les caracteristiques des objets possibles, leurs poids, leurs interets, et l'uniformasition de leurs valeurs
 * @author Emmanuel Adam*/
enum EnumSac {
	A0(648d,399d),B0(817d,286d),C0(533d,745d),D0(249d,997d),E0(386d,8d),F0(311d,648d),G0(343d,901d),H0(320d,82d),I0(188d,70d),J0(673d,572d),K0(692d,946d),L0(163d,111d),M0(783d,784d),N0(811d,80d),O0(633d,949d),P0(680d,672d),Q0(968d,808d),R0(422d,927d),S0(483d,68d),T0(551d,741d),U0(213d,89d),V0(313d,37d),W0(257d,415d),X0(387d,38d),Y0(424d,281d),Z0(209d,878d),A1(277d,658d),B1(375d,789d),C1(698d,838d),D1(489d,412d),E1(628d,983d),F1(433d,529d),G1(24d,691d),H1(314d,168d),I1(537d,278d),J1(585d,911d),K1(138d,874d),L1(68d,318d),M1(405d,631d),N1(218d,89d),O1(958d,840d),P1(74d,67d),Q1(506d,852d),R1(584d,673d),S1(489d,37d),T1(738d,872d),U1(226d,193d),V1(609d,284d),W1(942d,179d),X1(706d,958d),Y1(468d,606d),Z1(444d,328d),A2(372d,93d),B2(804d,520d),C2(255d,129d),D2(356d,656d),E2(355d,271d),F2(362d,206d),G2(749d,981d),H2(564d,653d),I2(111d,994d),J2(842d,582d),K2(833d,383d),L2(588d,28d),M2(251d,118d),N2(298d,915d),O2(608d,13d),P2(186d,980d),Q2(799d,503d),R2(580d,784d),S2(307d,519d),T2(410d,38d),U2(323d,834d),V2(915d,211d),W2(323d,288d),X2(174d,181d),Y2(649d,758d),Z2(388d,756d),A3(668d,780d),B3(870d,181d),C3(493d,822d),D3(174d,837d),E3(130d,529d),F3(41d,494d),G3(144d,516d),H3(753d,281d),I3(534d,120d),J3(801d,936d),K3(177d,997d),L3(690d,878d),M3(569d,266d),N3(915d,174d),O3(26d,288d),P3(445d,125d),Q3(374d,828d),R3(892d,77d),S3(832d,672d),T3(841d,55d),U3(252d,813d),V3(60d,699d),W3(626d,719d),X3(911d,847d),Y3(314d,845d),Z3(702d,562d);
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
