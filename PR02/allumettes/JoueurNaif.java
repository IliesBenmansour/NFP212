package allumettes;

import java.util.Random;

public class JoueurNaif implements Joueur {

	private String nom;

	public JoueurNaif(String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public int getPrise(Jeu jeu) {
		Random r = new Random();
		int max = jeu.getNombreAllumettes();
		if (max > 3) {
			max = 3;
		}
		return r.nextInt(max) + 1;
	}

}