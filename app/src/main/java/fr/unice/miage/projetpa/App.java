package fr.unice.miage.projetpa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fr.plugins.appPlugins.Plugin;

/**
 * Hello world!
 *
 */
public class App {
	
	//Taille de l'arène
	public static int arenaSize = 8;
	
	private JFrame frame;
	private Grille grille;
	private ArrayList<Robot> robots;
	private ArrayList<HUB> hub;
	private JPanel panel = new JPanel();
	MyClassLoader myCl = new MyClassLoader();
	HashMap<String, File> pluginMap = new HashMap<String, File>(); 
	File file = new File("..\\plugins\\target\\classes");
	
	public App(ArrayList<Robot> robots) throws Throwable {
		
		pluginMap = myCl.findAllPlugins(file, pluginMap);
		this.robots = robots;
		grille = new Grille();
		grille.setBorder(new EmptyBorder(5, 5, 5, 5)); 
		
		hub = new ArrayList<HUB>();
		panel.setLayout(new BorderLayout());
		
		for (Robot r : robots) {
			instanciateRobots(r);
			Object o = OutilReflection.construire(HUB.class, r);
			hub.add((HUB) OutilReflection.invokeMethod(o, "constructor", r));
		}
		
		JPanel infosPanel = new JPanel();
		infosPanel.setLayout(new GridLayout(1, robots.size()));
		for(HUB h : hub) {
			infosPanel.add(h);
		}
		
		panel.add(infosPanel, BorderLayout.NORTH);
		panel.add(grille, BorderLayout.CENTER);
	}

	//Affichage de la fenetre, de l'arene et des robots
	public void showFrame() throws Throwable {
		if (frame == null) {
			frame = new JFrame("Robot Plugins War");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setBounds(100, 100, 800, 800);
			frame.setContentPane(panel);
		}
		frame.setVisible(true);
	}
	
	//Demarrage de la bataille
	public void start() throws Throwable {
		int robotNumber = 0; //Permet de switch le tour de jeu (uniquement pour 2 robots pour l'instant)
		while(!isEndGame()) { //Tant que aucun robot n'est mort on continue
			Thread.sleep(100); //Attend 100ms
			if(robots.get(robotNumber).getEnergy() > 5) { //Se deplacer coute 5, donc si energy < 5 on recharge, sinon on se deplace
				move(robots.get(robotNumber), 0);
			} else {
				rechargeEnergy(robots.get(robotNumber));
			}
			Robot recepteur = checkRobotItCanAttack(robots.get(robotNumber));
			if(recepteur != null) {
				if(!attaque(robots.get(robotNumber), recepteur)) { //Si attaque return false c'est que le robot n'a pas assez d'energie pour l'effectuer
					rechargeEnergy(robots.get(0));
				}
				if(recepteur.getLife() <= 0) {
					robots.remove(recepteur);
				}
			}
			if(robotNumber < robots.size()-1) {
				robotNumber++;
			} else {
				robotNumber = 0;
			}
		}
	}
	
	public int getDistanceBetween(Robot r1, Robot r2) {
		int distance = -1;
		if(r1.getPosX() == r2.getPosX()) {
			distance = Math.abs(r1.getPosY() - r2.getPosY());
		} else if(r1.getPosY() == r2.getPosY()) {
			distance = Math.abs(r1.getPosX() - r2.getPosX());
		}
		return distance;
	}
	
	public Robot checkRobotItCanAttack(Robot robot) throws Throwable {
		Cell[] cells = getRobotCellPosition();
		int robotAtkDistance = -1;
		for(int i=0; i<cells.length; i++) {
			if(i<robots.size()) {
				if(cells[i] != null) {
					Robot recepteur = cells[i].getRobot();
					if(!robot.getName().equals(recepteur.getName())) {
						int distance = getDistanceBetween(robot, recepteur);
						if(distance != -1) {
							Class cl = getPluginAttaque(robot);
							if(cl!=null) { 
								Object o = OutilReflection.construire(cl);
								robotAtkDistance = (Integer) OutilReflection.invokeMethod(o, "getDistanceAtk", null);
								if(robot.getAtkType() != Robot.AtkType.DISTANCE && distance <= robotAtkDistance) {
									return recepteur;
								} else if(robot.getAtkType() == Robot.AtkType.DISTANCE && distance >= robotAtkDistance) {
									return recepteur;
								}
							}
						}
					}
				}
			}
		}
		return null;
	}
	
	public boolean pluginMapHasThisTypeOfPlugin(String type) throws ClassNotFoundException, IOException {
		for(File f : pluginMap.values()) {
			Class cl = myCl.loadPluginFromPluginFile(f);
			Plugin p = (Plugin) cl.getAnnotation(Plugin.class);
	        if(p.Type().equals(type)) {
	        	return true;
	        }
		}
		return false;
	}
	
	public Class getFirstPluginOfType(String type) throws ClassNotFoundException, IOException {
		for(File f : pluginMap.values()) {
			Class cl = myCl.loadPluginFromPluginFile(f);
			Plugin p = (Plugin) cl.getAnnotation(Plugin.class);
	        if(p.Type().equals(type)) {
	        	return cl;
	        }
		}
		return null;
	}
	
	public Class getPluginAttaque(Robot robot) throws ClassNotFoundException, IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		boolean find = pluginMapHasThisTypeOfPlugin("attaque");
		if(find) {
			if(robot.getAtkType() == Robot.AtkType.COURTE) {
				for(String pluginName : pluginMap.keySet()) {
					if(pluginName.contains("AttaqueCourte")) {
						return myCl.loadPluginFromPluginFile(pluginMap.get(pluginName));
					}
				}
			} if(robot.getAtkType() == Robot.AtkType.LOURDE) {
				for(String pluginName : pluginMap.keySet()) {
					if(pluginName.contains("AttaqueLourde")) {
						return myCl.loadPluginFromPluginFile(pluginMap.get(pluginName));
					}
				}
			} if(robot.getAtkType() == Robot.AtkType.DISTANCE) {
				for(String pluginName : pluginMap.keySet()) {
					if(pluginName.contains("AttaqueDistance")) {
						return myCl.loadPluginFromPluginFile(pluginMap.get(pluginName));
					}
				}
			}
			Class first = getFirstPluginOfType("attaque");
//				System.out.println(first.getSimpleName());
			return myCl.loadPluginFromPluginFile(pluginMap.get(first.getSimpleName()));
		}
		return null;
	}
	
	public Class getPluginMove(Robot robot) throws ClassNotFoundException, IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		boolean find = pluginMapHasThisTypeOfPlugin("move");
		if(find) {
			if(robot.getDepType() == Robot.DepType.ALEATOIRE) {
				for(String pluginName : pluginMap.keySet()) {
					if(pluginName.contains("RandomMove")) {
						return myCl.loadPluginFromPluginFile(pluginMap.get(pluginName));
					}
				}
			} else if(robot.getDepType() == Robot.DepType.AVANT_ET_ARRIERE) {
				for(String pluginName : pluginMap.keySet()) {
					if(pluginName.contains("AvantEtArriere")) {
						return myCl.loadPluginFromPluginFile(pluginMap.get(pluginName));
					}
				}
			} else {
//				Class first = getFirstPluginOfType("move");
////				System.out.println(first.getSimpleName());
//				return myCl.loadPluginFromPluginFile(pluginMap.get(first.getSimpleName()));
			}
		}
		return null;
	}
	
	public Cell[] getRobotCellPosition() throws Throwable {
		Cell[] cells = new Cell[robots.size()];
		int compteur = 0;
		for (int i = 1; i < App.arenaSize+1; i++) {
			for (int j = 1; j < App.arenaSize+1; j++) {
				if(grille.getCell(i, j).hasRobotOnIt()) {
					cells[compteur] = grille.getCell(i, j);
					compteur++;
				}
			}
		}
		return cells;
	}
	
	/**
	 * Recharge l'energie d'un robot
	 */
	public void rechargeEnergy(Robot robot) {
		System.out.println(robot.getName() + "(" + robot.getEnergy() + ") se recharge\n");
		robot.setEnergy(100);
	}
	
	//TODO Rajouter des attaque à longue distance (distance >= 2)
	/**
	 * Utilise l'attaque du robot attaquant vers le robot receveur
	 */
	public boolean attaque(Robot attaquant, Robot receveur) throws Throwable {
		boolean hasEnoughtEnergy = true;
		/*Calcul de la distance entre les deux robots
		 * = -1 s'ils sont pas sur une meme ligne ou colonne
		 */
		int distance = getDistanceBetween(attaquant, receveur);
		if(distance != -1) {
			Class cl = getPluginAttaque(attaquant);
			Object attaque = OutilReflection.construire(cl);
			if(attaque != null) {
				int energyUse = (Integer) OutilReflection.invokeMethod(attaque, "getEnergyUse", null);
				int distanceAtk = (Integer) OutilReflection.invokeMethod(attaque, "getDistanceAtk", null);
				int degat = (Integer) OutilReflection.invokeMethod(attaque, "getDegat", null);
				System.out.println(attaquant.getName() + " " + cl.getSimpleName() + " " + receveur.getName());
				if(attaquant.getEnergy() > energyUse) {
					attaquant.setEnergy(attaquant.getEnergy() - energyUse);
					receveur.setLife(receveur.getLife() - degat);
					System.out.println("Vie de " + receveur.getName() + " : " + receveur.getLife());
					System.out.println("Energy de " + attaquant.getName() + " : " + attaquant.getEnergy() + "\n");
				} else {
					System.out.println("Energie de " + attaquant.getName() + " est insuffisante !\n"
							+ "Passe son tour pour recharger");
					hasEnoughtEnergy = false;
				}
				hub.get(robots.indexOf(receveur)).update();
				if(receveur.getLife() <= 0) {
					hub.remove(robots.indexOf(receveur));
					robots.remove(receveur);
					grille.getCell(receveur.getPosX(), receveur.getPosY()).setRobot(null);
					grille.getCell(receveur.getPosX(), receveur.getPosY()).setBackground(Color.WHITE);
				}
				hub.get(robots.indexOf(attaquant)).update();
			}
		}
		return hasEnoughtEnergy;
	}
	
	/**
	 * Un robot n'a plus de vie ?
	 */
	public boolean someoneIsDead() {
		for(Robot r : robots) {
			if(r.getLife() <= 0) return true;
		}
		return false;
	}
	
	public boolean isEndGame() {
		int compteur = 0;
		for(Robot r : robots) {
			if(r.getLife() > 0) {
				compteur++;
			}
		}
		if(compteur == 1) {
			return true;
		}
		return false;
	}
	
	/**
	 * Permet a un robot de se deplacer
	 */
	public void move(Robot robot, int awareStackOverFlow) throws Throwable {
		if(!pluginMapHasThisTypeOfPlugin("move")) {
			return;
		}
		awareStackOverFlow++;
		if(awareStackOverFlow > 100) {
			return;
		}
		int next_move = askNextMove(robot); //Demande au robot la direction de son prochain deplacement
		if(next_move!=-1) { robot.setEnergy(robot.getEnergy() - 5);}
		grille.getCell(robot.getPosX(), robot.getPosY()).setColor(Color.WHITE); //Efface la couleur de la case de l'ancienne position du robot
		grille.getCell(robot.getPosX(), robot.getPosY()).setRobot(null); //Indique a la grille qu'il n'y a plus de robot sur cette case
		//HAUT
		if(next_move == 1) {
			if(robot.getPosY() >= 2 && !grille.getCell(robot.getPosX(), robot.getPosY()-1).hasRobotOnIt()) {
				robot.setPosY(robot.getPosY()-1); 
			} else {
				move(robot, awareStackOverFlow);
				return;
			}
		//BAS
		} else if(next_move == 2) {
			if(robot.getPosY() <= App.arenaSize-1 && !grille.getCell(robot.getPosX(), robot.getPosY()+1).hasRobotOnIt()) {
				robot.setPosY(robot.getPosY()+1);
			} else {
				move(robot, awareStackOverFlow);
				return;
			}
		//GAUCHE
		} else if(next_move == 3) {
			if(robot.getPosX() >= 2 && !grille.getCell(robot.getPosX()-1, robot.getPosY()).hasRobotOnIt()) {
				robot.setPosX(robot.getPosX()-1);
			} else {
				move(robot, awareStackOverFlow);
				return;
			}
		//DROITE
		} else if(next_move == 4) {
			if(robot.getPosX() <= App.arenaSize-1 && !grille.getCell(robot.getPosX()+1, robot.getPosY()).hasRobotOnIt()) {
				robot.setPosX(robot.getPosX()+1);
			} else {
				move(robot, awareStackOverFlow);
				return;
			}
		}
		hub.get(robots.indexOf(robot)).update();
		grille.getCell(robot.getPosX(), robot.getPosY()).setRobot(robot); //Indique a la grille qu'il n'y un robot sur cette case
		grille.update(); //Rafraichie l'affichage de la grille
	}
	
	/**
	 * Demande au robot son prochain deplacement a l'aide des plugins
	 * @throws IOException 
	 * @throws IllegalArgumentException 
	 * @throws ClassNotFoundException 
	 */
	public int askNextMove(Robot robot) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException, IllegalArgumentException, IOException {
		int value = -1;
		if(robot.getDepType().equals(Robot.DepType.ALEATOIRE)) {
			if(pluginMap.containsKey("RandomMove")) {
				Class cl = getPluginMove(robot);
				Object moveInstance = OutilReflection.construire(cl);
				value = (Integer) OutilReflection.invokeMethod(moveInstance, "nextMove", null);
			} else {
				return -1;
			}
		} else if(robot.getDepType().equals(Robot.DepType.AVANT_ET_ARRIERE)) {
			Object moveInstance = OutilReflection.construire("fr.unice.miage.projetpa.plugins.deplacement.AvantEtArriereMove");
			value = (Integer) OutilReflection.invokeMethod(moveInstance,"nextMove", robot, new Integer(arenaSize), new Integer(arenaSize)); 
		}
		return value;
	}

	/**
	 * Determine la couleur du robot grace aux plugins
	 */
	private void instanciateRobots(Robot robot) throws Throwable {
		Random rnd = new Random();
		int x = rnd.nextInt(App.arenaSize)+1;
		int y = rnd.nextInt(App.arenaSize)+1;
		if(!grille.getCell(x, y).hasRobotOnIt()) {
			robot.setPosX(x);
			robot.setPosY(y);
			grille.getCell(robot.getPosX(), robot.getPosY()).setRobot(robot);
			if(pluginMapHasThisTypeOfPlugin("robotcolor")) {
				Class cl = myCl.loadPluginFromPluginFile(pluginMap.get("RobotColor"));
				Object robotColor = OutilReflection.construire(cl);
				Color color = (Color) OutilReflection.invokeMethod(robotColor, "getColor", null);
				robot.setColor(color);
			}
			grille.update();
		} else {
			instanciateRobots(robot);
		}
	}

}
