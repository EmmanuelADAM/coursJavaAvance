package fxmlDeBase;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Application javaFx chargee a partir d'une description FXML
 * @author emmanueladam
 **/
public class Main extends Application {
    /**
     * plusieurs possibilite de charger le document FXML
     * en voici une...
     * */
    @java.lang.Override
    public void start(Stage stage)  {
        Parent root = null;
        try {
            URL url = new File("src/fxmlDeBase/test.fxml").toURI().toURL();
            root = FXMLLoader.load(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Hello World");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
