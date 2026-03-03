package TD07.src;

public interface Ligne {

	// @ public invariant 0 <= getLongueur(); // La longueur est positive
	// @ public invariant 0 <= getCurseur() && getCurseur() <= getLongueur();
	// @ public invariant getCurseur() == 0 <==> getLongueur() == 0;

	/** nombre de caractères dans la ligne */
	/* @ pure @ */ int getLongueur();

	/** Position du curseur sur la ligne */
	/* @ pure @ */ int getCurseur();

	/**
	 * le ième caractère de la ligne
	 *
	 * @param i l'indice du caractère
	 * @return le ième caractère de la ligne
	 */
	// @ requires 1 <= i && i <= getLongueur(); // indice valide
	/* @ pure @ */ char ieme(int i);

	/** Le caractère sous le curseur */
	// @ requires getLongueur() > 0; // la ligne est non vide
	/* @ pure @ */ char getCourant();

	/** Avancer le curseur d'une position à droite. */
	// @ requires getCurseur() < getLongueur(); // pas à la fin
	// @ ensures getCurseur() == \old(getCurseur()) + 1;
	void avancer();

	/** Avancer le curseur d'une position à gauche. */
	// @ requires getCurseur() > 1; // pas en début de ligne
	// @ ensures getCurseur() == \old(getCurseur()) - 1;
	void reculer();

	/** Placer le curseur sur le premier caractère. */
	// @ requires getLongueur() > 0; // ligne non vide
	// @ ensures getCurseur() == 1;
	void raz();

	/** Remplacer le caractère sous le curseur par le caractère c. */
	// @ requires getLongueur() > 0;
	// @ ensures getCourant() == c;
	void remplacer(char c);

	/**
	 * Supprimer le caractère sous le curseur. La position du curseur reste
	 * inchangée.
	 */
	// @ requires getLongueur() > 0;
	// @ ensures getLongueur() == \old(getLongueur()) - 1;
	// @ ensures getCurseur() == Math.min(\old(getCurseur()), getLongueur());
	void supprimer();

	/**
	 * Ajouter le caractère c avant le curseur. Le curseur reste sur le même
	 * caractère.
	 */
	// @ requires getLongueur() > 0;
	// @ ensures getLongueur() == \old(getLongueur()) + 1;
	// @ ensures getCurseur() == \old(getCurseur()) + 1;
	// @ ensures getCourant() == \old(getCourant());
	void ajouterAvant(char c);

	/**
	 * Ajouter le caractère c après le curseur. Le curseur reste sur le même
	 * caractère.
	 */
	// @ requires getLongueur() > 0;
	// @ ensures getLongueur() == \old(getLongueur()) + 1;
	// @ ensures getCurseur() == \old(getCurseur());
	// @ ensures getCourant() == \old(getCourant());
	void ajouterApres(char c);

	/** Afficher la ligne */
	/* @ pure @ */ void afficher();

	/** Ajouter le caractère c à la fin de la ligne */
	// @ ensures getLongueur() == \old(getLongueur()) + 1;
	// @ ensures ieme(getLongueur()) == c;
	// @ ensures (\forall int i; 1 <= i && i <= \old(getLongueur());
	// @ ieme(i) == \old(ieme(i)));
	// @ ensures getLongueur() > 1 ==> getCourant() == \old(getCourant());
	// @ ensures getCurseur() == Math.max(1, \old(getCurseur()));
	void ajouterFin(char c);

	/** Ajouter le caractère c au début de la ligne */
	// @ ensures getLongueur() == \old(getLongueur()) + 1;
	// @ ensures ieme(1) == c;
	// @ ensures (\forall int j; 2 <= j && j <= getLongueur();
	// @ ieme(j) == \old(ieme(j - 1)));
	// @ ensures getLongueur() > 1 ==> getCourant() == \old(getCourant());
	// @ ensures getCurseur() == \old(getCurseur()) + 1;
	void ajouterDebut(char c);

	/** Supprimer le premier caractère de la ligne */
	// @ requires getLongueur() > 0;
	// @ ensures getLongueur() == \old(getLongueur()) - 1;
	// @ ensures \old(getCurseur()) != 1 ==> getCourant() == \old(getCourant());
	// @ ensures getCurseur() ==
	// @ Math.min(Math.max(\old(getCurseur()) - 1, 1), getLongueur());
	void supprimerPremier();

	/** Supprimer le dernier caractère de la ligne */
	// @ requires getLongueur() > 0;
	// @ ensures getLongueur() == \old(getLongueur()) - 1;
	// @ ensures \old(getCurseur()) < \old(getLongueur())
	// @ ==> getCourant() == \old(getCourant());
	// @ ensures getCurseur() == Math.min(\old(getCurseur()), getLongueur());
	void supprimerDernier();
}
