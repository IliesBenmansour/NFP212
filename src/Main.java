public class Main {
	public static void main(String[] args) {

		// Création d’un point
		Point p = new Point(3, 4);

		// Affichage du point
		p.afficher();

		// Affichage du module
		System.out.println("Module du point : " + p.getModule());

		// Translation du point
		p.translater(2, -1);

		// Affichage après translation
		p.afficher();

		// Modification du X avec setX
		p.setX(10);

		// Affichage final
		p.afficher();
	}
}
