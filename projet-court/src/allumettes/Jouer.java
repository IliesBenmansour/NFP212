package allumettes;

import java.util.Scanner;

/** Lance une partie des 13 allumettes en fonction des arguments fournis
 *  sur la ligne de commande.
 *  Le premier argument peut etre {@code -confiant} pour accepter la triche
 *  (sinon toute tentative de triche provoque l'abandon de la partie).
 *  @author	Xavier Cregut
 *  @version	$Revision: 1.6 $
 */
public class Jouer {

	/** Lancer une partie. En argument sont donnes les deux joueurs sous
	 *  la forme nom@strategie, eventuellement precedes de {@code -confiant}.
	 *  @param args la description des deux joueurs
	 */
	public static void main(String[] args) {
		try {
			boolean confiant = false;
			int debut = 0;
			if (args.length > 0 && args[0].equals("-confiant")) {
				confiant = true;
				debut = 1;
			}

			verifierNombreArguments(args, debut);

			Scanner scanner = new Scanner(System.in);

			String[] j1 = args[debut].split("@");
			String[] j2 = args[debut + 1].split("@");

			Joueur joueur1 = creerJoueur(j1[0], j1[1], scanner);
			Joueur joueur2 = creerJoueur(j2[0], j2[1], scanner);

			Plateau jeu = new Plateau();
			Arbitre arbitre = new Arbitre(joueur1, joueur2, confiant);
			arbitre.arbitrer(jeu);

		} catch (ConfigurationException e) {
			System.out.println();
			System.out.println("Erreur : " + e.getMessage());
			afficherUsage();
			System.exit(1);
		}
	}

	/** Creer le Joueur correspondant a une strategie.
	 *  @param nom le nom du joueur
	 *  @param strategie la strategie (naif, rapide, humain, expert, tricheur)
	 *  @param scanner le scanner partage (pour le joueur humain)
	 *  @return le joueur cree
	 *  @throws ConfigurationException si la strategie est inconnue
	 */
	private static Joueur creerJoueur(String nom, String strategie,
			Scanner scanner) {
		if (strategie.equals("naif")) {
			return new JoueurNaif(nom);
		}
		if (strategie.equals("rapide")) {
			return new JoueurRapide(nom);
		}
		if (strategie.equals("humain")) {
			return new JoueurHumain(nom, scanner);
		}
		if (strategie.equals("expert")) {
			return new JoueurExpert(nom);
		}
		if (strategie.equals("tricheur")) {
			return new JoueurTricheur(nom);
		}
		throw new ConfigurationException("strategie inconnue : " + strategie);
	}

	private static void verifierNombreArguments(String[] args, int debut) {
		final int nbJoueurs = 2;
		int nb = args.length - debut;
		if (nb < nbJoueurs) {
			throw new ConfigurationException("Trop peu d'arguments : " + nb);
		}
		if (nb > nbJoueurs) {
			throw new ConfigurationException("Trop d'arguments : " + nb);
		}
	}

	/** Afficher des indications sur la maniere d'executer cette classe. */
	public static void afficherUsage() {
		System.out.println("\n" + "Usage :"
				+ "\n\t" + "java allumettes.Jouer [-confiant] joueur1 joueur2"
				+ "\n\t\t" + "joueur est de la forme nom@strategie"
				+ "\n\t\t" + "strategie = naif | rapide | expert | humain | tricheur"
				+ "\n"
				+ "\n\t" + "Exemple :"
				+ "\n\t" + "	java allumettes.Jouer Xavier@humain "
					   + "Ordinateur@naif"
				+ "\n"
				);
	}

}
