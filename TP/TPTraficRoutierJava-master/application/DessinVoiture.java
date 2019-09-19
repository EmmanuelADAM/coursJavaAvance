package application;


import java.awt.Point;

import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class DessinVoiture extends Circle {
	/**vrai si le jeton est actuellement selectionne*/
	boolean selected;
	/**position du jeton dans la grille (null si non posé)*/
	private Point position;
	/**ancienne position du jeton dans la grille (null si non posé)*/
	private Point anciennePosition;
	/**couleur pour voiture*/
	public static Color couleur = Color.RED;
   /**couleur courante du jeton*/
   Color cj = couleur;
	/**couleur pour voiture  selectionnee*/
	public static Color couleurSelected = Color.GAINSBORO;
	/**animation du deplacement*/
	Timeline animation;


	/**
	*constructeur
	*@param centerX coordonnee X du centre du disque
	*@param centerY coordonnee Y du centre du disque
	*@param radius taille en pixel du rayon du disque
	*@param _joueur numero du joueur associe
	*/
	public DessinVoiture(double centerX, double centerY, double radius) {
		super(centerX, centerY, radius);
		selected = false;
		getCouleur();
		setFill(cj);
	}

	/**active ou desactive la selection du jeton*/
	public void switchSelected()
	{
		selected = !selected;
      getCouleur();
      colorerVoiture();
      if(selected && animation!=null) animation.pause();
      if(!selected && animation!=null) animation.play();
	}

	/**definit la bonne couleur pour le jeton en fonction du joueur et de son �tat s�lectionn� ou non
	*@return la couleur du jeton*/
	public Color getCouleur()
	{
	   cj=(selected?couleurSelected:couleur);	      
		return cj;
	}

	/**remplit le disque avec la couleur courante*/
	public void colorerVoiture()
	{
		setFill(cj);
	}

	/**
	 * @return the position
	 */
	public Point getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(Point _position) {
		this.position = _position;
	}


	/**
	 * @return the anciennePosition
	 */
	public Point getAnciennePosition() {
		return anciennePosition;
	}
	
	public void moveToPosition(Point _point)
	{
		this.anciennePosition.setLocation(this.position);
		this.position.setLocation(_point);
	}

	/**
	 * @param anciennePosition the anciennePosition to set
	 */  
	public void setAnciennePosition(Point anciennePosition) {
		this.anciennePosition = anciennePosition;
	}
	
	public String toString()
	{
		return "("+position.x+","+position.y+") avant en "+ "("+anciennePosition.x+","+anciennePosition.y+") ";
	}

   public Timeline getAnimation()
   {
      return animation;
   }

   public void setAnimation(Timeline animation)
   {
      this.animation = animation;
   }
}
