package allumettes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/** Tests unitaires des strategies des joueurs. */
public class JoueursTest {

	/** Jeu minimal pour tester les strategies avec un nombre
	 *  d'allumettes arbitraire. */
	private static class JeuFixe implements Jeu {

		private int nb;

		JeuFixe(int nb) {
			this.nb = nb;
		}

		@Override
		public int getNombreAllumettes() {
			return this.nb;
		}

		@Override
		public void retirer(int prise) throws CoupInvalideException {
			if (prise < 1 || prise > this.nb) {
				throw new CoupInvalideException(prise, "invalide");
			}
			this.nb -= prise;
		}
	}

	// --- Noms ---

	@Test
	public void testGetNom() {
		assertEquals("Riri", new JoueurRapide("Riri").getNom());
		assertEquals("Fifi", new JoueurNaif("Fifi").getNom());
		assertEquals("Loulou", new JoueurExpert("Loulou").getNom());
		assertEquals("Picsou", new JoueurTricheur("Picsou").getNom());
	}

	// --- Joueur rapide ---

	@Test
	public void testRapidePrendLeMax() {
		JoueurRapide rapide = new JoueurRapide("Riri");
		assertEquals(Jeu.PRISE_MAX, rapide.getPrise(new JeuFixe(13)));
		assertEquals(Jeu.PRISE_MAX, rapide.getPrise(new JeuFixe(3)));
	}

	@Test
	public void testRapidePrendLeResteSiMoins() {
		JoueurRapide rapide = new JoueurRapide("Riri");
		assertEquals(2, rapide.getPrise(new JeuFixe(2)));
		assertEquals(1, rapide.getPrise(new JeuFixe(1)));
	}

	// --- Joueur naif ---

	@Test
	public void testNaifPriseToujoursValide() {
		JoueurNaif naif = new JoueurNaif("Fifi");
		final int nbTirages = 100;
		for (int i = 0; i < nbTirages; i++) {
			int prise = naif.getPrise(new JeuFixe(13));
			assertTrue("prise >= 1 attendue, obtenu " + prise, prise >= 1);
			assertTrue("prise <= PRISE_MAX attendue, obtenu " + prise,
					prise <= Jeu.PRISE_MAX);
		}
	}

	@Test
	public void testNaifNePrendPasPlusQueRestantes() {
		JoueurNaif naif = new JoueurNaif("Fifi");
		final int nbTirages = 100;
		for (int i = 0; i < nbTirages; i++) {
			int prise = naif.getPrise(new JeuFixe(2));
			assertTrue("prise entre 1 et 2 attendue, obtenu " + prise,
					prise >= 1 && prise <= 2);
		}
		// une seule allumette restante : pas le choix
		assertEquals(1, naif.getPrise(new JeuFixe(1)));
	}

	// --- Joueur expert ---

	@Test
	public void testExpertPositionsGagnantes() {
		JoueurExpert expert = new JoueurExpert("Loulou");
		// il laisse l'adversaire sur un multiple de 4 plus 1 (1, 5, 9)
		assertEquals(3, expert.getPrise(new JeuFixe(12)));
		assertEquals(2, expert.getPrise(new JeuFixe(11)));
		assertEquals(1, expert.getPrise(new JeuFixe(10)));
		assertEquals(3, expert.getPrise(new JeuFixe(8)));
		assertEquals(2, expert.getPrise(new JeuFixe(7)));
		assertEquals(1, expert.getPrise(new JeuFixe(6)));
		assertEquals(3, expert.getPrise(new JeuFixe(4)));
		assertEquals(2, expert.getPrise(new JeuFixe(3)));
		assertEquals(1, expert.getPrise(new JeuFixe(2)));
	}

	@Test
	public void testExpertPositionsPerdantes() {
		JoueurExpert expert = new JoueurExpert("Loulou");
		// sur 13, 9, 5 ou 1 : perdu face a un expert, il prend 1 pour durer
		assertEquals(1, expert.getPrise(new JeuFixe(13)));
		assertEquals(1, expert.getPrise(new JeuFixe(9)));
		assertEquals(1, expert.getPrise(new JeuFixe(5)));
		assertEquals(1, expert.getPrise(new JeuFixe(1)));
	}

	// --- Joueur tricheur ---

	@Test
	public void testTricheurRameneADeuxEtPrendUne() {
		JoueurTricheur tricheur = new JoueurTricheur("Picsou");
		JeuFixe jeu = new JeuFixe(13);
		int prise = tricheur.getPrise(jeu);
		// il a triche pour laisser 2 allumettes...
		assertEquals(2, jeu.getNombreAllumettes());
		// ... puis annonce une prise officielle de 1
		assertEquals(1, prise);
	}

	@Test
	public void testTricheurNeTrichePasSiInutile() {
		JoueurTricheur tricheur = new JoueurTricheur("Picsou");
		JeuFixe jeu = new JeuFixe(2);
		int prise = tricheur.getPrise(jeu);
		// deja 2 restantes : rien a voler, prise normale de 1
		assertEquals(2, jeu.getNombreAllumettes());
		assertEquals(1, prise);
	}

	@Test(expected = OperationInterditeException.class)
	public void testTricheurDemasqueParLaProcuration() {
		JoueurTricheur tricheur = new JoueurTricheur("Picsou");
		Procuration procuration =
				new Procuration(new Plateau(), false, "Picsou");
		tricheur.getPrise(procuration);
	}

}
