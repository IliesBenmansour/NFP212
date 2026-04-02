package TP13.src.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import TP13.src.vue.MorpionSwing;

public class ActionNouvellePartie implements ActionListener {
	private MorpionSwing morpionSwing;

	public ActionNouvellePartie(MorpionSwing morpionSwing) {
		this.morpionSwing = morpionSwing;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.morpionSwing.recommencer();
	}

}
