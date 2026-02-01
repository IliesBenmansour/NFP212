package TP05.src;

import afficheur.AfficheurSVG;
import afficheur.Ecran;

/**
 * Construire le schéma proposé dans le sujet de TP avec des points, et des
 * segments.
 *
 * @author Xavier Crégut
 * @version $Revision: 1.7 $
 */
public class ExempleSchema1 {

	/**
	 * Construire le schéma et le manipuler. Le schéma est affiché.
	 *
	 * @param args les arguments de la ligne de commande
	 */
	public static void main(String[] args) {
		// Créer les trois segments
		Point p1 = new Point(3, 2);
		Point p2 = new Point(6, 9);
		Point p3 = new Point(11, 4);
		Segment s12 = new Segment(p1, p2);
		Segment s23 = new Segment(p2, p3);
		Segment s31 = new Segment(p3, p1);

		// Créer le barycentre
		double sx = p1.getX() + p2.getX() + p3.getX();
		double sy = p1.getY() + p2.getY() + p3.getY();
		Point barycentre = new Point(sx / 3, sy / 3);

		// Afficher le schéma
		Ecran ecran = new Ecran("Exemple Ecran", 600, 400, 20);
		p1.dessiner(ecran);
		p2.dessiner(ecran);
		p3.dessiner(ecran);
		s12.dessiner(ecran);
		s23.dessiner(ecran);
		s31.dessiner(ecran);
		barycentre.dessiner(ecran);

		Ecran ecran2 = new Ecran("Ecran 2", 600, 400, 20);
		p1.dessiner(ecran2);
		p2.dessiner(ecran2);
		p3.dessiner(ecran2);
		s12.translater(4, -3);
		s23.translater(4, -3);
		s31.translater(4, -3);
		barycentre.translater(4, -3);
		s12.dessiner(ecran2);
		s23.dessiner(ecran2);
		s31.dessiner(ecran2);

		AfficheurSVG aff = new AfficheurSVG("schema", null, 600, 400);
		p1.dessiner(aff);
		p2.dessiner(aff);
		p3.dessiner(aff);
		s12.dessiner(aff);
		s23.dessiner(aff);
		s31.dessiner(aff);
		barycentre.dessiner(aff);
		aff.ecrire("schema.svg");
	}

}
