package demoCours.jeuDeLaVie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * panneau pour afficher les cellules du jeu de la vie
 *
 * @author emmanueladam
 */
public class PanneauDeLaVie extends JPanel {
    /**
     * couleur pour cellule active
     */
    static final public Color COUL_Active = new Color(200, 240, 255);
    /**
     * lien vers la matrice qui contient les cellules
     */
    Matrice mat;
    /**
     * taille en pixel du panneau
     */
    int taille;
    /**taille en pixel d'une cellule*/
    int dimPatch;
    /**
     * attribut de qualite de dessin (optionnel)
     */
    RenderingHints qualiteDessin;

    PanneauDeLaVie(int taille) {
        setSize(taille, taille);
        this.taille = taille;
        setBackground(Color.BLACK);
        this.requestFocus();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(mat!=null) {
                    int mi = e.getY()/dimPatch;
                    int mj = e.getX()/dimPatch;
                    if(mi<mat.getTaille() && mj<mat.getTaille()) {
                        mat.change(mi, mj);
                        repaint();
                    }
                }
            }
        });

        qualiteDessin = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        qualiteDessin.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    }

    /**
     * appelé automatiquement à chaque repaint.
     * dessine un cercle pour chaque cellule en le remplissant d'une couleur ou d'une autre selon son etat
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (mat != null) {
            g2.setRenderingHints(qualiteDessin);
            //on récupère la taille actuelle (en cas de redimensionnement)
            var taillePixel = getWidth();
            var nb = mat.getTaille();
            dimPatch = taillePixel / nb;
            for (int i = 0; i < nb; i++)
                for (int j = 0; j < nb; j++) {
                        if(mat.grille[i][j].vivante) {
                            g2.setColor(COUL_Active);
                            //attention ici, j pour x, i pour y
                            g2.fillOval(j * dimPatch, i * dimPatch, dimPatch, dimPatch);
                        }
                }
        }
    }

    public void setMat(Matrice mat) {
        this.mat = mat;
        repaint();
    }


}
