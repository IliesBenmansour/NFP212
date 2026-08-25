package allumettes;

public class StrategieRapide implements Strategie {

	@Override
	public int getPrise(Jeu jeu, String nomJoueur) {
		// strategie rapide on prend toujour le max possible
		int nbRestantes = jeu.getNombreAllumettes();
		if (nbRestantes >= Jeu.PRISE_MAX) {
			return Jeu.PRISE_MAX;
		}
		// si il reste moin que le max on prend ce qui reste
		return nbRestantes;
	}

}
