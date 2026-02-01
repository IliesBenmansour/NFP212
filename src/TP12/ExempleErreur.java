package TP12;
/**
 * Une erreur Ã la compilation ! Pourquoi ?
 *
 * @author Xavier CrÃ©gut
 * @version 1.3
 */
public class ExempleErreur {

	// Il y avait une erreur car il manque des paramettre a l'appel du constructeur

	/** MÃ©thode principale */
	public static void main(String[] args) {
		double x = 0;
		double y = 0;
		Point p1 = new Point(x, y);
		p1.setX(1);
		p1.setY(2);
		p1.afficher();
		System.out.println();
	}

}