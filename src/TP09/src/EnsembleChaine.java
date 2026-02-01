package TP09.src;

public class EnsembleChaine implements Ensemble {

	protected Cellule cellule;
	private int cardinal;

	public EnsembleChaine(Cellule cellule, int cardinal) {
		super();
		this.cellule = cellule;
		this.cardinal = cardinal;
	}

	@Override
	public int cardinal() {
		return cardinal;
	}

	@Override
	public boolean estVide() {
		if (cardinal == 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean contient(int x) {
		Cellule cel = cellule;
		while (cel != null) {
			if (cel.getValeur() == x) {
				return true;
			}
			cel = cel.getProchaine();
		}
		return false;
	}

	@Override
	public void ajouter(int x) {
		if (!contient(x)) {
			cellule = new Cellule(x, cellule);
			cardinal += 1;
		}
	}

	@Override
	public void supprimer(int x) {
		Cellule courant = cellule;
		Cellule precedent = null;

		while (courant != null) {
			if (courant.getValeur() == x) {
				if (precedent == null) {
					cellule = courant.getProchaine();
				} else {
					precedent.setProchaine(courant.getProchaine());
				}
				cardinal--;
				return;
			}
			precedent = courant;
			courant = courant.getProchaine();
		}
	}
}
