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
	public Attribut nyAttribut() {
		return new AntalAttribut(this, prisPerStyk);
	}
}
