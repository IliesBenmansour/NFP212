package PR01.src;

import java.awt.Color;

/**
 *
 * @author Ilies Benmansour
 */
public class Cercle {

	private Point centre;
	private double rayon;
	private Color color;
	private static final double PI = Math.PI;

	/**
	 * Construire un cercle grace a son centre et son rayon
	 *
	 * @param centre centre du cercle
	 * @param rayon  rayon du cercle
	 */
	public Cercle(Point centre, double rayon) {
		assert centre != null : "le centre ne doit pas = null";
		assert rayon > 0 : "le rayon doit etre positif";
		this.centre = new Point(centre.getX(), centre.getY());
		this.rayon = rayon;
		this.color = Color.blue;
	}

	/**
	 * Construire un cercle a partir de deux point de diametre opposé la couleur par
	 * défaut est bleu
	 *
	 * @param point1 point1 du diametre
	 * @param point2 point2 du diametre
	 */
	public Cercle(Point point1, Point point2) {
		assert point1 != null : "le point 1 ne doit pas = null";
		assert point2 != null : "le point 2 ne doit pas = null";
		assert point1.distance(point2) > 0 : "les deux point doivent etre different";

		double cx = (point1.getX() + point2.getX()) / 2;
		double cy = (point1.getY() + point2.getY()) / 2;
		this.centre = new Point(cx, cy);
		this.rayon = point1.distance(point2) / 2;
		this.color = Color.blue;
	}

	/**
	 * Construire un cercle a partir de deux point de diametre opposé la couleur
	 * choisis en parametre
	 *
	 * @param point1  point1 du diametre
	 * @param point2  point2 du diametre
	 * @param couleur
	 */
	public Cercle(Point point1, Point point2, Color couleur) {
		assert point1 != null : "le point 1 ne doit pas = null";
		assert point2 != null : "le point 2 ne doit pas = null";
		assert point1.distance(point2) > 0 : "les deux point doivent etre different";
		assert couleur != null : "la couleur ne doit pas = null";
		double cx = (point1.getX() + point2.getX()) / 2;
		double cy = (point1.getY() + point2.getY()) / 2;
		this.centre = new Point(cx, cy);
		this.rayon = point1.distance(point2) / 2;
		this.color = couleur;
	}

	/**
	 * Construire un cercle a partir de deux point de diametre opposé
	 *
	 * @param point1 point1 du diametre
	 * @param point2 point2 du diametre
	 * @return Cercle
	 */
	public static Cercle creerCercle(Point point1, Point point2) {
		assert point1 != null : "le point 1 ne doit pas = null";
		assert point2 != null : "le point 2 ne doit pas = null";
		assert point1.distance(point2) > 0 : "les deux point doivent etre different";
		return new Cercle(point1, point2);
	}

	/**
	 * Avoir le centre grace au coordonne de centre avec getY et getX
	 *
	 * @return un nouveau point
	 */
	public Point getCentre() {
		return new Point(this.centre.getX(), this.centre.getY());
	}

	/**
	 * Modifier le centre du cercle
	 *
	 * @param centre
	 */
	public void setCentre(Point centre) {
		this.centre = new Point(centre.getX(), centre.getY());
	}

	/**
	 * Avoir rayon
	 *
	 * @return rayon
	 */
	public double getRayon() {
		return rayon;
	}

	/**
	 * Modifier le rayon
	 *
	 * @param rayon
	 */
	public void setRayon(double rayon) {
		assert rayon > 0 : "le rayon doit etre positif";
		this.rayon = rayon;
	}

	/**
	 * Avoir le diamettre
	 *
	 * @return le double du rayon
	 */
	public double getDiametre() {
		return 2 * rayon;
	}

	/**
	 * Modifie le diametre mais modifie aussi donc le rayon
	 *
	 * @param diametre
	 */
	public void setDiametre(double diametre) {
		assert diametre > 0 : "le diametre doit ere positif";
		this.rayon = diametre / 2;
	}

	/**
	 * Obtenir la couleur
	 *
	 * @return color
	 */
	public Color getCouleur() {
		return color;
	}

	/**
	 * Modifier la couleur
	 *
	 * @param color
	 */
	public void setCouleur(Color color) {
		assert color != null : "la couleur ne doit pas = null";
		this.color = color;
	}

	/**
	 * Translater le cercle en utilisant la methode de Point
	 *
	 * @param dx abscisse
	 * @param dy ordonnée
	 */
	public void translater(double dx, double dy) {
		this.centre.translater(dx, dy);
	}

	/**
	 * Verifier si un point est dans le cercle
	 *
	 * @param p le point a tester
	 * @return true quand le point est dans le cercle sinon false
	 */
	public boolean contient(Point p) {
		assert p != null : "le point ne doit pas = null";
		double dx = p.getX() - centre.getX();
		double dy = p.getY() - centre.getY();
		return dx * dx + dy * dy <= rayon * rayon;
	}

	/**
	 * Faire la formule du perimetre
	 *
	 * @return le perimetre
	 */
	public double perimetre() {
		return 2 * PI * this.rayon;
	}

	/**
	 * Faire la formule de l'air pour un cercle
	 *
	 * @return l'air du cercle
	 */
	public double aire() {
		return this.rayon * this.rayon * PI;
	}

	@Override
	public String toString() {
		return "C" + this.rayon + "@" + this.centre;
	}
}