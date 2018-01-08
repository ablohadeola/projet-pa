package fr.unice.miage.projetpa;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Launcher {

	public static void main( String[] args ) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
    	File repository_path = new File("./target/classes");
    	Repository repository = new Repository(repository_path);
    	ArrayList<Robot> list_robot = new ArrayList<Robot>();
    	list_robot.add(new Robot("robot_1", Robot.depType.Aleatoire, Robot.atkType.Courte));
    	list_robot.add(new Robot("robot_2", Robot.depType.Aleatoire, Robot.atkType.Lourde));
    	App app = new App(repository, list_robot);
    	app.showFrame();
    	app.start();
    }
	
}
