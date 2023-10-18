package TD.TDJeuDeLaVie.VieSwing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**fenetre pour afficher le jeu de la vie
 * @author emmanueladam */
public class SwingDeLaVie extends JFrame {
    /**lien vers le panneau dans lequel seront dessines les cellules*/
    PanneauDeLaVie zoneDessin;
    /**matrice qui contient les cellules*/
    Matrice mat;
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
                System.out.println(e.getKeyChar());
                if(e.getKeyChar()=='s') timer.start();
                if(e.getKeyChar()=='h') timer.stop();
                if(e.getKeyChar()=='r') timer.restart();
        }});


    }

    void creerTimer(){
        timer = new Timer(200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mat.animGrille();
                zoneDessin.repaint();
//                System.out.println(mat);
//                System.out.println("-".repeat(50));
            }
        });
    }

    /**pour relier la fenetre a une matrice de cellules*/
    public void setMat(Matrice mat){
        this.mat = mat;
        zoneDessin.setMat(mat);
    }

    /**fonction principale, cree la fenetre, creer la matrice et relie l'ensemble*/
    public static void main(String[] args)
    {
        var fenetre = new SwingDeLaVie("Jeu de la vie", 600);
        Matrice mat = new Matrice(30, 0.3);
        fenetre.setMat(mat);

    }


}
