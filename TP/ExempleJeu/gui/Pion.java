package gui;

import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**classe représentant un pion de jeu
 * @author emmanueladam
 * */
public class Pion extends Circle {

    private boolean selected;
    private Paint maCouleur;

    /**@param x centre x
     * @param y centre y
     * @param rayon rayon!
     * @param couleur la couleur..
     * */
    public Pion(double x, double y, double rayon, Paint couleur) {
        super(x, y, rayon, couleur);
        maCouleur = couleur;
        setStroke(Color.BLACK);
        setStrokeWidth(4);
    }

    /**@param x centre x
     * @param y centre y
     * @param rayon rayon!
     * @param couleur la couleur..
     * @param dropShadow l'ombre portee
     * */
    public Pion(double x, double y, double rayon, Paint couleur, DropShadow dropShadow) {
        this(x,y,rayon, couleur);
        setEffect(dropShadow);
    }

    /**
     * selectionne ou deselectionne le pion et change sa couleur
     * */
    public void select() {
        selected = !selected;
        if (selected)
            this.setFill(Color.RED);
        else
            this.setFill(maCouleur);
    }

    public boolean isSelected() {
        return selected;
    }

    public String toString()
    {
        return "pion en " + getCenterX() + ", " + getCenterY();
    }
}
