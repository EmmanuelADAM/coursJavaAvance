# Aides pour le TP le Compte est bon

## Les énumérations
Le TP était basé un peu sur la manipulation d'énumération et de lambda calcul (programmation fonctionnelle)
Le code le plus utile pour le TP était le code OperateurBis.java

* la classe Operateur définit une énumération qui possède 1 attribut chaine et 2 fonctions à surcharger. 
La définition de chaque valeur implique de devoir surcharger explicitement ces fonctions.
Les 4 opérateurs sont des instances de 4 classes différentes qui étendent la classe Operateur.
* la classe OperateurBis définit une énumération qui possède 3 attributs : 
  * 1 chaine, 1 fonction binaire (entier, entier)->entier qui effectue le calcul de l'operateur et 1 fonction binaire (entier, entier)->booléen qui effectue le test de l'operateur.
C'est la version la plus simple à définir.
* la classe OperateurTer définit une énumération qui possède 3 attributs : 
  * 1 chaine, 1 objet d'une classe qui doit implémenter l'interface Calcul, 1 objet d'une classe qui doit implémenter l'interface Prerequis.
Ces 2 interfaces ne contenant qu'une fonction, la surcharge de leurs méthode est simplifiée par les lambdas expressions.

Clairement, le code de la classe OperateurBis occupe le moins de mémoire car nécessite moins de créations de classes anonymes.
