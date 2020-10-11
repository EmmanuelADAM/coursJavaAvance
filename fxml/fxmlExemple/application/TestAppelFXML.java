package fxmlExemple.application;

import fxmlExemple.gui.ChoixCouleursControleur;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

/***
 * classe appelant une fenetre de dialogue creee en FXML par SceneBuilder
 * Exemple de dialogues pour la gestion de couleurs
 * @author emmanueladam
 */
public class TestAppelFXML extends Application {
    /** carre */
    private static Rectangle carre;
    /** cercle */
    private static Circle cercle;

    /**couleur du carre*/
    private Color colCarre = Color.LIGHTGREEN;
    /**couleur du cercle*/
    private Color colCercle = Color.DIMGRAY;

    /**
     * initialisation de l'application graphique
     */
    @Override
    public void start(Stage primaryStage) {
        construireScene(primaryStage);
    }


    /**
     * construction du théâtre et de la scène
     */
    private void construireScene(Stage primaryStage) {
        // points de base des éléments graphiques
        Group root = new Group();
        // creer un menu
        Menu mParam = new Menu("Parametres");
        // creer un item de menu
        MenuItem miCol = new MenuItem("couleurs");
        //lui definir une action
        miCol.setOnAction(e->choixCouleurs(primaryStage));
        //l'ajouter au menu
        mParam.getItems().add(miCol);

        // creer une barre de menu
        MenuBar mb = new MenuBar();
        // ajout du menu a la barre
        mb.getMenus().addAll(mParam);

        // creation d'une mise en forme verticale du menu
        VBox vb = new VBox(mb);

        root.getChildren().add(vb);
        Scene scene = new Scene(root, 500, 500, Color.BLACK);
        primaryStage.setTitle("TestFXML...");
        primaryStage.setScene(scene);
        // definir les acteurs et les habiller
        dessinEnvironnement(root);

        // afficher le theatre
        primaryStage.show();


    }
    /**fonction qui lance une fenetre modale definie en fxml par JavaFX Scene Builder
     * @param primaryStage theatre de l'application*/
    private void choixCouleurs(Stage primaryStage)
    {
        FXMLLoader fxmlLoader = null;
        AnchorPane page = null;
        try
        {
            URL url = new File("src/coursFXML/gui/ChoixCouleursGui.fxml").toURI().toURL();
            fxmlLoader = new FXMLLoader(url);
            page = fxmlLoader.load();
        }
        catch ( Exception ex )  {ex.printStackTrace();}

        //si le chargement a reussi
        if (page!=null)
        {
            //creation d'une petite fenetre et de son theatre
            Stage dialogStage = new Stage();

            dialogStage.setTitle("Choix des couleurs...");
            //fenetre modale, obligation de quitter pour revenir a la fenetre principale
            dialogStage.initModality(Modality.WINDOW_MODAL);
            //dialogue modale liee a la fenetre parente
            dialogStage.initOwner(primaryStage);
            //creation de la scene a partir de la page chargee du fichier fxml
            Scene miniScene = new Scene(page);
            dialogStage.setScene(miniScene);
            //recuperation du controleur associe a la fenetre
            ChoixCouleursControleur controller = fxmlLoader.getController();
            //liaison entre le controleur et sa fenetre
            controller.setDialogStage(dialogStage);
            //liaison entre le controleur et l'application
            controller.setApplication(this);
            //affichage de la fenetre
            dialogStage.showAndWait();
        }

    }

    /**
     * creation des elements graphiques
     */
    private void dessinEnvironnement(Group root) {
        carre = new Rectangle(100, 100, 300, 300);
        root.getChildren().add(carre);
        cercle = new Circle(200, 200, 100);
        root.getChildren().add(cercle);
        fill();
    }

    /**remplissage des elements graphiques par des couleurs*/
    public void fill()
    {
        carre.setFill(colCarre);
        cercle.setFill(colCercle);
    }

    public Color getColCarre() {
        return colCarre;
    }
    public void setColCarre(Color colCarre) {
        this.colCarre = colCarre;
    }

    public Color getColCercle() {
        return colCercle;
    }
    public void setColCercle(Color colCercle) {
        this.colCercle = colCercle;
    }

    /**
     * lancement de l'application
     */
    public static void main(String[] args) {
        launch(args);
    }
}
