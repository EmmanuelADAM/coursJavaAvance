# Gestion de sac à dos par algo génétique

Ces codes présentent la solution complete au porblème de la gestion de Sac à Dos bi-critère par algo généqique, 
sujet de l'examen de Java 2019 en Licence 3 Info à l'Université Hauts-De-France..

Dans ce type de problème, il s’agit de remplir un sac à contenance limitée avec des objets, en prenant en compte au moins 2 critères, par exemple minimiser le poids et maximiser l’intérêt global des objets retenus.

Si 52 objets peuvent être choisis, il y a en tout 2^52 sacs possibles (env. 4 500 000 millards). Les construire tous pour tous les évaluer et rechercher le meilleur est coûteux en temps et en espace.

En algo génétique, on représente une solution par une séquence génétique (un gène). Les algos les plus simples utilise deux entiers 0 ou 1. Retour ligne automatique
Ici, on codera un sac sous la forme d’un gène contenant 52 entiers : Retour ligne manuel
gene = [1,1,0,1,0,1, 0, 0, -, 0]Retour ligne automatique
signifie que le sac comporte les objets n°0, n°1, n°3 et n°5.

On somme alors l’intérêt porté à ces objets, on somme également leurs poids et on calcule l’utilité du sac en combinant ces sommes : sommeInteret - SommePoids.
** Ainsi, à poids égal, un sac plus intéressant est plus utile
** A intérêt égal, un sac moins lourd est plus utile.
Aussi, on dira qu’un sac est inutile (utilité = -∞) si son poids dépasse un seuil fixé.

Les algorithmes génétiques permettent d’approcher d’une bonne solution en étant peu couteux :
* quelques sacs sont générés, tous différents
* puis, pendant n générations, ou jusqu’à ce que l’utilité du meilleur n’évolue plus après n générations
** puis les meilleurs se croisent pour donner naissance à de nouveaux sacs
** Pour éviter de boucler sur les mêmes gènes et permettre d’atteindre potentiellement de meilleurs résultats, quelques mutations peuvent apparaître dans la population.
** en évitant les sacs les plus utiles !
** les doublons de sac sont retirés et l’ensemble est trié.
