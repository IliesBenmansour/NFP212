package Swing.vue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Swing.modele.Compteur;

class CompteurGUI {

	public CompteurGUI(Compteur compteur) {
		// Création de la fenêtre
		JFrame fenetre = new JFrame("Compteur");

		// Récupération du contenu et définition du layout
		java.awt.Container contenu = fenetre.getContentPane();
		contenu.setLayout(new java.awt.FlowLayout());

		// Afficheur du compteur
		JLabel afficheur = new JLabel("" + compteur.getValeur());
		contenu.add(afficheur);

		// Boutons
		JButton bINC = new JButton("INC");
		contenu.add(bINC);

		JButton bRAZ = new JButton("RAZ");
		contenu.add(bRAZ);

		JButton bQuitter = new JButton("QUITTER");
		contenu.add(bQuitter);

		// Dimensionner la fenêtre et la rendre visible
		fenetre.pack();
		fenetre.setVisible(true);

		// Option pour fermer la fenêtre en cliquant sur la croix
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bQuitter.addActionListener(new ActionQuitter());
		bRAZ.addActionListener(new ActionRAZ(compteur, afficheur));
		bINC.addActionListener(new ActionINC(compteur, afficheur));
	}

	public static void main(String[] args) {
		new CompteurGUI(new Compteur(0));
	}
}