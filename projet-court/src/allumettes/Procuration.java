package allumettes;

public class Procuration implements Jeu {

	private Plateau jeu;
	private boolean confiant;
	private String nomJoueur;

	/** Creer une procuration pour un joueur.
	 *  @param jeu le jeu reel sous-jacent
	 *  @param confiant true si on accepte la triche, false sinon
	 *  @param nomJoueur le nom du joueur titulaire de la procuration
	 */
	public Procuration(Plateau jeu, boolean confiant, String nomJoueur) {
		this.jeu = jeu;
		this.confiant = confiant;
		this.nomJoueur = nomJoueur;
	}

	@Override
	public int getNombreAllumettes() {
		return this.jeu.getNombreAllumettes();
	}

	@Override
	public void retirer(int nb) throws CoupInvalideException {
		if (!this.confiant) {
			throw new OperationInterditeException(this.nomJoueur);
		}
		this.jeu.voler(nb);
	}

}
