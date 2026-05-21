package allumettes;

/** Procuration : proxy de {@link Jeu} attribue a chaque joueur.
 *  Le joueur peut consulter le jeu (via getNombreAllumettes) mais une
 *  tentative de retirer des allumettes est consideree comme une tentative
 *  de triche : seul l'arbitre est legitime pour faire des retraits.
 *  <p>
 *  En mode <em>non confiant</em>, une telle tentative leve une
 *  {@link OperationInterditeException}.
 *  En mode <em>confiant</em>, la triche est acceptee et delegue a la
 *  methode retirer du jeu reel (le joueur doit donc respecter
 *  {@link Jeu#PRISE_MAX}, ce qui peut l'obliger a tricher en plusieurs
 *  appels successifs).
 */
public class Procuration implements Jeu {

	private Jeu jeu;
	private boolean confiant;
	private String nomJoueur;

	/** Creer une procuration pour un joueur.
	 *  @param jeu le jeu reel sous-jacent
	 *  @param confiant true si on accepte la triche, false sinon
	 *  @param nomJoueur le nom du joueur titulaire de la procuration
	 */
	public Procuration(Jeu jeu, boolean confiant, String nomJoueur) {
		this.jeu = jeu;
		this.confiant = confiant;
		this.nomJoueur = nomJoueur;
	}

	@Override
	public int getNombreAllumettes() {
		return this.jeu.getNombreAllumettes();
	}

	@Override
	public void retirer(int nb) {
		if (!this.confiant) {
			throw new OperationInterditeException(this.nomJoueur);
		}
		try {
			this.jeu.retirer(nb);
		} catch (CoupInvalideException e) {
			// en mode confiant, on ignore les retraits invalides
		}
	}

}
