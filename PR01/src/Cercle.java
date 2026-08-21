
import java.awt.Color;

/**
 * Cercle modelise un cercle dans le plan avec un centre un rayon et une couleur.
 *
 * @author Ilies Benmansour
 */
public class Cercle implements Mesurable2D {

	/** La valeur de pi utilise dans les calculs. */
	public static final double PI = Math.PI;

	/** Le centre du cercle. */
	private Point centre;

	/** Le rayon du cercle strictement positif. */
	private double rayon;

	/** La couleur du cercle. */
	private Color couleur;

	/**
	 * Construire un cercle a partir de son centre et de son rayon la couleur par defaut est bleu.
	 *
	 * @param centre le centre du cercle
	 * @param rayon le rayon du cercle strictement positif
	 */
	public Cercle(Point centre, double rayon) {
		assert centre != null : "le centre ne doit pas être null";
		assert rayon > 0 : "le rayon doit être strictement positif";
		this.centre = new Point(centre.getX(), centre.getY());
		this.rayon = rayon;
		this.couleur = Color.blue;
	}

	/**
	 * Construire un cercle a partir de deux points diametralement opposé et de sa couleur.
	 *
	 * @param point1 un point du cercle
	 * @param point2 le point diametralement opposé a point1
	 * @param couleur la couleur du cercle
	 */
	public Cercle(Point point1, Point point2, Color couleur) {
		this(milieu(point1, point2), point1.distance(point2) / 2);
		assert couleur != null : "la couleur ne doit pas être null";
		this.couleur = couleur;
	}

	/**
	 * Construire un cercle a partir de deux points diametralement opposé la couleur par defaut est bleu.
	 *
	 * @param point1 un point du cercle
	 * @param point2 le point diametralement opposé a point1
	 */
	public Cercle(Point point1, Point point2) {
		this(point1, point2, Color.blue);
	}

	/**
	 * Creer un cercle a partir de son centre et d un point du cercle.
	 *
	 * @param centre le centre du cercle
	 * @param point un point du cercle
	 * @return le cercle creé
	 */
	public static Cercle creerCercle(Point centre, Point point) {
		assert centre != null : "le centre ne doit pas être null";
		assert point != null : "le point ne doit pas être null";
		return new Cercle(centre, centre.distance(point));
	}

	/**
	 * Calculer le milieu de deux points.
	 *
	 * @param p1 le premier point
	 * @param p2 le deuxieme point
	 * @return le milieu de p1 et p2
	 */
	private static Point milieu(Point p1, Point p2) {
		assert p1 != null : "le point 1 ne doit pas être null";
		assert p2 != null : "le point 2 ne doit pas être null";
		assert p1.distance(p2) > 0 : "les deux points doivent être différents";
		double mx = (p1.getX() + p2.getX()) / 2;
		double my = (p1.getY() + p2.getY()) / 2;
		return new Point(mx, my);
	}

	/**
	 * Obtenir une copie du centre du cercle.
	 *
	 * @return une copie du centre
	 */
	public Point getCentre() {
		return new Point(this.centre.getX(), this.centre.getY());
	}

	/**
	 * Changer le centre du cercle.
	 *
	 * @param nouveauCentre le nouveau centre
	 */
	public void setCentre(Point nouveauCentre) {
		assert nouveauCentre != null : "le centre ne doit pas être null";
		this.centre = new Point(nouveauCentre.getX(), nouveauCentre.getY());
	}

	/**
	 * Obtenir le rayon du cercle.
	 *
	 * @return le rayon
	 */
	public double getRayon() {
		return this.rayon;
	}

	/**
	 * Changer le rayon du cercle.
	 *
	 * @param nouveauRayon le nouveau rayon strictement positif
	 */
	public void setRayon(double nouveauRayon) {
		assert nouveauRayon > 0 : "le rayon doit être strictement positif";
		this.rayon = nouveauRayon;
	}

	/**
	 * Obtenir le diametre du cercle.
	 *
	 * @return le diametre
	 */
	public double getDiametre() {
		return 2 * this.rayon;
	}

	/**
	 * Changer le diametre du cercle.
	 *
	 * @param nouveauDiametre le nouveau diametre strictement positif
	 */
	public void setDiametre(double nouveauDiametre) {
		assert nouveauDiametre > 0 : "le diamètre doit être strictement positif";
		this.rayon = nouveauDiametre / 2;
	}

	/**
	 * Obtenir la couleur du cercle.
	 *
	 * @return la couleur
	 */
	public Color getCouleur() {
		return this.couleur;
	}

	/**
	 * Changer la couleur du cercle.
	 *
	 * @param nouvelleCouleur la nouvelle couleur
	 */
	public void setCouleur(Color nouvelleCouleur) {
		assert nouvelleCouleur != null : "la couleur ne doit pas être null";
		this.couleur = nouvelleCouleur;
	}

	/**
	 * Translater le cercle.
	 *
	 * @param dx deplacement en x
	 * @param dy deplacement en y
	 */
	public void translater(double dx, double dy) {
		this.centre.translater(dx, dy);
	}

	/**
	 * Savoir si un point est a l interieur du cercle.
	 *
	 * @param p le point a tester
	 * @return vrai si p est dans le cercle
	 */
	public boolean contient(Point p) {
		assert p != null : "le point ne doit pas être null";
		return this.centre.distance(p) <= this.rayon;
	}

	/**
	 * Obtenir le perimetre du cercle.
	 *
	 * @return le perimetre
	 */
	public double perimetre() {
		return 2 * PI * this.rayon;
	}

	/**
	 * Obtenir l aire du cercle.
	 *
	 * @return l aire
	 */
	public double aire() {
		return PI * this.rayon * this.rayon;
	}

	@Override
	public String toString() {
		return "C" + this.rayon + "@" + this.centre;
	}

}
