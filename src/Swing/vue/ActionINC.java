package Swing.vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import Swing.modele.Compteur;

public class ActionINC implements ActionListener {

	private Compteur compteur;
	private JLabel afficheur;

	public ActionINC(Compteur compteur, JLabel afficheur) {
		this.compteur = compteur;
		this.afficheur = afficheur;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		compteur.incrementer();
		afficheur.setText("" + compteur.getValeur());
	}

}
