package stelling.model;

/**
 * Repræsenterer en attributtype, som i prisudregningen bare returnerer den
 * angivne pris
 */
public class ManuelAttribut extends Attribut {
	private Beloeb pris;

	/**
	 * Kreerer en ny manuel attribut
	 * 
	 * @param inType
	 *            Typen af den nye attribut
	 * @param inNavn
	 *            Navnet på den nye attribut
	 */
	ManuelAttribut(ManuelAttributType inType, String inNavn) {
		super(inType, inNavn);
		pris = Beloeb.NUL;
	}

	/**
	 * Ændrer prisen i denne attribut
	 * 
	 * @param nytPris
	 *            Ny pris
	 */
	public void setPris(Beloeb nyPris) {
		pris = nyPris;
	}

	@Override
	public Beloeb pris(LaengdeMaal hoejde, LaengdeMaal bredde) {
		return pris;
	}
}
