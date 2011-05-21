package stelling.model;

import junit.framework.Assert;

import org.junit.Test;

public class OpgaveTypeTest {

	/**
	 * Test at attributterne i en konkret opgave står i samme rækkefølge som
	 * attributtyperne specificeredes i opgavetypen
	 */
	@Test
	public void testAttributTypeOrdenBeholdes() {
		String[] attributNavne = new String[] { "c", "a", "f", "g", "b", "e",
				"d", "h" };
		OpgaveType opgaveType = MockKonfiguration
				.lavOpgaveTypeMedDummeAttributter("OpgaveTypeNavn",
						attributNavne);
		Assert.assertEquals(attributNavne.length, opgaveType.attributTyper()
				.size());
		for (int i = 0; i < attributNavne.length; i++) {
			Assert.assertEquals(attributNavne[i], opgaveType.attributTyper()
					.get(i).navn());
		}
	}
}
