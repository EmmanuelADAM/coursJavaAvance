package gui;

import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**classe représentant un pion de jeu
 * @author emmanueladam
 * */
public class PersonneImg extends Circle {

    private boolean infected;
    private final Paint maCouleur;

    /**@param x centre x
     * @param y centre y
     * @param rayon rayon!
     * @param couleur la couleur..
     * */
    public PersonneImg(double x, double y, double rayon, Paint couleur) {
        super(x, y, rayon, couleur);
        maCouleur = couleur;
        setStroke(Color.BLACK);
        setStrokeWidth(1.5);
    }

    /**@param x centre x
     * @param y centre y
     * @param rayon rayon!
     * @param couleur la couleur..
     * @param dropShadow l'ombre portee
     * */
    public PersonneImg(double x, double y, double rayon, Paint couleur, DropShadow dropShadow) {
        this(x,y,rayon, couleur);
        setEffect(dropShadow);
    }

    /**
     * selectionne ou deselectionne le pion et change sa couleur
     * */
    public void switchInfected() {
        infected = !infected;
        if (infected)
            this.setFill(Color.RED);
        else
            this.setFill(maCouleur);
    }

    /**
     * selectionne ou deselectionne le pion et change sa couleur
     * */
    public void setInfected() {
        infected = true;
        this.setFill(Color.RED);
    }

    public boolean isInfected() {
        return infected;
    }

    public String toString()
    {
        return "pion en " + getCenterX() + ", " + getCenterY();
    }
}
