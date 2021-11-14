RAPPORT SUR LE DEVOIR


D'abord vous remarquerez qu'il existe trois packages.
 
le package timer comporte les classes qui implementent le timer;
le package clock comporte les classes qui implementent la partie graphique (horloge et boutons);
et le package controller comporte une classe qui implemente l'action de tous les boutons et la classe main.

COMMENT ÇA MARCHER ?

pour compiler:
javac -cp build -d build src/timer/*.java src/clock/*.java src/controller/*.java

où build est le repertoire qui contient les .class; taper la ligne de commande ci-dessus suppose qu'on se trouve dans un repertoire qui contient "build" et "src"

pour exécuter:

java -cp build controleur.Main

ASTUCES APPLIQUÉES POUR L'AFFICHAGE DES CHRONOMETRES:

Dans la classe ClockPanel il y a une variable clock de type boolean qui permet d'afficher l'horloge quand elle est à vrai et de la cacher quand elle est à faux, c'est pareil pour la variable booleenne digit qui est sensé afficher le chronometre numérique.
Pour cacher un chronometre on le dessine avec la couleur d'arrière plan.




