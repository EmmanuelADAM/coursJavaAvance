package exam.AgloGSac;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


/**
 * classe codant la recherche du sac le plus intéressant, et le moins lourd,
 * et ne dépassant pas  poids maximum autorisé<br>
 * Cette classe créé un panneau d'affichage des genes et de leurs résultats<br>
 * algo génétique, une séquence est un tableau binaire composé de 0 et de 1<br>
 * ex: [0,1,1,0] signifie que le sac contient les produits 1 et 2 (si le décompte des produits débute à 0)
 * @author Emmanuel Adam*/
@SuppressWarnings("serial")
public class AlgoGPourSacs extends JPanel implements ActionListener{

	/** nb de sequences a faire "vivre"*/
	 private int nbSequences = 50;
	
	/** tableau des sequences */
	private List<Sequence> listeSequences;
	
	/**nb de sequences devant muter*/
	private int nbSequenceAMuter;

	/** nb cycles de reproduction*/
	private int nbRepro = 500;
	
	/**hasard*/
	private static Random hasard;

	/** Text area : pour affichage*/
	private JTextArea jTextArea;


	/**constructeur par défaut */
	private  AlgoGPourSacs()
	{
		hasard = new Random();
		new CriteresSac();
		nbSequenceAMuter = (int)(nbSequences  * CriteresSac.MutationDesSequences);
		listeSequences = new ArrayList<>();
		construireTableauGenome();
		
		setLayout(new BorderLayout());
		jTextArea =  new JTextArea();
		jTextArea.setRows(5);
		JScrollPane jScrollPane  = new JScrollPane(jTextArea); 
		add(BorderLayout.CENTER, jScrollPane);

		JPanel jpanel = new JPanel(new GridLayout(0, 2));
		JButton jbOK = new JButton("Croiser");
		jbOK.setActionCommand("croiser");
		jbOK.addActionListener(this);
		jpanel.add(jbOK);
		jbOK = new JButton("Croiser "+nbRepro+" fois");
		jbOK.setActionCommand("croiserEnBoucle");
		jbOK.addActionListener(this);
		jpanel.add(jbOK);
		add(BorderLayout.NORTH, jpanel);
		jbOK = new JButton("Relancer");
		jbOK.setActionCommand("relancer");
		jbOK.addActionListener(this);
		add(BorderLayout.SOUTH, jbOK);

		afficheCodes(listeSequences);
	}
	

	/**affiche les sequences
	 * @param tab tableau des sequences*/
	private void afficheCodes(List<Sequence>  tab)
	{
		if(listeSequences.size()==0)
		{
			println("aucune sequence ne correpond à la demande");
		}
		else
		{
		Sequence pire = Collections.max(listeSequences);
		Sequence meilleur =Collections.min(listeSequences);

		jTextArea.setText("");
		println("meilleur = "+meilleur.getUtilite() );
		println("pire = "+pire.getUtilite() );
		
		for(Sequence s : tab) println(s.toString());

		println("--------------------");
		println("meilleur sac  = " +  meilleur);
		StringBuilder sb = new StringBuilder();
		int []listeMeilleur =  meilleur.getSequence();
		sb.append("meilleur sac composé de = ");
		for(int i=0; i<listeMeilleur.length; i++)
		{
			if(listeMeilleur[i]==1) sb.append(CriteresSac.NOMS[i]).append(", ");
		}
		println(sb.toString());
		affichePoidsInteretSequence( meilleur);
		}
	}

	/**affiche les details de la meilleure solution courante*/
	private void affichePoidsInteretSequence(Sequence s)
	{
		int[] sequence = s.getSequence();
		double sommePoids = 0.0;
		double sommeInterets = 0.0;
		for(int j=0; j<CriteresSac.LongSequence; j++)
		{
			if(sequence[j]==1) sommePoids +=  CriteresSac.POIDS[j] ;
			if(sequence[j]==1) sommeInterets += CriteresSac.INTERET[j] ;
		}
		println("poids de la meilleure solution : "+  (sommePoids/1000) +" kg." );
		println("intérêt de la meilleure solution :" + sommeInterets);
	}
	
	/**ajoute une ligne sur la fenetre
	 * @param s chaine a ajouter dans la zone de texte*/
	private void println(String s)
	{
		jTextArea.setText(jTextArea.getText()+s+"\n");
	}


	/**construit la liste des nbSequences sequences */
	private void construireTableauGenome()
	{
		int i=0;
		while ( i<nbSequences)
		{
			Sequence seq = new Sequence(CriteresSac.LongSequence, CriteresSac.MutationDansSequence);
			seq.construireSequence();
			seq.calculUtilite();
			if (seq.getUtilite()!=Double.NEGATIVE_INFINITY)
			{
				listeSequences.add(seq);
				i++;
			}
		}
	}

	/**
	 * ote les doublons de la liste de séquence et les trie selon leurs comparateurs (basé normalement sur l'utilite)* @see Sequence
	 * */
	private void oterDoublon()
	{
		final TreeSet<Sequence> ensemble = new TreeSet<>(listeSequences);
		listeSequences.clear();
		listeSequences.addAll(ensemble);
	}	


	/** les croisements des nbElites meilleures sequences remplacent les nbElites pires, le reste ne change pas
	 * @param nbElites nb d'elites a considerer, et donc nb de pires a remplacer*/
	private void croiserMeilleurs(int nbElites)
	{
		if(listeSequences.size()>=2)
		{
			if(listeSequences.size()<nbElites ) nbElites=2;
			for(int i=0; i<nbElites; i++)
			{
				for(int j=0; j<nbElites; j++)					
				{
					if(i==j) continue;
					Sequence enfant = listeSequences.get(i).croiserSequence(listeSequences.get(j));
					if(enfant.getUtilite()!=Double.NEGATIVE_INFINITY) listeSequences.add(enfant);
				}
			}			
		}
	}

	/** fonction effectuant des mutations dans le tableau des sequences : <br>
	 * ici, on choisit des sequences au hasard, sauf la premiere, et demande la modification des genes */
	private void muter()
	{
		int nbSequences = listeSequences.size();
		for(int i=0; i<nbSequenceAMuter && (nbSequences>1); i++)
		{			
			int num =hasard.nextInt(nbSequences-1)+1;
			Sequence mutant = listeSequences.get(num);
			mutant.muterSequence();
			if(mutant.getUtilite()==Double.NEGATIVE_INFINITY)
			{
				listeSequences.remove(num);
				nbSequences  --;
			}
		}
	}

	/** fonction lancee par la demande de croisement : recherche les meilleures, les croise, lance la mutation et propage les modifs*/
	public void actionPerformed(ActionEvent event) {
		String verif = event.getActionCommand();
		if(verif.equalsIgnoreCase("croiser"))
		{			
			oterDoublon();
			croiserMeilleurs(8);
			muter();
			oterDoublon();
			afficheCodes(listeSequences);
			repaint();
		}
		if(verif.equalsIgnoreCase("croiserEnBoucle"))
		{			
			for(int i=0; i<nbRepro; i++)
			{
				oterDoublon();
				croiserMeilleurs(8);
				muter();
			}
			oterDoublon();
			afficheCodes(listeSequences);
			repaint();
		}
		if(verif.equalsIgnoreCase("relancer"))
		{			
			listeSequences.clear();
			construireTableauGenome();
			afficheCodes(listeSequences);
			repaint();
		}
	}


	/**fonction principale*/
	public static void main(String[] args)
	{
		JFrame jf = new JFrame("Elevage de Sacs");
		jf.setBounds(100, 100, 700, 500);
		Container ct = jf.getContentPane();
		ct.add(new AlgoGPourSacs());
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}

}
