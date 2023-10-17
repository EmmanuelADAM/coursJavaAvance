package demoCours.jeuDeLaVie;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**fenetre pour afficher le jeu de la vie
 * @author emmanueladam */
public class SwingDeLaVie extends JFrame {
    /**lien vers le panneau dans lequel seront dessinées les cellules*/
    PanneauDeLaVie zoneDessin;
    /**matrice qui contient les cellules*/
    Matrice mat;
    /**tache pour faire vivre le jeu de la vie*/
    Timer timer;
    /**constructeur avec titre de la fenetre et taille en pixel*/
    SwingDeLaVie(String titre, int taille)
    {
        super(titre);
        //on en ajoute un peu en hauteur a cause de la taille de la barre de titre
        setSize(taille, taille+20);
        //on affiche la fenetre au centre de l'ecran
        setLocationRelativeTo(null);

        //creation du panneau de dessin et ajout
        zoneDessin = new PanneauDeLaVie(taille);
        getContentPane().add(zoneDessin);

        //on oublie pas de rendre la fenetre visible
        setVisible(true);
        //si on quitte la fenetre, on quitte le programme
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        creerTimer();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                switch (e.getKeyChar()) {
                    case 's' -> timer.start();
                    case 'p' -> timer.stop();
                    case 'r' -> timer.restart();
                    case '+' -> timer.setDelay(Math.max(0, timer.getDelay() - 20));
                    case '-' -> timer.setDelay(timer.getDelay() + 20);
//                    case 'e' -> {mat.effaceTout();mat.copieGrille();repaint();}
                    case 'h' -> {mat.initHasard();repaint();}
                    case 'q' -> System.exit(0);
                    default -> System.err.println("""
                    touche non reconnue.
                    |    s démarre la simu  
                    |    p met en pause
                    |    r redémarre
                    |    e efface tout
                    |    h initialise au hasard
                    |    q quitter
                    |    + accélère la simu
                    |    - ralentit la simu
                    |    possibilité d'utiliser la roulette de la souris pour changer la vitesse
                    -----------------------------------------------------------------------------""");
                }
            }
        });

        //petite ecoute sur la roulette de souris pour changer le tempo
        addMouseWheelListener(e-> timer.setDelay(Math.max(0, timer.getDelay() + (int)(e.getPreciseWheelRotation()*10))));

    }

    /**pour relier la fenetre a une matrice de cellules*/
    public void setMat(Matrice mat){
        this.mat = mat;
        zoneDessin.setMat(mat);
    }

    void creerTimer() {
        timer = new Timer(200, evt -> {
            mat.copieGrille();
            mat.animGrille();
            zoneDessin.repaint();
        });
    }


    /**fonction principale, cree la fenetre, creer la matrice et relie l'ensemble*/
    public static void main(String[] args)
    {
        var fenetre = new SwingDeLaVie("Jeu de la vie", 800);
        var rep = JOptionPane.showInputDialog(fenetre,
                "Donnez le nb de cellules sur 1 ligne de la grille : (5 - 300) : ", "50");
        int nb = Integer.parseInt(rep);
        rep = JOptionPane.showInputDialog(fenetre,
                "Donnez le % de cellules actives (0 - 100) : ", "20");
        double taux = Double.parseDouble(rep)/100d;
        Matrice mat = new Matrice(nb, taux);
        fenetre.setMat(mat);
    }


}
