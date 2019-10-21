# Javafx, Menu et FXML

Ces codes permettent de lancer une application JavaFX qui affiche un carre et un cercle.
Cette application est dotée d'un menu qui permet d'appeler une fenêtre pour changer les couleurs.
Cette fenêtre est créée à partir d'un ficier FXML...

Pour que cela fonctionne, il faut inclure les librairies javafx à votre projet.
Ces librairies sont téléchargeables [ici](https://openjfx.io/).

Le fichier FXML est créé à partir de l'application **Scene Builder** téléchargeable [ici](https://gluonhq.com/products/scene-builder/).

Pour lancer le projet, il faut éditer la configuration du lancement (*Edit Run configuration*) et modifier les arguments de la VM (Machine Virtuelle) pour ajouter : `--module-path /chemin_vers_javafx-sdk/lib --add-modules=javafx.controls,javafx.fxml`

Selon la configuration du projet, il pourra être nécessaire de dupliquer le fichier fxml; 
ainsi sous *IntelliJ*, il faudra dupliquer le fichier fxml dans le répertoire `out->production->nomProjet->gui`

----

Des explications sur le lien FXML - Java se trouvent [ici](http://emmanuel.adam.free.fr/site/spip.php?article143)

----
