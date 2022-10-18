package swing.defileTexte;

import javax.swing.*;
import java.awt.*;

/**classe qui dessine un texte dans une image et qui la fait défiler
 * @author emmanueladam */
class Defile extends JPanel  {
    /**image conteant un texte*/
    Image image;

    /**position de l'image*/
    int x;
    /**largeur de l'image*/
    int largeurImage;
    /**temps en ms entre chaquee déplacement*/
    int top = 10;
    /**temps vitesse de décallage (en pixel) */
    int pas = 1;

    /**definit la taille, la couleur de fond et le timer*/
    Defile() {
        setPreferredSize(new Dimension(300, 150));
        setBackground(Color.BLACK);
        //à chaque "top" l'image sera décalé
        Timer timer = new Timer(top, e->decaleImage());
        timer.start();
    }


    /**construction de l'image contenant le texte*/
    void construireImage() {

        String chaine = "CA BOUGE AVEC LE JAVA !!!";
        Font police = new Font("TimesRoman", Font.PLAIN, 25);

        //récupération des tailles de la police et de la chaine
        FontMetrics mesure = getFontMetrics(police);
        int hauteurChaine = mesure.getHeight();
        int largeurChaine = mesure.stringWidth(chaine);

        largeurImage = largeurChaine + 10;
        int hauteurImage = hauteurChaine;
        // creation d'une zone image pouvant contenir l'image et la chaine
        image = createImage(largeurImage, hauteurImage);
        // recuperation du pinceau de cette image
        Graphics2D g = (Graphics2D) image.getGraphics();

        //dessin d'une zone jaune
        g.setColor(new Color(250, 250, 100));
        g.fill3DRect(0, 0, largeurImage, hauteurImage, true);

        //dessin du texte, en rouge
        g.setFont(police);
        g.setColor(Color.red);
        int yChaine = (hauteurImage + mesure.getAscent()) / 2;
        g.drawString(chaine, 5, yChaine);
    }


    /**fonction lancee par le timer, décale l'image de 'pas' pixels vers la gauche*/
    void decaleImage() {
        if (image != null) {
            x = x - pas;
            if (x < -largeurImage) x = getWidth();
            repaint();
        }
    }

    /**fonction lancee par l'appel à repaint, affiche l'image, si elle existe*/
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(image == null)  construireImage();
        if(image != null)  g.drawImage(image, x, 40, this);
    }


    /**programme principal<br>
     * crée unne fenetre qui va contenir le panneau*/
    public static void main(String[] argv) {
        JFrame monCadre = new JFrame("texte défilant");
        monCadre.setContentPane(new Defile());
        monCadre.setLocation(100, 100);
        monCadre.pack();
        monCadre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        monCadre.setVisible(true);
    }

}
