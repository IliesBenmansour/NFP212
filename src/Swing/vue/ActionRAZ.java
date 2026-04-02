package Swing.vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import Swing.modele.Compteur;

public class ActionRAZ implements ActionListener {

	private Compteur compteur;
	private JLabel afficheur;

	public ActionRAZ(Compteur compteur, JLabel afficheur) {
		this.compteur = compteur;
		this.afficheur = afficheur;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		compteur.raz();
		afficheur.setText("" + compteur.getValeur());
	}

}
