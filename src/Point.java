import java.awt.Color;

public class Point {

	public Point(double vx, double vy) {
		this.x = vx;
		this.y = vy;
		this.couleur = Color.green;
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	public void setX(double vx) {
		this.x = vx;
	}

	public void setY(double vy) {
		this.y = vy;
	}

	public void afficher() {
		System.out.print("(" + this.x + ", " + this.y + ")");
	}

	public double distance(Point autre) {
		return Math.sqrt(Math.pow(autre.x - this.x, 2) + Math.pow(autre.y - this.y, 2));
	}

	public void translater(double dx, double dy) {
		this.x += dx;
		this.y += dy;
	}

	public Color getCouleur() {
		return this.couleur;
	}

	public void setCouleur(Color nouvelleCouleur) {
		this.couleur = nouvelleCouleur;
	}

	private double x; // abscisse
	private double y; // ordonnÃ©e
	private Color couleur; // couleur du point

}