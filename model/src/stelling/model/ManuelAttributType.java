package stelling.model;

/**
 * Repræsenterer en attribut, hvor prisen specificeres manuelt
 * <p>
 * Bruges f.eks. i forbindelse med passepartouts, hvor visse udskæringer
 * prissættes manuelt
 */
public class ManuelAttributType extends AttributType {
	/**
	 * Kreerer en ny manuel attributtype med det specificerede navn
	 * 
	 * @param inNavn
	 *            Navn på den nye attributtype
	 */
	public ManuelAttributType(String inNavn) {
		super(inNavn);
	}

	@Override
	public Attribut nyAttribut() {
		return new ManuelAttribut(this, navn());
	}
}
