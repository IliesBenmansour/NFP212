package allumettes;

/** Le plateau de jeu avec les allumettes. */
public class Plateau implements Jeu {

	// nombre d'allumettes au depart
	public static final int NB_ALLUMETTES_DEPART = 13;

	private int nbAllumettes;

	public Plateau() {
		this.nbAllumettes = NB_ALLUMETTES_DEPART;
	}

	@Override
	public int getNombreAllumettes() {
		return this.nbAllumettes;
	}

	@Override
	public void retirer(int nb) throws CoupInvalideException {
		// verifier que le nb est valide
		if (nb < 1) {
			throw new CoupInvalideException(nb, "< 1");
		}
		if (nb > this.nbAllumettes) {
			throw new CoupInvalideException(nb, "> " + this.nbAllumettes);
		}
		if (nb > PRISE_MAX) {
			throw new CoupInvalideException(nb, "> " + PRISE_MAX);
		}
		this.nbAllumettes = this.nbAllumettes - nb;
	}

}