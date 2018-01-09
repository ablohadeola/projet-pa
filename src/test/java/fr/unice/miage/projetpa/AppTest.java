package fr.unice.miage.projetpa;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AppTest {
	protected Robot rob1;
	protected Robot rob2;
	protected Robot rob3;
	protected Robot rob4;
	protected App app;
	protected ArrayList listRob;

	@Before
	public void setUp() throws Exception {
		rob1 = new Robot("robot_1", Robot.DepType.ALEATOIRE, Robot.AtkType.COURTE);
		rob2 = new Robot("robot_2", Robot.DepType.ALEATOIRE, Robot.AtkType.LOURDE);
		rob3 = new Robot("robot_3", Robot.DepType.ALEATOIRE, Robot.AtkType.DISTANCE);
		rob4 = new Robot("robot_4", Robot.DepType.ALEATOIRE, Robot.AtkType.ABSORBE);
		listRob = new ArrayList();
		listRob.add(rob1);
		listRob.add(rob2);
		listRob.add(rob3);
		listRob.add(rob4);
		app = new App(listRob);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void rechargeEnergyTest() {
		rob1.setEnergy(30);
		assertEquals(30, rob1.getEnergy());
		app.rechargeEnergy(rob1);
		assertEquals(100, rob1.getEnergy());
	}

	@Test
	public void attaqueTest() throws Throwable {
		app.showFrame();

		// attaque courte sur rob 2
		assertEquals(100, rob2.getLife());
		rob2.setPosX(5);
		rob1.setPosX(4);
		rob2.setPosY(5);
		rob1.setPosY(5);
		app.attaque(rob1, rob2);
		assertEquals(90, rob2.getLife());

		// attaque lourde sur rob 1
		assertEquals(100, rob1.getLife());
		app.attaque(rob2, rob1);
		assertEquals(75, rob1.getLife());

		// attaque distance sur rob 4
		rob4.setPosX(5);
		rob3.setPosX(1);
		rob4.setPosY(5);
		rob3.setPosY(5);
		assertEquals(100, rob4.getLife());
		app.attaque(rob3, rob4);
		assertEquals(98, rob4.getLife());

		// attaque absorbante sur rob 3
		rob3.setPosX(4);
		assertEquals(100, rob3.getLife());
		app.attaque(rob4, rob3);
		assertEquals(85, rob3.getLife());

		// test que le robot a assez d'energie
		assertTrue(app.attaque(rob1, rob2));

	}

	@Test
	public void getDistanceBetweenTest() {
		// test distance = -1 parce que les robots sont pas sur une meme ligne ou
		// colonne
		rob2.setPosX(5);
		rob1.setPosX(4);
		rob2.setPosY(5);
		rob1.setPosY(4);
		assertEquals(-1, app.getDistanceBetween(rob1, rob2));

		// test sinon
		rob2.setPosX(5);
		rob1.setPosX(5);
		rob2.setPosY(5);
		rob1.setPosY(4);
		assertEquals(1, app.getDistanceBetween(rob1, rob2));

		rob3.setPosX(5);
		rob4.setPosX(2);
		rob3.setPosY(5);
		rob4.setPosY(5);
		assertEquals(3, app.getDistanceBetween(rob3, rob4));
	}

	@Test
	public void checkRobotItCanAttackTest() throws Throwable {
		app.showFrame();
		// test si le check robot est égal a null
		rob1.setPosX(1);
		rob1.setPosY(1);
		rob2.setPosX(5);
		rob2.setPosY(1);
		rob3.setPosX(1);
		rob3.setPosY(5);
		rob4.setPosX(5);
		rob4.setPosY(5);
		assertEquals(null, app.checkRobotItCanAttack(rob1));

		rob2.setPosX(2);
		rob2.setPosY(1);
		assertEquals(rob2, app.checkRobotItCanAttack(rob1));

	}

	@Test
	public void someoneIsDeadTest() {
		assertEquals(false, app.someoneIsDead());

		rob2.setLife(0);
		assertTrue(app.someoneIsDead());
	}

	@Test
	public void isEndGameTest() {
		assertEquals(false, app.isEndGame());

		rob1.setLife(0);
		rob2.setLife(0);
		rob4.setLife(0);
		assertTrue(app.isEndGame());
	}

}
