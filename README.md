# Programmation Avancée #

#Sujet

Le but de ce projet est de construire un jeu de combat virtuel de type RobotWar

Dans une arêne de combat en 2D, vue de dessus, des robots s'affrontent, gérés par une IA relativement basique. Le comportement ainsi que le graphisme des robots est décidé par des **plugins**. Un robot est actif tant que sa vie n'a pas atteint 0 et le gagnant est le dernier robot actif. À chaque robot est associé une quantité d'énergie et chaque action consomme une partie de celle-ci. L'énergie remonte régulièrement tant qu'elle n'a pas atteint la valeur maximale. 

Le mécanisme de plugins devra utiliser les **annotations** comme vu dans le TP4 (pas de classes ou interfaces pour marquer les plugins).

#Plugins graphisme

Ces plugins servent à la représentation graphique des robots et de leurs armes. Le jeu devra contenir de base un plugin qui représente les robots par un carré de couleur alératoire. D'autres plugins possibles sont par exemple une animation, la visualisation des barres de vie et d'énergie...

Ces plugins ne consomment pas d'énergie. 

#Plugins de déplacement

Ces plugins servent à décider du déplacement du robot. Le plugin le plus simple consiste en un déplacement aléatoire mais d'autres plus compliqués peuvent être implémentés. Par exemple dans le cas d'un robot avec une arme de courte portée, un déplacement vers un autre robot sera utile. On peut aussi avoir un déplacement dont la vitesse dépend de la barre de vie ou de la quantité d'énergie restante. 

Ces plugins consomment de l'énergie. 

#Plugins d'attaque

Ces plugins servent à décider attaquer les autres robots et à diminuer leur barre de vie. Chaque attaque sera définie par une portée et un graphisme. De base un plugin implémentant une attaque de courte portée sera proposé.  

Ces plugins consomment de l'énergie. 

#Remarques

Vous pouvez supposer que les robots ont 100 d'énergie à chaque tour et activer les plugins jusqu'à consommer ces points. Si il n'y a plus de points, alors un plugin ne fera aucune action. 

Ne perdez pas de vue que l'objectif est de faire une architecture à plugin, donc ne passez pas trop de temps à rendre votre jeu intéressant/amusant, ce n'est pas le but. Il faut par contre avoir un nombre significatif de plugins. Des plugins complexes (nécessitant par exemple une interaction avec d'autres plugins) rapporteront plus de points.