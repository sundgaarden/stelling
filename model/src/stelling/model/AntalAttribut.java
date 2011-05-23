package stelling.model;

/**
 * Repræsenterer en attributtype, som opererer med en fast stykpris og et
 * variabelt antal
 */
public class AntalAttribut extends Attribut {
	private final Beloeb prisPerStyk;
	private int antal;

	/**
	 * Kreerer en ny antalattribut
	 * 
	 * @param inType
	 *            Typen af den nye attribut
	 * @param inNavn
	 *            Navnet på den nye attribut
	 * @param inPrisPerStyk
	 *            Stykprisen for den ny attribut
	 */
	AntalAttribut(AntalAttributType inType, Beloeb inPrisPerStyk) {
		super(inType);
		prisPerStyk = inPrisPerStyk;
		antal = 0;
	}

	/**
	 * Ændrer antallet i denne attribut
	 * 
	 * @param nytAntal
	 *            Nyt antal
	 */
	public void setAntal(int nytAntal) {
		if (nytAntal < 0) {
			throw new IllegalArgumentException(
					"Det specificerede antal er negativt: " + nytAntal);
		}
		antal = nytAntal;
	}

	@Override
	public Beloeb pris(LaengdeMaal hoejde, LaengdeMaal bredde) {
		return prisPerStyk.multiplicer(antal);
	}
}
