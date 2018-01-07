package fr.unice.miage.projetpa;

import java.io.File;

public class Launcher {

	public static void main( String[] args )
    {
    	File repository_path = new File("./bin");
    	Repository repository = new Repository(repository_path);
    	App app = new App(repository);
    	app.showFrame();
    }
	
}
