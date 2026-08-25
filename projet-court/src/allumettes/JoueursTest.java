package allumettes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class JoueursTest {

	// petit jeu bidon pour tester avec le nombre d'allumettes qu'on veut
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

	@Test
	public void testGetNom() {
		assertEquals("Riri", new Joueur("Riri", new StrategieRapide()).getNom());
	}

	@Test
	public void testGetPriseDelegueALaStrategie() {
		Joueur joueur = new Joueur("Riri", new StrategieRapide());
		assertEquals(Jeu.PRISE_MAX, joueur.getPrise(new JeuFixe(13)));
	}

	@Test
	public void testChangementDynamiqueDeStrategie() {
		// C14 on change la strategie en cours de partie
		Joueur joueur = new Joueur("Riri", new StrategieRapide());
		assertEquals(Jeu.PRISE_MAX, joueur.getPrise(new JeuFixe(13)));

		Strategie experte = new StrategieExperte();
		joueur.setStrategie(experte);
		assertSame(experte, joueur.getStrategie());
		assertEquals(1, joueur.getPrise(new JeuFixe(13)));
		assertEquals("Riri", joueur.getNom());
	}

	@Test
	public void testRapidePrendLeMax() {
		Strategie rapide = new StrategieRapide();
		assertEquals(Jeu.PRISE_MAX, rapide.getPrise(new JeuFixe(13), "Riri"));
		assertEquals(Jeu.PRISE_MAX, rapide.getPrise(new JeuFixe(3), "Riri"));
	}

	@Test
	public void testRapidePrendLeResteSiMoins() {
		Strategie rapide = new StrategieRapide();
		assertEquals(2, rapide.getPrise(new JeuFixe(2), "Riri"));
		assertEquals(1, rapide.getPrise(new JeuFixe(1), "Riri"));
	}

	@Test
	public void testNaivePriseToujoursValide() {
		Strategie naive = new StrategieNaive();
		final int nbTirages = 100;
		for (int i = 0; i < nbTirages; i++) {
			int prise = naive.getPrise(new JeuFixe(13), "Fifi");
			assertTrue("prise trop petite " + prise, prise >= 1);
			assertTrue("prise trop grande " + prise, prise <= Jeu.PRISE_MAX);
		}
	}

	@Test
	public void testNaiveNePrendPasPlusQueRestantes() {
		Strategie naive = new StrategieNaive();
		final int nbTirages = 100;
		for (int i = 0; i < nbTirages; i++) {
			int prise = naive.getPrise(new JeuFixe(2), "Fifi");
			assertTrue("prise hors limites " + prise, prise >= 1 && prise <= 2);
		}
		// une seule restante pas le choix
		assertEquals(1, naive.getPrise(new JeuFixe(1), "Fifi"));
	}

	@Test
	public void testExpertePositionsGagnantes() {
		Strategie experte = new StrategieExperte();
		// elle laisse l'adversaire sur 1 5 ou 9
		assertEquals(3, experte.getPrise(new JeuFixe(12), "Loulou"));
		assertEquals(2, experte.getPrise(new JeuFixe(11), "Loulou"));
		assertEquals(1, experte.getPrise(new JeuFixe(10), "Loulou"));
		assertEquals(3, experte.getPrise(new JeuFixe(8), "Loulou"));
		assertEquals(2, experte.getPrise(new JeuFixe(7), "Loulou"));
		assertEquals(1, experte.getPrise(new JeuFixe(6), "Loulou"));
		assertEquals(3, experte.getPrise(new JeuFixe(4), "Loulou"));
		assertEquals(2, experte.getPrise(new JeuFixe(3), "Loulou"));
		assertEquals(1, experte.getPrise(new JeuFixe(2), "Loulou"));
	}

	@Test
	public void testExpertePositionsPerdantes() {
		Strategie experte = new StrategieExperte();
		// sur 13 9 5 ou 1 c'est perdu elle prend 1 pour durer
		assertEquals(1, experte.getPrise(new JeuFixe(13), "Loulou"));
		assertEquals(1, experte.getPrise(new JeuFixe(9), "Loulou"));
		assertEquals(1, experte.getPrise(new JeuFixe(5), "Loulou"));
		assertEquals(1, experte.getPrise(new JeuFixe(1), "Loulou"));
	}

	@Test
	public void testTricheuseRameneADeuxEtPrendUne() {
		Strategie tricheuse = new StrategieTricheuse();
		JeuFixe jeu = new JeuFixe(13);
		int prise = tricheuse.getPrise(jeu, "Picsou");
		assertEquals(2, jeu.getNombreAllumettes());
		assertEquals(1, prise);
	}

	@Test
	public void testTricheuseNeTrichePasSiInutile() {
		Strategie tricheuse = new StrategieTricheuse();
		JeuFixe jeu = new JeuFixe(2);
		int prise = tricheuse.getPrise(jeu, "Picsou");
		assertEquals(2, jeu.getNombreAllumettes());
		assertEquals(1, prise);
	}

	@Test(expected = OperationInterditeException.class)
	public void testTricheuseDemasqueeParLaProcuration() {
		Joueur tricheur = new Joueur("Picsou", new StrategieTricheuse());
		Procuration procuration =
				new Procuration(new Plateau(), false, "Picsou");
		tricheur.getPrise(procuration);
	}

}
