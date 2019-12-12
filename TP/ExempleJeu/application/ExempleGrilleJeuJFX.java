package application;

import gui.Pion;
import gui.Place;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

/**classe exemple pour un jeu de pion en java fx
 * @author emmanueladam
 * */
public class ExempleGrilleJeuJFX extends Application implements EventHandler<MouseEvent>
{

    /**hauteur du panneau*/
    private double height;
    /**largeur du panneau*/
    private double width;
    /**decalage pour centrer le dessin*/
    private int decalage=3;

    /**  nb pixels utilise en largeur pour une "case de la grille"*/
    private double widthStep;
    /**  nb pixels utilise en hauteur pour une "case de la grille"*/
    private double heightStep;

    private Pion pionSelectionne;

    private Group troupe;

    /**
     * lancement automatique de l'application graphique
     */
    public void start(Stage primaryStage) {
        decalage = 3;
        width = 500;
        height = 500;
        heightStep = height/9;
        widthStep = width/9;
        construirePlateauJeu(primaryStage);
    }

    /**
     * construction du theatre et de la scene
     */
    private void construirePlateauJeu(Stage primaryStage) {
        // definir la troupe des acteurs et des decors
        troupe = new Group();
        // definir la scene principale
        Scene scene = new Scene(troupe, 2 * widthStep + width, 2 * heightStep + height, Color.ANTIQUEWHITE);
        scene.setFill(Color.BLANCHEDALMOND);
        // definir le decor
        dessinEnvironnement();
        // ajouter les acteurs
        ajoutPions();

        primaryStage.setTitle("exemple de jeu ...");
        primaryStage.setScene(scene);
        // afficher le theatre
        primaryStage.show();
    }


    /**
     * dessin de la zone de jeu
     */
    private void dessinEnvironnement()
    {
        //Les lignes
        for(int i=0; i<=5; i++)
        {
            Line line1 = new Line(decalage*widthStep, (i+decalage)*heightStep, (5+decalage)*widthStep, (i+decalage)*heightStep);
            Line line2 = new Line((i+decalage)*widthStep, decalage*heightStep, (i+decalage)*widthStep, (5+decalage)*heightStep);
            line1.setStrokeWidth(6);
            line1.setStroke(Color.GRAY);
            line1.setOpacity(0.9);
            line2.setStrokeWidth(6);
            line2.setStroke(Color.GRAY);
            line2.setOpacity(0.9);
            troupe.getChildren().add(line1);
            troupe.getChildren().add(line2);
        }
        //les places
        ajouterPlaces();
    }

    /**ajouter les places cliquables (croisements)*/
    private void ajouterPlaces() {
        for(int i=0; i<=5; i++)
        {
            double x = (i+decalage)*widthStep;
            for(int j=0; j<=5; j++)
            {
                double y = (j+decalage)*heightStep;
                Place c = new Place(x,y,heightStep/5);
                c.setOnMouseClicked(this);
                troupe.getChildren().add(c);
            }
        }
    }


    /**ajouter les pions des joueurs*/
    private void ajoutPions()
    {
        for(int i=0; i<=5; i++)
        {
            double x = (i+decalage)*widthStep;
            for(int j=0; j<=1; j++)
            {
                double y = (j+decalage)*heightStep;
                Pion p = new Pion(x,y,heightStep/3, Color.WHITE);
                p.setOnMouseClicked(this);
                troupe.getChildren().add(p);
                p = new Pion(x,y+4*heightStep,heightStep/3, Color.BLACK);
                p.setOnMouseClicked(this);
                troupe.getChildren().add(p);
            }
        }
    }



    /**gestion de la souris : selection de jeton, de place
     * */
    @Override
    public void handle(MouseEvent mouseEvent) {

        //si le clic est sur un jeton, alterne son etat selectionne ou non
        if(mouseEvent.getSource().getClass() == Pion.class)
        {
            Pion p = (Pion)mouseEvent.getSource();
            p.select();
            pionSelectionne = (p.isSelected()?p:null);
        }
        //si le clic est sur un croisement, si un jeton a ete selectionne, il s'y deplace
        else if(mouseEvent.getSource().getClass() == Place.class)
        {
            Place p = (Place)mouseEvent.getSource();
            if(pionSelectionne!=null && pionSelectionne.isSelected()) {
                int startX = (int)Math.round(pionSelectionne.getCenterX() / widthStep - decalage);
                int startY = (int)Math.round(pionSelectionne.getCenterY() / heightStep - decalage);
                int endX = (int)Math.round(p.getCenterX() / widthStep - decalage);
                int endY = (int)Math.round(p.getCenterY() / heightStep - decalage);
                System.err.println ("startX, startY, endX, endY=" + startX+","+ startY+","+ endX+","+ endY);
                    animPionVers(p);
                    pionSelectionne.select();
            }
        }
    }


    /**lancement d'une animation de deplacement du jeton selectionne vers la place
     *@param p la place destination du jeton selectionne */
    private void animPionVers(Place p)
    {
        Timeline timeline = new Timeline();
        double xdest =p.getCenterX();
        double ydest = p.getCenterY();
        KeyFrame bougeVoiture = new KeyFrame(new Duration(500),
                new KeyValue(pionSelectionne.centerXProperty(), xdest),
                new KeyValue(pionSelectionne.centerYProperty(), ydest));
        timeline.getKeyFrames().add(bougeVoiture);
        timeline.play();

    }

    /**lancement de la fenetre*/
    public static void main(String[] args) {
        launch(args);
    }

}
