package stelling.model;

/**
 * Repr�senterer en konkret attributv�rdi, som er en af et fast s�t af mulige
 * v�rdier
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
	 *            Navn p� ny attribut
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
