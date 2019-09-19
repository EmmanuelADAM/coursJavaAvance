# TPTraficRoutierJava
Source pour bien débuter le TP sur le trafic routier.

Des véhicules se déplacent sur un réseau routier. Dans un permier temps, il s'agit de gérer l'affichage, l'animation, les colisions, les freinages, les stops et reprises par clic sur les véhicules. 
* Dans ce premier source (master), il reste à gérer les collisions quand 2 véhiculent entrent en même temps de façon croisé sur un carrefour. Les véhicules accidentés doivent être supprimés au bout "d'un certain temps" (7 tops par exemple). Idem, les véhicules arrivés à destination sont supprimés du réseau. Des véhicules doivent être ajoutés aléatoirement tous les 2 tops.  La classe principale est AppliTrafic.
* Avec le source de la branche V2, il est plus facile de gérer les bouchons (freinage quand un noeud est occupé); des noeuds et arcs intermédiaires sont créés entre 2 noeuds principaux. Dans ce source, une meilleure distribution des actions est effectuée, La classe pricipale AppliTraffic gère la fenêtre et les événements et interagit avec une classe de contrôle de traffic qui créé le réseau routier et anime les voitures qui sont eux définis dans les package 'modele'.
