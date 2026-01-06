import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

/**
 * Programme de test de la classe Point.
 *
 * @author Xavier CrÃ©gut
 * @version 1.11
 */

// Les erreur viens que il faut importer les JUnit Test
public class PointTest {

	public static final double EPSILON = 1e-6;
	// prÃ©cision pour la comparaison entre rÃ©els.

	private Point p1;
	private Point p2;

	@Before
	public void setUp() {
		p1 = new Point(1, 2);
		p2 = new Point(4, -2);
	}

	@Test
	public void testInitialisation() {
		assertTrue(p1 != null);
		assertTrue(p2 != null);
		assertTrue(p1.getX() == 1);
		assertTrue(p1.getY() == 2);
		assertTrue(p2.getX() == 4);
		assertTrue(p2.getY() == -2);
	}

	// L'erreur viens ded cette fonction car on verifie si le point 2 x = 1.0 alors
	// qu'il est = a 4.0
	@Test
	public void testInitialisationMieux() {
		assertNotNull(p1);
		assertNotNull(p2);
		// Remarque : faire un test d'Ã©galitÃ© sur des rÃ©els est Ã Ã©viter
		// Ã cause des erreurs d'arrondi. En consÃ©quence, il faut
		// vÃ©rifier que les deux nombres sont Ã©gaux Ã EPSILON prÃ¨s.
		// C'est ce que fait assertEquals(attendu, rÃ©el, prÃ©cision)
		assertEquals(1.0, p1.getX(), EPSILON);
		assertEquals(2.0, p1.getY(), EPSILON);
		assertEquals(1.0, p2.getX(), EPSILON);
		assertEquals(2.0, p2.getY(), EPSILON);
	}

	@Test
	public void testSetX() {
		p1.setX(10);
		assertEquals(10.0, p1.getX(), EPSILON);
		p1.setX(-5);
		assertEquals(-5.0, p1.getX(), EPSILON);
	}

	@Test
	public void testSetY() {
		p1.setY(10);
		assertEquals(10.0, p1.getY(), EPSILON);
		p1.setY(-5);
		assertEquals(-5.0, p1.getY(), EPSILON);
	}

	@Test
	public void testDistance() {
		assertEquals(0.0, p1.distance(p1), EPSILON);
		assertEquals(0.0, p2.distance(p2), EPSILON);
		assertEquals(5.0, p1.distance(p2), EPSILON);
		assertEquals(5.0, p2.distance(p1), EPSILON);
	}

	@Test
	public void testTranslater1() {
		p1.translater(2, 4);
		assertEquals(3.0, p1.getX(), EPSILON);
		assertEquals(6.0, p1.getY(), EPSILON);
	}

	@Test
	public void testTranslater2() {
		p2.translater(-2, -4);
		assertEquals(2.0, p2.getX(), EPSILON);
		assertEquals(-6.0, p2.getY(), EPSILON);
	}

	@Test
	public void testCouleursPoint() {
		assertEquals(Color.green, p1.getCouleur());
		assertEquals(Color.green, p2.getCouleur());
	}

	@Test
	public void testGetCouleur() {
		p1.setCouleur(Color.black);
		assertEquals(Color.black, p1.getCouleur());
	}

}