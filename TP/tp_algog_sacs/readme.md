# TP 1. Algo génétique
## Gestion de sac à dos

Le but de ce TP est de coder un algorithme génétique, dédié à la recherche de solutions.

Ici le but sera de constituer un sac, pouvant supporter un poids maximum, en faisant des choix parmi des objets.

Chaque objet a un intérêt et un poids; il s'agit de créer le sac ayant le plus haut rapport intérêt-poids.

---
### Principe de l'algo génétique

Un algorithme génétique est basé sur une population de séquence; chaque séquence correspond à une solution au problème.

 1. Les séquences sont évaluées, ici sur le ratio poids/intérêt.
 2. Les meilleures séquences sont croisées, et le résultat remplacent les moins bonnes.
 3. Un pourcentage de séquences est choisi aléatoirement pour devenir mutantes.
 4. Pour chaque 'mutant' un pourcentage de gènes est modifié au hasard.

L'ensemble de ces points se répète "un certain temps" afin d'obtenir des séquences de plus en plus performantes.

Au final la 1ère des séquences est affichée.

Ce type d'algorithme est générique et s'applique à tout type de problème; le plus complexe étant de savoir transformer un énoncé en une séquence...

---

### Le problème
Remplir un sac à dos d'un capacité maximale 10kg avec des objets, chacun possédant un poids et un intérêt propre.
Il existe 104 objets possibles (de A1 à Z3).

Parmi toutes les combinaisons possibles, on recherche celle dont le couple (légèreté, intérêt) est le plus intéressant. Il s'agit donc ici d'une recherche bi-critère.

Si 52 objets peuvent être choisis, il y a en tout 2<sup>104</sup> sacs possibles (20 282 409 603 651 670 423 947 251 286 016 sacs, donc environ 20 millions de millards de millards sacs différents). 
Les construire tous pour tous les évaluer est coûteux en temps et en espace.

Les algorithmes génétiques permettent d'approcher d'une bonne solution en étant peu couteux : quelques sacs sont générés puis les meilleurs se croisent pour donner naissance à de nouveaux sacs, etc..

Une séquence génétique sera codée ici sous forme d'un tableau de 104 entiers dans 0,1. 
1 signifiant la presence d'un objet dans le sac.
Ainsi genes = [1,1,0,0,1,0, ...] signifie que le sac comporte les objets 0,1,4, ....

L'utilité d'un sac est la différence entre l'intérêt et le poids. 
Mais si on suppose qu'un poids va de 1 à 3000g et que l'intérêt est notée de 0 à 10, la valeur de l'intérêt sera écrasée par celle du poids. 
Il faut uniformiser les valeurs pour qu'elles soient de même ordre (une valeur bornée dans [0,1] par exemple).
Ainsi on notera utilité = intérêtNormée - poidsNormée
- à intérêt égal, un sac plus lourd sera moins utile
- à poids égal, un sac plus intéressant sera plus utile

On veillera aussi à fortement pénaliser les sac dépassant le seuil des 10kg

---
### Caractéristique des sacs
Les objets pouvant être dans les sacs sont stockés dans une énumération (cf. code source). 
Ils se nomment A1, B1, ... Z3. Il y a donc 104 objets possibles.

Dans cette énumération, 
- poids est le poids de l'objet
- interet est l'intérêt de l'objet
- poidsUniforme est le poids divisé par le poids du plus lourd objet
- interetUniforme est l'intérêt de l'objet divisé par l'intérêt de l'objet le plus intéressant.
- SEUILPOIDSMAXI est le poids à ne pas dépasser pour un sac
- MutationDesSequences est le taux de séquences mutantes
- MutationDansSequence est le taux de mutation dans une séquence

<p style="background-color:lightgrey">
<b>Question 1</b></p>

*Reprenez le code EnumSac.java et compléter la fonction ``uniformiser()`` qui calcule les poids et intérêts uniformisés de chaque sac.*

On pose ``poidsUniformise = poids/maxiPoids``, maxiPoids étant le poids de l'objet le plus lourd de l'énumération.

De même, on pose ``interetUniformise = intérêt/maxiInteret``, maxiInteret étant l'intérêt le plus haut trouve parmi les objets de l'énumération.

Vous lancerez le ``main()`` pour tester votre fonction.

---
### Séquence

Une séquence possède un tableau de gènes (ici des entiers sur 0,1).

- ``gènes[0] = 1`` signifie que le produit 0 fait parti du sac 
- ``gènes[0] = 0`` signifie que le produit 0 n'en fait pas parti

Travaillez sur le code Sequence.java.

<p style="background-color:lightgrey">
<b>Question 2</b></p>

*Définissez la fonction de la classe Sequence ``void construireSequence()`` qui remplit aléatoirement et à distribution équitable le tableau de gènes de chiffres 0 ou 1, et lance le calcul de l'utilité.*

>N.B. On peut utiliser une variable de type Random. Exemple :<br>
``var hasard = new Random();``<br>
``int no = hasard.nextInt(10); ``// retourne un entier entre 0 et 9 inclus.

---

<p style="background-color:lightgrey">
<b>Question 3</b></p>

Définissez la fonction de la classe Sequence ``Sequence croiserSequence(Sequence autre)`` qui retourne le résultat du croisement de la séquence avec un autre.

Ex. si la séquence ``a`` possède **a0a1a2a3a4a5**
et qu'une autre séquence ``b`` possède **b0b1b2b3b4b5**
alors le croisement retourne une séquence qui possède **a0a1a2b3b4b5**.
Le calcul de l'utilité est lancé sur cette séquence fille.

>N.B. Il suffit de créer une nouvelle séquence avec la même longueur de séquence et le même taux de gènes mutants que ses 'parents' et de remplir son tableau de gènes de moitié de ses parents.<br>
>Possibilité d'utiliser la fonction ``System.arraycopy(tab0, i0, tabDest, iDest, nb)`` qui copie les éléments ``tab[i0], tab[i0+1], ..., tab[i0+nb-1]`` dans ``tabDest[iDest],tabDest[iDest+1],...,tabDest[iDest+nb-1]``<br>
>Possibilité également d'utiliser la fonctions ``Arrays.copyOfRange(short[] original, int from, int to)``
 
---

<p style="background-color:lightgrey">
<b>Question 4</b></p>

*Définissez la fonction de la classe Sequence ``void muterSequence()`` qui effectue des mutations dans les genes de la sequence en fonction du nombres de gènes à muter.*

Une mutation change simplement un 1 en 0 et inversement.
Un calcul d'utilité est également réalisé après la mutation.

---

<p style="background-color:lightgrey">
<b>Question 5</b></p>

*Définissez la fonction de la classe Sequence ``void calculUtilite()`` qui calcule l'utilité d'une séquence (sommes des interetsuniformes de chaque produit qu'elle contient moins la somme des poidsuniformes).*

Si la somme des poids (normaux) dépasse le seuil limite, l'utilite prend la valeur Double.NEGATIVE_INFINITY (le réel le plus bas possible).

---

<p style="background-color:lightgrey">
<b>Question 6</b></p>

*Définissez la fonction de la classe Sequence ``int compareTo(Sequence autre)`` qui compare les sequences par utilité décroissante.*

> Soient 2 séquences s1 et s2, ``s1.compareTo(s2)`` retourne <br>
>  . un entier < 0 si s1 a une utilité > à celle de s2 (s1 est donc placé en début de tableau suite à un tri);<br>
>  . un entier > 0 si s1 a une utilité < à celle de s2, <br>
>  . et 0 si s1 et s2 ont la même utilité +- 0.00000001 ou si elles sont toutes deux égales à Double.NEGATIVE_INFINITY<br>

On cherche à placer en 1er les grandes utilités, donc une utilité comparé à Double.NEGATIVE_INFINITY retournera une valeur < à 0 et Double.NEGATIVE_INFINITY comparé à une utilité retournera une valeur > à 0

---

<p style="background-color:lightgrey">
<b>Question 7</b></p>

*Définissez la fonction de la classe Sequence ``String toString()`` qui retourne une chaine contenant les valeurs des gènes, ou les noms des objets, ainsi que l'utilité de la séquence (arrondie à 2 chiffres après la virgule)*

>N.B. La classe Arrays propose la méthode toString pour retourner une chaine à partir d'un tableau (Arrays.toString(tab))

---
### Algo Génétique

Les séquences étant définies, il reste à créer une population et à générer des cycles de vie. 
Complétez le code de la classe AlgoGPourSacs


<p style="background-color:lightgrey">
<b>Question 8</b></p>

*Définissez la fonction de la classe AlgoGPourSacs ``void construirePopulation()`` qui construit nbSequences sequences viables (d'utilité différente de Double.NEGATIVE_INFINITY) et les place dans le tableau tabSequences*

---
<p style="background-color:lightgrey">
<b>Question 9</b></p>

*Définissez la fonction de la classe AlgoGPourSacs ``void afficherCodes()`` qui affiche les sequences, la meilleure, la pire.*

Pour la meilleure, la fonction ``afficherDetail(Sequence s)`` est lancée.
>N.B. Pour trier un tableau d'objets comparables, on peut utiliser la classe Arrays et sa fonction de tri : ``Arrays.sort(tableau)`` trie le tableau.

---
<p style="background-color:lightgrey">
<b>Question 10</b></p>

*Définissez la fonction de la classe AlgoGPourSacs ``void croiserMeilleurs(int nbElites)`` qui croise nbElites entre elles.*

Chaque résultat de croisement s'il est viable est classé en bas de tableau.

---
<p style="background-color:lightgrey">
<b>Question 11</b></p>

*Définissez la fonction de la classe AlgoGPourSacs ``void muter()`` qui effectue des mutations dans le tableau des sequences.*

On choisit des sequences au hasard, sauf la première, et demande à chaque mutante de modifier ses gènes.

---
<p style="background-color:lightgrey">
<b>Question 12</b></p>

*Définissez la fonction de la classe AlgoGPourSacs ``void cycleDeVie()`` qui trie le tableau des sequences, demande le croisement des 2 meilleurs, et une mutation.*

---

Le TP est déjà fini, il reste à tester en lancant la méthode ``main()``.

---

>Vous avez créé votre premier algorithme génétique; ce même code, moyennant quelques légères adaptation ce même code vous permettra de trouver le chemin le plus efficace dans un réseau, l'efficacité pouvant être notée sur différents critères (distance, durée, taux de co2, ...), etc. <br>
>Ces algorithmes sont utilisés pour aider à trouver des solutions là où le nombre de paramètres à tester devient trop complexe pour pouvoir être calculés en un temps acceptable.<br>
>Il n'est pas garantie que la solution obtenue par un algo génétique soit la meilleure possible; on peut ssimplement dire que c'était la meilleure trouvée au bout d'un certain temps....

---
### REMARQUES SUR LE TP

- Avancez par étape. Ne vous perdez pas trop dans les détails d'affichage. Si vous ne pouvez pas afficher les noms des objets du meilleur sac à la fin de la séance, ce n'est pas grave.
- Les fonctions sont courtes, en moyenne 5 lignes, parfois même 1 seule (fonction toString), parfois 7...
- Les fonctions les plus longues (12 lignes en comptant les lignes ne comportant qu'une accolade) sont calculUtilite() et afficherCodes()

**Travail à rendre à la fin de la séance pour garantir le maximum de points**
