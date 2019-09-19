package application;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import modele.Cellule;
import modele.Matrice;

import java.awt.event.MouseEvent;


public class JeuDeLaVie extends Application {
    /**
     * matrice liee a cet objet graphique
     */
    Matrice matrice;
    /**
     * elements graphiques représentant les cellules
     */
    public static Circle[][] circles;
    /**
     * taille d'une cellule en pixel
     */
    int espace = 20;
    /**
     * taille de la matrice
     */
    private int taille;
    /**
     * pourcentage de cellules initialement actives
     */
    private double nb;
    /**
     * délai en ms entre chaque évolution
     */
    public static final int tempo = 100;
    /**
     * toutes les transitions rassemblees
     */
    public static Timeline chronologie;


    @Override
    public void start(Stage primaryStage) {
        taille = 100;
        nb = 0.1;
        chronologie = new Timeline();
        construireScenePourJeuDeLaVie(primaryStage);
    }

    void construireScenePourJeuDeLaVie(Stage primaryStage) {
        int largeur = 800;
        int hauteur = 900;
        espace = largeur / taille;
        //definir la scene principale
        Group root = new Group();
        Scene scene = new Scene(root, largeur, hauteur, Color.BLACK);
        primaryStage.setTitle("Life...");
        primaryStage.setScene(scene);
        //definir les acteurs
        matrice = new Matrice(taille, nb);
        //definir les costumes
        JeuDeLaVie.circles = new Circle[taille][taille];
        //habiller les acteurs
        dessinMatrice(root);
        //afficher le theatre
        primaryStage.show();
        //-----lancer le timer pour faire vivre la matrice
        Timeline littleCycle = new Timeline(new KeyFrame(Duration.millis(tempo),
                event -> {
                    matrice.copieGrille();
                    matrice.animGrille();
                }));
        littleCycle.setCycleCount(Timeline.INDEFINITE);
        littleCycle.play();
    }

    /**
     * creation des cellules et de leurs habits
     *
     * @param root racine
     */
    void dessinMatrice(Group root) {
        Cellule[][] grille = matrice.getGrille();
        int rayon = espace / 2;
        for (int i = 0; i < taille; i++)
            for (int j = 0; j < taille; j++) {
                Cellule cell = grille[i][j];
                circles[i][j] = new Circle((j * espace + rayon), (i * espace + rayon), rayon);
                if (cell.isVivante()) circles[i][j].setFill(Cellule.coulActive);
                else circles[i][j].setFill(Cellule.coulDesactive);
                cell.setCircle(circles[i][j]);
                root.getChildren().add(circles[i][j]);
            }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
