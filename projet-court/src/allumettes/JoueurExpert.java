package allumettes;

/** Joueur expert : applique la strategie gagnante du jeu des 13 allumettes.
 *  Les positions perdantes pour celui qui doit jouer sont les multiples
 *  de 4 plus 1 (1, 5, 9, 13)
 */
public class JoueurExpert implements Joueur {

	private String nom;

	public JoueurExpert(String nom) {
		this.nom = nom;
	}

	@Override
	public String getNom() {
		return this.nom;
	}

	@Override
	public int getPrise(Jeu jeu) {
		int n = jeu.getNombreAllumettes();
		int reste = (n - 1) % 4;
		if (reste == 0) {
			// position perdante : on ne peut que retarder
			return 1;
		}
		return reste;
	}

}
