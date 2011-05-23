package stelling.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Repr�senterer en type opgaveattribut, som kun kan antage en af et fast s�t af
 * mulige v�rdier
 * <p>
 * De mulige v�rdier kan naturligvis konfigureres gennem
 * konfigurationsklasserne, men systemet kreerer ikke nye v�rdier af en s�dan
 * attributtype under k�rsel
 */
public class ValgAttributType extends AttributType {
	private final List<Attribut> attributter;
	private final AttributBeregner beregner;
	private Attribut defaultAttribut;

	/**
	 * Kreerer en ny valg-attributtype med de specificerede karakteristika
	 * 
	 * @param inNavn
	 *            Attributtypens navn
	 * @param inFeltNavne
	 *            Navne p� attributtypens felter
	 * @param inBeregner
	 *            Beregner til brug ved prisudregning
	 */
	public ValgAttributType(String inNavn, List<String> inFeltNavne,
			AttributBeregner inBeregner) {
		super(inNavn, inFeltNavne);
		attributter = new ArrayList<Attribut>();
		defaultAttribut = nil();
		beregner = inBeregner;
	}

	/**
	 * Returnerer attributberegneren associeret med denne attributtype
	 * 
	 * @return Attributberegner associeret med denne attributtype
	 */
	AttributBeregner beregner() {
		return beregner;
	}

	/**
	 * Returnerer s�ttet af attributv�rdier, som attributter af denne type kan
	 * antage
	 * 
	 * @return S�t af mulige v�rdier for attributter af denne type
	 */
	public List<Attribut> attributter() {
		return Collections.unmodifiableList(attributter);
	}

	/**
	 * �ndrer s�ttet af attributv�rdier, som attributter af denne type kan
	 * antage
	 * 
	 * @param inAttributter
	 *            Nyt s�t af attributv�rdier
	 */
	public void setAttributter(List<Attribut> inAttributter) {
		attributter.clear();
		attributter.add(nil());
		for (Attribut attribut : inAttributter) {
			if (attributter.contains(attribut)) {
				throw new IllegalArgumentException(
						"Attributten findes allerede: " + attribut.navn());
			}
			if (attribut.attributType() != this) {
				throw new IllegalArgumentException("Attributten: "
						+ attribut.navn() + " er ikke af denne type: " + navn());
			}
			attributter.add(attribut);
		}
	}

	@Override
	public Attribut nyAttribut() {
		return defaultAttribut;
	}

	/**
	 * �ndrer denne attributtypes defaultv�rdi
	 * 
	 * @param defaultVaerdi
	 *            Ny defaultv�rdi
	 */
	public void setDefaultVaerdi(Attribut attribut) {
		if (!attributter.contains(attribut)) {
			throw new IllegalArgumentException("Attributten: "
					+ attribut.navn() + " er ikke inkluderet i denne type: "
					+ navn());
		}
		defaultAttribut = attribut;
	}
}
