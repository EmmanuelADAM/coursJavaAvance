package swing.defileImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImagePassante extends JFrame implements ActionListener {
    JButton arret = new JButton("arret");
    JButton reprise = new JButton("reprendre");

    PanneauImages ardoise;

    ImagePassante() {
        ardoise = new PanneauImages();
        JPanel p = new JPanel();
        p.setBackground(Color.white);
        arret.setActionCommand("arreter");
        //un composant peut avoir plusieur gestionnaires d'événements
        arret.addActionListener(ardoise);
        arret.addActionListener(this);
        reprise.setActionCommand("reprendre");
        reprise.addActionListener(ardoise);
        reprise.addActionListener(this);
        reprise.setEnabled(false);
        p.add(arret);
        p.add(reprise);
        //le panneau principal est formaté selon un "BorderLayout"
        Container interieur = getContentPane();
        interieur.setLayout(new BorderLayout());
        //panneau des boutons au nord
        interieur.add(p, BorderLayout.NORTH);
        //panneau graphique au centre
        interieur.add(ardoise, BorderLayout.CENTER);
        interieur.setBackground(Color.white);
		pack();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ardoise.timer.start();
    }


    /**
     * ecoute d'événements sur les boutons
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("arreter")) {
            arret.setEnabled(false);
            reprise.setEnabled(true);
        }
        if (e.getActionCommand().equals("reprendre")) {
            arret.setEnabled(true);
            reprise.setEnabled(false);
        }
    }

	/**programme principal*/
	public static void main(String[] argv) {
		ImagePassante monCadre = new ImagePassante();
	}

}
		