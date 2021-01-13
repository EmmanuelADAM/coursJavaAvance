package coursJFX;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;


public class TimeLineJFX extends Application {

   Group root = null;
   final int largeur = 800;
   final int hauteur = 600;
   Random hasard;
   Circle cercleChoisi;


   /**lancement de l'application*/
   public void start(Stage primaryStage) {
      hasard = new Random();
      construireScene( primaryStage);
   }

   void construireScene(Stage primaryStage)  
   {
      //definir la troupe
      root = new Group();
      //definir la scene principale
      Scene scene = new Scene(root, largeur, hauteur, Color.WHITE);
      primaryStage.setTitle("Fenetre...");
      primaryStage.setScene(scene);

      Timeline chronologie = ajoutActeurs();

      scene.setOnKeyTyped(e->{
         switch (e.getCharacter()) {
            case "s" -> chronologie.stop();
            case "p" -> chronologie.play();
         }
      });

      scene.setOnMouseClicked(e->{
         double xx=e.getSceneX();double yy=e.getSceneY();
         if(cercleChoisi!=null)
         {
            var chrono = new Timeline();
            KeyValue kv1 = new KeyValue(cercleChoisi.centerXProperty(), xx, Interpolator.EASE_BOTH);
            KeyValue kv2 = new KeyValue(cercleChoisi.centerYProperty(), yy, Interpolator.EASE_BOTH);
            KeyFrame kf = new KeyFrame(Duration.millis(500), kv1, kv2);
            chrono.getKeyFrames().add(kf);
            chrono.play();
         }
      });

      //afficher le theatre
      primaryStage.show();


   }

   private Timeline ajoutActeurs() {
      int nb = 100;
      //creation de nb cercles places aleatoirement
      Circle[] tabCercles = new Circle[nb];
      Arrays.setAll(tabCercles, i -> new Circle(hasard.nextInt(largeur), hasard.nextInt(hauteur), hasard.nextInt(10) + 10, Color.color(0, 0, Math.min(1, hasard.nextDouble() + 0.2))));
      for(Circle c:tabCercles)c.setOnMouseClicked(r->cercleChoisi = Objects.equals(cercleChoisi,c)?null:c);
      root.getChildren().addAll(tabCercles);

      //toutes les 1000ms, demander une animation
      int tempo = 1000;
      Timeline chronologie = new Timeline();
      chronologie.getKeyFrames().add(new KeyFrame(Duration.millis(tempo), event -> {
         //creation d'une animation pour les mouvements de tous les cercles
         Timeline littleChrono = new Timeline();
         for (var c : tabCercles) animerCercle(c, littleChrono, tempo);
         littleChrono.play();
      }));
      chronologie.setCycleCount(Timeline.INDEFINITE);
      return chronologie;

   }

   /**definit les fenetres de temps pour le deplacement aleatoire du cercle c
    * @param c cercle
    * @param chronologie agenda recevant les fenetres de temps associees aux deplacement aleatoires de c
    * @param tempo temps que doit durer le deplace du cercle
    * */
    private void animerCercle(Circle c, Timeline chronologie, int tempo)
    {
       KeyValue kv1 = new KeyValue(c.centerXProperty(), hasard.nextInt(largeur), Interpolator.EASE_BOTH);
       KeyValue kv2 = new KeyValue(c.centerYProperty(), hasard.nextInt(hauteur), Interpolator.EASE_BOTH);
       KeyFrame kf = new KeyFrame(Duration.millis(tempo), kv1, kv2);
       chronologie.getKeyFrames().add(kf);
    }



   /**lancement du prog*/
   public static void main(String[] args) {
      launch(args);
   }
}
