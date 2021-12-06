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
     <img src="https://github.com/EmmanuelADAM/coursJavaAvance/blob/master/TP/images/demoFourmis.png" alt=""> 
</center>

*NB. Le code utilise JavaFX. Il vous faut donc posséder la librairie téléchargeable [ici](https://gluonhq.com/products/javafx/) et ajouter le répertoire menant à '''javafx-sdk/lib''' dans les propriétés de votre projet.
De plus, pour lancer l'application, il vous faut préciser dans les options de la machine virtuelle Java (JVM) les chemins d'accès.*
  - *Exemple : --module-path MONREPERTOIRE/javafx-sdk/lib --add-modules=javafx.controls,javafx.fxml*

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

---
## Travail à rendre

Le code fourni permet de lancer des fourmis (appuyer sur "f" pour ajouter une nouvelle fourmi au centre) et de les faire se déplacer aléatoirement.
Les fourmis restent dans ce code "scotchées" à la zone de nourriture..

A vous de programmer le ramassage de nourriture, le retour au nid et le dépôt de phéromones.. (l'affichage est déjà programmé).
: 
<span style="background-color: #ffff99;">**Remplisser pour cela les points indiqués TODO:**</span>, de préférence dans l'ordre suivant : 

### Etapes
1. compléter l'énumération Direction
2. permettre à une fourmi de ramasser de la nourriture et de faire demi tour
3. permettre à une fourmi de poser une goute de pheromone lorsquelle n'est pas vide.
4. faire dilluer et dégrader la phéromone
5. orienter la fourmi selon les odeurs de nourriture, de phéromone ou de nid..
6. ajouter une gestion d'événement souris : un clic sur la scene ajoute une zone de nourriture

### A créer
1. ajouter un menu contenant un item ('configuration')
  - Cet item affiche une fenêtre réalisée en FXML (cf. les explication sur FXML [ici](https://github.com/EmmanuelADAM/coursJavaAvance/tree/master/fxml) et le code de fxmlExemple.
  - Cette fenêtre amodale affiche deux glisseurs : pour régler la vitesse de dégradation (évaporation) et la dillution
2. ajouter une gestion d'événement combinée : un clic sur la scene avec la touche ``Control`` appuyée ajoute une petite zone de danger
3. La zone de danger émet une odeur répulsive, les fourmis refusent d'y aller si l'odeur de danger dépasse un seuil... 
4. Maintenant, un clic combiné avec touche ``Shift``  ajoute un prédateur qui erre dans l'environnement. Il emet une phéromone de danger et les fourmis cherchent à l'éviter tout en poursuivant leurs tâches si possible.. 
---

En plus des dangers, ajouter des obstacles. 
 - Une case de type obstacle ne laisse passer aucune odeur de nid, de nourriture, de danger, de phéromones..
 - Un clic souris + un appui sur les touches ``Control`` et ``Shift`` permet d'ajouter des obstacles.
 - Vous créerez une ligne d'obstacles initiale comme dans la figure ci-dessous..


<center>
     <img src="https://github.com/EmmanuelADAM/coursJavaAvance/blob/master/TP/images/simuFourmisObstacle.png" alt=""> 
</center>

-----
## Système proie-prédateur

Initialement, 50 fourmis sont lancées, 5 zones de nourritures sont posées; 5 enemis sont lancés.
- Nid : 
  - La nourriture du nid est initialisée à 100
  - Pour chaque 10 points de nourritures rentrés au nid, 1 nouvelle fourmi est crée.
  - La nourriture disparait de 1 point à chaque top. 
- Fourmi
- Prédateur
  - S'il n'y plus de nourriture au nid, 1 fourmi meurt à chauqe top
  - Un nouveau prédateur possède 100 points de nourriture
  - Un  prédateur mange la fourmi sur sa case; ce qui lui rapporte 10 points de nourritures
  - Si un prédacteur a 120 points de nourriture, il crée un nouveau prédateur et partage sa nourriture avec lui.
  - Un prédateur perd 1 point de nourriture à chaque top, il meurt lorsqu'il n'a plus de nourriture
- Zone de nourriture
  - Une zone de nourriture met 50 étapes à se reconstituer

Ceci est le principe du modèle proie-prédateur. Il est utilisé pour mesurer, évaluer l'équilibre entre des populations d'animaux..
- Trop de consommateurs assèchent l'environnement, entraînent l'arrivée de nouveaux prédateurs. Les consomateurs meurent ensuite de faim et de chasse, et donc leurs prédateurs sans proies meurent également; l'environnement se reconstiue ensuite sans présence d'être vivants.
- Trop de prédateurs mangent les consommateurs et meurent ensuite par manque de proies.
----


