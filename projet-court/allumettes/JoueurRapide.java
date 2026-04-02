package allumettes;

public class JoueurRapide implements Joueur {

	private String nom;

	public JoueurRapide(String nom) {
		this.nom = nom;
	}

	@Override
	public String getNom() {
		return this.nom;
	}

	@Override
	public int getPrise(Jeu jeu) {
		// strategie rapide : on prend toujour le max possible
		int nbRestantes = jeu.getNombreAllumettes();
		if (nbRestantes >= Jeu.PRISE_MAX) {
			return Jeu.PRISE_MAX;
		} else {
			// si il reste moin que 3 on prend ce qui reste
			return nbRestantes;
		}
	}

}