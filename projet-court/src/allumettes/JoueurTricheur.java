package allumettes;

/** Joueur tricheur : retire directement des allumettes via la procuration
 *  pour amener la position a 2 allumettes restantes avant de prendre une
 *  derniere allumette officiellement, laissant l'adversaire devoir prendre
 *  la derniere.
 *  <p>
 *  Si la procuration n'est pas en mode confiant, l'appel a {@code retirer}
 *  leve {@link OperationInterditeException} qui remonte jusqu'au point
 *  d'entree (Jouer) pour abandonner la partie.
 */
public class JoueurTricheur implements Joueur {

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
		if (nb > 2) {
			try {
				jeu.retirer(nb - 2);
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
