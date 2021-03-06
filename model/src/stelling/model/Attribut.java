package stelling.model;

/**
 * Repr�senterer en konkret attributv�rdi ved en specifik opgave, f.eks.
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
	 *            Navn p� denne attribut
	 */
	Attribut(AttributType inType, String inNavn) {
		type = inType;
		navn = inNavn;
	}

	/**
	 * Kreerer en ny attribut med den specificerede type. Som navn bruges navnet
	 * p� attributtypen
	 * 
	 * @param inType
	 *            Type af denne attribut
	 */
	Attribut(AttributType inType) {
		this(inType, inType.navn());
	}

	/**
	 * Returnerer prisen for den p�g�ldende attribut iht. den specificerede
	 * st�rrelse. Udregningen foretages forskelligt af forskellige attributtyper
	 * 
	 * @param hoejde
	 *            H�jden p� opgaven
	 * @param bredde
	 *            Bredden p� opgaven
	 * @return Pris for denne attribut givet de specificerede m�l
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
	 * Returnerer navnet p� denne attribut
	 * 
	 * @return Navnet p� denne attribut
	 */
	public String navn() {
		return navn;
	}
}
