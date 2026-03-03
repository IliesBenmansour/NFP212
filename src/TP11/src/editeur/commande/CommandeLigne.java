package TP11.src.editeur.commande;

import TP11.src.editeur.Ligne;
import TP11.src.menu.Commande;

/**
 * Une CommandeLigne est une commande qui travaille sur une ligne de l'éditeur
 * orienté ligne.
 *
 * @author Xavier Crégut
 * @version 1.4
 */
abstract public class CommandeLigne implements Commande {
	/** La ligne manipulée par la commande. */
	protected Ligne ligne;

	/**
	 * Initialiser la ligne sur laquelle travaille cette commande.
	 *
	 * @param l la ligne
	 */
	// @ requires l != null; // la ligne doit être définie
	public CommandeLigne(Ligne l) {
		ligne = l;
	}

}
