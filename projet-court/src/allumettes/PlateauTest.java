package allumettes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

/** Tests unitaires de la classe Plateau. */
public class PlateauTest {

	private Plateau plateau;

	@Before
	public void setUp() {
		this.plateau = new Plateau();
	}

	@Test
	public void testEtatInitial() {
		assertEquals(Plateau.NB_ALLUMETTES_DEPART,
				plateau.getNombreAllumettes());
	}

	@Test
	public void testRetirerValide() throws CoupInvalideException {
		plateau.retirer(2);
		assertEquals(11, plateau.getNombreAllumettes());
		plateau.retirer(Jeu.PRISE_MAX);
		assertEquals(8, plateau.getNombreAllumettes());
		plateau.retirer(1);
		assertEquals(7, plateau.getNombreAllumettes());
	}

	@Test
	public void testRetirerZeroInterdit() {
		try {
			plateau.retirer(0);
			fail("CoupInvalideException attendue pour 0");
		} catch (CoupInvalideException e) {
			assertEquals(0, e.getCoup());
			assertEquals("< 1", e.getProbleme());
		}
		// le plateau n'a pas change
		assertEquals(Plateau.NB_ALLUMETTES_DEPART,
				plateau.getNombreAllumettes());
	}

	@Test
	public void testRetirerNegatifInterdit() {
		try {
			plateau.retirer(-1);
			fail("CoupInvalideException attendue pour -1");
		} catch (CoupInvalideException e) {
			assertEquals(-1, e.getCoup());
			assertEquals("< 1", e.getProbleme());
		}
	}

	@Test
	public void testRetirerPlusQuePriseMax() {
		try {
			plateau.retirer(Jeu.PRISE_MAX + 1);
			fail("CoupInvalideException attendue pour PRISE_MAX + 1");
		} catch (CoupInvalideException e) {
			assertEquals(Jeu.PRISE_MAX + 1, e.getCoup());
			assertEquals("> " + Jeu.PRISE_MAX, e.getProbleme());
		}
	}

	@Test
	public void testRetirerPlusQueRestantes() throws CoupInvalideException {
		// on descend a 1 allumette
		final int prisesDeTrois = 4;
		for (int i = 0; i < prisesDeTrois; i++) {
			plateau.retirer(Jeu.PRISE_MAX);
		}
		assertEquals(1, plateau.getNombreAllumettes());
		try {
			plateau.retirer(2);
			fail("CoupInvalideException attendue : plus que 1 allumette");
		} catch (CoupInvalideException e) {
			assertEquals(2, e.getCoup());
			assertEquals("> 1", e.getProbleme());
		}
	}

	@Test
	public void testVolerIgnorePriseMax() throws CoupInvalideException {
		// voler permet de depasser PRISE_MAX (utilise en mode confiant)
		plateau.voler(11);
		assertEquals(2, plateau.getNombreAllumettes());
	}

	@Test(expected = CoupInvalideException.class)
	public void testVolerPlusQueRestantes() throws CoupInvalideException {
		plateau.voler(Plateau.NB_ALLUMETTES_DEPART + 1);
	}

	@Test(expected = CoupInvalideException.class)
	public void testVolerZero() throws CoupInvalideException {
		plateau.voler(0);
	}

}
