# Bases de Java
(retour au site)[https://emmanueladam.github.io/coursJavaAvance/]

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
  - exemple de sortie :
  ```
  96863403, 90311323, 19108406, 50518617, 82411494, 91616917, 21814790, 49344932, 93615853, 75765243, 
  temps écoulé pour le tri de 100000000 entiers = 13.151510906 s
  0, 4, 7, 8, 9, 10, 12, 13, 13, 15, 
- fonction pour illustrer le tri de 100 millions d'entiers en parallele
  - exemple de sortie :
  ```
  74019091, 52268044, 27977908, 57592398, 16585235, 2273969, 36737277, 50142161, 41780788, 69067071, 
  temps écoulé pour le tri de 100000000 entiers en parallèle = 6.198591867 s
  3, 4, 8, 8, 10, 10, 11, 11, 11, 11, 
- fonction qui illustre le fonctionnement de liste dynamiques et statiques
  - exemple de sortie :
  ```
  liste1 = [1, 2, 3, 4, 5]
  liste2 = [4, 5, 6, 7, 8]
  concaténation = [1, 2, 3, 4, 5, 4, 5, 6, 7, 8]
  union = [1, 2, 3, 4, 5, 6, 7, 8]
  intersection = [4, 5]
  unionDisjointe = [1, 2, 3, 6, 7, 8]
  valeur > 5 dans l'union :
  6, 7, 8, 
  union sans les valeurs < 5 :[5, 6, 7, 8]
- test de map String < - > String
  - exemple de sortie :
  ```
  krol correspond a carole
  al1 correspond a alain
  yan correspond a yanice
  valeur associee à krol=carole
  valeur associee precedemment à yan =yanice
  nouvelle valeur associee  à yan =yann
- exemple de fonction utilisant une fonction passee en parametre (programmation fonctionnelle (lambda))
  - exemple de sortie :
  ```
  tabA=[2, 5, 4, 3, 1, 7, 8]
  tabB=[5, 4, 8, 2] 
  tabC=tabAxTabB =[10.0, 20.0, 32.0, 6.0, 0.0, 0.0, 0.0, 0.0]
  tabC=tabA/TabB =[0.4, 1.25, 0.5, 1.5, 0.0, 0.0, 0.0, 0.0]
