package application;

import java.util.ArrayList;
import java.util.List;

import modele.Arc;
import modele.Noeud;
import modele.ReseauRoutier;
import modele.Voiture;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AppliTrafic extends Application implements EventHandler<MouseEvent> {

 	/** taille d'une case en pixels */
	int tailleCase;
   /**les voitures*/
   List<Voiture> voitures;
   /**les dessins des voitures*/
   List<DessinVoiture> dessinsVoitures;
	/** scene de jeu */
	Scene scene;
	/**troupe des acteurs*/
	Group troupe;
	/**vitesse de l'animation*/
   long tempo = 1000;


	/** lancement automatique de l'application graphique */
	public void start(Stage primaryStage) {
		tailleCase = 100;
		voitures = new ArrayList<>();
		dessinsVoitures= new ArrayList<>();
		construirePlateauJeu(primaryStage);
	}

	/** construction du th�atre et de la sc�ne */
	void construirePlateauJeu(Stage primaryStage) {
		// definir la scene principale
		troupe = new Group();
		scene = new Scene(troupe, tailleCase + 5 * tailleCase, 2 * tailleCase + 4 * tailleCase, Color.ANTIQUEWHITE);
		// definir les acteurs et les habiller
		dessinEnvironnement(troupe);

		primaryStage.setTitle("Trafic Routier...");
		primaryStage.setScene(scene);
		// afficher le theatre
		primaryStage.show();
      //-----lancer le timer pour faire vivre la matrice
      Timeline littleCycle = new Timeline(new KeyFrame(Duration.millis(tempo), 
            event-> animDeplacement() ));
      littleCycle.setCycleCount(Timeline.INDEFINITE);
      littleCycle.play();
	}



	/**
	 * creation des dessins des routes, carrefours et voitures
	 */
	void dessinEnvironnement(Group troupe) {
	   ReseauRoutier.creerReseau();
	   List<Noeud> noeuds = ReseauRoutier.getNoeuds();

      voitures.add(new Voiture(0, 1,5, 1,0));
      voitures.add(new Voiture(1, 0,4, 5,4));

		int decalage = tailleCase / 2;
      // creation des carrefours
      int radius = 1 * decalage / 3;
      for (Noeud noeud:noeuds)
      {
         int cx = decalage + noeud.getX() * tailleCase;
         int cy = decalage + noeud.getY() * tailleCase;
         Circle c = new Circle(cx, cy, radius);
         c.setFill(Color.BLACK);
         c.setOpacity(0.2);
         troupe.getChildren().add(c);
         c.setSmooth(true);
      }
      // creation des routes
      for (Noeud noeud:noeuds)
      {
         int ox = decalage + noeud.getX() * tailleCase;
         int oy = decalage + noeud.getY() * tailleCase;
         for (Arc arc:noeud.getArcSortants())
         {
            Noeud dest=arc.getEnd();
            int dx = decalage + dest.getX() * tailleCase;
            int dy = decalage + dest.getY() * tailleCase;
            Line l = new Line(ox, oy, dx, dy);
            l.setStrokeWidth(6);
            l.setStroke(Color.DARKGOLDENROD);
            troupe.getChildren().add(l);
         }
      }
      //dessin des voitures
      for(Voiture v:voitures)
      {
         System.out.println(v);
         int cx = decalage + v.getX() * tailleCase;
         int cy = decalage + v.getY() * tailleCase;
         DessinVoiture dv = new DessinVoiture(cx, cy, radius);
         troupe.getChildren().add(dv);
         dv.setSmooth(true);
         dv.setOnMouseClicked(this);
         DropShadow dropShadow = new DropShadow();
         dropShadow.setRadius(5.0);
         dropShadow.setOffsetX(3.0);
         dropShadow.setOffsetY(3.0);
         dropShadow.setColor(Color.color(0.4, 0.5, 0.5));
         dv.setEffect(dropShadow);
         dessinsVoitures.add(dv);
      }


	}


	/** reponse aux evenements de souris */
	public void handle(MouseEvent me) {

		Object o = me.getSource();
		if (o instanceof DessinVoiture) {
			DessinVoiture dv = (DessinVoiture) o;
			dv.switchSelected();
			Voiture v = voitures.get(dessinsVoitures.indexOf(dv));
			v.setPause(!v.isPause());
		}
	}

	
	/**realise l'animation pour le deplacement du jeton sélectionné
	 * @param xdest coordonnees x d'arrivée
	 * @param ydest coordonnees y d'arrivée
	 * */
	private void animDeplacement()
	{
      int decalage = tailleCase / 2;
		for(int i=0; i<voitures.size(); i++)
		{
		   Voiture v = voitures.get(i);
		   if(!v.isArrivee() && !v.isPause())
		   {
	         System.out.println("deplacement de " + v);
	         Noeud n = v.prochainNoeud();
	         if(n!=null)
	         {
	            DessinVoiture dv = dessinsVoitures.get(i);
	            if(!dv.selected)
	            {
	               Timeline timeline = new Timeline();
	               int xdest = decalage + n.getX() * tailleCase;
	               int ydest = decalage + n.getY() * tailleCase;
	               KeyFrame bougeVoiture = new KeyFrame(new Duration(tempo), 
	                     new KeyValue(dv.centerXProperty(), xdest),
	                     new KeyValue(dv.centerYProperty(), ydest));
	               timeline.getKeyFrames().add(bougeVoiture);
	               timeline.play();
	               dv.setAnimation(timeline);
	            }
		      
	         }
		   }
		}

	}



	public static void main(String[] args) {
		launch(args);
	}

}
