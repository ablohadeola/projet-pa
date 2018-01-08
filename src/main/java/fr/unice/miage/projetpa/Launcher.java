package fr.unice.miage.projetpa;

import java.io.File;
import java.util.ArrayList;

public class Launcher {

	public static void main( String[] args ) throws Throwable
    {
//    	File repository_path = new File("./target/classes");
//    	Repository repository = new Repository(repository_path);
    	ArrayList<Robot> list_robot = new ArrayList<Robot>();
    	list_robot.add(new Robot("robot_1", Robot.DepType.ALEATOIRE, Robot.AtkType.COURTE));
    	//list_robot.add(new Robot("robot_2", Robot.DepType.ALEATOIRE, Robot.AtkType.LOURDE));
    	list_robot.add(new Robot("robot_3", Robot.DepType.ALEATOIRE, Robot.AtkType.DISTANCE));
    	App app = new App(list_robot);
    	app.showFrame();
    	app.start();
    }
	
}
