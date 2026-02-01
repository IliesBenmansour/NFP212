package TP09.src;

public class EnsembleOrdonneChaine extends EnsembleChaine implements EnsembleOrdonne {

	public EnsembleOrdonneChaine(Cellule cellule, int cardinal) {
		super(cellule, cardinal);
	}

	@Override
	public int petit() {
		if (estVide()) {
			throw new IllegalStateException("Ensemble vide");
		}
		return super.cellule.getValeur();
	}
}