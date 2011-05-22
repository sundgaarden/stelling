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

	@Override
	public Beloeb beregnPris(LaengdeMaal hoejde, LaengdeMaal bredde, Beloeb pris) {
		throw new UnsupportedOperationException(
				"Denne metode burde aldrig blive kaldt, idet ManuelAttribut-klassen"
						+ " foretager prisberegningen selv");
	}

	/**
	 * Denne klasse overrider prisberegningen i standard-attributklassen med
	 * bare at returnere prisen
	 */
	public static class ManuelAttribut extends Attribut {
		private Beloeb pris;

		/**
		 * Kreerer en ny manuel attribut med de specificerede karakteristika
		 * 
		 * @param inType
		 *            Typen af den nye attribut
		 * @param inNavn
		 *            Navnet på den nye attribut
		 */
		private ManuelAttribut(AttributType inType, String inNavn) {
			super(inType, inNavn, Beloeb.NUL);
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
}
