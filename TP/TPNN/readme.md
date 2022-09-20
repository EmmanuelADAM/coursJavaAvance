# Supports de TP Java R�seau de Neurones

Le but de ce TP est de programmer un r�seau de neurones mono-couche capable d'apprendre � classer des donn�es en 
deux cat�gories.

C'est le premier type de r�seau invent� dans les ann�es 60; dans les ann�es 70s/80s sont apparus les r�seaux 
multicouches utilis�s actuellement.

## Classification de textes

### sacs de mots
Lorsqu'on souhaite apprendre � des textes sur deux cat�gories (spam/non-spam, par exemple); on �pure ces textes en 
�tant les mots de liaison et mots peu utilis�s.

Il reste donc des ensembles de mots pour chaque texte. 
- Pour encoder ces textes, on regarde le nombre de mots diff�rents utilis�s par l'ensemble des textes et on cr�e un 
tableau o� ne colonne correspond � un mot.
- Ainsi pour le texte i, on mettra 1 en colonne j si le mot n� j du tableau appara�t dans le texte.
  - c'est la conversion en � sacs de mots �

Ici dans le TP, on cherche � apprendre � reproduire le classement suivant : 
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

  - Un texte contenant les mots 'tete' & 'balle' (en non les autres) est consid�r� comme suspect; un texte contenant 
    bombe et  'peinture' (en non les autres) est consid�r� comme non suspect.

Dans la classe **Data**, vous trouverez le tableau correspondant : 

<pre>
static double[][]data = {
{1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
{1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},...
[----------------Xs----------------------][Yi]
</pre>

L'algo doit donc apprendre � r�pondre '1' lorsqu'on lui pr�sente l'***exemple*** <code>{1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0,
0, 0}</code>, ...

### R�seau mono-couche
Un r�seau de neurones mono-couche et � une seule sortie contient n neurones d'entr�es et 1 neurone de sortie.

Chaque neurone d'entr�e transmet le signal au neurone de sortie.
Il y a autant de neurones d'entr�es que de valeurs composant un exemple � apprendre plus 1 (donc 15 ici); 
*(en effet, l'ajout d'un neurone sp�cifique g�n�rant la valeur 1 est n�cessaire pour un bon apprentissage)*.

#### Apprendre sur un exemple
Pour un exemple $e$, le neurone de sortie re�oit donc chaque valeur $e_i$, applique un poids 
$w_i$,  et r�alise  une somme pond�r�e : 
  - $s = \omega_0.e_0 + \omega_1.e_1 + \omega_2.e_2 + \dots + \omega_n.e_n$

Pour contenir cette somme sur $[0,1]$, on applique une fonction sigmo�de : 
  - $y_p = f(s) = \frac{1}{1+e^{-s}}$

Puis l'algo mesure son erreur ($dif = yi-yp$, diff�rence entre le  y id�al et le  y pr�dit) et modifie les 
coefficients  omegas � partir de cette formule :
    - $\omega_i = \omega_i + \alpha \times dif \times e_i$


#### R�it�rer le cycle d'apprentissage

Il s'agit de r�p�ter l'"apprentissage" ci-dessus sur l'ensemble des exemples.

Et de recommencer des cycles d'apprentissage complet jusqu'� � un certain temps � (un nb de cycles pr�d�fini) ou 
jusqu'� l'obtention d'un degr� d'erreur satisfaisant.


Le TP se compose de plusieurs classes et de plusieurs �tapes.



____________

**Etape 1 : Les donn�es**

Compl�tez la classe GestionData fournie. (compl�tez les "ToDo")

 **Testez l'application !**
Lancez le main de la classe Perceptron1. 
Le contenu des exemples doit s'afficher.

____________

**Etape 2 : Les calculs**

Dans la classe Calcul,
- **compl�ter le code de la fonction retournant la sigmo�de** :
    - $f(x) = \frac{1}{1+e^{-x}}$
        - Math.exp(x) retourne $e^x$

- **compl�ter le code de la fonction retournant l'erreur moyenne** :
    - on utilise la formule de la "Binary cross entropy" :
        - $-\frac{1}{n}{\sum_{j=0}}^{n-1}(Yi_j*log(Yp_j) + (1-Yi_j)*log(1-Yp_j))$
            - Yi est le tableau des valeurs y id�ales
            - Yp est le tableau des valeurs Y pr�dites (calcul�es)
            - Math.log(x) retourne $log(x)$
        - on somme donc l'ensemble des "erreurs" et on en retourne $-erreurs/n$


____________

**Etape 3 : L'apprentissage par r�seaux de neurones**
Reste � coder le noyau du syst�me : la classe proposant et modifiant les coefficients $\omega$.

Coder les 3 fonctions manquantes : feedForward, computeOutputAndErrors et train dans la classe LogisticRegression

____________

**Etape 4 : Test**
- D�commentez les lignes de la classe FenetreRegression
- V�rifiez l'apprentissage
