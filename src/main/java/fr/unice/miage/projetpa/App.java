package fr.unice.miage.projetpa;

import java.awt.Color;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import fr.unice.miage.projetpa.plugins.attaque.AttaqueCourte;
import fr.unice.miage.projetpa.plugins.core.Plugin;
import fr.unice.miage.projetpa.plugins.deplacement.RandomMove;
import fr.unice.miage.projetpa.plugins.graphique.RobotColorBlack;
import fr.unice.miage.projetpa.plugins.graphique.RobotColorBlue;
import fr.unice.miage.projetpa.plugins.graphique.RobotColorGreen;
import fr.unice.miage.projetpa.plugins.graphique.RobotColorRed;

/**
 * Hello world!
 *
 */
public class App {

	private Repository repository;
	private JFrame frame;
	private AppPanel contentPane;
	private ArrayList<Robot> robots;
	private Random rnd = new Random();

	public App(Repository repository, ArrayList<Robot> robots) {
		this.repository = repository;
		this.robots = robots;
	}

	public void showFrame() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
		if (frame == null) {
			frame = new JFrame("Robot Plugins War");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setBounds(100, 100, 800, 800);
			for (Robot r : robots) {
				setRobotColor(r);
			}
			contentPane = new AppPanel(robots);
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			frame.setContentPane(contentPane);
		}
		frame.setVisible(true);
	}
	
	public void start() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		Object moveInstance = OutilReflection.construire(RandomMove.class);
		int value = (Integer) OutilReflection.invokeMethod(moveInstance, Plugin.Type.Deplacement, null);
		//TODO Wait grille pour déplacement
//		attaqueRobot(robots.get(0), robots.get(1));
		//TODO @Melvin -> Adapter le code à l'outil reflection
//		Object attaque = OutilReflection.construire(AttaqueCourte.class);
//		OutilReflection.invokeMethod(attaque, Plugin.Type.Deplacement, robots.get(0), robots.get(1));
	}

	private void setRobotColor(Robot robot) {
		List<Class<?>> classes = this.repository.load();
		for (Class<?> classe : classes) {
			// On recupere la liste des methodes definies
			Method[] methods = classe.getDeclaredMethods();
			for (Method m : methods) {
				Parameter[] parameters = m.getParameters();
				int modifiers = m.getModifiers();
				// On verifie si la methode est publique, statique et sans parametres
				if (parameters.length == 0 && Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers)) {
					// Pour chaque methode, on recupere ses annotations
					Annotation[] annotations = m.getAnnotations();
					int memo_random = -1;
					for (Annotation a : annotations) {
						int random_value = rnd.nextInt(4) + 1;
						while (memo_random == random_value) {
							random_value = rnd.nextInt(4) + 1;
						}
						switch (random_value) {
						case 1:
							if (a.annotationType().getSimpleName().equals("RobotColorBlack")) {
								RobotColorBlack robot_color = m.getAnnotation(RobotColorBlack.class);
								int r = robot_color.colorR();
								int g = robot_color.colorG();
								int b = robot_color.colorB();
								robot.setColor(new Color(r, g, b));
							}
							break;
						case 2:
							if (a.annotationType().getSimpleName().equals("RobotColorRed")) {
								RobotColorRed robot_color = m.getAnnotation(RobotColorRed.class);
								int r = robot_color.colorR();
								int g = robot_color.colorG();
								int b = robot_color.colorB();
								robot.setColor(new Color(r, g, b));
							}
							break;
						case 3:
							if (a.annotationType().getSimpleName().equals("RobotColorGreen")) {
								RobotColorGreen robot_color = m.getAnnotation(RobotColorGreen.class);
								int r = robot_color.colorR();
								int g = robot_color.colorG();
								int b = robot_color.colorB();
								robot.setColor(new Color(r, g, b));
							}
							break;
						case 4:
							if (a.annotationType().getSimpleName().equals("RobotColorBlue")) {
								RobotColorBlue robot_color = m.getAnnotation(RobotColorBlue.class);
								int r = robot_color.colorR();
								int g = robot_color.colorG();
								int b = robot_color.colorB();
								robot.setColor(new Color(r, g, b));
							}
							break;
						}
						memo_random = random_value;
					}
				}
			}
		}
	}

	private void attaqueRobot(Robot em, Robot rec) {
		List<Class<?>> classes = this.repository.load();
		for (Class<?> classe : classes) {
			// On recupere la liste des methodes definies
			Method[] methods = classe.getDeclaredMethods();
			for (Method m : methods) {
				Parameter[] parameters = m.getParameters();
				int modifiers = m.getModifiers();
				// On verifie si la methode est publique, statique et sans parametres
				if (parameters.length == 2 && Modifier.isPublic(modifiers)) {
					// Pour chaque methode, on recupere ses annotations
					Annotation[] annotations = m.getAnnotations();
					for (Annotation a : annotations) {
						if (a.annotationType().getSimpleName().equals("Plugin")) {
							Plugin plugin = m.getAnnotation(Plugin.class);
							if (plugin.Type() == Plugin.Type.Attaque) {
								String name = plugin.Nom();
								try {
									Class<?> cl = Class.forName(name);
									Class[] paramTypes = new Class[] { Robot.class, Robot.class };
									Method met;
									try {
										met = cl.getMethod("attaque", paramTypes);
										try {
											try {
												met.invoke(cl.newInstance(), new Robot[] { em, rec });
												return;
											} catch (InstantiationException e) {
												e.printStackTrace();
											}
										} catch (IllegalAccessException e) {
											e.printStackTrace();
										} catch (IllegalArgumentException e) {
											e.printStackTrace();
										} catch (InvocationTargetException e) {
											e.printStackTrace();
										}
									} catch (NoSuchMethodException e) {
										e.printStackTrace();
									} catch (SecurityException e) {
										e.printStackTrace();
									}

								} catch (ClassNotFoundException e) {
									e.printStackTrace();
								}
							}
						}
					}
				}
			}
		}
	}

}
