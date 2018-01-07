package fr.unice.miage.projetpa;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class AppPanel extends JPanel {
	
	ArrayList<Robot> listRobots;
	
	public AppPanel(ArrayList<Robot> list_robots) {
		this.listRobots = list_robots;
	}
	
	public void paintComponent(Graphics g) {
        for(Robot robot : listRobots) {
            paint(g, robot);
        }
    }

    public void paint(Graphics g, Robot robot)
    {
    	Color c = Color.black;
    	g.drawRect(robot.getPosX(), robot.getPosY(), 50, 50); 
    	c = robot.getColor();
    	g.setColor(c);
    	g.fillRect(robot.getPosX(), robot.getPosY(), 50, 50); 
    }

}
