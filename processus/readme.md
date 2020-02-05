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
