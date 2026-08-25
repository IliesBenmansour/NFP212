package allumettes;

/** Le plateau de jeu avec les allumettes : detient l'etat reel. */
public class Plateau implements Jeu {

	public static final int NB_ALLUMETTES_DEPART = 13;

	private int nbAllumettes;

	public Plateau() {
		this(NB_ALLUMETTES_DEPART);
	}

	// C9 on peut demarrer avec un nombre d'allumettes quelconque
	public Plateau(int nbAllumettes) {
		this.nbAllumettes = nbAllumettes;
	}

	@Override
	public int getNombreAllumettes() {
		return this.nbAllumettes;
	}

	@Override
	public void retirer(int nb) throws CoupInvalideException {
		if (nb < 1) {
			throw new CoupInvalideException(nb, "< 1");
		}
		if (nb > this.nbAllumettes) {
			throw new CoupInvalideException(nb, "> " + this.nbAllumettes);
		}
		if (nb > PRISE_MAX) {
			throw new CoupInvalideException(nb, "> " + PRISE_MAX);
		}
		this.nbAllumettes -= nb;
	}

	/** Voler des allumettes : retire {@code nb} allumettes sans verifier
	 *  {@link Jeu#PRISE_MAX}. Reservee a la Procuration en mode confiant.
	 *  @param nb nombre d'allumettes a voler (1 a nbAllumettes)
	 *  @throws CoupInvalideException si nb &lt; 1 ou nb &gt; nbAllumettes
	 */
	void voler(int nb) throws CoupInvalideException {
		if (nb < 1) {
			throw new CoupInvalideException(nb, "< 1");
		}
		if (nb > this.nbAllumettes) {
			throw new CoupInvalideException(nb, "> " + this.nbAllumettes);
		}
		this.nbAllumettes -= nb;
	}

}
