package application;

import donnees.Environnement;
import donnees.Personne;
import gui.PersonneImg;
import gui.PlaceImg;
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

import java.awt.*;
import java.util.Optional;

/**A partir de l'exemple du jeu de pion
 * Petite simulation de propagation de virus
 * @author emmanueladam
 * */
public class SimuVirus extends Application implements EventHandler<MouseEvent>
{

    /**hauteur du panneau*/
    private double height;
    /**largeur du panneau*/
    private double width;
    /**decalage pour centrer le dessin*/
    private int decalage;
    /**dimension de la grille*/
    private int dim;

    /**  nb pixels utilise en largeur pour une "case de la grille"*/
    private double widthStep;
    /**  nb pixels utilise en hauteur pour une "case de la grille"*/
    private double heightStep;

    /**rond selectionne*/
    private PersonneImg personneSelectionnee;

    /**troupe des 'acteurs'*/
    private Group troupe;

    /**lien vers le modele*/
    Environnement env;

    /**active ou non l'animation*/
    boolean go = false;
    /**tempo pour l'animation*/
    Timeline littleCycle;

    /**quelques couleurs*/
    private final Color couleurFond = Color.DARKGRAY;
    private final Color couleurDomicile = Color.ALICEBLUE;
    private final Color couleurEntreprise = Color.CORAL;
    private final Color couleurMagasin = Color.FORESTGREEN;

    /**
     * lancement automatique de l'application graphique
     */
    public void start(Stage primaryStage) {
        dim = 30;
        decalage = 1;
        width = 500;
        height = 500;
        heightStep = height/dim;
        widthStep = width/dim;
        env = new Environnement(dim,dim, this);
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
        scene.setFill(couleurFond);
        // definir le decor
        dessinEnvironnement();
        // ajouter les acteurs
        ajoutPions();
        //-----lancer le timer pour faire vivre la matrice
        //cycle de 4 arret + 1 mouvement
        long tempo = 300;
        int[] step={0};
        littleCycle = new Timeline(new KeyFrame(Duration.millis(tempo),
                event -> {if(step[0] > 4)
                {
                    env.bouger();
                    step[0] = 0;
                }
                else env.avancerTemps();
                step[0]++;
                }));
        littleCycle.setCycleCount(Timeline.INDEFINITE);
        //ecoute des evenements claviers
        System.out.println("tapez b pour bouger, t pour temporiser, g pour une animation automatique");
        scene.setOnKeyTyped(e->agirSelonTouche( e.getCharacter()));

        primaryStage.setTitle("simu propagation ...");
        primaryStage.setScene(scene);
        // afficher le theatre
        primaryStage.show();
    }

    /**active ou pause l'animation*/
    private void go()
    {
        go = !go;
        if(go) littleCycle.play();
        else littleCycle.pause();
    }

    /**action selon la touche clavier*/
    void agirSelonTouche(String c)
    {
        System.out.println("clic sur " + c);
        switch(c)
        {
            case "b" -> env.bouger();
            case "g" -> go();
            case "t" -> env.avancerTemps();
        }
    }


    /**
     * dessin de la zone de jeu
     */
    private void dessinEnvironnement()
    {
        //Les lignes
        for(int i=0; i<dim; i++)
        {
            Line line1 = new Line(decalage*widthStep, (i+decalage)*heightStep, (dim-1+decalage)*widthStep, (i+decalage)*heightStep);
            Line line2 = new Line((i+decalage)*widthStep, decalage*heightStep, (i+decalage)*widthStep, (dim-1+decalage)*heightStep);
            line1.setStrokeWidth(widthStep/10);
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
        for(int i=0; i<dim; i++)
        {
            double x = (i+decalage)*widthStep;
            for(int j=0; j<dim; j++)
            {
                double y = (j+decalage)*heightStep;
                PlaceImg c = new PlaceImg(x,y,heightStep/5);
                c.setMaCouleur(couleurDomicile);
                c.setFill(c.getMaCouleur());
                c.setOnMouseClicked(this);
                troupe.getChildren().add(c);
            }
        }
    }


    /**ajouter les pions des joueurs*/
    private void ajoutPions()
    {
        for(int i=0; i<dim; i++)
        {
            double x = (i+decalage)*widthStep;
            for(int j=dim/2; j<dim; j++)
            {
                double y = (j+decalage)*heightStep;
                PersonneImg pi = new PersonneImg(x,y,heightStep/3, Color.WHITE);
                pi.setOnMouseClicked(this);
                Point pos = new Point(i,j);
                for(int k=0; k<2; k++)
                {
                    Personne p = new Personne(pos, env);
                    env.addPersonne(p);
                    p.setImg(pi);
                }
                troupe.getChildren().add(pi);
            }
        }
    }



    /**gestion de la souris : selection de jeton, de place
     * */
    @Override
    public void handle(MouseEvent mouseEvent) {

        //si le clic est sur un jeton, alterne son etat selectionne ou non
        if(mouseEvent.getSource().getClass() == PersonneImg.class)
        {
            personneSelectionnee = (PersonneImg)mouseEvent.getSource();
            int px = (int)Math.round(personneSelectionnee.getCenterX() / widthStep - decalage);
            int py = (int)Math.round(personneSelectionnee.getCenterY() / heightStep - decalage);
            env.switchContagieux(px, py);
            personneSelectionnee.switchInfected();
            if(!personneSelectionnee.isInfected()) personneSelectionnee =null;
        }
        //si le clic est sur un croisement, si un jeton a ete selectionne, il s'y deplace
        else if(mouseEvent.getSource().getClass() == PlaceImg.class)
        {
            PlaceImg p = (PlaceImg)mouseEvent.getSource();
            if(personneSelectionnee !=null && personneSelectionnee.isInfected()) {
                int startX = (int)Math.round(personneSelectionnee.getCenterX() / widthStep - decalage);
                int startY = (int)Math.round(personneSelectionnee.getCenterY() / heightStep - decalage);
                int endX = (int)Math.round(p.getCenterX() / widthStep - decalage);
                int endY = (int)Math.round(p.getCenterY() / heightStep - decalage);
                Optional<Personne> op = env.getPersonnes().stream().filter(person->person.getImg()== personneSelectionnee).findFirst();
                op.ifPresent(person->person.setPos(new Point(endX, endY)));
                System.err.println ("startX, startY, endX, endY=" + startX+","+ startY+","+ endX+","+ endY);
                animPionVers(p);
            }
        }
    }

    /**deplace l'image de la personne vers le point d'arrivee
     * fonction lancee par l'environnement*/
    public void updatePos(PersonneImg img, Point arrivee)
    {
        double endX = (arrivee.x + decalage ) *  widthStep ;
        double endY = (arrivee.y + decalage ) *  heightStep ;

        Timeline timeline = new Timeline();
        KeyFrame bougePersone = new KeyFrame(new Duration(500),
                new KeyValue(img.centerXProperty(), endX),
                new KeyValue(img.centerYProperty(), endY));
        timeline.getKeyFrames().add(bougePersone);
        timeline.play();
    }


    /**lancement d'une animation de deplacement du jeton selectionne vers la place
     *@param p la place destination du jeton selectionne */
    private void animPionVers(PlaceImg p)
    {
        Timeline timeline = new Timeline();
        double xdest = p.getCenterX();
        double ydest = p.getCenterY();
        KeyFrame bougeVoiture = new KeyFrame(new Duration(500),
                new KeyValue(personneSelectionnee.centerXProperty(), xdest),
                new KeyValue(personneSelectionnee.centerYProperty(), ydest));
        timeline.getKeyFrames().add(bougeVoiture);
        timeline.play();

    }

    /**lancement de la fenetre*/
    public static void main(String[] args) {
        launch(args);
    }

}