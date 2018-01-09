package fr.unice.miage.projetpa;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HUB extends JPanel {
	
	Robot robot;
	JLabel name;
	Barre b_vie;
	Barre b_nrj;
	
	public HUB(Robot robot) {
		this.robot = robot;
		this.setLayout(new GridLayout(0, 1));
		name = new JLabel(robot.getName());
		name.setHorizontalAlignment(JLabel.CENTER);
		name.setForeground(robot.getColor());
		name.setBackground(robot.getColor());
		b_vie = new Barre(Color.RED, 100);
		b_nrj = new Barre(Color.BLUE, 100);
		this.add(name);
		this.add(b_vie);
		this.add(b_nrj);
	}
	
	public void update() {
		b_vie.setNumber(robot.getLife());
		b_nrj.setNumber(robot.getEnergy());
		b_vie.update();
		b_nrj.update();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g); 
		name.setForeground(robot.getColor());
		name.setBackground(robot.getColor());
    }
	
	public Color getColor() {
		return this.getBackground();
	}
	
	public void setColor(Color c) {
		this.setBackground(c);
		this.revalidate();
		this.repaint();
	}

	public Barre getB_vie() {
		return b_vie;
	}

	public Barre getB_nrj() {
		return b_nrj;
	}
	
}
