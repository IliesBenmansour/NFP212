package allumettes;

import java.util.Random;

public class JoueurNaif implements Joueur {

	private String nom;
	private Random random;

	public JoueurNaif(String nom) {
		this.nom = nom;
		this.random = new Random();
	}

	@Override
	public String getNom() {
		return this.nom;
	}

	@Override
	public int getPrise(Jeu jeu) {
		// on prend un nombre aleatoir entre 1 et 3 (ou moin si il reste pas assez)
		int nbRestantes = jeu.getNombreAllumettes();
		int max = Jeu.PRISE_MAX;
		if (nbRestantes < max) {
			max = nbRestantes;
		}
		int prise = this.random.nextInt(max) + 1;
		return prise;
	}

}