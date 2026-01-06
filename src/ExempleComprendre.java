/**
 * Comprendre les objets et les poignÃ©es sur la classe Point.
 *
 * @author Xavier CrÃ©gut
 * @version 1.6
 */
public class ExempleComprendre {

	/**
	 * Afficher un point prÃ©cÃ©dÃ© d'un texte.
	 *
	 * @param texte le texte Ã afficher
	 * @param p     le point Ã afficher
	 */
	public static void afficher(String texte, Point p) {
		System.out.print(texte + " = ");
		p.afficher();
		System.out.println();
	}

	/** MÃ©thode principale */
	public static void main(String[] args) {
		Point p1; // dÃ©clarer une poignÃ©e sur un Point
		p1 = new Point(3, 4); // crÃ©er un point et l'attacher Ã p1
		afficher("p1", p1);

		// CrÃ©er et afficher un point p2
		Point p2 = new Point(0, 0); // On dÃ©clare des poignÃ©es et on
									// crÃ©e des objets quand on veut.
		afficher("p2", p2);

		// Afficher la distance entre p1 et p2
		double d = p1.distance(p2);
		System.out.println("Distance de p1 Ã  p2 : " + d);

		// Translater p1
		System.out.println("> p1.translater(6, -2);");
		p1.translater(6, -2);
		afficher("p1", p1); // Qu'est ce qui est affichÃ© ?

		// Changer l'abscisse de p1 et afficher p1
		System.out.println("> p1.setX(0);");
		p1.setX(0);
		afficher("p1", p1); // Qu'est ce qui est affichÃ© ?

		// Changer l'ordonnÃ©e de p1 et afficher p1
		System.out.println("> p1.setY(10);");
		p1.setY(10);
		afficher("p1", p1);
		// Dessiner l'Ã©tat de la mÃ©moire
		// Prendre une nouvelle poignÃ©e sur p1
		System.out.println("> Point p3 = p1;");
		Point p3 = p1;
		afficher("p3", p3); // Qu'est ce qui est affichÃ© ?
		afficher("p1", p1); // Qu'est ce qui est affichÃ© ?

		// DÃ©placer p3
		System.out.println("> p3.translater(100, 100);");
		p3.translater(100, 100);
		afficher("p3", p3); // Qu'est ce qui est affichÃ© ?

		// Afficher p1
		afficher("p1", p1); // Qu'est ce qui est affichÃ© ?

		// Dessiner l'Ã©tat de la mÃ©moire
		// Affectations entre poignÃ©es
		System.out.println("> p3 = new Point(123, 321);");
		p3 = new Point(123, 321);
		afficher("p3", p3); // Qu'est ce qui est affichÃ© ?
		afficher("p1", p1); // Qu'est ce qui est affichÃ© ?

		System.out.println("> p1 = p2 = p3;");
		p1 = p2 = p3;
		// Dessiner l'Ã©tat de la mÃ©moire
		afficher("p1", p1); // Qu'est ce qui est affichÃ© ?
		afficher("p2", p2); // Qu'est ce qui est affichÃ© ?
		afficher("p3", p3); // Qu'est ce qui est affichÃ© ?

		// p1, p2 et p3 sont-ils des points diffÃ©rents ?
		System.out.println("> p1.translater(-123, -321);");
		p1.translater(-123, -321);
		afficher("p1", p1); // Qu'est ce qui est affichÃ© ?
		afficher("p2", p2); // Qu'est ce qui est affichÃ© ?
		afficher("p3", p3); // Qu'est ce qui est affichÃ© ?

		d = new Point(5, 5).distance(new Point(8, 1));
		System.out.println("d = " + d);

		// Combien y a-t-il de points accessibles ?
	}
}