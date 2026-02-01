package TP09.src;

/**
 * Classe de test pour EnsembleChaine.
 */
public class EnsembleChaineTest extends EnsembleTestAbstrait {

	@Override
	protected Ensemble nouvelEnsemble(int capacite) {
		return new EnsembleChaine();
	}

}
