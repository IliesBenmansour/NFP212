package allumettes;

public class Plateau implements Jeu {

	private int nbAllumettes;

	public Plateau() {
		nbAllumettes = 13;
	}

	public int getNombreAllumettes() {
		return nbAllumettes;
	}

	public void retirer(int nb) throws CoupInvalideException {
		if (nb < 1) {
			throw new CoupInvalideException(nb, "< 1");
		}
		if (nb > nbAllumettes) {
			throw new CoupInvalideException(nb, "> " + nbAllumettes);
		}
		if (nb > PRISE_MAX) {
			throw new CoupInvalideException(nb, "> " + PRISE_MAX);
		}
		nbAllumettes = nbAllumettes - nb;
	}

}