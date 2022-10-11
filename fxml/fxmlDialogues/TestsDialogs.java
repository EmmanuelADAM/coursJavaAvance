package fxmlDialogues;


import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.*;
import java.util.Optional;

/**
 * Quelques exemples de fenetre de dialogues modales en JavaFX
 * @author emmanueladam
 * */
public class TestsDialogs {

    /**fenetre d'information*/
    static void testAlertInfo() {
        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.setTitle("Une boite d'information");
        dialog.setContentText("Le message d'information  ");
        dialog.showAndWait();
    }

    /**fenetre d'alerte*/
    static void testAlertWarning() {
        Alert dialog = new Alert(Alert.AlertType.WARNING);
        dialog.setTitle("Une boite de mise en garde");
        dialog.setContentText("Le message d'alerte  ");
        dialog.showAndWait();
    }

    /**fenetre de demande de confirmation*/
    static void testAlertConfirm() {
        Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);
        dialog.setTitle("Une boite de confirmation");
        dialog.setContentText("Formatter le disque ?  ");
        Optional<ButtonType> typeReponse = dialog.showAndWait();
        typeReponse.ifPresent(r-> System.out.println("choix  = " + r.getText()));
    }

    /**fenetre de demande de confirmation, avec boutons personnalises*/
    static void testAlertConfirmBoutons() {
        Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);
        dialog.setTitle("Une boite de confirmation");
        dialog.setContentText("Formatter le disque ?  ");
        dialog.getButtonTypes().setAll(new ButtonType("oui"), new ButtonType("oui bien sur"), new ButtonType("oui un peu"));
        Optional<ButtonType> typeReponse = dialog.showAndWait();
        typeReponse.ifPresent(r-> System.out.println("choix  = " + r.getText()));
    }

    /**fenetre d'erreur*/
    static void testAlertError() {
        Alert dialog = new Alert(Alert.AlertType.ERROR);
        dialog.setTitle("Une boite d'erreur");
        dialog.setContentText("Le message d'erreur  ");
        dialog.showAndWait();
    }

    /**fenetre de saisie de texte */
    static void testDialog() {
        TextInputDialog tid =  new TextInputDialog("saisir un texte...");
        tid.setTitle("Une boite de dialogue");
        tid.setHeaderText("saisissez qq chose...");
        tid.setContentText("votre texte : ");
        Optional<String> rep =  tid.showAndWait();
        rep.ifPresent(r->System.out.println("vous avez saisi : " + r));
    }

    /**fenetre de saisie de choix */
    static void testDialogChoix() {
        String[] choix = {"java", "python", "c", "php"};
        ChoiceDialog<String> cd =new ChoiceDialog<>(choix[0], choix);
        cd.setTitle("Une boite de choix");
        cd.setHeaderText("choisissez votre langage prefere : ");
        cd.setContentText("langage : ");
        Optional<String> rep =  cd.showAndWait();
        rep.ifPresent(r->System.out.println("vous avez saisi : " + r));
    }

    /**lancement des differentes fenetres*/
    public static void main(String[] args)
    {
        new JFXPanel();
        Platform.runLater(TestsDialogs::testAlertInfo);
        Platform.runLater(TestsDialogs::testAlertWarning);
        Platform.runLater(TestsDialogs::testAlertError);
        Platform.runLater(TestsDialogs::testAlertConfirm);
        Platform.runLater(TestsDialogs::testAlertConfirmBoutons);
        Platform.runLater(TestsDialogs::testDialog);
        Platform.runLater(TestsDialogs::testDialogChoix);
    }
}
