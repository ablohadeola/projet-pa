# Programmation Avancée

#Sujet

Le but de ce projet est de construire un jeu de combat virtuel de type RobotWar

Dans une arêne de combat en 2D, vue de dessus, des robots s'affrontent, gérés par une IA relativement basique. Le comportement ainsi que le graphisme des robots est décidé par des **plugins**. Un robot est actif tant que sa vie n'a pas atteint 0 et le gagnant est le dernier robot actif. À chaque robot est associé une quantité d'énergie et chaque action consomme une partie de celle-ci. L'énergie remonte régulièrement tant qu'elle n'a pas atteint la valeur maximale. 

Le mécanisme de plugins devra utiliser les **annotations** comme vu dans le TP4 (pas de classes ou interfaces pour marquer les plugins).
