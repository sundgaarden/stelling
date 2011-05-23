package stelling.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Repr�senterer en type opgaveattribut, f.eks. 'glastype' som bruges ved
 * indramningsopgaver
 * <p>
 * En attributtype har et s�t feltnavne, som kan bruges til at l�se v�rdier fra
 * konkrete attributter af den p�g�ldende type
 * <p>
 * Der er visse faste felter, som en attributtype altid implicit specificerer,
 * f.eks. 'navn' og 'pris'
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
		nil = new Attribut(this, "Ingen", Beloeb.NUL);
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

	/**
	 * Beregner en pris ud fra de angivne oplysninger
	 * <p>
	 * Udregningen foretages forskelligt af forskellige attributtyper og kan
	 * derfor udvise forskellig sammenh�ng mellem st�rrelse og pris (f.eks.
	 * omkreds eller areal)
	 * 
	 * @param hoejde
	 *            Opgavens h�jde
	 * @param bredde
	 *            Opgavens bredde
	 * @param pris
	 *            Attributtens basispris
	 * @return Pris for attributten i den angivne st�rrelse
	 */
	public abstract Beloeb beregnPris(LaengdeMaal hoejde, LaengdeMaal bredde,
			Beloeb pris);
}
