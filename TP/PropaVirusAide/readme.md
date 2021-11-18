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
Les places sont dans un premier temps distiguées : Domicile, Entreprise, Magasin.
- Créez donc une énumération TypeLieu qui contient ces types; et une classe Lieu. Cette classe contient :

Point pos : position dans la grille (pas en pixel)
TypeLieu type : type de lieu
List personnes : liste de personnes dans le lieu
Plus quelques fonctions que vous choisirez (addPersonne(..), removePersonne(..)
L'environnement possède une liste de lieux maintenant, ainsi que des Map (Hashtable) :

Map<Point, Lieu> plan : table (position; lieu) qui permet de récupérer un lieu à partir de sa position
Map<TypeLieu, List<Lieu>> annuaire : table (Type; liste de lieux) qui permet de récupérer la liste des lieux associés à un type de lieu
une fonction void addLieu(Lieu l) : qui ajoute le lieu à la liste et dans le plan
une fonction void creerAnnuaire() : qui crée le plan. Cela est faisable en 1 ligne, cf "Collectors.groupingBy" du cours no 2
Lieu getLieu(Point p) : retourne le lieu en position p
Lieu getLieu(Point p) : retourne le lieu en position p
Lieu selectLieu(TypeLieu typeLieu) : retourne un lieu au hasard du type demandé
Une personne possède maintenant un champs domicile qui est un lieu et une position du domicile (un Point), ainsi que son lieu actuel et sa position actuelle.
Quant une personne se déplace à une position x,y, elle se retire du lieu actuel pour s'ajouter au lieu à la position x,y.
Une Personne contient un champs activite de type Activite qui est un énumération possédant les champs Repos, Travail, Course.
 
