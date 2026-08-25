package allumettes;

/** Joueur tricheur : retire directement des allumettes via la procuration
 *  pour amener la position a 2 allumettes restantes, puis prend
 *  officiellement une derniere allumette. L'adversaire devra alors prendre
 *  la derniere.
 *  <p>
 *  Si la procuration n'est pas en mode confiant, l'appel a {@code retirer}
 *  leve {@link OperationInterditeException} qui remonte jusqu'au point
 *  d'entree pour abandonner la partie.
 */
public class JoueurTricheur implements Joueur {

	private static final int CIBLE_RESTANTE = 2;

	private String nom;

	public JoueurTricheur(String nom) {
		this.nom = nom;
	}

	@Override
	public String getNom() {
		return this.nom;
	}

	@Override
	public int getPrise(Jeu jeu) {
		System.out.println("[Je triche...]");
		int nb = jeu.getNombreAllumettes();
		if (nb > CIBLE_RESTANTE) {
			try {
				jeu.retirer(nb - CIBLE_RESTANTE);
			} catch (CoupInvalideException e) {
				// ne devrait pas arriver (nb-2 >= 1 et <= nb)
			}
			// Si on arrive ici, la triche a ete acceptee (mode confiant).
			System.out.println("[Allumettes restantes : "
					+ jeu.getNombreAllumettes() + "]");
		}
		return 1;
	}

}
