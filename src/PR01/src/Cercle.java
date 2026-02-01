package PR01.src;

import java.awt.Color;

public class Cercle {

	private Point centre;
	private double rayon;
	private Color color;

	public Cercle(Point centre, double rayon) {
		if (centre == null) {
			throw new AssertionError("le centre doit pas etre null");
		}
		if (rayon <= 0) {
			throw new AssertionError("les rayon doit etre <= 0 ");
		}
		this.centre = new Point(centre.getX(), centre.getY());
		this.rayon = rayon;
		this.color = Color.blue;
	}

	public Cercle(Point a, Point b) {
		if (a == null) {
			throw new AssertionError("le point a doit pas etre null");
		}
		if (b == null) {
			throw new AssertionError("Le point b doit pas etre null");
		}
		this.centre = new Point((a.getX() + b.getX()) / 2, (a.getY() + b.getY()) / 2);
		// utiliser la methode de point pour calculer le rayon du cercle
		this.rayon = a.distance(b) / 2;
		this.color = Color.blue;
	}

	public Cercle(Point a, Point b, Color couleur) {
		if (a == null) {
			throw new AssertionError("le point a doit pas etre null");
		}
		if (b == null) {
			throw new AssertionError("le point a doit pas etre null");
		}
		if (couleur == null) {
			throw new AssertionError("La couleur ne doit pas = null");
		}
		this.centre = new Point((a.getX() + b.getX()) / 2, (a.getY() + b.getY()) / 2);
		this.rayon = a.distance(b) / 2;
		this.color = couleur;
	}

	public static Cercle creerCercle(Point a, Point b) {
		if (a == null) {
			throw new AssertionError("le point a doit pas etre null");
		}
		if (b == null) {
			throw new AssertionError("le point a doit pas etre null");
		}
		return new Cercle(a, b);
	}

	public Point getCentre() {
		return new Point(this.centre.getX(), this.centre.getY());
	}

	public void setCentre(Point c) {
		this.centre = new Point(c.getX(), c.getY());
	}

	public double getRayon() {
		return rayon;
	}

	public void setRayon(double rayon) {
		if (rayon <= 0) {
			throw new AssertionError("le rayon doit etre  <= 0");
		}
		this.rayon = rayon;
	}

	public double getDiametre() {
		return 2 * rayon;
	}

	public void setDiametre(double diametre) {
		if (diametre <= 0) {
			throw new AssertionError("Le diametre doit etre  <= 0");
		}
		this.rayon = diametre / 2;
	}

	public Color getCouleur() {
		return color;
	}

	public void setCouleur(Color color) {
		if (color == null) {
			throw new AssertionError("La couleur doit pas etre null");
		}
		this.color = color;
	}

	public void translater(double dx, double dy) {
		this.centre.translater(dx, dy);
	}

	public boolean contient(Point p) {
		if (p == null) {
			throw new AssertionError("le point doit pas etre null");
		}
		double dx = p.getX() - centre.getX();
		double dy = p.getY() - centre.getY();
		return dx * dx + dy * dy <= rayon * rayon;
	}

	public double perimetre() {
		return 2 * Math.PI * this.rayon;
	}

	public double aire() {
		return Math.PI * this.rayon * this.rayon;
	}

	@Override
	public String toString() {
		return "C" + this.rayon + "@" + this.centre.toString();
	}
}