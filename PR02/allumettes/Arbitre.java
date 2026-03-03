package allumettes;

public class Arbitre {

	private Joueur j1;
	private Joueur j2;

	public Arbitre(Joueur j1, Joueur j2) {
		this.j1 = j1;
		this.j2 = j2;
	}

	public void arbitrer(Jeu jeu) {
		int tour = 0;
		Joueur joueur;

		while (jeu.getNombreAllumettes() > 0) {
			if (tour % 2 == 0) {
				joueur = j1; // si le tour est pair ces le joueur 1 qui joue
			} else {
				joueur = j2;
			}

			System.out.println("Allumettes restantes : " + jeu.getNombreAllumettes());

			try {
				int prise = joueur.getPrise(jeu);
				System.out.println(joueur.getNom() + " prend " + prise
						+ " allumette" + (prise > 1 ? "s" : "") + "."); // si on prend plus d'une allumette on met un s
				jeu.retirer(prise);
				tour++;
			} catch (CoupInvalideException e) {
				System.out.println("Impossible ! Nombre invalide : "
						+ e.getCoup() + " (" + e.getProbleme() + ")");
			}
			System.out.println();
		}

		if ((tour - 1) % 2 == 0) {
			System.out.println(j1.getNom() + " perd !"); // si le tour est pair ces le joueur 1 qui perd
			System.out.println(j2.getNom() + " gagne !"); // si le tour est impair ces le joueur 2 qui gagne
		} else {
			System.out.println(j2.getNom() + " perd !");
			System.out.println(j1.getNom() + " gagne !");
		}
	}

}