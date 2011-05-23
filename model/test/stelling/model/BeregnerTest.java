package stelling.model;

import junit.framework.Assert;

import org.junit.Test;

public class BeregnerTest {
	@Test
	public void testBeregner() {
		runTest(0, 0, 0, 0, 0, 0);
		runTest(100, 100, 0, 0, 0, 0);
		runTest(300, 400, 0, 0, 0, 0);
		runTest(0, 0, 499.5, 0, 0, 499.5);
		runTest(100, 100, 499.5, 4.995, 199.8, 499.5);
		runTest(300, 400, 499.5, 59.94, 699.3, 499.5);
	}

	private void runTest(int hoejde, int bredde, double basisPris,
			double forventetArealPris, double forventetOmkredsPris,
			double forventetFastPris) {
		Assert.assertEquals(
				forventetArealPris,
				AttributBeregner.AREAL.beregnPris(new LaengdeMaal(hoejde),
						new LaengdeMaal(bredde), new Beloeb(basisPris))
						.vaerdi());
		Assert.assertEquals(
				forventetOmkredsPris,
				AttributBeregner.OMKREDS.beregnPris(
						new LaengdeMaal(hoejde), new LaengdeMaal(bredde),
						new Beloeb(basisPris)).vaerdi());
		Assert.assertEquals(
				forventetFastPris,
				AttributBeregner.FAST.beregnPris(new LaengdeMaal(hoejde),
						new LaengdeMaal(bredde), new Beloeb(basisPris))
						.vaerdi());
	}
}
