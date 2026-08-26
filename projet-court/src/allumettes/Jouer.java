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

			Joueur joueur1 = parserJoueur(args[debut], scanner);
			Joueur joueur2 = parserJoueur(args[debut + 1], scanner);

			Plateau jeu = new Plateau();
			Arbitre arbitre = new Arbitre(joueur1, joueur2, confiant);
			arbitre.arbitrer(jeu);

			// sans ca le programme se termine pas quand on a joué
			// avec swing (le thread de swing tourne encore derriere)
			System.exit(0);

		} catch (ConfigurationException e) {
			System.out.println();
			System.out.println("Erreur : " + e.getMessage());
			afficherUsage();
			System.exit(1);
		}
	}

	/** Analyser un argument de la forme nom@strategie et creer le joueur
	 *  correspondant. Leve ConfigurationException si l'argument est mal
	 *  forme (manque @, nom vide ou strategie vide).
	 *  @param arg l'argument du joueur
	 *  @param scanner le scanner partage
	 *  @return le joueur cree
	 *  @throws ConfigurationException si l'argument est invalide
	 */
	private static Joueur parserJoueur(String arg, Scanner scanner) {
		String[] parts = arg.split("@");
		if (parts.length != 2) {
			throw new ConfigurationException("Joueur mal forme : " + arg);
		}
		String nom = parts[0];
		String strategie = parts[1];
		if (nom.isEmpty()) {
			throw new ConfigurationException("Nom non defini : " + arg);
		}
		if (strategie.isEmpty()) {
			throw new ConfigurationException("Strategie non definie : " + arg);
		}
		return creerJoueur(nom, strategie, scanner);
	}

	/** Creer le Joueur correspondant a une strategie.
	 *  @param nom le nom du joueur
	 *  @param strategie la strategie (naif, rapide, humain, expert,
	 *      tricheur, supertricheur, swing)
	 *  @param scanner le scanner partage (pour le joueur humain)
	 *  @return le joueur cree
	 *  @throws ConfigurationException si la strategie est inconnue
	 */
	private static Joueur creerJoueur(String nom, String strategie,
			Scanner scanner) {
		switch (strategie) {
		case "naif":
			return new Joueur(nom, new StrategieNaive());
		case "rapide":
			return new Joueur(nom, new StrategieRapide());
		case "humain":
			return new Joueur(nom, new StrategieHumaine(scanner));
		case "expert":
			return new Joueur(nom, new StrategieExperte());
		case "tricheur":
			return new Joueur(nom, new StrategieTricheuse());
		case "swing":
			return new Joueur(nom, new StrategieSwing());
		case "supertricheur":
			return new Joueur(nom, new StrategieSupertricheuse());
		default:
			throw new ConfigurationException("strategie inconnue : " + strategie);
		}
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
				+ "\n\t\t" + "strategie = naif | rapide | expert | humain"
					   + " | tricheur | supertricheur | swing"
				+ "\n"
				+ "\n\t" + "Exemple :"
				+ "\n\t" + "	java allumettes.Jouer Xavier@humain "
					   + "Ordinateur@naif"
				+ "\n"
				);
	}

}
