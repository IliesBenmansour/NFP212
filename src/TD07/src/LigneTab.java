package TD07.src;

public class LigneTab implements Ligne {

	/** tableau de caractères */
	private char[] contenu;

	/** longueur réelle de la ligne */
	private int longueur;

	/** position du curseur (0 si ligne vide, sinon entre 1 et longueur) */
	private int curseur;

	/**
	 * Constructeur : crée une ligne vide avec une capacité donnée
	 *
	 * @param capacite capacité maximale de la ligne
	 */
	public LigneTab(int capacite) {
		contenu = new char[capacite];
		longueur = 0;
		curseur = 0;
	}

	@Override
	public int getLongueur() {
		return longueur;
	}

	@Override
	public int getCurseur() {
		return curseur;
	}

	/**
	 * Avancer le curseur d'une position à droite
	 */
	@Override
	public void avancer() {
		if (curseur < longueur) {
			curseur++;
		}
	}

	/**
	 * Placer le curseur sur le premier caractère
	 */
	@Override
	public void raz() {
		if (longueur > 0) {
			curseur = 1;
		}
	}

	/*
	 * ====================================================== Les autres méthodes de
	 * l’interface seront ajoutées plus tard
	 * ======================================================
	 */

	@Override
	public char ieme(int i) {
		throw new UnsupportedOperationException();
	}

	@Override
	public char getCourant() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void reculer() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void remplacer(char c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void supprimer() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void ajouterAvant(char c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void ajouterApres(char c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void afficher() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void ajouterFin(char c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void ajouterDebut(char c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void supprimerPremier() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void supprimerDernier() {
		throw new UnsupportedOperationException();
	}
}
