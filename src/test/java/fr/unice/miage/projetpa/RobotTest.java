package fr.unice.miage.projetpa;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.unice.miage.projetpa.Robot.AtkType;
import fr.unice.miage.projetpa.Robot.DepType;

public class RobotTest {
	protected Robot rob1;
	protected Robot rob2;
	protected Robot rob3;
	protected Robot rob4;

	@Before
	public void setUp() throws Exception {
		rob1 = new Robot("robot_1", Robot.DepType.ALEATOIRE, Robot.AtkType.COURTE);
    	rob2 = new Robot("robot_2", Robot.DepType.INTELLIGENT, Robot.AtkType.LOURDE);
    	rob3 = new Robot("robot_3", Robot.DepType.ALEATOIRE, Robot.AtkType.DISTANCE);
    	rob4 = new Robot("robot_4", Robot.DepType.INTELLIGENT, Robot.AtkType.ABSORBE);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void getLifeTest() {
		assertEquals(100, rob1.getLife());
		assertEquals(100, rob2.getLife());
		assertEquals(100, rob3.getLife());
		assertEquals(100, rob4.getLife());
	}

	@Test
	public void getNameTest() {
		assertEquals("robot_1", rob1.getName());
		assertEquals("robot_2", rob2.getName());
		assertEquals("robot_3", rob3.getName());
		assertEquals("robot_4", rob4.getName());
	}
	
	@Test
	public void getEnergyTest() {
		assertEquals(100, rob1.getEnergy());
		assertEquals(100, rob2.getEnergy());
		assertEquals(100, rob3.getEnergy());
		assertEquals(100, rob4.getEnergy());
	}

	@Test
	public void getDepTypeTest() {
		assertEquals(Robot.DepType.ALEATOIRE, rob1.getDepType());
		assertEquals(Robot.DepType.INTELLIGENT, rob2.getDepType());
		assertEquals(Robot.DepType.ALEATOIRE, rob3.getDepType());
		assertEquals(Robot.DepType.INTELLIGENT, rob4.getDepType());
	}
	
	@Test
	public void getArkTypeTest() {
		assertEquals(Robot.AtkType.COURTE, rob1.getAtkType());
		assertEquals(Robot.AtkType.LOURDE, rob2.getAtkType());
		assertEquals(Robot.AtkType.DISTANCE, rob3.getAtkType());
		assertEquals(Robot.AtkType.ABSORBE, rob4.getAtkType());
	}


}
