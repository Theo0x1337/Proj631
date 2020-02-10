
# Table des matières

* [Description du projet](https://github.com/TheoBernardin/Proj631#proj631)
* [Diagramme UML du projet](https://github.com/TheoBernardin/Proj631#utilisation-du-programme)
* [Ajouter la bibliothèque JGraphT avec eclipse](https://github.com/TheoBernardin/Proj631#ajout-de-la-bibliothèque-jgrapht-sous-eclipse)
* [Utilisation du programme](https://github.com/TheoBernardin/Proj631#utilisation-du-programme)
* [Fichier Main](https://github.com/TheoBernardin/Proj631#fichier-main-)


# Proj631


Ce depôt concerne le premier projet d'algorithmique à réaliser en Proj631 
Pour ma part,j'ai choisi de travailler sur le placement de données dans un graphe, en implémentant une structure utilisant des
noeuds ainsi que des arcs, dans le langage Java. 

Pour ce faire j'ai tout d'abord tenté de réaliser le projet "à la main" sans m'aider d'une librairie implémentant divers algorithmes nécessaires au traitement des graphes. Puis je me suis rendu compte que l'utilisation d'une librairie était nécessaire, et j'ai donc opté pour JGraphT.


## Diagramme UML du projet

![](img/diagUML.png)


### Ajout de la bibliothèque JGraphT sous eclipse

Pour tester mon programme il sera nécessaire d'ajouter JGraphT dans vos librairies externes, voici un thread stackoverflow expliquant comment procéder pour le faire : https://stackoverflow.com/questions/3280353/how-to-import-a-jar-in-eclipse

Vous trouverez le .jar de JGraphT dans le repertoire JGraphT

Voici également un lien vers la doc de cette librairie : https://jgrapht.org/guide/UserOverview

## Utilisation du programme 

Dans ce dépôt vous trouverez la javadoc associé aux différentes classes du projet, pour plus de précisions sur les méthodes ainsi que l'utilisation des classes je vous conseille de lire la javadoc disponible depuis le fichier index.html dans le repertoire DOC (il faut cloner le dépôt puis ouvrir le fichier index.html avec un navigateur web, il ne faut pas l'ouvrir depuis le dépôt github).


### Fichier Main : 

Pour tester le projet, vous trouverez un fichier main.java dans le repertoire SRC. Dans ce fichier, il y a un test du placement des données pour un utilisateur ainsi qu'un test pour la placement d'une donnée interessant 2 utilisateurs.

Vous pouvez modifier le fichier main pour tester le programme a votre guise, en créant des graphes et en testant d'inserer des données dans celui-ci. 




