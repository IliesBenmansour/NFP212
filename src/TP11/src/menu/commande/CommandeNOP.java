package TP11.src.menu.commande;

import TP11.src.menu.Commande;

/**
 * Commande... qui ne fait rien !
 *
 * @author Xavier Crégut
 * @version $Revision: 1.1 $
 */
final public class CommandeNOP implements Commande {

	public void executer() {
	}

	public boolean estExecutable() {
		return true;
	}

}
