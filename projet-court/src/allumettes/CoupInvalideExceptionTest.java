package allumettes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CoupInvalideExceptionTest {

	@Test
	public void testCoupEtProbleme() {
		CoupInvalideException e = new CoupInvalideException(0, "< 1");
		assertEquals(0, e.getCoup());
		assertEquals("< 1", e.getProbleme());
		assertEquals("Coup invalide car < 1 : 0", e.getMessage());
	}

	@Test
	public void testCoupTropGrand() {
		CoupInvalideException e = new CoupInvalideException(4, "> 3");
		assertEquals(4, e.getCoup());
		assertEquals("> 3", e.getProbleme());
	}

	@Test
	public void testOperationInterditePorteLeNom() {
		OperationInterditeException e =
				new OperationInterditeException("Picsou");
		assertEquals("Picsou", e.getMessage());
	}

}
