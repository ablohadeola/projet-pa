package fr.unice.miage.projetpa.plugins.attaque;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.unice.miage.projetpa.Robot;

public class AttaqueAbsorbeVieTest {

	protected Robot rob1;
	protected Robot rob2;
	protected AttaqueAbsorbeVie atk;

	@Before
	public void setUp() throws Exception {
		rob1 = new Robot("robot_1", Robot.DepType.ALEATOIRE, Robot.AtkType.ABSORBE);
    	rob2 = new Robot("robot_2", Robot.DepType.ALEATOIRE, Robot.AtkType.ABSORBE);
    	atk = new AttaqueAbsorbeVie();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void attaqueTest() {
		assertEquals(100, rob1.getLife());
		assertEquals(100, rob2.getLife());
		assertEquals(100, rob1.getEnergy());
		assertEquals(100, rob2.getEnergy());
		
		atk.attaque(rob1,rob2);
		
		assertEquals(115, rob1.getLife());
		assertEquals(85, rob2.getLife());
		assertEquals(75, rob1.getEnergy());
		assertEquals(100, rob2.getEnergy());
		
		rob1.setEnergy(2);
		
		//assertEquals(false,atk.attaque(rob1,rob2));
		assertEquals(2, rob1.getEnergy());
	}

}
