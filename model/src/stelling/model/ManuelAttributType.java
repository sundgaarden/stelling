package stelling.model;

/**
 * Repr�senterer en attribut, hvor prisen specificeres manuelt
 * <p>
 * Bruges f.eks. i forbindelse med passepartouts, hvor visse udsk�ringer
 * priss�ttes manuelt
 */
public class ManuelAttributType extends AttributType {
	/**
	 * Kreerer en ny manuel attributtype med det specificerede navn
	 * 
	 * @param inNavn
	 *            Navn p� den nye attributtype
	 */
	public ManuelAttributType(String inNavn) {
		super(inNavn);
	}

	@Override
	public Attribut nyAttribut() {
		return new ManuelAttribut(this, navn());
	}
}
