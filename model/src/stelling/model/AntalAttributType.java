package stelling.model;

/**
 * Repræsenterer en type opgaveattribut, som føjes til en opgave i et vist
 * antal, f.eks. 'breddestivere' eller 'udskæringer'
 */
public class AntalAttributType extends AttributType {
	private final Beloeb prisPerStyk;

	/**
	 * Kreerer en ny antal-attributtype med de specificerede karakteristika
	 * 
	 * @param inNavn
	 *            Navn på den nye attributtype
	 * @param pris
	 *            Pris per styk
	 */
	public AntalAttributType(String inNavn, Beloeb pris) {
		super(inNavn);
		prisPerStyk = pris;
	}

	@Override
	public Beloeb beregnPris(LaengdeMaal hoejde, LaengdeMaal bredde, Beloeb pris) {
		throw new UnsupportedOperationException(
				"Denne metode burde aldrig blive kaldt, idet AntalAttribut-klassen"
						+ " foretager prisberegningen selv");
	}

	@Override
	public Attribut nyAttribut() {
		return new AntalAttribut(this, navn(), prisPerStyk);
	}

	/**
	 * Denne klasse overrider prisberegningen i standard-attributklassen med
	 * antal * pris per styk
	 */
	public static class AntalAttribut extends Attribut {
		private final Beloeb prisPerStyk;
		private int antal;

		/**
		 * Kreerer en ny antalattribut med de specificerede karakteristika
		 * 
		 * @param inType
		 *            Typen af den nye attribut
		 * @param inNavn
		 *            Navnet på den nye attribut
		 * @param inPris
		 *            Stykprisen for den ny attribut
		 */
		private AntalAttribut(AttributType inType, String inNavn, Beloeb inPris) {
			super(inType, inNavn, inPris);
			prisPerStyk = inPris;
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
}
