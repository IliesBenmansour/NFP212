package allumettes;

public class Joueur {

	private String nom;
	private Strategie strategie;

	public Joueur(String nom, Strategie strategie) {
		this.nom = nom;
		this.strategie = strategie;
	}

	public String getNom() {
		return this.nom;
	}

	public Strategie getStrategie() {
		return this.strategie;
	}

	// on peut changer de strategie en cours de partie
	public void setStrategie(Strategie strategie) {
		this.strategie = strategie;
	}

	public int getPrise(Jeu jeu) {
		return this.strategie.getPrise(jeu, this.nom);
	}

}
