package allumettes;

import java.lang.reflect.Field;

/** Supertricheur du pr3 : il triche meme en mode non confiant.
 *  L'astuce : la procuration garde le vrai jeu dans un champ privé,
 *  avec la reflexion java on peut le recuperer et tricher directement
 *  dessus. Comme on passe pas par le retirer() de la procuration elle
 *  voit rien et on se fait jamais prendre.
 */
public class StrategieSupertricheuse implements Strategie {

	private static final int CIBLE_RESTANTE = 2;

	@Override
	public int getPrise(Jeu jeu, String nomJoueur) {
		Jeu reel = recupererJeuReel(jeu);
		int nb = reel.getNombreAllumettes();
		try {
			if (nb > CIBLE_RESTANTE && reel instanceof Plateau) {
				// on ramene a 2 en douce puis on prend la derniere officiellement
				((Plateau) reel).voler(nb - CIBLE_RESTANTE);
				System.out.println("[Je triche...]");
				System.out.println("[Allumettes restantes : "
						+ reel.getNombreAllumettes() + "]");
			} else if (nb == 1 && reel instanceof Plateau) {
				// piege : sur la derniere allumette on en remet une en douce
				((Plateau) reel).voler(-1);
				System.out.println("[Je triche... 1 allumette en plus]");
			}
		} catch (CoupInvalideException e) {
			// devrait pas arriver
		}
		return 1;
	}

	// va chercher le vrai jeu caché dans la procuration (champ privé jeu)
	private Jeu recupererJeuReel(Jeu jeu) {
		if (jeu instanceof Plateau) {
			return jeu; // deja le vrai
		}
		try {
			Field champ = jeu.getClass().getDeclaredField("jeu");
			champ.setAccessible(true); // on force l'acces au champ privé
			Object reel = champ.get(jeu);
			if (reel instanceof Jeu) {
				return (Jeu) reel;
			}
		} catch (ReflectiveOperationException e) {
			// pas de champ jeu, tant pis on triche pas ce coup ci
		}
		return jeu;
	}

}
