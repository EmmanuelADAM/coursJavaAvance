package gui;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import modele.Parcelle;
import modele.TypeParcelle;

/**image d'une parcelle de terrain
 * une image contient un carre representant sa nature (terrain, nid, nourriture)
 * et un carre blanc le recouvrant representant la dose de pheromone
 * le carre de pheromone est plus ou moins opaque selon la dose de pheromone
 *
 * @author emmanueladam */
public class ImgParcelle  {
    // des couleurs
    static final public Color coulFond = Color.color(0.3, 0.7, 0.3);
    static final public Color coulPhero = Color.color(1, 1, 1);
    static final public Color coulNid = Color.color(0.6, 0.2, 0.2);
    static final public Color coulNourriture = Color.color(0.6, 0.4, 0.6);

    /**parcelle liee a cette image*/
    private Parcelle parcelle;
    /**carre liee au type*/
    private Rectangle elt;
    /**carre contenant la dose de pheromone au type*/
    private Rectangle phero;

    /**
     * creation d'une image de parcelle
     * le carre elt porte la couleur du type de la parcelle
     * le carre phero est plus ou moins opaque selon la dose de pheromone
     * initialement ce carre est transparent (opacity = 0)
     * @param troupe troupe des elements graphiques a laquelle doivent se raccrocher elt et phero
     * @param parcelle parcelle liee a cette image
     * @param x position x en pixel
     * @param y position y en pixel
     * @param dim taille de la case en pixel
     * */
    public ImgParcelle(Group troupe, Parcelle parcelle, int x, int y, int dim) {
        this.parcelle = parcelle;
        elt = new Rectangle(x,y,dim, dim);

        phero = new Rectangle(x,y,dim, dim);
        phero.setFill(coulPhero);
        phero.setOpacity(0);
        troupe.getChildren().add(elt);
        troupe.getChildren().add(phero);
    }

    /**definit l'opacite de la case phero selon le degre de pheromone de la cellule*/
    public void colorerPheromone()
    {
        double value = parcelle.getPheromone();
        //TODO: expliquer la ligne suivante
        if(value>0) value = Math.max(0.1, Math.min(0.9, value));
        phero.setOpacity(value);
    }

    /**choisit la couleur de l'elt selon la nature de la parcelle */
    public void choisirCouleur()
    {
        switch (parcelle.getType())
        {
            case Terrain -> elt.setFill(coulFond);
            case Nid -> {elt.setFill(coulNid); elt.setOpacity(Math.pow(parcelle.getOdeurNid(),10));}
            case Nourriture -> elt.setFill(coulNourriture);
        }
//        elt.setOpacity(parcelle.getOdeurNid());
    }

    public Parcelle getParcelle() { return parcelle; }
}
