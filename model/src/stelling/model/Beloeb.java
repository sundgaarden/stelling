package stelling.model;

import java.text.DecimalFormat;

/**
 * Repr�senterer et pengebel�b
 */
public class Beloeb {
	private final double vaerdi;

	/**
	 * Repr�senterer v�rdien nul
	 */
	public static final Beloeb NUL = new Beloeb(0);

	/**
	 * Kreerer et nyt bel�b
	 * 
	 * @param inVaerdi
	 *            St�rrelsen p� det nye bel�b
	 */
	public Beloeb(double inVaerdi) {
		vaerdi = inVaerdi;
	}

	/**
	 * Returnerer bel�bets st�rrelse som en r� talv�rdi
	 * 
	 * @return Bel�bets st�rrelse
	 */
	public double vaerdi() {
		return vaerdi;
	}

	/**
	 * Returnerer en ny instans af Beloeb som er multiplum af dette bel�b og den
	 * angivne faktor
	 * 
	 * @param faktor
	 *            Faktoren der skal multipliceres med
	 * @return Multipliceret bel�b
	 */
	public Beloeb multiplicer(double faktor) {
		double ny = faktor * vaerdi;
		return ny == 0 ? NUL : new Beloeb(ny);
	}

	/**
	 * Returnerer en ny instans af Beloeb som er dette bel�b divideret med den
	 * angivne divisor
	 * 
	 * @param divisor
	 *            Faktoren der skal multipliceres med
	 * @return Divideret bel�b
	 */
	public Beloeb divider(double divisor) {
		double ny = vaerdi / divisor;
		return ny == 0 ? NUL : new Beloeb(ny);
	}

	/**
	 * Returnerer en ny instans af Beloeb som er summen af dette bel�b og det
	 * andet bel�b
	 * 
	 * @param beloeb
	 *            Bel�b der skal l�gges til
	 * 
	 * @return Sum af de to bel�b
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
	 * Returnerer en instans af Beloeb med den specificerede v�rdi.
	 * <p>
	 * Hvis den angivne streng ikke repr�senterer et tal, returneres Beloeb.NUL
	 * 
	 * @param tekst
	 *            Talv�rdi p� strengform
	 * @return Den specificerede v�rdi repr�senteret som et Beloeb
	 */
	public static Beloeb parse(String tekst) {
		double vaerdi = Double.parseDouble(tekst);
		return vaerdi == 0 ? NUL : new Beloeb(vaerdi);
	}
}
