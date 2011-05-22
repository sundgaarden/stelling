package stelling.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Repræsenterer en type opgaveattribut, som kun kan antage en af et fast sæt af
 * mulige værdier
 * <p>
 * De mulige værdier kan naturligvis konfigureres gennem
 * konfigurationsklasserne, men systemet kreerer ikke nye værdier af en sådan
 * attributtype under kørsel
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
	 *            Navne på attributtypens felter
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
	 * Returnerer sættet af attributværdier, som attributter af denne type kan
	 * antage
	 * 
	 * @return Sæt af mulige værdier for attributter af denne type
	 */
	public List<Attribut> attributter() {
		return Collections.unmodifiableList(attributter);
	}

	/**
	 * Ændrer sættet af attributværdier, som attributter af denne type kan
	 * antage
	 * 
	 * @param inAttributter
	 *            Nyt sæt af attributværdier
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
	 * Ændrer denne attributtypes defaultværdi
	 * 
	 * @param defaultVaerdi
	 *            Ny defaultværdi
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
