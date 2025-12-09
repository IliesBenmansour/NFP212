import java.awt.Color;

/**
 * Un segment est dÃ©fini pas ses deux points qui constituent ses extrÃ©mitÃ©s.
 * Un segment peut Ãªtre affichÃ© et translatÃ©.
 *
 * @author Xavier CrÃ©gut
 * @version 1.9
 */
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

	/**
	 * Translater le segment.
	 *
	 * @param dx dÃ©placement suivant l'axe des X
	 * @param dy dÃ©placement suivant l'axe des Y
	 */
	public void translater(double dx, double dy) {
		System.err.println("Segment.translater(double, double) non implantÃ©e");
	}

	/**
	 * Obtenir la longueur du segment.
	 *
	 * @return la longueur du segment
	 */
	public double longueur() {
		System.err.println("Segment.longueur() non implantÃ©e");
		return 0;
	}

	/**
	 * Afficher le segment. Le segment est affichÃ© sous la forme :
	 *
	 * <PRE>
	 *		[extremite1-extremite2]
	 * </PRE>
	 */
	public void afficher() {
		System.err.println("Segment.afficher() non implantÃ©e");
	}

	/**
	 * Obtenir la couleur du segment.
	 *
	 * @return la couleur du segment
	 */
	public Color getCouleur() {
		return this.couleur;
	}

	/**
	 * Changer la couleur du segment.
	 *
	 * @param nouvelleCouleur nouvelle couleur
	 */
	public void setCouleur(Color nouvelleCouleur) {
		this.couleur = nouvelleCouleur;
	}

}