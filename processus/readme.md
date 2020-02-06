# Gestion de processus en Java

- **TestProcess** : montre que les méthodes d'une tâche peuvent être appelées en parralèle. Dans ce code, la tache effectue un long calcul qui est interrompu lorsque sa methode arrete() est exécutée en //.
  - Exemple de sortie de programme : 
  ```
  Mon nom est : tache 1
  Je suis la tâche principale
  Mon nom est : tache 2
  Je suis la tâche principale et j'arrete les taches
  tache 2 ::: J'ai été arrété, il me restait 8949623 calculs à faire !
  tache 2 ::: Je ne suis plus en vie, result=15320113446
  tache 1 ::: J'ai été arrété, il me restait 1049854 calculs à faire !
  tache 1 ::: Je ne suis plus en vie, result=17059353828

- **TestTimerTask** : montre un exemple de planification d'exécution de tâches. 2 tâches sont exécutées, l'une toutes les 2 secondes, l'autre toute les 3 secondes.
  - Exemple de sortie de programme : 
  ```
  10:49:45 -> Execution de tache t1
  10:49:45 -> Execution de tache t2
  10:49:47 -> Execution de tache t1
  10:49:48 -> Execution de tache t2
  10:49:49 -> Execution de tache t1
  10:49:51 -> Execution de tache t1
  10:49:51 -> Execution de tache t2
  10:49:53 -> Execution de tache t1
  10:49:54 -> Execution de tache t2
  10:49:55 -> Execution de tache t1
  10:49:57 -> Execution de tache t1
  10:49:57 -> Execution de tache t2
  10:49:59 -> FIN !!

- **TestLambdaRun** : montre deux exemples de création de processus par lambda expression; ce qui permet de paralléliser des calculs sans devoir créer de classe de processus dédiées. Un des exemple calcule la combinatoire en utilisant de grands entiers (BigInteger)
  - Exemple de sortie de programme : 
  ```
  fact[0] = 265252859812191058636308480000000
  fact[1] = 957391540294832002027614722000941251336135085932935459563196844422460451015364906387985902326752146410533027840000000000
  combi de 30 elements parmi 10000 = 3609354262844521281086173583674469373181460849385353937541082122453577570133286597008000

# **TestSynchro** : contient deux classes permettant d'illustrer la notion de synchronisation, de mise en attente et de réveil
  - Exemple de sortie de programme : 
  ```
  02:31:01.790951 lancement de p1
  02:31:01.801028 lancement de p2
  p1, je suis entré à 02:31:01.801256
  le nb n'est pas pair, je mets en attente
  p2, je suis entré à 02:31:03.807059
  le nb n'est pas pair, je mets en attente
  02:31:06.805848 lancement de p3
  p3, je suis entré à 02:31:06.806624
  le nb est pair
  p3 sort de la fonction à 02:31:08.812298
  p1 sort de la fonction à 02:31:08.812858
  p2 sort de la fonction à 02:31:08.813171
