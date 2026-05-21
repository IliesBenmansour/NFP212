package allumettes;

/** Procuration : proxy de {@link Jeu} attribue a chaque joueur. Le joueur
 *  ne peut consulter le jeu (via getNombreAllumettes) mais une tentative
 *  de retirer des allumettes est consideree comme une tentative de triche
 *  car seul l'arbitre est legitime pour faire des retraits officiels.
 *  <p>
 *  En mode <em>non confiant</em>, une telle tentative leve une
 *  {@link OperationInterditeException} (la partie est abandonnee).
 *  En mode <em>confiant</em>, la triche est acceptee et appliquee
 *  directement sur le {@link Plateau} sous-jacent via la methode
 *  {@link Plateau#voler(int)}, ce qui permet de retirer plus de
 *  {@link Jeu#PRISE_MAX} allumettes.
 */
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
