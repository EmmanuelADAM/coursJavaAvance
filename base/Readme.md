# Bases de Java

## TestsBase
- fonction pour illustrer le cout de la creation d'objets
  - exemple de sortie : 
  ```
  somme de 0 à 60000 = 1799970000
  -- tps de calcul en utilisant les primitives =  1.307451 ms
  -- memoire utilisee =  0 o
  somme de 0 à 60000 = 1799970000
  -- tps de calcul en utilisant les objets =  8.709526 ms
  -- memoire utilisee =  94113 o
- fonction qui compare les couts en tps de l'utilisation de String, StringBuilder et StringBuffer
  - exemple de sortie : 
  ```
  0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 
  Concaténation de 60000 String par +, temps écoulé = 3326.877432 ms
  0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 
  Concaténation de 60000 String par StringBuilder, temps écoulé = 10.271225 ms
  0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 
  Concaténation de 60000 String par StringBuffer, temps écoulé = 5.380104 ms

- fonction illustrant la creation d'un tableau, son affichage, son tri par lambda expression et sans boucles
- fonction pour illustrer le tri de 100 millions d'entiers en parallele
- fonction qui illustre le fonctionnement de liste dynamiques et statiques
- test de map String < - > String
- exemple de fonction utilisant une fonction passee en parametre (programmation fonctionnelle (lambda))
