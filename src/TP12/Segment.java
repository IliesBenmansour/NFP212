package TP12;
import java.awt.Color;

public class Segment {

	private Point extremite1;
	private Point extremite2;
	private Color couleur;

	/**
	 * Construire un Segment Ã partir de ses deux points extrÃ©mitÃ©s.
	 *
	 * @param ext1 le premier point extrÃ©mitÃ©
	 * @param ext2 le deuxiÃ¨me point extrÃ©mitÃ©
	 */
	public Segment(Point ext1, Point ext2) {
		this.extremite1 = ext1;
		this.extremite2 = ext2;
		this.couleur = Color.green;
	}

	public void translater(double dx, double dy) {
		extremite1.translater(dx, dy);
		extremite2.translater(dx, dy);
	}

	public double longueur() {
		// pas compris quel formule
		return 0;
	}

	public void afficher() {
		extremite1.afficher();
		System.out.print(" , ");
		extremite2.afficher();
	}

	public Color getCouleur() {
		return this.couleur;
	}

	public void setCouleur(Color nouvelleCouleur) {
		this.couleur = nouvelleCouleur;
	}

}