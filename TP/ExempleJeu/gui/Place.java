package gui;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**classe representant une zone de jeu cliquable
 * */
public class Place extends Circle {

    /**@param x centre x
     * @param y centre y
     * @param rayon rayon!
     * */
    public Place(double x, double y, double rayon) {
        super(x, y, rayon, Color.WHITE);
        setOpacity(0.3);
    }


    public String toString()
    {
        return "place en " + getCenterX() + ", " + getCenterY();
    }
}
