package fr.unice.miage.projetpa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fr.unice.miage.projetpa.plugins.attaque.AttaqueAbsorbeVie;
import fr.unice.miage.projetpa.plugins.attaque.AttaqueCourte;
import fr.unice.miage.projetpa.plugins.attaque.AttaqueDistance;
import fr.unice.miage.projetpa.plugins.attaque.AttaqueLourde;
import fr.unice.miage.projetpa.plugins.deplacement.RandomMove;
import fr.unice.miage.projetpa.plugins.graphique.RobotColor;

/**
 * Hello world!
 *
 */
public class App {
	
	//Taille de l'arène
	public static int arenaSize = 10;
	
	private JFrame frame;
	private Grille grille;
	private ArrayList<Robot> robots;
	private ArrayList<HUB> hub;
	
	public App(ArrayList<Robot> robots) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		this.robots = robots;
		Object o = OutilReflection.construire(Grille.class);
		grille = (Grille) OutilReflection.invokeMethod(o, "constructor", null);
		grille.setBorder(new EmptyBorder(5, 5, 5, 5)); 
		hub = new ArrayList<HUB>();
	}

	//Affichage de la fenetre, de l'arene et des robots
	public void showFrame() throws Throwable {
		if (frame == null) {
			frame = new JFrame("Robot Plugins War");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setBounds(100, 100, 800, 800);
			JPanel panel = new JPanel();
			panel.setLayout(new BorderLayout());
			
			for (Robot r : robots) {
				instanciateRobots(r);
				hub.add(new HUB(r));
			}
			
			JPanel infosPanel = new JPanel();
			infosPanel.setLayout(new GridLayout(1, robots.size()));
			for(HUB h : hub) {
				infosPanel.add(h);
			}
			
			panel.add(infosPanel, BorderLayout.NORTH);
			panel.add(grille, BorderLayout.CENTER);
			
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
				move(robots.get(robotNumber));
				robots.get(robotNumber).setEnergy(robots.get(robotNumber).getEnergy() - 5);
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
		for(int i=0; i<cells.length; i++) {
			if(i<robots.size()) {
				Robot recepteur = cells[i].getRobot();
				if(!robot.getName().equals(recepteur.getName())) {
					int distance = getDistanceBetween(robot, recepteur);
					if(distance != -1) {
						Object o = OutilReflection.construire(AttaqueCourte.class);
						int robotAtkDistance = (Integer) OutilReflection.invokeMethod(o, "getDistanceAtk", null);
						if(robot.getAtkType() != Robot.AtkType.DISTANCE && distance <= robotAtkDistance) {
							return recepteur;
						} else if(robot.getAtkType() == Robot.AtkType.DISTANCE && distance >= robotAtkDistance) {
							return recepteur;
						}
					}
				}
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
		Object attaque = null;
		boolean hasEnoughtEnergy = true;
		/*Calcul de la distance entre les deux robots
		 * = -1 s'ils sont pas sur une meme ligne ou colonne
		 */
		int distance = getDistanceBetween(attaquant, receveur);
		if(distance != -1) {
			if(attaquant.getAtkType() == Robot.AtkType.COURTE) {
				//Si atk courte & distance entre les robots égal à 1
				Object o = OutilReflection.construire(AttaqueCourte.class);
				int robotAtkDistance = (Integer) OutilReflection.invokeMethod(o, "getDistanceAtk", null);
				if(distance <= robotAtkDistance){
					attaque = OutilReflection.construire(AttaqueCourte.class);
				}
			} else if(attaquant.getAtkType() == Robot.AtkType.LOURDE) {
				//Si atk lourde & distance entre les robots égal à 1
				Object o = OutilReflection.construire(AttaqueLourde.class);
				int robotAtkDistance = (Integer) OutilReflection.invokeMethod(o, "getDistanceAtk", null);
				if(distance <= robotAtkDistance){
					attaque = OutilReflection.construire(AttaqueLourde.class);
				}
			} else if(attaquant.getAtkType() == Robot.AtkType.ABSORBE) {
				attaque = OutilReflection.construire(AttaqueAbsorbeVie.class);
			} else if(attaquant.getAtkType() == Robot.AtkType.DISTANCE) {
				Object o = OutilReflection.construire(AttaqueCourte.class);
				int robotAtkDistance = (Integer) OutilReflection.invokeMethod(o, "getDistanceAtk", null);
				//Si atk a distance & distance entre les robots supérieur à un tier de l'arene
				if(distance >= robotAtkDistance){
					attaque = OutilReflection.construire(AttaqueDistance.class);
				}
			} else if(attaquant.getAtkType() == Robot.AtkType.ABSORBE) {
				if(distance <= 1 && distance != -1){
					attaque = OutilReflection.construire("fr.unice.miage.projetpa.plugins.AttaqueAbsorbeVie");
				}
			}

			if(attaque != null) {
				int energyUse = (Integer) OutilReflection.invokeMethod(attaque, "getEnergyUse", null);
				if(attaquant.getEnergy() > energyUse) {
					hasEnoughtEnergy = (Boolean) OutilReflection.invokeMethod(attaque, "attaque", attaquant, receveur); //return false si pas assez d'energie pour effectuer l'attaque
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
	public void move(Robot robot) throws Throwable {
		int next_move = askNextMove(robot); //Demande au robot la direction de son prochain deplacement
		grille.getCell(robot.getPosX(), robot.getPosY()).setColor(Color.WHITE); //Efface la couleur de la case de l'ancienne position du robot
		grille.getCell(robot.getPosX(), robot.getPosY()).setRobot(null); //Indique a la grille qu'il n'y a plus de robot sur cette case
		//HAUT
		if(next_move == 1) {
			if(robot.getPosY() >= 2 && !grille.getCell(robot.getPosX(), robot.getPosY()-1).hasRobotOnIt()) {
				robot.setPosY(robot.getPosY()-1); 
			} else {
				move(robot);
				return;
			}
		//BAS
		} else if(next_move == 2) {
			if(robot.getPosY() <= App.arenaSize-1 && !grille.getCell(robot.getPosX(), robot.getPosY()+1).hasRobotOnIt()) {
				robot.setPosY(robot.getPosY()+1);
			} else {
				move(robot);
				return;
			}
		//GAUCHE
		} else if(next_move == 3) {
			if(robot.getPosX() >= 2 && !grille.getCell(robot.getPosX()-1, robot.getPosY()).hasRobotOnIt()) {
				robot.setPosX(robot.getPosX()-1);
			} else {
				move(robot);
				return;
			}
		//DROITE
		} else {
			if(robot.getPosX() <= App.arenaSize-1 && !grille.getCell(robot.getPosX()+1, robot.getPosY()).hasRobotOnIt()) {
				robot.setPosX(robot.getPosX()+1);
			} else {
				move(robot);
				return;
			}
		}
		hub.get(robots.indexOf(robot)).update();
		grille.getCell(robot.getPosX(), robot.getPosY()).setRobot(robot); //Indique a la grille qu'il n'y un robot sur cette case
		grille.update(); //Rafraichie l'affichage de la grille
	}
	
	/**
	 * Demande au robot son prochain deplacement a l'aide des plugins
	 */
	public int askNextMove(Robot robot) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		int value = -1;
		if(robot.getDepType().equals(Robot.DepType.ALEATOIRE)) {
			Object moveInstance = OutilReflection.construire(RandomMove.class);
			value = (Integer) OutilReflection.invokeMethod(moveInstance, "nextMove", null);
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
		grille.getCell(robot.getPosX(), robot.getPosY()).setRobot(robot);
		Object robotColor = OutilReflection.construire(RobotColor.class);
		Random rand = new Random();
		float r = rand.nextFloat();
		float g = rand.nextFloat();
		float b = rand.nextFloat();
		OutilReflection.invokeMethod(robotColor, "setColor", robot, new Color(r, g, b));
		grille.update();
	}

}
