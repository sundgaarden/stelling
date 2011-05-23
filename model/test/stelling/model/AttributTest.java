package stelling.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

public class AttributTest {

	private static final List<String> FELT_NAVNE = cut(" Navn Id Pris Farve Materiale Placering");
	private static final List<String> SORT_RAMME = cut("|Sort Træramme|0001|500.00|Sort|Træ|Sydvest");
	private static final List<String> BLAA_RAMME = cut("|Blå Træramme|0001|480.00|Blå|Træ|Sydvest");
	private static final List<String> HVID_RAMME = cut("|Hvid Træramme|0001|480.00|Hvid|Træ|Syd");
	private static final List<String> KLAR_RAMME = cut("|Klar Træramme|0001|450.00|Klar|Træ|Sydøst");

	@Test
	public void testValgAttribut() {
		ValgAttributType type = new ValgAttributType("Tilpasselig Ramme",
				FELT_NAVNE, AttributBeregner.OMKREDS_PRIS);
		ValgAttribut sort = type.tilfoejAttribut(SORT_RAMME);
		ValgAttribut blaa = type.tilfoejAttribut(BLAA_RAMME);
		ValgAttribut hvid = type.tilfoejAttribut(HVID_RAMME);
		ValgAttribut klar = type.tilfoejAttribut(KLAR_RAMME);
		Assert.assertEquals(type.nil(), type.nyAttribut());
		type.setDefaultVaerdi(klar);
		Assert.assertEquals(klar, type.nyAttribut());
		LaengdeMaal hoejde = new LaengdeMaal(300);
		LaengdeMaal bredde = new LaengdeMaal(200);
		Assert.assertEquals(0.0, type.nil().pris(hoejde, bredde).vaerdi());
		Assert.assertEquals(500.0, sort.pris(hoejde, bredde).vaerdi());
		Assert.assertEquals(480.0, blaa.pris(hoejde, bredde).vaerdi());
		Assert.assertEquals(480.0, hvid.pris(hoejde, bredde).vaerdi());
		Assert.assertEquals(450.0, klar.pris(hoejde, bredde).vaerdi());
	}

	@Test
	public void testCut() {
		Assert.assertTrue(sameSet(new String[] {}, cut("")));
		Assert.assertTrue(sameSet(new String[] {}, cut(" ")));
		Assert.assertTrue(sameSet(new String[] {}, cut("x")));
		Assert.assertTrue(sameSet(new String[] { "a" }, cut(" a")));
		Assert.assertTrue(sameSet(new String[] { "a" }, cut("xa")));
		Assert.assertTrue(sameSet(new String[] { "a", "" }, cut(" a ")));
		Assert.assertTrue(sameSet(new String[] { "a", "bb", "ccc" },
				cut(" a bb ccc")));
		Assert.assertTrue(sameSet(new String[] { "a a ", "bb b" },
				cut("xa a xbb b")));
		Assert.assertTrue(sameSet(new String[] { "Navn", "Id", "Pris", "Farve",
				"Materiale", "Placering" },
				cut(" Navn Id Pris Farve Materiale Placering")));
	}

	private static List<String> cut(String text) {
		if (text.length() < 2) {
			return Collections.emptyList();
		}
		List<String> snippets = new ArrayList<String>();
		int here = 0;
		int next = -1;
		char split = text.charAt(here);
		while (next < text.length()) {
			next = text.indexOf(split, here + 1) == -1 ? text.length() : text
					.indexOf(split, here + 1);
			snippets.add(text.substring(here + 1, next));
			here = next;
		}
		return snippets;
	}

	private <E> boolean sameSet(E[] expected, List<E> actual) {
		if (expected.length != actual.size()) {
			return false;
		}
		for (int i = 0; i < expected.length; i++) {
			if (!expected[i].equals(actual.get(i))) {
				return false;
			}
		}
		return true;
	}
}
