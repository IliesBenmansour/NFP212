package allumettes;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/** Strategie swing du PR3 : le joueur choisi son nombre d'allumettes
 *  en cliquant sur les boutons d'une petite fenetre.
 *  L'arbitre tourne dans le thread main et la fenetre dans le thread
 *  de swing, donc faut synchroniser les deux avec wait/notify sur un
 *  verrou (j'ai repris le code donné dans le sujet).
 *  La fenetre est cachée une fois le choix fait et revient au tour
 *  suivant du joueur.
 */
public class StrategieSwing implements Strategie {

	private static final int TAILLE_POLICE = 48;

	// objets partagés entre le thread main et celui de swing
	private final Object verrou = new Object();
	private int choix = 0; // nb d'allumettes choisi (0 = pas encore choisi)
	private boolean triche = false; // vrai qd on a cliqué sur tricher
	private int nbTriche = 1; // combien on enleve en douce

	private boolean fenetreCreee = false;
	private JFrame fenetre;
	private JLabel affichage;
	private JButton[] boutons = new JButton[Jeu.PRISE_MAX];
	private JTextField champTriche;

	@Override
	public int getPrise(Jeu jeu, String nomJoueur) {
		if (!this.fenetreCreee) {
			// on cree la fenetre qu'au premier tour (c'est la qu'on
			// connait le nom du joueur)
			this.fenetreCreee = true;
			SwingUtilities.invokeLater(() -> creerFenetre(nomJoueur));
		}
		int restantes = jeu.getNombreAllumettes();
		SwingUtilities.invokeLater(() -> rafraichir(restantes));

		// ici on est dans le thread main
		while (true) {
			int nb;
			boolean veutTricher;
			int aEnlever;
			synchronized (this.verrou) {
				while (this.choix < 1 && !this.triche) { // rien de choisi
					try {
						this.verrou.wait(); // on attend que le joueur clique
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
					}
				}
				nb = this.choix;
				veutTricher = this.triche;
				aEnlever = this.nbTriche;
				this.choix = 0;
				this.triche = false;
			}

			if (veutTricher) {
				// apres avoir triché faut quand meme jouer un coup
				tricherSur(jeu, aEnlever);
				int maj = jeu.getNombreAllumettes();
				SwingUtilities.invokeLater(() -> rafraichir(maj));
			} else {
				// on cache la fenetre jusqu'a son prochain tour
				SwingUtilities.invokeLater(() -> this.fenetre.setVisible(false));
				return nb;
			}
		}
	}

	// triche discrete. si il reste qu'une allumette on en remet une
	// (cas special du sujet), sinon on enleve nb. en mode pas confiant
	// la procuration leve OperationInterditeException et ca remonte
	// jusqu'a l'arbitre qui arrete la partie
	private void tricherSur(Jeu jeu, int nb) {
		try {
			if (jeu.getNombreAllumettes() == 1) {
				jeu.retirer(-1); // retirer -1 ca revient a en rajouter une
				System.out.println("[Je triche... 1 allumette en plus]");
			} else if (nb < 1 || nb >= jeu.getNombreAllumettes()) {
				// faut laisser au moin une allumette sinon le jeu est cassé
				System.out.println("[Je ne peux pas tricher de " + nb + "]");
			} else {
				jeu.retirer(nb);
				String s = (nb > 1) ? "s" : "";
				System.out.println("[Je triche... " + nb + " allumette" + s
						+ " en moins]");
			}
		} catch (CoupInvalideException e) {
			System.out.println("[Je ne peux pas tricher : "
					+ e.getProbleme() + "]");
		}
	}

	// ----- partie coté swing -----

	private void creerFenetre(String nomJoueur) {
		this.fenetre = new JFrame(nomJoueur + " ?");
		this.fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// en haut le bouton tricher avec le champ pour dire combien
		JPanel haut = new JPanel(new FlowLayout());
		JButton boutonTricher = new JButton("tricher");
		boutonTricher.addActionListener(e -> demanderTriche());
		this.champTriche = new JTextField("1", 2);
		haut.add(boutonTricher);
		haut.add(this.champTriche);
		this.fenetre.add(haut, BorderLayout.NORTH);

		// au milieu le nombre d'allumettes en gros
		this.affichage = new JLabel("", SwingConstants.CENTER);
		this.affichage.setFont(new Font(Font.SANS_SERIF, Font.BOLD,
				TAILLE_POLICE));
		this.fenetre.add(this.affichage, BorderLayout.CENTER);

		// en bas les boutons 1 2 3
		JPanel bas = new JPanel(new GridLayout(1, Jeu.PRISE_MAX));
		for (int i = 1; i <= Jeu.PRISE_MAX; i++) {
			final int prise = i;
			JButton bouton = new JButton(Integer.toString(prise));
			bouton.addActionListener(e -> choisir(prise));
			this.boutons[i - 1] = bouton;
			bas.add(bouton);
		}
		this.fenetre.add(bas, BorderLayout.SOUTH);

		this.fenetre.pack();
		this.fenetre.setLocationByPlatform(true);
	}

	private void rafraichir(int restantes) {
		this.affichage.setText(Integer.toString(restantes));
		// on grise les boutons trop grand (comme sur la figure du sujet)
		for (int i = 1; i <= Jeu.PRISE_MAX; i++) {
			this.boutons[i - 1].setEnabled(i <= restantes);
		}
		this.fenetre.setVisible(true);
	}

	// appelé par le thread swing quand on clique sur 1 2 ou 3
	private void choisir(int prise) {
		synchronized (this.verrou) {
			this.choix = prise;
			this.verrou.notify(); // reveille le main qui attend
		}
	}

	// appelé par le thread swing quand on clique sur tricher
	private void demanderTriche() {
		int nb;
		try {
			nb = Integer.parseInt(this.champTriche.getText().trim());
		} catch (NumberFormatException e) {
			nb = 1; // si c'est pas un nombre on triche de 1 par defaut
		}
		synchronized (this.verrou) {
			this.triche = true;
			this.nbTriche = nb;
			this.verrou.notify(); // reveille le main qui attend
		}
	}

}
