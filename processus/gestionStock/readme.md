# Cas des Producteurs & Consommateurs

Illustration du principe de synchronisation : 
- Un entrepot peut contenir un nb limité d'entiers
- Des processus protucteurs produisent des entiers et les stockent dans un entrepôt.
- Des clients retirent des entiers de l'entrepot.
- La classe principale est ProductConsom qui crée un entrepot, et crée des producteurs et consommateurs tous lies au meme entrepot.

- Exemple de sortie : 
```
consom 3-- est mis en pause dans le retrait d'objet
product1 : j'ai depose l'objet 2
consom 1: j'ai preleve l'objet 3
product2 : j'ai depose l'objet 3
consom 2: j'ai preleve l'objet 2
product2 : j'ai depose l'objet 4
consom 3-- reprend la tentative de retrait d'objet
consom 3: j'ai preleve l'objet 4
product1 : j'ai depose l'objet 5
product1 : j'ai depose l'objet 6
consom 1: j'ai preleve l'objet 6
product2 : j'ai depose l'objet 7
product1 : j'ai depose l'objet 8
consom 3: j'ai preleve l'objet 8
product2 : j'ai depose l'objet 9
consom 2: j'ai preleve l'objet 9
product2 : j'ai depose l'objet 10
voici le contenu de l'entrepot apres 2000ms: 
[5, 7, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
 nbObjetsProduits = 9, nbObjetsConsommes = 6
```

