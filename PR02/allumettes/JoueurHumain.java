package allumettes;

import java.util.Scanner;

public class JoueurHumain implements Joueur {

	private String nom;
	private Scanner sc; // le scanner pour lire les entrees

	/** Constructeur du joueur humain.
	 * @param nom le nom du joueur
	 * @param sc le scanner
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
		String saisie = sc.next();
		try {
			int nbAllu = Integer.parseInt(saisie);
			return nbAllu;
		} catch (NumberFormatException e) {
			// cas ou l'utilisateur rentre pas un nombre
			System.out.println("Vous devez donner un entier.");
			return getPrise(jeu); // on redemande
		}
	}

}