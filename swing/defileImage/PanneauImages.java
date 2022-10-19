package swing.defileImage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;


class PanneauImages extends JPanel implements ActionListener
{
	/**timer donnant le tempo pour le defilement de l'image*/
	Timer timer;
	/**image mobile*/
	BufferedImage  imgMobile;
	/**image fixe*/
	BufferedImage imgFixe;
	/**largeur en pixel du panneau*/
	int largeur = 600;
	/**hauteur en pixel du panneau*/
	int hauteur = 300;
	/**largeur en pixel de l'image defilante*/
	int largeurImage = 50;
	/**hauteur en pixel du panneau defilante*/
	int hauteurImage = 50;
	/**deplacement en ixel de l'image par top*/
	int pas=1;
	/**duree en ms entre chaque deplacement*/
	int top=5;
	/**position actuelle de l'image*/
	int x, y = 100;
	/**zone correspondant a l'image mobile*/
	Rectangle zoneImgMobile;

	PanneauImages()
	{
		setPreferredSize(new Dimension(largeur, hauteur));
		setBackground(Color.blue);

		//lecture des images
		try{
		imgMobile = ImageIO.read(new File("./src/swing/defileImage/Lettre2.gif"));
		imgFixe = ImageIO.read(new File("./src/swing/defileImage/Lettre.jpg")); }
		catch (Exception e)  {
			System.out.println("probleme au moment du chargement des images");
			System.exit(0); }
		//recuperation des proprietes
		largeur = (int)(imgFixe.getWidth()*1.5);
		hauteur = (int)(imgFixe.getHeight()*1.5);
		this.setPreferredSize(new Dimension(largeur, hauteur));
		largeurImage = imgMobile.getWidth();
		hauteurImage = imgMobile.getHeight();
		x = -largeurImage - 5;

		zoneImgMobile = new Rectangle(0, 0, 0, 0);

		//creation du Timer et lien vers l'action a realiser
		timer = new Timer(top, e->decaleImage());
	}

	/**decale la petite image vers la droite.<br>
	 * demande la (re)ecriture uniquement sur la zone touchee par le deplacement*/
	public void decaleImage()
	{
		largeur = getWidth();
		x += pas;
		if (x > largeur) x = -largeurImage;
		y = getHeight()/3;
		Rectangle nouveauRectangle =  new Rectangle(x, y, largeurImage , hauteurImage);
		Rectangle zoneRepaint = nouveauRectangle.union(zoneImgMobile);
		zoneImgMobile = nouveauRectangle;
		repaint(zoneRepaint.x, zoneRepaint.y, zoneRepaint.width, zoneRepaint.height);
		//tentez de remplacer la ligne ci-dessus par un simple repaint et observez
//		repaint();
	}

	/**gestion des événements sur les boutons*/
	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand().equals("arreter")) timer.stop();
		else if (e.getActionCommand().equals("reprendre")) timer.restart();
	}

	/**fonction appelee automatiquement a chaque repaint, meme local*/
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(imgFixe, 0, 0, largeur, hauteur, this);
		g.drawImage(imgMobile, x, y, largeurImage, hauteurImage, this);
	}
}
