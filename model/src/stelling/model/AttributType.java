package stelling.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Repr�senterer en type opgaveattribut, f.eks. 'glastype' som bruges ved
 * indramningsopgaver
 * <p>
 * En attributtype har et s�t feltnavne, som kan bruges til at l�se v�rdier fra
 * konkrete attributter af den p�g�ldende type. Alle attributter har feltet
 * 'navn'
 */

public abstract class AttributType {
	private final String navn;
	private final List<String> feltNavne;
	private final Attribut nil;

	/**
	 * Kreerer en ny attributtype med det specificerede navn
	 * 
	 * @param inNavn
	 *            Attributtypens navn
	 */
	public AttributType(String inNavn) {
		this(inNavn, new ArrayList<String>());
	}

	/**
	 * 
	 * Kreerer en ny attributtype med det specificerede navn og feltnavne
	 * 
	 * @param inNavn
	 *            Attributtypens navn
	 * @param inFeltNavne
	 *            Navne p� denne attributs felter
	 */
	public AttributType(String inNavn, List<String> inFeltNavne) {
		navn = inNavn;
		feltNavne = new ArrayList<String>(inFeltNavne);
		nil = new Attribut(this, "Ingen") {
			@Override
			public Beloeb pris(LaengdeMaal hoejde, LaengdeMaal bredde) {
				return Beloeb.NUL;
			}
		};
	}

	/**
	 * Returnerer navnet p� denne attributtype
	 * 
	 * @return Navn p� attributtypen
	 */
	public String navn() {
		return navn;
	}

	/**
	 * Returnerer navnene p� de felter som denne attributtype har, f.eks.
	 * {'navn', 'pris', 'materiale', 'id'}
	 * 
	 * @return Navne p� felter i denne attributtype
	 */
	public List<String> feltNavne() {
		return Collections.unmodifiableList(feltNavne);
	}

	/**
	 * Returnerer nil, den tomme attribut
	 * 
	 * @return nil, den tomme attribut
	 */
	Attribut nil() {
		return nil;
	}

	/**
	 * Returnerer en attribut af denne attributtype
	 * 
	 * @return Attribut af denne attributtype
	 */
	public abstract Attribut nyAttribut();
}
