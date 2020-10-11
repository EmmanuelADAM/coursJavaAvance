package fxmlExemple.gui;

import fxmlExemple.application.TestAppelFXML;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 * classe servant d'intermediaire de dialogue entre le fichier fxml et la fenetre recevant les objets d'interaction
 * @author emmanueladam
 * */
public class ChoixCouleursControleur {
    /**couleur pour le carre*/
    private Color colCarre;
    /**couleur pour le cercle*/
    private Color colCercle;
    /**selecteur de couleur pour carre, lie au fichier fxml*/
    @FXML private ColorPicker colPickCarre;
    /**selecteur de couleur pour cercle, lie au fichier fxml*/
    @FXML private ColorPicker colPickCercle;

    /**petit theatre associe */
    private Stage dialogStage;

    /**application associe*/
    private TestAppelFXML application;


    /**fonction accessible par SceneBuilder, lie au fichier fxml*/
    @FXML void choixCouleurCarre(ActionEvent event) { colCarre = colPickCarre.getValue(); }

    /**fonction accessible par SceneBuilder, lie au fichier fxml*/
    @FXML void choixCouleurCercle(ActionEvent event) { colCercle = colPickCercle.getValue(); }


    /**fonction accessible par SceneBuilder, lie a fxml; acceptation du choix des couleurs*/
    @FXML protected void gestionBoutonGo(ActionEvent event) {
        application.setColCarre(this.colCarre);
        application.setColCercle(this.colCercle);
        application.fill();
        //fermeture de la fenetre
        dialogStage.close();
    }

    /**annulation : fermeture de la fenetre sans prendre en compte les couleurs*/
    @FXML protected void gestionBoutonCancel(ActionEvent event) {
        dialogStage.close();
    }

    /**
     * @param dialogStage lien vers le petit theatre associe
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**lien vers l'application parente
     * recuperation des couleurs actuellement utilisee
     * @param _application une reference a l'application*/
    public void setApplication(TestAppelFXML _application)
    {
        application = _application;
        this.colCarre = application.getColCarre();
        this.colCercle = application.getColCercle();

        colPickCarre.setValue(colCarre);
        colPickCercle.setValue(colCercle);
    }

}
