package javafx;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;


public class EvenementsJFX extends Application {

   Group root = null;


   /**lancement de l'application*/
   public void start(Stage primaryStage) {

      construireScene( primaryStage);
   }

   void construireScene(Stage primaryStage)  
   {
      int largeur = 800;
      int hauteur = 400;
      //definir la troupe
      root = new Group();
      //definir la scene principale
      Scene scene = new Scene(root, largeur, hauteur, Color.BLACK);
      primaryStage.setTitle("Fenetre...");
      primaryStage.setScene(scene);

      //ajout de gestion d'evement sur la scene
      scene.setOnKeyTyped(e->System.out.println(e));
      scene.setOnMouseClicked(e-> System.out.println(e));
//      scene.setOnKeyTyped(e->{
//         switch (e.getCharacter()) {
//            case "d" -> root.getChildren().clear();
//            case "n" -> ajoutActeur();
//         }
//      });

      ajoutActeur();

      //afficher le theatre
      primaryStage.show();


   }

   void ajoutActeur()
   {
      Rectangle rectangle = new Rectangle(150, 200, 300, 200);
      rectangle.setFill(Color.CADETBLUE);
      root.getChildren().add(rectangle);
      //transition mouvement
      Path path = new Path();
      path.getElements().add(new MoveTo(150, 200));
      path.getElements().add(new LineTo(650, 200));
      PathTransition pathTransition = new PathTransition(Duration.millis(4000), path, rectangle);
      pathTransition.setAutoReverse(true);
      pathTransition.setCycleCount(Timeline.INDEFINITE);
      pathTransition.play();
      //ajout d'ecoute d'evenement
      // affiche les coordonnees (x,y) relativement a la position initiale de l'objet
      rectangle.setOnMouseClicked(e->System.out.printf("(x,y)=(%f, %f)\n", e.getX(), e.getY()));
      //affiche les coordonnees (x,y) du clic relativement a la scene
      //rectangle.setOnMouseClicked(e->System.out.printf("(x,y)=(%f, %f)\n", e.getSceneX(), e.getSceneY()));
      //affiche les coordonnees (x,y) du clic dans l'objet
      //rectangle.setOnMouseClicked(e->System.out.printf("(x,y)=(%f, %f)\n", e.getX()-rectangle.getX(), e.getY()-rectangle.getY()));
      //stoppe/reactive l'animation
      //final boolean[] pause  = {false};
      //rectangle.setOnMouseClicked(e-> {if (!pause[0]){pathTransition.pause(); pause[0] = true; }
      //                                 else          {pathTransition.play(); pause[0] = false;}});
    }




   /**lancement du prog*/
   public static void main(String[] args) {
      launch(args);
   }
}
