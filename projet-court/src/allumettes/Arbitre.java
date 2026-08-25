package allumettes;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/** La classe Arbitre gere le deroulement de la partie entre 2 joueurs.
 *  Chaque joueur joue via une {@link Procuration} qui detecte (et,
 *  selon le mode, autorise ou interdit) les tentatives de triche.
 *  A la fin de la partie, un document XML deroulement.xml est genere,
 *  valide vis-a-vis de src/allumettes/deroulement.dtd.
 */
public class Arbitre {

	private static final String FICHIER_XML = "deroulement.xml";
	private static final String DTD_SYSTEM = "src/allumettes/deroulement.dtd";

	private Joueur joueur1;
	private Joueur joueur2;
	private boolean confiant;

	private Document doc;
	private Element racine;

	// par defaut l'arbitre refuse la triche
	public Arbitre(Joueur joueur1, Joueur joueur2) {
		this(joueur1, joueur2, false);
	}

	/** Creer un arbitre avec les deux joueurs.
	 *  @param joueur1 le premier joueur
	 *  @param joueur2 le deuxieme joueur
	 *  @param confiant true si la triche est acceptee, false si elle
	 *      provoque l'abandon de la partie
	 */
	public Arbitre(Joueur joueur1, Joueur joueur2, boolean confiant) {
		this.joueur1 = joueur1;
		this.joueur2 = joueur2;
		this.confiant = confiant;
	}

	/** Lancer et arbitrer la partie.
	 *  @param jeu le jeu reel sur lequel on joue (modifie au fil de la partie)
	 */
	public void arbitrer(Jeu jeu) {
		initialiserXml();

		Plateau plateau = (Plateau) jeu;
		Procuration proc1 = new Procuration(plateau, this.confiant,
				this.joueur1.getNom());
		Procuration proc2 = new Procuration(plateau, this.confiant,
				this.joueur2.getNom());

		int numTour = 0;
		Joueur joueurCourant;
		Procuration procCourante;

		try {
			while (jeu.getNombreAllumettes() > 0) {
				if (numTour % 2 == 0) {
					joueurCourant = this.joueur1;
					procCourante = proc1;
				} else {
					joueurCourant = this.joueur2;
					procCourante = proc2;
				}

				System.out.println("Allumettes restantes : "
						+ jeu.getNombreAllumettes());

				try {
					int prise = joueurCourant.getPrise(procCourante);
					String s = (prise > 1) ? "s" : "";
					System.out.println(joueurCourant.getNom() + " prend " + prise
							+ " allumette" + s + ".");
					jeu.retirer(prise);
					ajouterCoup(numTour + 1, joueurCourant.getNom(), prise);
					numTour++;
				} catch (CoupInvalideException e) {
					System.out.println("Impossible ! Nombre invalide : "
							+ e.getCoup() + " (" + e.getProbleme() + ")");
				}
				System.out.println();
			}

			// le dernier joueur qui a joue a pris la derniere allumette donc il perd
			String nomGagnant;
			if ((numTour - 1) % 2 == 0) {
				System.out.println(this.joueur1.getNom() + " perd !");
				System.out.println(this.joueur2.getNom() + " gagne !");
				nomGagnant = this.joueur2.getNom();
			} else {
				System.out.println(this.joueur2.getNom() + " perd !");
				System.out.println(this.joueur1.getNom() + " gagne !");
				nomGagnant = this.joueur1.getNom();
			}
			ajouterGagnant(nomGagnant);
		} catch (OperationInterditeException e) {
			System.out.println("Abandon de la partie car "
					+ e.getMessage() + " triche !");
			ajouterTriche(e.getMessage());
		}

		ecrireXml();
	}

	private void initialiserXml() {
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			this.doc = builder.newDocument();
			this.racine = this.doc.createElement("deroulement");
			this.doc.appendChild(this.racine);
		} catch (ParserConfigurationException e) {
			System.err.println("Erreur initialisation XML : " + e.getMessage());
		}
	}

	private void ajouterCoup(int numero, String joueur, int prises) {
		Element coup = this.doc.createElement("coup");
		coup.setAttribute("numero", Integer.toString(numero));
		coup.setAttribute("joueur", joueur);
		coup.setAttribute("prises", Integer.toString(prises));
		this.racine.appendChild(coup);
	}

	private void ajouterGagnant(String nom) {
		Element gagnant = this.doc.createElement("gagnant");
		gagnant.setAttribute("nom", nom);
		this.racine.appendChild(gagnant);
	}

	private void ajouterTriche(String nomTricheur) {
		Element triche = this.doc.createElement("triche");
		triche.setAttribute("joueur", nomTricheur);
		this.racine.appendChild(triche);
	}

	private void ecrireXml() {
		try {
			Transformer transformer = TransformerFactory.newInstance()
					.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, DTD_SYSTEM);
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(
					"{http://xml.apache.org/xslt}indent-amount", "2");
			transformer.transform(new DOMSource(this.doc),
					new StreamResult(new File(FICHIER_XML)));
		} catch (TransformerException e) {
			System.err.println("Erreur ecriture XML : " + e.getMessage());
		}
	}

}
