package gui;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**classe representant une zone de jeu
 * @author emmanueladam
 * */
public class PlaceImg extends Circle {

    private  Paint maCouleur;

    /**@param x centre x
     * @param y centre y
     * @param rayon rayon!
     * */
    public PlaceImg(double x, double y, double rayon) {
        super(x, y, rayon, Color.WHITE);
        setOpacity(0.5);
    }

    public Paint getMaCouleur() {
        return maCouleur;
    }

    public void setMaCouleur(Paint maCouleur) {
        this.maCouleur = maCouleur;
    }


    public String toString()
    {
        return "place en " + getCenterX() + ", " + getCenterY();
    }
}
