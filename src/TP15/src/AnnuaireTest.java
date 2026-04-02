package TP15.src;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

/**
 * Programme de test de la classe Annuaire.
 *
 * @author Xavier Crégut <Prenom.Nom@enseeiht.fr>
 */
public class AnnuaireTest {

	protected Annuaire annuaire;

	@Before
	public void setUp() throws DejaPresentException {
		this.annuaire = new Annuaire();
		this.annuaire.enregistrerArrivee("Xavier", "F305");
		this.annuaire.enregistrerArrivee("Marc", "F305");
		this.annuaire.enregistrerArrivee("Aurélie", "F307");
		this.annuaire.enregistrerArrivee("Christiane", "F309");
		this.annuaire.enregistrerArrivee("Marcel", "F309");
	}

	@Test
	public void testerBureau() throws PersonnelInconnuException {
		assertEquals("F305", this.annuaire.bureau("Xavier"));
		assertEquals("F305", this.annuaire.bureau("Marc"));
		assertEquals("F307", this.annuaire.bureau("Aurélie"));
		assertEquals("F309", this.annuaire.bureau("Christiane"));
		assertEquals("F309", this.annuaire.bureau("Marcel"));
	}

	@Test
	public void testerModifierBureau() throws PersonnelInconnuException {
		this.annuaire.modifierBureau("Xavier", "F301");
		assertEquals("F301", this.annuaire.bureau("Xavier"));
	}

	@Test
	public void testerPersonnels() {
		assertEquals(5, this.annuaire.personnels().size());
		assertTrue(this.annuaire.personnels().contains("Xavier"));
		assertTrue(this.annuaire.personnels().contains("Marc"));
		assertTrue(this.annuaire.personnels().contains("Aurélie"));
		assertTrue(this.annuaire.personnels().contains("Christiane"));
		assertTrue(this.annuaire.personnels().contains("Marcel"));
	}

	@Test
	public void testerPersonnelsParBureau() {
		Collection<String> personnels = this.annuaire.personnels("F307");
		assertEquals(1, personnels.size());
		assertTrue(personnels.contains("Aurélie"));

		personnels = this.annuaire.personnels("F305");
		assertEquals(2, personnels.size());
		assertTrue(personnels.contains("Xavier"));
		assertTrue(personnels.contains("Marc"));

		personnels = this.annuaire.personnels("Salle virtuelle");
		assertEquals(0, personnels.size());
	}

	@Test
	public void testerBureaux() {
		assertEquals(3, this.annuaire.bureaux().size());
		assertTrue(this.annuaire.bureaux().contains("F305"));
		assertTrue(this.annuaire.bureaux().contains("F307"));
		assertTrue(this.annuaire.bureaux().contains("F309"));
	}

	@Test
	public void testerEnregistrerDepart() throws PersonnelInconnuException {
		this.annuaire.enregistrerDepart("Marcel");
		assertFalse(this.annuaire.personnels().contains("Marcel"));
		assertEquals(1, this.annuaire.personnels("F309").size());
		assertFalse(this.annuaire.personnels().contains("Marcel"));
	}

	@Test
	public void testerOccupationBureaux() {
		assertEquals(1, this.annuaire.occupationBureaux().get("F307").size());
		assertTrue(this.annuaire.occupationBureaux().get("F307").contains("Aurélie"));

		assertEquals(2, this.annuaire.occupationBureaux().get("F305").size());
		assertTrue(this.annuaire.occupationBureaux().get("F305").contains("Marc"));
		assertTrue(this.annuaire.occupationBureaux().get("F305").contains("Xavier"));

		assertEquals(2, this.annuaire.occupationBureaux().get("F309").size());
		assertTrue(this.annuaire.occupationBureaux().get("F309").contains("Christiane"));
		assertTrue(this.annuaire.occupationBureaux().get("F309").contains("Marcel"));

		assertEquals(3, this.annuaire.occupationBureaux().keySet().size());
	}

	@Test(expected = DejaPresentException.class)
	public void testerEnregistrerArrivee() throws DejaPresentException {
		this.annuaire.enregistrerArrivee("Xavier", "FFFF");
	}

	@Test(expected = NullPointerException.class)
	public void testerEnregistrerArriveeNomNul() throws DejaPresentException {
		this.annuaire.enregistrerArrivee(null, "FFFF");
	}

	@Test(expected = NullPointerException.class)
	public void testerEnregistrerArriveeBureauNul() throws DejaPresentException {
		this.annuaire.enregistrerArrivee("Xavier", null);
	}

	@Test(expected = PersonnelInconnuException.class)
	public void testerModifierBureauInconnu() throws PersonnelInconnuException {
		this.annuaire.modifierBureau("Superman", "FFFF");
	}

	@Test(expected = NullPointerException.class)
	public void testerModifierBureauNomNul() throws PersonnelInconnuException {
		this.annuaire.modifierBureau(null, "FFFF");
	}

	@Test(expected = NullPointerException.class)
	public void testerModifierBureauBureauNul() throws PersonnelInconnuException {
		this.annuaire.modifierBureau("Xavier", null);
	}

	@Test(expected = PersonnelInconnuException.class)
	public void testerBureauDInconnu() throws PersonnelInconnuException {
		this.annuaire.bureau("Superman");
	}

	@Test(expected = NullPointerException.class)
	public void testerBureauNomNul() throws PersonnelInconnuException {
		this.annuaire.bureau(null);
	}

	@Test(expected = NullPointerException.class)
	public void testerPersonnelsBureauNul() {
		this.annuaire.personnels(null);
	}

	@Test(expected = PersonnelInconnuException.class)
	public void testerEnregistrerDepartInconnu() throws PersonnelInconnuException {
		this.annuaire.enregistrerDepart("Superman");
	}

	@Test(expected = NullPointerException.class)
	public void testerEnregistrerDepartNomNul() throws PersonnelInconnuException {
		this.annuaire.enregistrerDepart(null);
	}

}
