package allumettes;

import java.util.Scanner;

/** Joueur humain : lit ses prises sur l'entree standard
 *  Le joueur peut tricher en tapant le mot triche
 */
public class JoueurHumain implements Joueur {

	private String nom;
	private Scanner sc;

	/** Constructeur du joueur humain.
	 *  @param nom le nom du joueur
	 *  @param sc le scanner partage par toute l'application
	 */
	public JoueurHumain(String nom, Scanner sc) {
		this.nom = nom;
		this.sc = sc;
	}

	@Override
	public String getNom() {
		return this.nom;
	}

	@Override
	public int getPrise(Jeu jeu) {
		System.out.print(this.nom + ", combien d'allumettes ? ");
		String saisie = this.sc.next();
		if (saisie.equals("triche")) {
			try {
				jeu.retirer(1);
			} catch (CoupInvalideException e) {
				// ne devrait pas arriver tant qu'il reste >= 1 allumette
			}
			System.out.println("[Une allumette en moins, plus que "
					+ jeu.getNombreAllumettes() + ". Chut !]");
			return getPrise(jeu);
		}
		try {
			return Integer.parseInt(saisie);
		} catch (NumberFormatException e) {
			System.out.println("Vous devez donner un entier.");
			return getPrise(jeu);
		}
	}

}
