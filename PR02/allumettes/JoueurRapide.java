package allumettes;

public class JoueurRapide implements Joueur {

	private String nom;

	public JoueurRapide(String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public int getPrise(Jeu jeu) {
		int nb = jeu.getNombreAllumettes();
		if (nb >= 3) {
			return 3;
		} else { //si on depasse le nb max d'allumette (3) on prend le nb restant
			return nb;
		}
	}

}