package fr.unice.miage.projetpa.plugins.deplacement;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RandomMoveTest {
	protected RandomMove rndM;
	protected ArrayList nb;

	@Before
	public void setUp() throws Exception {
		rndM = new RandomMove();
		nb = new ArrayList();
		nb.add(1);
		nb.add(2);
		nb.add(3);
		nb.add(4);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void nextMoveTest() {
		assertTrue(nb.contains(rndM.nextMove()));
		
	}

}
