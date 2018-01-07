package fr.unice.miage.projetpa;

import java.io.File;
import java.util.ArrayList;

public class Launcher {

	public static void main( String[] args )
    {
    	File repository_path = new File("./target/classes");
    	Repository repository = new Repository(repository_path);
    	ArrayList<Robot> list_robot = new ArrayList<Robot>();
    	list_robot.add(new Robot("robot_1"));
    	list_robot.add(new Robot("robot_2"));
    	App app = new App(repository, list_robot);
    	app.showFrame();
    }
	
}
