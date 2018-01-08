package fr.unice.miage.projetpa;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Cell extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1224449844680443435L;
	private Robot robot = null;
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g); 
		this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
    }
	
	public Color getColor() {
		return this.getBackground();
	}
	
	public void setColor(Color c) {
		this.setBackground(c);
		this.revalidate();
		this.repaint();
	}
	
	public boolean hasRobotOnIt() {
		if(robot != null) {
			this.setColor(robot.getColor());
			return true;
		} return false;
	}

	public Robot getRobot() {
		return robot;
	}

	public void setRobot(Robot robot) {
		this.robot = robot;
	}

}
