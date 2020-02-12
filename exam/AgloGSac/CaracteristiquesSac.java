package exam.AgloGSac;

import java.util.*;


/**
 * classe codant les caracteristiques des sacs; les objets possibles, leurs poids, leurs interets, et l'uniformasition de leurs valeurs
 * @author Emmanuel Adam*/
class CaracteristiquesSac {
	 /**noms des objets*/
	static final String []NOMS = {"A1", "B1", "C1", "D1", "E1", "F1", "G1", "H1", "I1", "J1", "K1", "L1", "M1", "N1", "O1", "P1", "Q1", "R1", "S1", "T1", "U1", "V1", "W1", "X1", "Y1", "Z1", "A2", "B2", "C2", "D2", "E2", "F2", "G2", "H2", "I2", "J2", "K2", "L2", "M2", "N2", "O2", "P2", "Q2", "R2", "S2", "T2", "U2", "V2", "W2", "X2", "Y2", "Z2", };
	 /**poids des objets en gramme*/
	static final Double []POIDS = {349d, 163d, 490d, 182d, 417d, 1d, 245d, 166d, 402d, 363d, 200d, 205d, 59d, 199d, 208d, 404d, 280d, 215d, 8d, 50d, 43d, 237d, 229d, 463d, 288d, 209d, 69d,
																 101d, 436d, 266d, 404d, 394d, 273d, 328d, 377d, 32d, 448d, 314d, 108d, 279d, 372d, 394d, 66d, 305d, 143d, 0d, 350d, 28d, 484d, 108d, 288d,  232d };
	 /**interet des objets*/
	static final Double []INTERET = { 9d, 9d, 8d, 7d, 5d, 9d, 0d, 3d, 6d, 7d, 7d, 8d, 2d, 2d, 0d, 7d, 7d, 2d, 3d, 5d, 6d, 6d, 9d, 9d, 6d, 6d, 8d, 2d, 2d, 4d, 9d, 5d, 1d, 9d, 6d, 1d, 1d, 0d, 0d, 4d, 6d, 6d, 2d, 8d, 9d, 7d, 5d, 0d, 4d, 9d, 5d, 5d};

	/**poids uniformises (poids divisés par le poids max)*/
	static List<Double> PoidsUniformes;
	/**interets uniformises (interets divisés par le poids max)*/
	static List<Double> InteretsUniformes;
	

	/**poids maximum autorisé pour un sac*/
	private final static double MAXPOIDS = 3000;
	/**poids maximum unifié (divisé par le poids du plus lourd produit*/
	static double MaxPoidsUnifie;
	
	/** longueur d'une sequence */
	 static final int LongSequence = 52;	 
	
	/** pourcentage de sequences touches par la mutation */
	static final double MutationDesSequences = 0.5;
	/** pourcentage de mutation dans une sequence*/
	static final double MutationDansSequence = 0.1;


	/**constructeur par défaut */
	CaracteristiquesSac()
	{
		init();
	}
	
	/**initialisation de l'algo*/
	private void init()
	{
		PoidsUniformes = new ArrayList<>(Arrays.asList(POIDS));
		double poidsMax = Collections.max(PoidsUniformes);
		PoidsUniformes.replaceAll(d -> d/poidsMax);
		
		MaxPoidsUnifie = MAXPOIDS / poidsMax ; 

		InteretsUniformes = new ArrayList<>(Arrays.asList(INTERET));
		double interetsMax = Collections.max(InteretsUniformes);
		InteretsUniformes.replaceAll(d -> d/interetsMax);
	}
}
