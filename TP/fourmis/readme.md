# TP Simulation de Fourmis

Les fourmis communiquent souvent indirectement via l'environnement sur lequel elles dispersent des goutent de phéromones indiquant la proximité d'une source de nourriture, de matériaux, étenvuellement de danger.

Il s'agit du princie de **stigmergie**, un principe de coordination indirecte.

Lorsqu'une fourmi *cherche de la nourriture*, 
- elle cherche autour d'elle la parcelle de terrain ayant le plus grand nombre de nourriture, 
-  si elle n'en trouve pas, elle cherche des traces de phéromones pour se déplacer vers la case la plus odorantes.
    - Si aucune case n'a de nourriture ou de phéromones, la fourmi prend une direction aléatoire devant elle.
- si une case proche contient de la nourriture, la fourmi en prend une partie, *fait demi-tour* et retourne vers le nid

Lorsqu'une fourmi *retourne vers le nid*, 
- elle dépose une dose de phéromone, cherche la parcelle autour d'elle ayant la plus grande odeur de nid et si déplace,
- si la case d'arrivée est une case nid, elle dépose sa nourriture et  *fait demi-tour*
- à chaque déplacement, la dose de phéromone déposée par la fourmi diminue

Environnement dynamique
- pour guider les fourmis voisines, la phéromone se dillue, se propage : une partie (ex. 2%) de la dose de phéromone d'une parelle est diluée et propagée aux 8 parcelles voisines, et la parcelle voit donc sa dose de phéromone dimunée (ex de 8x2% = 16%)
- la phéromone s'évapore : à chaque pas de temps, la dose de phéromone des parcelles se dégrade (de 10% par exemple). Au dessous d'un certain seuil (0.05 par exemple), la phéromone est considérée comme nulle...

Ce type de comportement est à la base des [Algorithme_de_colonies_de_fourmis](https://fr.wikipedia.org/wiki/Algorithme_de_colonies_de_fourmis) pour la recherche de meileur chemin dans un vaste réseau, *etc.*

<center>
![Exemple simulation fourmis](https://github.com/EmmanuelADAM/coursJavaAvance/blob/master/TP/images/demoFourmis.png)
</center>

----
## Composition de l'application

L'application sera effectuée en JavaFX..
Des codes sont déjà présents : 
- application/SimuFourmis.Java est la classe principale. Elle dessine la fenêtre, les parcelles, gère les interactions avec l'utilisateur et crée le tempo qui lance à chaque top les actions suivantes : 
  -  evt.avancer(); //demande à chaque fourmi de bouger
  - evt.diffuser(); // demande à chaque parcelle de diffuser sa phéromone le cas échéant
  - evt.evaporer(); // demande à chaque parcelle de dégrader sa phéromone le cas échéant
  - updateMatrice(); // demande la mise à jour des couleurs des cases
  
- gui/ImgParcelle.java : est une représentation d'une parcelle de terrain. Elle contient 2 rectangles de couleurs : le fond, et une case blanche plus ou moins transparente en fonction de la dose de phéromone

- modele/Direction.java : est une classe énumérée représentant les 8 directions possibles d'une foumi (Nord, Nord-Est, Est, Sud-Est, Sud, Sud-Ouest, Ouest, Nors-Ouest)
- modele/TypeParcelle.java : est une classe énumérée représentant le  de la foumi (Terrain, Nid, Nourriture)
- modele/Parcelle.java : est une classe représentant une parcelle qui contient une odeur de nourriture, de nid et de pheromone
- modele/Environnement.java : est une classe représentant l'environnement constitué d'une grille de Parcelle
- modele/Fourmi.java : est une classe représentant une fourmi. Elle contient entre autres les methodes de recherche de cases selon les odeurs de nourriture, de phéromone ou de nid..

Une grille de parcelles est définie ainsi (1er indice = colonne, 2nd = ligne): 

|  | 0 | 1 | 2 | ... | taille-1|
|:-: |:-: |:-: |:-: |:-: |:-:      |
| **0** |  .  |  . |  .  | .. |  .      |
| **1** |  .  | F    |  .  | .. |  .      |
| ... |  .  |  . |  .  | .. |  .      |
| **taille-1** |  .  |  . |  .  | .. |  .      |




---
## Comportement d'une fourmi
Pour plus de réallisme, une foumi possède une direction, et est situé sur une parcelle.

D'après le tableau précédent, une foumi en (1,1) se dirigeant vers le Nord regarde aux cases (0,1) (Ouest), (0,0) (Nord-Ouest), (1,0) (Nord), (2,0) (Nord-Est), (2,1) (Est), s'il y a  de la nourriture ou de la phéromone.
- Si elle n'en trouve pas, la foumi prend une direction au hasard entre Nord-Ouest, Nord et Nord-Est et avance vers la case suivante.

Le code fourni permet de lancer des fourmis (appuyer sur "f" pour ajouter une nouvelle fourmi au centre) e tde les faire se déplacer aléatoirement.

A vous de progerammer le le ramassage de nourriture, le retour au nid et le depot de phéromones..
(remplisser pour cela les poinst indiqués TODO:).

### Etapes
- compléter l'énumération Direction
- permettre à iune fourmi de ramasser de la nourriture et de faire demi tour
- permettre à une fourmi de poser une goute de pheromone lorsquelle n'est pas vide.
- faire dilluer et dégrader la phéromone
- orienter la fourmi selon les odeurs de nourriture, de phéromone ou de nid..


-----
