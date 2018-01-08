package fr.unice.miage.projetpa;

import java.awt.Color;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import fr.unice.miage.projetpa.plugins.attaque.AttaqueCourte;
import fr.unice.miage.projetpa.plugins.attaque.AttaqueLourde;
import fr.unice.miage.projetpa.plugins.deplacement.RandomMove;
import fr.unice.miage.projetpa.plugins.graphique.RobotColor;

/**
 * Hello world!
 *
 */
public class App {

	public static int arenaSize = 10;
	
	private JFrame frame;
	private Grille grille;
	private ArrayList<Robot> robots;
	
	public App(Repository repository, ArrayList<Robot> robots) {
		this.robots = robots;
	}

	public void showFrame() throws Throwable {
		if (frame == null) {
			frame = new JFrame("Robot Plugins War");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setBounds(100, 100, 800, 800);
			grille = new Grille(arenaSize, arenaSize);
			grille.setBorder(new EmptyBorder(5, 5, 5, 5));
			for (Robot r : robots) {
				instanciateRobots(r);
			}
			frame.setContentPane(grille);
		}
		frame.setVisible(true);
	}
	
	public void start() throws Throwable {
		int robotNumber = 0;
		while(!someoneIsDead()) {
			Thread.sleep(100);
			if(robots.get(robotNumber).getEnergy() > 5) {
				move(robots.get(robotNumber));
				robots.get(robotNumber).setEnergy(robots.get(robotNumber).getEnergy() - 5);
			} else {
				rechargeEnergy(robots.get(robotNumber));
			}
			if(robotNumber == 0) { 
				if(!attaque(robots.get(0), robots.get(1))) {
					rechargeEnergy(robots.get(0));
				}
			} else {
				if(!attaque(robots.get(1), robots.get(0))) {
					rechargeEnergy(robots.get(1));
				}
			}
			if(robotNumber == 0) { 
				robotNumber++;
			} else {
				robotNumber--;
			}
		}
	}
	
	public void rechargeEnergy(Robot robot) {
		System.out.println(robot.getName() + "(" + robot.getEnergy() + ") se recharge\n");
		robot.setEnergy(100);
	}
	
	//TODO Rajouter des attaque à longue distance (distance >= 2)
	public boolean attaque(Robot attaquant, Robot receveur) throws Throwable {
		Object attaque = null;
		boolean hasEnoughtEnergy = true;
		int distance = grille.getDistance(grille.getCell(attaquant.getPosX(), attaquant.getPosY()), grille.getCell(receveur.getPosX(), receveur.getPosY()));
		if(attaquant.getAtkType() == Robot.AtkType.COURTE) {
			//Si atk courte & distance entre les robots égal à 1
			if(distance <= 1 && distance != -1){
				attaque = OutilReflection.construire(AttaqueCourte.class);
			}
		} else if(attaquant.getAtkType() == Robot.AtkType.LOURDE) {
			//Si atk lourde & distance entre les robots égal à 1
			if(distance <= 1 && distance != -1){
				attaque = OutilReflection.construire(AttaqueLourde.class);
			}
		} else if(attaquant.getAtkType() == Robot.AtkType.ABSORBE) {
			attaque = OutilReflection.construire("fr.unice.miage.projetpa.plugins.AttaqueAbsorbeVie");
		}
		if(distance != -1 && attaque != null) {
			int energyUse = (Integer) OutilReflection.invokeMethod(attaque, "getEnergyUse", null);
			if(attaquant.getEnergy() > energyUse) {
				hasEnoughtEnergy = (Boolean) OutilReflection.invokeMethod(attaque, "attaque", attaquant, receveur);
			}
		}
		return hasEnoughtEnergy;
	}
	
	public boolean someoneIsDead() {
		for(Robot r : robots) {
			if(r.getLife() <= 0) return true;
		}
		return false;
	}
	
	public void move(Robot robot) throws Throwable {
		int next_move = askNextMove(robot);
		grille.getCell(robot.getPosX(), robot.getPosY()).setColor(Color.WHITE);
		grille.getCell(robot.getPosX(), robot.getPosY()).setRobot(null);
		//HAUT
		if(next_move == 1) {
			if(robot.getPosY() >= 2) {
				robot.setPosY(robot.getPosY()-1);
			} else {
				move(robot);
				return;
			}
		//BAS
		} else if(next_move == 2) {
			if(robot.getPosY() <= App.arenaSize-1) {
				robot.setPosY(robot.getPosY()+1);
			} else {
				move(robot);
				return;
			}
		//GAUCHE
		} else if(next_move == 3) {
			if(robot.getPosX() >= 2) {
				robot.setPosX(robot.getPosX()-1);
			} else {
				move(robot);
				return;
			}
		//DROITE
		} else {
			if(robot.getPosX() <= App.arenaSize-1) {
				robot.setPosX(robot.getPosX()+1);
			} else {
				move(robot);
				return;
			}
		}
		grille.getCell(robot.getPosX(), robot.getPosY()).setRobot(robot);
		grille.update();
	}
	
	public int askNextMove(Robot robot) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		int value = -1;
		if(robot.getDepType().equals(Robot.DepType.ALEATOIRE)) {
			Object moveInstance = OutilReflection.construire(RandomMove.class);
			value = (Integer) OutilReflection.invokeMethod(moveInstance, "nextMove", null);
		}
		return value;
	}

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
