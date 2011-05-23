package stelling.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Test cases der dækker attribut-klasserne
 */
public class AttributTest {

	private static final List<String> VALG_FELT_NAVNE = cut(" Navn Id Pris Farve Materiale Placering");
	private static final List<String> SORT_RAMME = cut("|Sort Træramme|0001|500.00|Sort|Træ|Sydvest");
	private static final List<String> BLAA_RAMME = cut("|Blå Træramme|0001|480.00|Blå|Træ|Sydvest");
	private static final List<String> HVID_RAMME = cut("|Hvid Træramme|0001|480.00|Hvid|Træ|Syd");
	private static final List<String> KLAR_RAMME = cut("|Klar Træramme|0001|450.00|Klar|Træ|Sydøst");

	private static final List<String> PPTK_FELT_NAVNE = cut(" Navn Pris Farve Kernefarve");
	private static final List<String> SORT_SORT = cut("|Sort/Sort|450.00|Sort|Sort");
	private static final List<String> SORT_HVID = cut("|Sort/Hvid|450.00|Sort|Hvid");
	private static final List<String> HVID_HVID = cut("|Hvid/Hvid|450.00|Hvid|Hvid");

	private static final Beloeb PRIS_PER_EKSTRA_UDSKAERING = new Beloeb(15);
	private static final Beloeb EKSTRA_FOR_OERN = new Beloeb(225);
	private static final LaengdeMaal HOEJDE = new LaengdeMaal(300);
	private static final LaengdeMaal BREDDE = new LaengdeMaal(200);

	@Test
	public void testValgAttribut() {
		ValgAttributType type = new ValgAttributType("Tilpasselig Ramme",
				VALG_FELT_NAVNE, AttributBeregner.OMKREDS);
		ValgAttribut sort = type.tilfoejAttribut(SORT_RAMME);
		ValgAttribut blaa = type.tilfoejAttribut(BLAA_RAMME);
		ValgAttribut hvid = type.tilfoejAttribut(HVID_RAMME);
		ValgAttribut klar = type.tilfoejAttribut(KLAR_RAMME);
		Assert.assertEquals(type.nil(), type.nyAttribut());
		type.setDefaultVaerdi(klar);
		Assert.assertEquals(klar, type.nyAttribut());
		Assert.assertEquals(0.0, type.nil().pris(HOEJDE, BREDDE).vaerdi());
		Assert.assertEquals(500.0, sort.pris(HOEJDE, BREDDE).vaerdi());
		Assert.assertEquals(480.0, blaa.pris(HOEJDE, BREDDE).vaerdi());
		Assert.assertEquals(480.0, hvid.pris(HOEJDE, BREDDE).vaerdi());
		Assert.assertEquals(450.0, klar.pris(HOEJDE, BREDDE).vaerdi());
	}

	@Test
	public void testAntalAttribut() {
		AntalAttributType type = new AntalAttributType("Breddestivere",
				new Beloeb(25));
		AntalAttribut attribut = (AntalAttribut) type.nyAttribut();
		Assert.assertEquals(0.0, attribut.pris(HOEJDE, BREDDE).vaerdi());
		Assert.assertEquals(0.0, attribut
				.pris(LaengdeMaal.NUL, LaengdeMaal.NUL).vaerdi());
		attribut.setAntal(1);
		Assert.assertEquals(25.0, attribut.pris(HOEJDE, BREDDE).vaerdi());
		Assert.assertEquals(25.0,
				attribut.pris(LaengdeMaal.NUL, LaengdeMaal.NUL).vaerdi());
		attribut.setAntal(2);
		Assert.assertEquals(50.0, attribut.pris(HOEJDE, BREDDE).vaerdi());
		Assert.assertEquals(50.0,
				attribut.pris(LaengdeMaal.NUL, LaengdeMaal.NUL).vaerdi());
		attribut.setAntal(5);
		Assert.assertEquals(125.0, attribut.pris(HOEJDE, BREDDE).vaerdi());
		Assert.assertEquals(125.0,
				attribut.pris(LaengdeMaal.NUL, LaengdeMaal.NUL).vaerdi());
	}

	@Test
	public void testManuelAttribut() {
		ManuelAttributType type = new ManuelAttributType(
				"Kompliceret udskæring");
		ManuelAttribut attribut = (ManuelAttribut) type.nyAttribut();
		Assert.assertEquals(0.0, attribut.pris(HOEJDE, BREDDE).vaerdi());
		Assert.assertEquals(0.0, attribut
				.pris(LaengdeMaal.NUL, LaengdeMaal.NUL).vaerdi());
		attribut.setPris(new Beloeb(12));
		Assert.assertEquals(12.0, attribut.pris(HOEJDE, BREDDE).vaerdi());
		Assert.assertEquals(12.0,
				attribut.pris(LaengdeMaal.NUL, LaengdeMaal.NUL).vaerdi());
		attribut.setPris(new Beloeb(140.75));
		Assert.assertEquals(140.75, attribut.pris(HOEJDE, BREDDE).vaerdi());
		Assert.assertEquals(140.75,
				attribut.pris(LaengdeMaal.NUL, LaengdeMaal.NUL).vaerdi());
	}

	@Test
	public void testSammensatAttribut() {
		ValgAttributType kartonType = new ValgAttributType("Karton",
				PPTK_FELT_NAVNE, AttributBeregner.AREAL);
		ValgAttribut sortSort = kartonType.tilfoejAttribut(SORT_SORT);
		ValgAttribut sortHvid = kartonType.tilfoejAttribut(SORT_HVID);
		ValgAttribut hvidHvid = kartonType.tilfoejAttribut(HVID_HVID);
		AntalAttributType udskaeringType = new AntalAttributType(
				"Ekstra udskæringer", PRIS_PER_EKSTRA_UDSKAERING);
		ManuelAttributType justeringType = new ManuelAttributType("Justering");
		List<AttributType> underAttributTyper = Arrays
				.asList(new AttributType[] { kartonType, udskaeringType,
						justeringType });
		SammensatAttributType pptType = new SammensatAttributType(
				"Passepartout", underAttributTyper);
		SammensatAttribut attribut = (SammensatAttribut) pptType.nyAttribut();

		// Default
		Assert.assertEquals(0.0, attribut.pris(HOEJDE, BREDDE).vaerdi());
		Assert.assertEquals(0.0, attribut
				.pris(LaengdeMaal.NUL, LaengdeMaal.NUL).vaerdi());

		// Set karton
		attribut.setUnderAttribut(sortHvid);
		Assert.assertEquals(27.0, attribut.pris(HOEJDE, BREDDE).vaerdi());
		Assert.assertEquals(0.0, attribut
				.pris(LaengdeMaal.NUL, LaengdeMaal.NUL).vaerdi());

		// Tilføj to ekstra udskæringer
		((AntalAttribut) attribut.underAttribut(udskaeringType)).setAntal(2);
		Assert.assertEquals(27.0 + 2 * PRIS_PER_EKSTRA_UDSKAERING.vaerdi(),
				attribut.pris(HOEJDE, BREDDE).vaerdi());
		Assert.assertEquals(2 * PRIS_PER_EKSTRA_UDSKAERING.vaerdi(), attribut
				.pris(LaengdeMaal.NUL, LaengdeMaal.NUL).vaerdi());

		// Tilføj justering for ørn
		((ManuelAttribut) attribut.underAttribut(justeringType))
				.setPris(EKSTRA_FOR_OERN);
		Assert.assertEquals(27.0 + 2 * PRIS_PER_EKSTRA_UDSKAERING.vaerdi()
				+ EKSTRA_FOR_OERN.vaerdi(), attribut.pris(HOEJDE, BREDDE)
				.vaerdi());
		Assert.assertEquals(2 * PRIS_PER_EKSTRA_UDSKAERING.vaerdi()
				+ EKSTRA_FOR_OERN.vaerdi(),
				attribut.pris(LaengdeMaal.NUL, LaengdeMaal.NUL).vaerdi());
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
