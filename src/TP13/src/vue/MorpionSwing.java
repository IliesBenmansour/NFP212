package TP13.src.vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import TP13.src.controller.ActionNouvellePartie;
import TP13.src.modele.ModeleMorpion;
import TP13.src.modele.ModeleMorpionSimple;

/**
 * Programmation d'un jeu de Morpion avec une interface graphique Swing.
 *
 * REMARQUE : Dans cette solution, le patron MVC n'a pas été appliqué ! On a un
 * modèle (?), une vue et un contrôleur qui sont fortement liés.
 *
 * @author Xavier Crégut
 * @version $Revision: 1.4 $
 */

public class MorpionSwing {

	// les images à utiliser en fonction de l'état du jeu.
	private static final Map<ModeleMorpion.Etat, ImageIcon> images = new HashMap<ModeleMorpion.Etat, ImageIcon>();
	static {
		images.put(ModeleMorpion.Etat.VIDE, new ImageIcon("src/TP13/blanc.jpg"));
		images.put(ModeleMorpion.Etat.CROIX, new ImageIcon("src/TP13/croix.jpg"));
		images.put(ModeleMorpion.Etat.ROND, new ImageIcon("src/TP13/rond.jpg"));
	}

// Choix de réalisation :
// ----------------------
//
//  Les attributs correspondant à la structure fixe de l'IHM sont définis
//	« final static » pour montrer que leur valeur ne pourra pas changer au
//	cours de l'exécution.  Ils sont donc initialisés sans attendre
//  l'exécution du constructeur !

	private ModeleMorpion modele; // le modèle du jeu de Morpion

//  Les éléments de la vue (IHM)
//  ----------------------------

	/** Fenêtre principale */
	private JFrame fenetre;

	/** Bouton pour quitter */
	private final JButton boutonQuitter = new JButton("Q");

	/** Bouton pour commencer une nouvelle partie */
	private final JButton boutonNouvellePartie = new JButton("N");

	/** Cases du jeu */
	private final JLabel[][] cases = new JLabel[3][3];

	/** Zone qui indique le joueur qui doit jouer */
	private final JLabel joueur = new JLabel();

// Le constructeur
// ---------------

	/** Construire le jeu de morpion */
	public MorpionSwing() {
		this(new ModeleMorpionSimple());
	}

	/** Construire le jeu de morpion */
	public MorpionSwing(ModeleMorpion modele) {
		// Initialiser le modèle
		this.modele = modele;

		for (int i = 0; i < this.cases.length; i++) {
			for (int j = 0; j < this.cases[i].length; j++) {
				this.cases[i][j] = new JLabel();
			}
		}

		this.recommencer();
		this.fenetre = new JFrame("Morpion");
		this.fenetre.setLocation(100, 200);

		JPanel panelPrincipal = new JPanel(new BorderLayout());
		JPanel panelJeu = new JPanel(new GridLayout(3, 3));

		for (int i = 0; i < this.cases.length; i++) {
			for (int j = 0; j < this.cases[i].length; j++) {
				final int x = i;
				final int y = j;
				this.cases[i][j].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						jouerCase(x, y);
					}
				});
				panelJeu.add(this.cases[i][j]);
			}
		}

		JPanel panelBoutons = new JPanel();
		boutonNouvellePartie.addActionListener(new ActionNouvellePartie(this));

		boutonQuitter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		panelBoutons.add(boutonNouvellePartie);
		panelBoutons.add(boutonQuitter);
		panelPrincipal.add(joueur, BorderLayout.NORTH);
		panelPrincipal.add(panelJeu, BorderLayout.CENTER);
		panelPrincipal.add(panelBoutons, BorderLayout.SOUTH);

		this.fenetre.add(panelPrincipal);
		this.fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.fenetre.pack(); // redimmensionner la fenêtre
		this.fenetre.setVisible(true); // l'afficher

	}

	/** Recommencer une nouvelle partie. */
	public void recommencer() {
		this.modele.recommencer();
		mettreAJourAffichage();
	}

	public void jouerCase(int x, int y) {
		try {
			if (!modele.estTerminee()) {
				modele.cocher(x, y);
				mettreAJourAffichage();
			}
		} catch (Exception e) {
		}
	}

	/** Mettre à jour l'affichage complet */
	private void mettreAJourAffichage() {
		// Mettre à jour les cases avec les images
		for (int i = 0; i < this.cases.length; i++) {
			for (int j = 0; j < this.cases[i].length; j++) {
				this.cases[i][j].setIcon(images.get(this.modele.getValeur(i, j)));
			}
		}

		// Mettre à jour le joueur courant
		if (!modele.estTerminee()) {
			joueur.setText("Joueur: ");
			joueur.setIcon(images.get(modele.getJoueur()));
		}
	}

// La méthode principale
// ---------------------

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MorpionSwing();
			}
		});
	}

}
