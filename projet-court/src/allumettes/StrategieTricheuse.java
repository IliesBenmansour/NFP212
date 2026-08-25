package allumettes;

public class StrategieTricheuse implements Strategie {

	private static final int CIBLE_RESTANTE = 2;

	@Override
	public int getPrise(Jeu jeu, String nomJoueur) {
		// on triche pour laisser 2 allumettes puis on prend la derniere officiellement
		System.out.println("[Je triche...]");
		int nb = jeu.getNombreAllumettes();
		if (nb > CIBLE_RESTANTE) {
			try {
				jeu.retirer(nb - CIBLE_RESTANTE);
			} catch (CoupInvalideException e) {
				// devrait pas arriver
			}
			// si on arrive ici la triche est passee (mode confiant)
			System.out.println("[Allumettes restantes : "
					+ jeu.getNombreAllumettes() + "]");
		}
		return 1;
	}

}
