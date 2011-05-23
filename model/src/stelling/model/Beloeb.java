package stelling.model;

import java.text.DecimalFormat;

/**
 * Repræsenterer et pengebeløb
 */
public class Beloeb {
	private final double vaerdi;

	/**
	 * Repræsenterer værdien nul
	 */
	public static final Beloeb NUL = new Beloeb(0);

	/**
	 * Kreerer et nyt beløb
	 * 
	 * @param inVaerdi
	 *            Størrelsen på det nye beløb
	 */
	public Beloeb(double inVaerdi) {
		vaerdi = inVaerdi;
	}

	/**
	 * Returnerer beløbets størrelse som en rå talværdi
	 * 
	 * @return Beløbets størrelse
	 */
	public double vaerdi() {
		return vaerdi;
	}

	/**
	 * Returnerer en ny instans af Beloeb som er multiplum af dette beløb og den
	 * angivne faktor
	 * 
	 * @param faktor
	 *            Faktoren der skal multipliceres med
	 * @return Multipliceret beløb
	 */
	public Beloeb multiplicer(double faktor) {
		double ny = faktor * vaerdi;
		return ny == 0 ? NUL : new Beloeb(ny);
	}

	/**
	 * Returnerer en ny instans af Beloeb som er dette beløb divideret med den
	 * angivne divisor
	 * 
	 * @param divisor
	 *            Faktoren der skal multipliceres med
	 * @return Divideret beløb
	 */
	public Beloeb divider(double divisor) {
		double ny = vaerdi / divisor;
		return ny == 0 ? NUL : new Beloeb(ny);
	}

	/**
	 * Returnerer en ny instans af Beloeb som er summen af dette beløb og det
	 * andet beløb
	 * 
	 * @param beloeb
	 *            Beløb der skal lægges til
	 * 
	 * @return Sum af de to beløb
	 */
	public Beloeb adder(Beloeb beloeb) {
		double ny = vaerdi + beloeb.vaerdi();
		return ny == 0 ? NUL : new Beloeb(ny);
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Beloeb)) {
			return false;
		}
		Beloeb beloeb = (Beloeb) other;
		return vaerdi == beloeb.vaerdi;
	}

	@Override
	public String toString() {
		return new DecimalFormat("0.00").format(vaerdi);
	}

	/**
	 * Returnerer en instans af Beloeb med den specificerede værdi.
	 * <p>
	 * Hvis den angivne streng ikke repræsenterer et tal, returneres Beloeb.NUL
	 * 
	 * @param tekst
	 *            Talværdi på strengform
	 * @return Den specificerede værdi repræsenteret som et Beloeb
	 */
	public static Beloeb parse(String tekst) {
		double vaerdi = Double.parseDouble(tekst);
		return vaerdi == 0 ? NUL : new Beloeb(vaerdi);
	}
}
