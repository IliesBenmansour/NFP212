package allumettes;

/** La classe Arbitre gere le deroulement de la partie entre 2 joueurs.
 *  Chaque joueur joue via une {@link Procuration} qui detecte (et,
 *  selon le mode, autorise ou interdit) les tentatives de triche.
 */
public class Arbitre {

	private Joueur joueur1;
	private Joueur joueur2;
	private boolean confiant;

	/** Creer un arbitre avec les deux joueurs.
	 *  @param joueur1 le premier joueur
	 *  @param joueur2 le deuxieme joueur
	 *  @param confiant true si la triche est acceptee, false si elle
	 *      provoque l'abandon de la partie
	 */
	public Arbitre(Joueur joueur1, Joueur joueur2, boolean confiant) {
		this.joueur1 = joueur1;
		this.joueur2 = joueur2;
		this.confiant = confiant;
	}

	/** Lancer et arbitrer la partie.
	 *  @param jeu le jeu reel sur lequel on joue (modifie au fil de la partie)
	 */
	public void arbitrer(Jeu jeu) {
		Plateau plateau = (Plateau) jeu;
		Procuration proc1 = new Procuration(plateau, this.confiant,
				this.joueur1.getNom());
		Procuration proc2 = new Procuration(plateau, this.confiant,
				this.joueur2.getNom());

		int numTour = 0;
		Joueur joueurCourant;
		Procuration procCourante;

		try {
			while (jeu.getNombreAllumettes() > 0) {
				if (numTour % 2 == 0) {
					joueurCourant = this.joueur1;
					procCourante = proc1;
				} else {
					joueurCourant = this.joueur2;
					procCourante = proc2;
				}

				System.out.println("Allumettes restantes : "
						+ jeu.getNombreAllumettes());

				try {
					int prise = joueurCourant.getPrise(procCourante);
					String s = (prise > 1) ? "s" : "";
					System.out.println(joueurCourant.getNom() + " prend " + prise
							+ " allumette" + s + ".");
					jeu.retirer(prise);
					numTour++;
				} catch (CoupInvalideException e) {
					System.out.println("Impossible ! Nombre invalide : "
							+ e.getCoup() + " (" + e.getProbleme() + ")");
				}
				System.out.println();
			}

			// le dernier joueur qui a joue a pris la derniere allumette donc il perd
			if ((numTour - 1) % 2 == 0) {
				System.out.println(this.joueur1.getNom() + " perd !");
				System.out.println(this.joueur2.getNom() + " gagne !");
			} else {
				System.out.println(this.joueur2.getNom() + " perd !");
				System.out.println(this.joueur1.getNom() + " gagne !");
			}
		} catch (OperationInterditeException e) {
			System.out.println("Abandon de la partie car "
					+ e.getMessage() + " triche !");
		}
	}

}
