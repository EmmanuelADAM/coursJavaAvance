package fxmlDeBase;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


/**
 * Classe controlant une fenetre constituee d'un champs texte et d'un bouton
 * @author emmanueladam
 **/
public class Controller {
    /**champs texte accessible par scene buidler et FXML*/
    @FXML TextField tf;

    /**texte recupere du champs texte*/
    String text;

    /**fonction accessible par scene buidler et FXML*/
    @FXML public  void setText(ActionEvent e){
        text=tf.getText();
        System.out.println("texte récupéré = " + text);
    }

    public String getText(){return text;}
}
