package stelling.model;

/**
 * Reprūsenterer en tilpasset indramning
 */
public class TilpassetIndramning extends Indramning {

	@Override
	public String beskriv() {
		return "Tilpasset indramning (" + getBredde() + " x " + getHoejde()
				+ ")";
	}
}
