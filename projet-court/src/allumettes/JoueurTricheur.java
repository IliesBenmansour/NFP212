package allumettes;

/** Joueur tricheur : retire des allumettes via la procuration pour amener
 *  la position a 2 allumettes restantes, puis prend officiellement une
 *  derniere allumette. L'adversaire devra alors prendre la derniere.
 *  <p>
 *  En mode non confiant, le premier appel a retirer leve
 *  {@link OperationInterditeException} qui remonte jusqu'a l'arbitre pour
 *  abandonner la partie. En mode confiant, la triche est appliquee par
 *  multiples retraits dans la limite de {@link Jeu#PRISE_MAX} par appel.
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
		while (jeu.getNombreAllumettes() > CIBLE_RESTANTE) {
			int restant = jeu.getNombreAllumettes() - CIBLE_RESTANTE;
			int prise = Math.min(Jeu.PRISE_MAX, restant);
			try {
				jeu.retirer(prise);
			} catch (CoupInvalideException e) {
				// ne devrait pas arriver (prise <= PRISE_MAX et <= restant)
			}
		}
		System.out.println("[Allumettes restantes : "
				+ jeu.getNombreAllumettes() + "]");
		return 1;
	}

}
