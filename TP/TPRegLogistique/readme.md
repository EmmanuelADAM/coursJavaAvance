# Supports de TP Java Regression Logistique



Le but de ce TP est de programmer un algorithme d'apprentissage simple par régression logistique.

Supposons 2 valeurs $x_1$ et  $x_2$, chaque couple de valeur est classé dans un groupe 1 ou 0 (y=1 ou y=0)

Le but de l'algorithme est d'apprendre à classer automatiquement les couples.


Pour cela, l'algo applique des coefficients theta aux valeurs de x et prédit une sortie :  
$\theta_0 + \theta_1.x_1 + \theta_2.x2 = yp$


Puis l'algo mesure son erreur (dif, différence entre le y idéal et le y prédit) modifie les coefficients thetas à 
partir de dif.

Le cycle calcul-correction se poursuit « un certain temps ».

Théoriquement, s'il est possible de déduire une logique dans le classement initial ; l'algo aura appris à classer.

Le TP se compose de plusieurs classes et de plusieurs étapes.



____________

**Etape 1 : Les données**

Complétez la classe Data fournie. (complétez les "ToDo")
Contient des codes répondant à quelques points de programmation sur les TP :

**Testez l'application !**
Lancez le main de la classe FenetreRegression. Les points doivent apparaître, colorées selon leurs classes.

____________

**Etape 2 : Les calculs**

Ici, la classification est binaire (0 ou 1), la prédiction doit donc tenir sur $[0,1]$.

On utilisera alors une fonction sigmoïde pour cadrer les valeurs $\theta_0 + \theta_1.x_1 + \theta_2.x2$ dans cet 
intervalle.

Dans la classe Calcul, 
- **compléter le code de la fonction retournant la sigmoïde** : 
  - $f(x) = \frac{1}{1+e^{-x}}$
    - Math.exp(x) retourne $e^x$

- **compléter le code de la fonction retournant l'erreur moyenne** : 
  - on utilise la formule de la "Binary cross entropy" : 
    - $\frac{1}{n}\sum_{j=0}^{n-1}(Yi_j*log(Yp_j) + (1-Yi_j)*log(1-Yp_j))$
      - Yi est le tableau des valeurs y idéales
      - Yp est le tableau des valeurs Y prédites (calculées)
    - on somme l'ensemble des erreurs et on en retourne $-erreurs/n$

- **compléter le code de la fonction retournant la précision** :
  - Yi est le tableau des valeurs y idéales
  - Yp est le tableau des valeurs Y prédites (calculées)
  - On crée un tableau YpSeuil qui contient les valeurs de Yp ramenés au plus proche entier (0 ou 1)
  - On somme les différences entre Yi et YpSeuil et on en retourne la moyenne
