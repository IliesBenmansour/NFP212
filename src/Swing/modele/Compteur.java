package Swing.modele;

public class Compteur {

	private int valeur;

	public Compteur(int v) {
		this.valeur = v;
	}

	public void incrementer() {
		this.valeur++;
	}

	public int getValeur() {
		return this.valeur;
	}

	public void raz() {
		this.valeur = 0;
	}

}
