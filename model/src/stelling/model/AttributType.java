package stelling.model;

/**
 * Repræsenterer en type opgaveattribut, f.eks. 'glastype' som bruges ved
 * indramningsopgaver
 */

public abstract class AttributType {
	private final String navn;
	private final Attribut nil;

	/**
	 * Kreerer en ny attributtype med det specificerede navn
	 * 
	 * @param inNavn
	 *            Attributtypens navn
	 */
	public AttributType(String inNavn) {
		navn = inNavn;
		nil = new Attribut(this, "Ingen") {
			@Override
			public Beloeb pris(LaengdeMaal hoejde, LaengdeMaal bredde) {
				return Beloeb.NUL;
			}
		};
	}

	/**
	 * Returnerer navnet på denne attributtype
	 * 
	 * @return Navn på attributtypen
	 */
	public String navn() {
		return navn;
	}

	/**
	 * Returnerer nil, den tomme attribut
	 * 
	 * @return nil, den tomme attribut
	 */
	Attribut nil() {
		return nil;
	}

	/**
	 * Returnerer en attribut af denne attributtype
	 * 
	 * @return Attribut af denne attributtype
	 */
	public abstract Attribut nyAttribut();
}
