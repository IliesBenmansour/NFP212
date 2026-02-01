package TP09.src;

public class Cellule {

	private int valeur;
	private Cellule prochaine;

	public Cellule(int valeur, Cellule prochaine) {
		this.valeur = valeur;
		this.prochaine = prochaine;
	}

	public int getValeur() {
		return valeur;
	}

	public void setValeur(int valeur) {
		this.valeur = valeur;
	}

	public Cellule getProchaine() {
		return prochaine;
	}

	public void setProchaine(Cellule prochaine) {
		this.prochaine = prochaine;
	}

}
