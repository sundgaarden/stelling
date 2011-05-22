package stelling.model;

/**
 * Samling af beregnere, der ud fra et s¾t af specificerede inputs beregner en
 * samlet pris for en attribut
 * <p>
 * Hver enkelt Beregner har sin egen sammenh¾ng mellem st¿rrelse og pris (f.eks.
 * omkreds eller areal)
 */
public enum AttributBeregner {
	AREAL_PRIS {
		@Override
		public Beloeb beregnPris(LaengdeMaal hoejde, LaengdeMaal bredde,
				Beloeb pris) {
			// kvmFaktor konverterer fra kvadratmillimeter til kvadratmeter
			double kvmFaktor = 1.0 / 1e6;
			return pris.multiplicer(kvmFaktor * hoejde.vaerdi()
					* bredde.vaerdi());
		}
	},
	OMKREDS_PRIS {
		@Override
		public Beloeb beregnPris(LaengdeMaal hoejde, LaengdeMaal bredde,
				Beloeb pris) {
			// meterFaktor konverterer fra millimeter til meter
			double meterFaktor = 1.0 / 1e3;
			double omkreds = 2 * hoejde.vaerdi() + bredde.vaerdi();
			return pris.multiplicer(meterFaktor * omkreds);
		}
	},
	FAST_PRIS {
		@Override
		public Beloeb beregnPris(LaengdeMaal hoejde, LaengdeMaal bredde,
				Beloeb pris) {
			return pris;
		}
	};

	/**
	 * Beregner en pris ud fra de angivne oplysninger
	 * <p>
	 * Udregningen foretages iht. Beregnerens egen sammenh¾ng mellem st¿rrelse
	 * og pris (f.eks. omkreds eller areal)
	 * 
	 * @param hoejde
	 *            Opgavens h¿jde
	 * @param bredde
	 *            Opgavens bredde
	 * @param pris
	 *            Attributtens basispris
	 * @return Pris for attributten i den angivne st¿rrelse
	 */
	public abstract Beloeb beregnPris(LaengdeMaal hoejde, LaengdeMaal bredde,
			Beloeb pris);
}
