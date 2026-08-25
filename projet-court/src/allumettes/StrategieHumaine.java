package allumettes;

import java.util.Scanner;

public class StrategieHumaine implements Strategie {

	private Scanner sc; // le scanner partage par toute l'application

	public StrategieHumaine(Scanner sc) {
		this.sc = sc;
	}

	@Override
	public int getPrise(Jeu jeu, String nomJoueur) {
		System.out.print(nomJoueur + ", combien d'allumettes ? ");
		String saisie = this.sc.next();
		if (saisie.equals("triche")) {
			try {
				jeu.retirer(1);
			} catch (CoupInvalideException e) {
				// devrait pas arriver
			}
			System.out.println("[Une allumette en moins, plus que "
					+ jeu.getNombreAllumettes() + ". Chut !]");
			return getPrise(jeu, nomJoueur);
		}
		try {
			return Integer.parseInt(saisie);
		} catch (NumberFormatException e) {
			// cas ou l'utilisateur rentre pas un nombre
			System.out.println("Vous devez donner un entier.");
			return getPrise(jeu, nomJoueur);
		}
	}

}
