# Supports de TP Java Réseau de Neurones

Le but de ce TP est de programmer un réseau de neurones mono-couche capable d'apprendre à classer des données en 
deux catégories.

C'est le premier type de réseau inventé dans les années 60; dans les années 70s/80s sont apparus les réseaux 
multicouches utilisés actuellement.

## Classification de textes

### sacs de mots
Lorsqu'on souhaite apprendre à des textes sur deux catégories (spam/non-spam, par exemple); on épure ces textes en 
ôtant les mots de liaison et mots peu utilisés.

Il reste donc des ensembles de mots pour chaque texte. 
- Pour encoder ces textes, on regarde le nombre de mots différents utilisés par l'ensemble des textes et on crée un 
tableau où ne colonne correspond à un mot.
- Ainsi pour le texte i, on mettra 1 en colonne j si le mot n° j du tableau apparaît dans le texte.
  - c'est la conversion en « sacs de mots »

Ici dans le TP, on cherche à apprendre à reproduire le classement suivant : 
 - 'bombe', 'feu' => danger
 - 'bombe', 'peinture' => _
 - 'bombe', 'pistolet' => danger
 - 'pistolet', 'peinture' => _
 - 'pistolet', 'balle' => danger
 - 'tete', 'balle' => danger
 - 'balle', 'tennis' => _
 - 'gun' => danger
 - 'gun', 'roses' => _
 - 'attaquant' => danger
 - 'attaquant', 'sport' => _
 - 'descendre' => danger
 - 'descendre', 'bierre' => _
 - 'descendre', 'escalier' => _

  - Un texte contenant les mots 'tete' & 'balle' (en non les autres) est considéré comme suspect; un texte contenant 
    bombe et  'peinture' (en non les autres) est considéré comme non suspect.

Dans la classe **Data**, vous trouverez le tableau correspondant : 

<pre>
static double[][]data = {
{1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
{1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},...
[----------------Xs----------------------][Yi]
</pre>

L'algo doit donc apprendre à répondre '1' lorsqu'on lui présente l'***exemple*** <code>{1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0,
0, 0}</code>, ...

### Réseau mono-couche
Un réseau de neurones mono-couche et à une seule sortie contient n neurones d'entrées et 1 neurone de sortie.

Chaque neurone d'entrée transmet le signal au neurone de sortie.
Il y a autant de neurones d'entrées que de valeurs composant un exemple à apprendre plus 1 (donc 15 ici); 
*(en effet, l'ajout d'un neurone spécifique générant la valeur 1 est nécessaire pour un bon apprentissage)*.

#### Apprendre sur un exemple
Pour un exemple $e$, le neurone de sortie reçoit donc chaque valeur $e_i$, applique un poids 
$w_i$,  et réalise  une somme pondérée : 
  - $s = \omega_0.e_0 + \omega_1.e_1 + \omega_2.e_2 + \dots + \omega_n.e_n$

Pour contenir cette somme sur $[0,1]$, on applique une fonction sigmoïde : 
  - $y_p = f(s) = \frac{1}{1+e^{-s}}$

Puis l'algo mesure son erreur ($dif = yi-yp$, différence entre le  y idéal et le  y prédit) et modifie les 
coefficients  omegas à partir de cette formule :
    - $\omega_i = \omega_i + \alpha \times dif \times e_i$


#### Réitérer le cycle d'apprentissage

Il s'agit de répéter l'"apprentissage" ci-dessus sur l'ensemble des exemples.

Et de recommencer des cycles d'apprentissage complet jusqu'à « un certain temps » (un nb de cycles prédéfini) ou 
jusqu'à l'obtention d'un degré d'erreur satisfaisant.


Le TP se compose de plusieurs classes et de plusieurs étapes.



____________

**Etape 1 : Les données**

Complétez la classe GestionData fournie. (complétez les "ToDo")

 **Testez l'application !**
Lancez le main de la classe Perceptron1. 
Le contenu des exemples doit s'afficher.

____________

**Etape 2 : Les calculs**

Dans la classe Calcul,
- **compléter le code de la fonction retournant la sigmoïde** :
    - $f(x) = \frac{1}{1+e^{-x}}$
        - Math.exp(x) retourne $e^x$

- **compléter le code de la fonction retournant l'erreur moyenne** :
    - on utilise la formule de la "Binary cross entropy" :
        - $-\frac{1}{n}{\sum_{j=0}}^{n-1}(Yi_j*log(Yp_j) + (1-Yi_j)*log(1-Yp_j))$
            - Yi est le tableau des valeurs y idéales
            - Yp est le tableau des valeurs Y prédites (calculées)
            - Math.log(x) retourne $log(x)$
        - on somme donc l'ensemble des "erreurs" et on en retourne $-erreurs/n$


____________

**Etape 3 : L'apprentissage par réseaux de neurones**
Reste à coder le noyau du système : la classe proposant et modifiant les coefficients $\omega$.

Coder les 3 fonctions manquantes : feedForward, computeOutputAndErrors et train dans la classe LogisticRegression

____________

**Etape 4 : Test**
- Décommentez les lignes de la classe FenetreRegression
- Vérifiez l'apprentissage
