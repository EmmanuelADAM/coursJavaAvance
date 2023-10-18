package TD.TDJeuDeLaVie.VieSwing;

import javax.swing.*;
import java.awt.*;

/**panneau pour afficher les cellules du jeu de la vie
 * @author emmanueladam */
public class PanneauDeLaVie extends JPanel {
    /**lien vers la matrice qui contient les cellules*/
    Matrice mat;
    /**taille en pixel du panneau*/
    int taille;
    /**couleur pour cellule active*/
    static final public Color COUL_Active = new Color(200, 240, 255);
    /**couleur pour cellule non active*/
    static final public Color COUL_Desactive = new Color(100, 0, 0);
    PanneauDeLaVie(int taille){
        setSize(taille, taille);
        this.taille = taille;
        setBackground(Color.BLACK);
    }

    /**
     * appelé automatiquement à chaque repaint.
     * dessine un cercle pour chaque cellule en le remplissant d'une couleur ou d'une autre selon son etat
     * */
    @Override public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if(mat!=null){
            //on récupère la taille actuelle (en cas de redimensionnement)
            var taillePixel = getWidth();
            var nb = mat.taille;
            var dimPatch = taillePixel/nb;
            for(int i=0;i<nb; i++)
                for(int j=0;j<nb; j++){
                    if (mat.grille[i][j].isVivante()) g2.setColor(COUL_Active);
                    else g2.setColor(COUL_Desactive);
                    //attention ici, j pour x, i pour y
                    g2.fillOval(j*dimPatch, i*dimPatch, dimPatch, dimPatch);
                }
        }
    }

    public void setMat(Matrice mat){
        this.mat = mat;
        repaint();
    }


}
