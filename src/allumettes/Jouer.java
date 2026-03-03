package allumettes;

import java.util.Scanner;

/** Lance une partie des 13 allumettes en fonction des arguments fournis
 * sur la ligne de commande.
 * @author	Xavier Crégut
 * @version	$Revision: 1.5 $
 */
public class Jouer {

	/** Lancer une partie. En argument sont donnés les deux joueurs sous
	 * la forme nom@stratégie.
	 * @param args la description des deux joueurs
	 */
	public static void main(String[] args) {
		try {
			verifierNombreArguments(args);

			Scanner scanner = new Scanner(System.in);

			String[] j1 = args[0].split("@");
			String[] j2 = args[1].split("@");

			Joueur joueur1 = null;
			Joueur joueur2 = null;

			if (j1[1].equals("naif")) {
				joueur1 = new JoueurNaif(j1[0]);
			} else if (j1[1].equals("rapide")) {
				joueur1 = new JoueurRapide(j1[0]);
			} else if (j1[1].equals("humain")) {
				joueur1 = new JoueurHumain(j1[0], scanner);
			}

			if (j2[1].equals("naif")) {
				joueur2 = new JoueurNaif(j2[0]);
			} else if (j2[1].equals("rapide")) {
				joueur2 = new JoueurRapide(j2[0]);
			} else if (j2[1].equals("humain")) {
				joueur2 = new JoueurHumain(j2[0], scanner);
			}

			Plateau plateau = new Plateau();
			Arbitre arbitre = new Arbitre(joueur1, joueur2);
			arbitre.arbitrer(plateau);

		} catch (ConfigurationException e) {
			System.out.println();
			System.out.println("Erreur : " + e.getMessage());
			afficherUsage();
			System.exit(1);
		}
	}

	private static void verifierNombreArguments(String[] args) {
		final int nbJoueurs = 2;
		if (args.length < nbJoueurs) {
			throw new ConfigurationException("Trop peu d'arguments : "
					+ args.length);
		}
		if (args.length > nbJoueurs + 1) {
			throw new ConfigurationException("Trop d'arguments : "
					+ args.length);
		}
	}

	/** Afficher des indications sur la manière d'exécuter cette classe. */
	public static void afficherUsage() {
		System.out.println("\n" + "Usage :"
				+ "\n\t" + "java allumettes.Jouer joueur1 joueur2"
				+ "\n\t\t" + "joueur est de la forme nom@stratégie"
				+ "\n\t\t" + "strategie = naif | rapide | expert | humain | tricheur"
				+ "\n"
				+ "\n\t" + "Exemple :"
				+ "\n\t" + "	java allumettes.Jouer Xavier@humain "
					   + "Ordinateur@naif"
				+ "\n"
				);
	}

}