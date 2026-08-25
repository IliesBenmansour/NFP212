package allumettes;

import java.util.Random;

public class StrategieNaive implements Strategie {

	private Random random = new Random();

	@Override
	public int getPrise(Jeu jeu, String nomJoueur) {
		// on prend un nombre aleatoir entre 1 et le max (ou moin si il reste pas assez)
		int nbRestantes = jeu.getNombreAllumettes();
		int max = Jeu.PRISE_MAX;
		if (nbRestantes < max) {
			max = nbRestantes;
		}
		return this.random.nextInt(max) + 1;
	}

}
