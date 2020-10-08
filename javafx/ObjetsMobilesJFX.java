package javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.animation.Timeline;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.util.Duration;


public class ObjetsMobilesJFX extends Application {

   Group root = null;


   /**lancement de l'application*/
   public void start(Stage primaryStage) {

      construireScene( primaryStage);
   }

   void construireScene(Stage primaryStage)  
   {
      int largeur = 800;
      int hauteur = 600;
      //definir la troupe
      root = new Group();
      //definir la scene principale
      Scene scene = new Scene(root, largeur, hauteur, Color.BLACK);
      primaryStage.setTitle("Fenetre...");
      primaryStage.setScene(scene);

      ajoutActeur();

      //afficher le theatre
      primaryStage.show();


   }

   void ajoutActeur()
   {
      Rectangle rectangle = new Rectangle(150, 100, 300, 200);
      rectangle.setFill(Color.DARKBLUE);
      root.getChildren().add(rectangle);
/*    Décommenter pour ajouter les transtions
      //transition fade (disparition)
      FadeTransition ft = new FadeTransition(Duration.millis(4000), rectangle ) ;
      ft.setFromValue(1.0); ft.setToValue (0.05) ;
      ft.setAutoReverse(true);
      ft.setCycleCount(Timeline.INDEFINITE);
      ft.play();*/
      //transition mouvement
/*      Path path = new Path();
      path.getElements().add(new MoveTo(150, 100));
      path.getElements().add(new LineTo(650, 300));
      path.getElements().add(new LineTo(150, 500));*/
/*      RotateTransition rt = new RotateTransition(Duration.millis(3000), rectangle);
      rt.setByAngle(180);
      rt.setCycleCount(-1);
      rt.setAutoReverse(true);
      rt.play();*/

      Circle circle = new Circle(10, 300, 30);
      circle.setFill(Color.RED);
      root.getChildren().add(circle);
/*    //transition mouvement
      path = new Path();
      path.getElements().add(new MoveTo (10f, 300f));
      path.getElements().add(new LineTo (350f, 300f));
      //definition de chemin par courbe de Bezier
      // du point actuel (350, 300), au point (450,300)
      // avec comme points attraceurs (375, 250) et (425, 250)
      CubicCurveTo cubicTo = new CubicCurveTo(375, 250,  425, 250, 450, 300);
      path.getElements().add(cubicTo);
      path.getElements().add(new LineTo (800f, 300f));
      pathTransition = new PathTransition(Duration.millis(5000), path, circle);
      pathTransition.setCycleCount(10);
      pathTransition.setAutoReverse(true);
      pathTransition.play();*/

      circle = new Circle(800, 300, 30);
      circle.setFill(Color.GREEN);
      root.getChildren().add(circle);
/*    //transition mouvement
      path = new Path();
      path.getElements().add(new MoveTo (800f, 300f));
      path.getElements().add(new LineTo (450f, 300f));
      cubicTo = new CubicCurveTo(425, 350,  375, 350, 350, 300);
      path.getElements().add(cubicTo);
      path.getElements().add(new LineTo (10f, 300f));
      pathTransition = new PathTransition(Duration.millis(5000), path, circle);
      pathTransition.setCycleCount(10);
      pathTransition.setAutoReverse(true);
      pathTransition.play();*/

    }




   /**lancement du prog*/
   public static void main(String[] args) {
      launch(args);
   }
}
