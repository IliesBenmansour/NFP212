package TP03.src;

import java.awt.Color;

/**
 * Cercle modélise un cercle géométrique dans un plan.
 * Un cercle est défini par son centre (un Point) et son rayon.
 * Il possède également une couleur.
 *
 * @author Ilies
 */
public class Cercle {

	private Point centre;
	private double rayon;
	private Color color;

	/**
	 * Construire un cercle à partir de son centre et de son rayon.
	 *
	 * @param centre le centre du cercle
	 * @param rayon le rayon du cercle (doit être strictement positif)
	 */
	public Cercle(Point centre, double rayon) {
		assert centre != null : "le centre ne doit pas être null";
		assert rayon > 0 : "le rayon doit être strictement positif";
		this.centre = new Point(centre.getX(), centre.getY());
		this.rayon = rayon;
		this.color = Color.blue;
	}

	/**
	 * Construire un cercle à partir de deux points diamétralement opposés.
	 * La couleur par défaut est bleu.
	 *
	 * @param a premier point du diamètre
	 * @param b deuxième point du diamètre
	 */
	public Cercle(Point a, Point b) {
		assert a != null : "le point a ne doit pas être null";
		assert b != null : "le point b ne doit pas être null";
		assert a.distance(b) > 0 : "les deux points doivent être différents";
		this.centre = new Point((a.getX() + b.getX()) / 2, (a.getY() + b.getY()) / 2);
		this.rayon = a.distance(b) / 2;
		this.color = Color.blue;
	}

	/**
	 * Construire un cercle à partir de deux points diamétralement opposés
	 * et d'une couleur.
	 *
	 * @param a premier point du diamètre
	 * @param b deuxième point du diamètre
	 * @param couleur la couleur du cercle
	 */
	public Cercle(Point a, Point b, Color couleur) {
		assert a != null : "le point a ne doit pas être null";
		assert b != null : "le point b ne doit pas être null";
		assert a.distance(b) > 0 : "les deux points doivent être différents";
		assert couleur != null : "la couleur ne doit pas être null";
		this.centre = new Point((a.getX() + b.getX()) / 2, (a.getY() + b.getY()) / 2);
		this.rayon = a.distance(b) / 2;
		this.color = couleur;
	}

	/**
	 * Créer un cercle à partir de deux points diamétralement opposés.
	 *
	 * @param a premier point du diamètre
	 * @param b deuxième point du diamètre
	 * @return le cercle créé
	 */
	public static Cercle creerCercle(Point a, Point b) {
		assert a != null : "le point a ne doit pas être null";
		assert b != null : "le point b ne doit pas être null";
		assert a.distance(b) > 0 : "les deux points doivent être différents";
		return new Cercle(a, b);
	}

	/**
	 * Obtenir le centre du cercle.
	 *
	 * @return une copie du centre du cercle
	 */
	public Point getCentre() {
		return new Point(this.centre.getX(), this.centre.getY());
	}

	/**
	 * Modifier le centre du cercle.
	 *
	 * @param c le nouveau centre
	 */
	public void setCentre(Point c) {
		this.centre = new Point(c.getX(), c.getY());
	}

	/**
	 * Obtenir le rayon du cercle.
	 *
	 * @return le rayon du cercle
	 */
	public double getRayon() {
		return rayon;
	}

	/**
	 * Modifier le rayon du cercle.
	 *
	 * @param rayon le nouveau rayon (doit être strictement positif)
	 */
	public void setRayon(double rayon) {
		assert rayon > 0 : "le rayon doit être strictement positif";
		this.rayon = rayon;
	}

	/**
	 * Obtenir le diamètre du cercle.
	 *
	 * @return le diamètre du cercle
	 */
	public double getDiametre() {
		return 2 * rayon;
	}

	/**
	 * Modifier le diamètre du cercle.
	 *
	 * @param diametre le nouveau diamètre (doit être strictement positif)
	 */
	public void setDiametre(double diametre) {
		assert diametre > 0 : "le diamètre doit être strictement positif";
		this.rayon = diametre / 2;
	}

	/**
	 * Obtenir la couleur du cercle.
	 *
	 * @return la couleur du cercle
	 */
	public Color getCouleur() {
		return color;
	}

	/**
	 * Modifier la couleur du cercle.
	 *
	 * @param color la nouvelle couleur (ne doit pas être null)
	 */
	public void setCouleur(Color color) {
		assert color != null : "la couleur ne doit pas être null";
		this.color = color;
	}

	/**
	 * Translater le cercle.
	 *
	 * @param dx déplacement suivant l'axe des X
	 * @param dy déplacement suivant l'axe des Y
	 */
	public void translater(double dx, double dy) {
		this.centre.translater(dx, dy);
	}

	/**
	 * Vérifier si un point est contenu dans le cercle.
	 *
	 * @param p le point à tester
	 * @return true si le point est dans le cercle, false sinon
	 */
	public boolean contient(Point p) {
		assert p != null : "le point ne doit pas être null";
		double dx = p.getX() - centre.getX();
		double dy = p.getY() - centre.getY();
		return dx * dx + dy * dy <= rayon * rayon;
	}

	/**
	 * Calculer le périmètre du cercle.
	 *
	 * @return le périmètre du cercle
	 */
	public double perimetre() {
		return 2 * Math.PI * this.rayon;
	}

	/**
	 * Calculer l'aire du cercle.
	 *
	 * @return l'aire du cercle
	 */
	public double aire() {
		return Math.PI * this.rayon * this.rayon;
	}

	@Override
	public String toString() {
		return "C" + this.rayon + "@" + this.centre.toString();
	}
}