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