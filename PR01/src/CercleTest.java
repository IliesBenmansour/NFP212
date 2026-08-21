
import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

/**
 * Classe de test de la classe Cercle pour les exigences E12 E13 et E14.
 *
 * @author Ilies Benmansour
 */
public class CercleTest {

	// precision pour les comparaisons reelle
	public static final double EPSILON = 0.001;

	// les points utilises dans les tests
	private Point A, B, C;

	@Before
	public void setUp() {
		A = new Point(1, 1);
		B = new Point(5, 1);
		C = new Point(1, 4);
	}

	/**
	 * Verifier si deux points ont les memes coordonnees.
	 *
	 * @param message le message en cas d erreur
	 * @param p1 le premier point
	 * @param p2 le deuxieme point
	 */
	static void memesCoordonnees(String message, Point p1, Point p2) {
		assertEquals(message + " (x)", p1.getX(), p2.getX(), EPSILON);
		assertEquals(message + " (y)", p1.getY(), p2.getY(), EPSILON);
	}

	// E12 : constructeur a partir de deux points diametralement opposés

	@Test
	public void testerE12Centre() {
		Cercle c = new Cercle(A, B);
		memesCoordonnees("E12 : centre incorrect", new Point(3, 1), c.getCentre());
	}

	@Test
	public void testerE12Rayon() {
		Cercle c = new Cercle(A, B);
		assertEquals("E12 : rayon incorrect", 2, c.getRayon(), EPSILON);
	}

	@Test
	public void testerE12Couleur() {
		Cercle c = new Cercle(A, B);
		assertEquals("E12 : la couleur par defaut doit etre bleu", Color.blue, c.getCouleur());
	}

	@Test
	public void testerE12PointsNonAlignes() {
		// les points ne sont pas sur la meme ligne
		Cercle c = new Cercle(A, C);
		memesCoordonnees("E12 : centre incorrect", new Point(1, 2.5), c.getCentre());
		assertEquals("E12 : rayon incorrect", 1.5, c.getRayon(), EPSILON);
	}

	@Test
	public void testerE12PointsPasPartages() {
		Cercle c = new Cercle(A, B);
		// si on bouge A le cercle ne doit pas bouger
		A.translater(10, 10);
		memesCoordonnees("E12 : le centre a bougé avec A", new Point(3, 1), c.getCentre());
		assertEquals("E12 : le rayon a changé", 2, c.getRayon(), EPSILON);
	}

	// E13 : constructeur a partir de deux points et d une couleur

	@Test
	public void testerE13Centre() {
		Cercle c = new Cercle(A, B, Color.red);
		memesCoordonnees("E13 : centre incorrect", new Point(3, 1), c.getCentre());
	}

	@Test
	public void testerE13Rayon() {
		Cercle c = new Cercle(A, B, Color.red);
		assertEquals("E13 : rayon incorrect", 2, c.getRayon(), EPSILON);
	}

	@Test
	public void testerE13Couleur() {
		Cercle c = new Cercle(A, B, Color.red);
		assertEquals("E13 : couleur incorrecte", Color.red, c.getCouleur());
	}

	@Test
	public void testerE13AutreCouleur() {
		Cercle c = new Cercle(A, C, Color.green);
		assertEquals("E13 : couleur incorrecte", Color.green, c.getCouleur());
		memesCoordonnees("E13 : centre incorrect", new Point(1, 2.5), c.getCentre());
	}

	// E14 : creerCercle a partir du centre et d un point du cercle

	@Test
	public void testerE14Centre() {
		Cercle c = Cercle.creerCercle(A, B);
		memesCoordonnees("E14 : le centre doit etre le premier point", A, c.getCentre());
	}

	@Test
	public void testerE14Rayon() {
		Cercle c = Cercle.creerCercle(A, B);
		assertEquals("E14 : rayon incorrect", 4, c.getRayon(), EPSILON);
	}

	@Test
	public void testerE14Couleur() {
		Cercle c = Cercle.creerCercle(A, B);
		assertEquals("E14 : la couleur par defaut doit etre bleu", Color.blue, c.getCouleur());
	}

	@Test
	public void testerE14PointsNonAlignes() {
		Cercle c = Cercle.creerCercle(B, C);
		memesCoordonnees("E14 : centre incorrect", B, c.getCentre());
		assertEquals("E14 : rayon incorrect", 5, c.getRayon(), EPSILON);
	}

	@Test
	public void testerE14PointsPasPartages() {
		Cercle c = Cercle.creerCercle(A, B);
		A.translater(10, 10);
		memesCoordonnees("E14 : le centre a bougé avec A", new Point(1, 1), c.getCentre());
	}

}
