package stelling.model;

/**
 * Repræsenterer en konkret attributværdi, som er en af et fast sæt af mulige
 * værdier
 */
public class ValgAttribut extends Attribut {
	private final Beloeb pris;
	private final AttributBeregner beregner;

	/**
	 * Kreerer en ny valgattribut
	 * 
	 * @param inType
	 *            Type af ny attribut
	 * @param inNavn
	 *            Navn på ny attribut
	 * @param inPris
	 *            Pris associeret med ny attribut
	 */
	public ValgAttribut(ValgAttributType inType, String inNavn, Beloeb inPris) {
		super(inType, inNavn);
		pris = inPris;
		beregner = inType.beregner();
	}

	@Override
	public Beloeb pris(LaengdeMaal hoejde, LaengdeMaal bredde) {
		return beregner.beregnPris(hoejde, bredde, pris);
	}
}
