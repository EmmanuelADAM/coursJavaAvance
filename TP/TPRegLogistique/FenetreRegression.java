package TP.TPRegLogistique;

import javax.swing.*;
import java.awt.*;

/**
 * classe pour dessiner des points et lancer un calcul de regression logistique.
 * Le but de cette regression logistique est de deviner la valeur de ces points
 * @author E. ADAM
 * */
public class FenetreRegression extends JFrame {


    /**Point est un "record" contenant x,y réels et c Color*/
    record Point(double x, double y, Color c){}
    /**original points*/
    Point[] dataPoints;
    /**couleur des predictions*/
    double[] yp;
    /**valeurs predites pour les points*/
    /**largeur de la fenetre*/
    int largeur;
    /**hauteur de la fenetre*/
    int hauteur;


    /**contructeur
     * @param _title titre de la fenetre
     * @param largeur largeur de la fenetre
     * @param hauteur hauteur de la fenetre*/
    FenetreRegression(String _title, int largeur, int hauteur){
        this.largeur = largeur;
        this.hauteur = hauteur;
        setBounds(100, 200, largeur, hauteur);
        setTitle(_title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    /**affiche les points du tableau, avec la bonne couleur
     * si des Y ont été calculé, place une zone de couleur correspondant à la valeur prédite sous le point
     * */
    void drawPoints(Graphics2D g2d){
        if(dataPoints !=null) {
            //TODO: tracer les prédictions si elles existent (if(yp!=null))
            // placer des cercles de taille 10
            // de couleur gris clair si yp<0.5
            // de couleur rose si yp>=0.5

            for (Point p : dataPoints) {
                g2d.setPaint(p.c);
                g2d.fillOval((int) (p.x() * largeur)-2, (int) ((1. - p.y()) * hauteur)-2, 4, 4);
            }
        }
    }


    /**
     * remplit le tableau de points à partir des valeurs X de la classe Data
     * si le y associé est 0, le point est noir, il est rouge si le y est 1
     * */
    void fillPoints()
    {
        int m = Data.values.length;
        dataPoints = new Point[m];
        Color c;
        for(int i=0; i<m; i++) {
            var ligne = Data.values[i];
            c = ligne[2] == 0 ? Color.BLACK : Color.RED;
            dataPoints[i] =  new Point(ligne[0], ligne[1], c);
        }
    }



    /**fonction appelée automatiquement et à chaque repaint. Demande le dessin des points*/
    @Override
    public void paint(Graphics g) {
        drawPoints((Graphics2D) g);
    }



    /**replace les y calculés*/
    public void setYp(double[] yp) {
        this.yp = yp;
    }


    public static void main(String[] args)
    {
        Data data = new Data();
        var frame = new FenetreRegression("Apprentissage par regression logistique", 400, 400);
        frame.setVisible(true);
        //TODO: decommenter au fur et a mesure du TP
        //LogisticRegression lr = new LogisticRegression(2, 10000);
        //var yp  = lr.predict(Data.getXs());
        //frame.setYp(yp);
        //System.out.println(" erreur = " + Calcul.binaryCrossEntropy(Data.getYi(), yp));
        //System.out.println(" qualité = " + Calcul.binaryAccuracy(Data.getYi(), yp, 0.5));
        frame.fillPoints();
        frame.repaint();
        //System.out.println("appuyer sur entree pour debuter l'apprentissage..");
        //Scanner sc = new Scanner(System.in);
        //sc.nextLine();
        //lr.fit(Data.getXs(), Data.getYi());
        //yp  = lr.predict(Data.getXs());
        //frame.setYp(yp);
        //frame.repaint();
        //System.out.println("-".repeat(30));
        //System.out.println(" erreur = " + Calcul.binaryCrossEntropy(Data.getYi(), yp));
        //System.out.println(" qualité = " + Calcul.binaryAccuracy(Data.getYi(), yp, 0.5));
    }

}
