package allumettes;

/** La classe Arbitre gere le deroulement de la partie entre 2 joueurs. */
public class Arbitre {

	private Joueur joueur1;
	private Joueur joueur2;

	/** Creer un arbitre avec les deux joueur.
	 * @param joueur1 le premier joueur
	 * @param joueur2 le deuxieme joueur
	 */
	public Arbitre(Joueur joueur1, Joueur joueur2) {
		this.joueur1 = joueur1;
		this.joueur2 = joueur2;
	}

	/** Lancer et arbitrer la partie.
	 * @param jeu le jeu (plateau) sur lequel on joue
	 */
	public void arbitrer(Jeu jeu) {
		int numTour = 0; // compteur de tours
		Joueur joueurCourant;

		while (jeu.getNombreAllumettes() > 0) {
			// on alterne entre joueur 1 et 2
			if (numTour % 2 == 0) {
				joueurCourant = this.joueur1;
			} else {
				joueurCourant = this.joueur2;
			}

			System.out.println("Allumettes restantes : " + jeu.getNombreAllumettes());

			try {
				int prise = joueurCourant.getPrise(jeu);
				// afficher ce que le joueur a pris (avec le s au pluriel)
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

		// le dernier joueur qui a joué a pris la derniere allumette donc il perd
		if ((numTour - 1) % 2 == 0) {
			System.out.println(this.joueur1.getNom() + " perd !");
			System.out.println(this.joueur2.getNom() + " gagne !");
		} else {
			System.out.println(this.joueur2.getNom() + " perd !");
			System.out.println(this.joueur1.getNom() + " gagne !");
		}
	}

}