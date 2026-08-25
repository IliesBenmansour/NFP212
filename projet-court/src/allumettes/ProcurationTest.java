package allumettes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class ProcurationTest {

	private Plateau plateau;

	@Before
	public void setUp() {
		this.plateau = new Plateau();
	}

	@Test
	public void testConsultationDeleguee() {
		Procuration procuration = new Procuration(plateau, false, "Lea");
		assertEquals(plateau.getNombreAllumettes(),
				procuration.getNombreAllumettes());
	}

	@Test
	public void testConsultationSuitLeJeuReel() throws CoupInvalideException {
		Procuration procuration = new Procuration(plateau, false, "Lea");
		plateau.retirer(3);
		assertEquals(10, procuration.getNombreAllumettes());
	}

	@Test
	public void testRetirerInterditSiNonConfiant() {
		Procuration procuration = new Procuration(plateau, false, "Lea");
		try {
			procuration.retirer(1);
			fail("la triche aurait du etre bloquee");
		} catch (OperationInterditeException e) {
			// le nom du tricheur est dans le message
			assertEquals("Lea", e.getMessage());
		} catch (CoupInvalideException e) {
			fail("mauvaise exception " + e);
		}
		// le jeu reel a pas bouge
		assertEquals(Plateau.NB_ALLUMETTES_DEPART,
				plateau.getNombreAllumettes());
	}

	@Test
	public void testRetirerAccepteSiConfiant() throws CoupInvalideException {
		Procuration procuration = new Procuration(plateau, true, "Max");
		// en mode confiant la triche passe meme au dela de la prise max
		procuration.retirer(11);
		assertEquals(2, plateau.getNombreAllumettes());
	}

	@Test(expected = CoupInvalideException.class)
	public void testRetirerConfiantResteValide() throws CoupInvalideException {
		// meme confiant on peut pas prendre plus que ce qui reste
		Procuration procuration = new Procuration(plateau, true, "Max");
		procuration.retirer(Plateau.NB_ALLUMETTES_DEPART + 1);
	}

}
