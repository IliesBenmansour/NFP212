package allumettes;

public class StrategieExperte implements Strategie {

	@Override
	public int getPrise(Jeu jeu, String nomJoueur) {
		// les positions perdantes sont les multiples de 4 plus 1 (1 5 9 13)
		int n = jeu.getNombreAllumettes();
		int reste = (n - 1) % (Jeu.PRISE_MAX + 1);
		if (reste == 0) {
			// position perdante on peut juste retarder
			return 1;
		}
		return reste;
	}

}
