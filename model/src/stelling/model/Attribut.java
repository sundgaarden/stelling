package stelling.model;

/**
 * Repræsenterer en konkret attributværdi ved en specifik opgave, f.eks.
 * 'museumsglas' ved en indramningsopgave
 */
public abstract class Attribut {
	private final AttributType type;
	private final String navn;

	/**
	 * Kreerer en ny attribut med den specificerede type og navn
	 * 
	 * @param inType
	 *            Type af denne attribut
	 * @param inNavn
	 *            Navn på denne attribut
	 */
	Attribut(AttributType inType, String inNavn) {
		type = inType;
		navn = inNavn;
	}

	/**
	 * Kreerer en ny attribut med den specificerede type. Som navn bruges navnet
	 * på attributtypen
	 * 
	 * @param inType
	 *            Type af denne attribut
	 */
	Attribut(AttributType inType) {
		this(inType, inType.navn());
	}

	/**
	 * Returnerer prisen for den pågældende attribut iht. den specificerede
	 * størrelse. Udregningen foretages forskelligt af forskellige attributtyper
	 * 
	 * @param hoejde
	 *            Højden på opgaven
	 * @param bredde
	 *            Bredden på opgaven
	 * @return Pris for denne attribut givet de specificerede mål
	 */
	public abstract Beloeb pris(LaengdeMaal hoejde, LaengdeMaal bredde);

	/**
	 * Returnerer typen af denne attribut
	 * 
	 * @return Typen af denne attribut
	 */
	public AttributType attributType() {
		return type;
	}

	/**
	 * Returnerer navnet på denne attribut
	 * 
	 * @return Navnet på denne attribut
	 */
	public String navn() {
		return navn;
	}
}
