# TP Propagation de virus

Le but du TP est de simuler la propagation de virus entre personnes. Plusieurs personnes peuvent partager un même emplacement (habitation, magasin, lieu de travail).
Si une personne malade partage le même emplacament qu'une autre pendant un certain temps; elle lui transmet le virus.

----

Ce code de départ du TP sur la programmation de virus. 
- dessine une grille de 30 x 30
- place 60 "Personnes" dans la partie basse de l'environnement
- les personnes sont 'cliquables', un clic permet de les rendre contagieuses (rouges) ou guerries (blanches)
- ajoute des écoutes claviers : 
  - g pour 'go()' lance une série de cycles (1 déplacement, pause x 4)
  - b fait bouger 1 fois
  - t fait faire 1 pause.

Au bout de 4 pauses dans la même place qu'une personne contagieuse, une personne le devient à son tour. 

  - A vous de poursuivre pour l'énoncé suivant du TP.....

---
## Question 1 : Distinguer les places

Les places sont dans un premier temps distiguées : Domicile, Entreprise, Magasin.
Créez donc une énumération TypeLieu qui contient ces types; et une classe Lieu. 
 

Cette classe contient :
 - Point pos : position dans la grille (pas en pixel)
 - TypeLieu type : type de lieu
 - List personnes : liste de personnes dans le lieu

Plus quelques fonctions que vous choisirez (addPersonne(..), removePersonne(..)


L'environnement possède une liste de lieux maintenant, ainsi que des Map (Hashtable) :
 - Map<Point, Lieu> plan : table (position; lieu) qui permet de récupérer un lieu à partir de sa position
 - Map<TypeLieu, List<Lieu>> annuaire : table (Type; liste de lieux) qui permet de récupérer la liste des lieux associés à un type de lieu
 - une fonction void addLieu(Lieu l) : qui ajoute le lieu à la liste et dans le plan
 - une fonction void creerAnnuaire() : qui crée le plan. Cela est faisable en 1 ligne, cf "Collectors.groupingBy" du cours no 2
 - Lieu getLieu(Point p) : retourne le lieu en position p
 - Lieu getLieu(Point p) : retourne le lieu en position p
 - Lieu selectLieu(TypeLieu typeLieu) : retourne un lieu au hasard du type demandé

  Une personne possède maintenant un champs domicile qui est un lieu et une position du domicile (un Point), ainsi que son lieu actuel et sa position actuelle.
Quant une personne se déplace à une position x,y, elle se retire du lieu actuel pour s'ajouter au lieu à la position x,y.
Une Personne contient un champs activite de type Activite qui est un énumération possédant les champs Repos, Travail, Course.
 
---
Initialement, il y a 2 personnes par domicile, et nulle part ailleurs

SCENARIO : Les Personnes maintenant effectue le cycle suivant : Repos, Travail, Course.  
 - En Repos, la personne va à SON domicile, en Travail, elle va dans une place de travail aléatoire, en Course, elle va dans un magasin au hasard. Ce scénario sera plus réaliste par la suite
  
La personne ne va plus errer, mais à la place va "sactiver" : cette fonction avance la personne dans les activités cycle (repos, travail, course) et positionne la personne dans les places adaptées (Domicile, Entreprise, Magasin).

----
## Question 2 : Dialogue FXML
  
Pour mieux voir l'évolution, on utilise une fenêtre détachée.
On décide de la réaliser en FXML.

Cette boite de dialogue sera ici très simple : 2 champs textes indiquant l'un le nb de personnes, l'autre le nb de personne infectées.

Par exemple, dans la classe principale de l'application, créer la fonction void construirePetitTheatre(Stage primaryStage) qui reprend le code de choixCouleurs(Stage primaryStage) du site. La fenêtre sera amodale (dialogStage.initModality(Modality.NONE);).

----

## Question 3 : Séparation du temps

La journée maintenant est découpée en 24 heures : 24 cycles.
On va poser le principe suivant :
 - de 9h à 17h au travail
 - de 17h à 21h au magasin (ou pas, 1 chance / 3, sinon elle rentre directement chez elle)
 - de 21h à 9h chez soi.
  
Une personne n'est pas directement contaminée si elle est en présence d'une personne malade; mais elle a ----

---

## Question 4 : Tailles des batiments

Les entreprises et magasins ont maintenant des tailles, de 1 à 10.

Une entreprise de taille 1 occupe 1 point, taille 2 : 2 points consécutifs, ... taille 4 : carre de 2x2... de taille 10 : 10 points (organisés en blocs 5x2)
Les entreprises peuvent aussi simplement être rangées en 1 ligne (ligne de 10 points par exemple) si vous trouvez cela plus simple (et ça l'est).
Sur un point (une place) peuvent être placée 6 personnes maximum.

Tous les points de la zone supérieure ne sont pas nécessairement entreprise ou magasin, certains peuvent être vides.

Pour une entreprise, 1/4 des points est réservé au restaurant d'entreprise (pour les entreprises <= 4 points, 1 point est utilisé pour cela (dont le point unique pour en entreprise de taille 1).

Le temps de repas est de 1h de 12 h à 13h. Tous les travailleurs se répartissent dans les points restaurant de leur entreprise à ce créneau.


-----
## Question 5 : Incubation

Maintenant lorsque l'on clique sur une personne, est est contaminée; elle deviendra malade au bout de 3 jours; mais peut contaminer d'autres personnes pendant ce temps.
 - Une personne peut contaminer au plus 3 personnes qu'elle croise une heure
 - Une personne malade revient chez elle pour un espoir de guérison sous 15 j.
Ajouter à l'interface le bouton quarantaine.

Lorsque la quaraine est activée; un personne malade retourne à son domicile, et ses contacts avec lesquels elle a passé plus d'une heure également (contaminés ou non).

Vous vérifierez ainsi l'intérêt de ce principe sur le temps de propagation du virus.

Aussi, vous créerez un bouton "switch resto" qui active ou désactive  les restaurants d'entreprise. Quel est l'impact sur le temps de propagation ?

  
