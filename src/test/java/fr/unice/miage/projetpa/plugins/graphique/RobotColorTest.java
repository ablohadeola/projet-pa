package fr.unice.miage.projetpa.plugins.graphique;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.unice.miage.projetpa.Robot;

public class RobotColorTest {
	protected Robot rob;
	protected Color red;
	protected RobotColor robCol;

	@Before
	public void setUp() throws Exception {
		rob = new Robot("robot_1", Robot.DepType.ALEATOIRE, Robot.AtkType.COURTE);
		red = new Color(255,0,0);
		robCol = new RobotColor();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void setColorTest() {
		robCol.setColor(rob, red);
		assertEquals(Color.red, rob.getColor());
	}

}
