package fr.unice.miage.projetpa;

import java.io.File;
import java.util.ArrayList;

public class Launcher {

	public static void main( String[] args ) throws Throwable
    {
    	ArrayList<Robot> list_robot = new ArrayList<Robot>();
    	list_robot.add(new Robot("robot_1", Robot.DepType.ALEATOIRE, Robot.AtkType.COURTE));
    	list_robot.add(new Robot("robot_2", Robot.DepType.ALEATOIRE, Robot.AtkType.LOURDE));
    	list_robot.add(new Robot("robot_3", Robot.DepType.ALEATOIRE, Robot.AtkType.DISTANCE));
    	list_robot.add(new Robot("robot_4", Robot.DepType.ALEATOIRE, Robot.AtkType.COURTE));
    	list_robot.add(new Robot("robot_5", Robot.DepType.ALEATOIRE, Robot.AtkType.LOURDE));
        list_robot.add(new Robot("robot_6", Robot.DepType.ALEATOIRE, null)); //si AtkType = null, le robot charge le premier plugin de type "attaque" qu'il trouve

    	App app = new App(list_robot);
    	app.showFrame();
    	app.start();
    }
	
}
