package allumettes;

import java.util.Scanner;

public class JoueurHumain implements Joueur {

	private String nom;
	private Scanner sc;

	public JoueurHumain(String nom, Scanner sc) {
		this.nom = nom;
		this.sc = sc;
	}

	public String getNom() {
		return nom;
	}

	public int getPrise(Jeu jeu) {
		System.out.print(nom + ", combien d'allumettes ? ");
		String s = sc.next();
		try {
			int n = Integer.parseInt(s);
			return n;
		} catch (Exception e) {
			System.out.println("Vous devez donner un entier.");
			return getPrise(jeu);
		}
	}

}