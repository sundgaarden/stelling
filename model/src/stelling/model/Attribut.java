package stelling.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Repræsenterer en konkret attributværdi ved en specifik opgave, f.eks.
 * 'museumsglas' ved en indramningsopgave
 */
public abstract class Attribut {
	private final AttributType type;
	private String navn;
	private final Map<String, String> felter;

	private static String DEFAULT_FELT_VAERDI = "Ingen";

	/**
	 * Kreerer en ny attribut med den specificerede type
	 * 
	 * @param inType
	 *            Type af den nye attribut
	 */
	Attribut(AttributType inType, String inNavn) {
		type = inType;
		navn = inNavn;
		felter = new HashMap<String, String>();
	}

	/**
	 * Returnerer prisen for den pågældende attribut iht. den specificerede
	 * størrelse. Udregningen foretages forskelligt af forskellige attributtyper
	 * 
	 * @param hoejde
	 *            Højden på opgaven
	 * @param bredde
	 *            Bredden på opgaven
	 * @return Pris for denne attribut givet de specificerede mål
	 */
	public abstract Beloeb pris(LaengdeMaal hoejde, LaengdeMaal bredde);

	/**
	 * Returnerer typen af denne attribut
	 * 
	 * @return Typen af denne attribut
	 */
	public AttributType attributType() {
		return type;
	}

	/**
	 * Returnerer navnet på denne attribut
	 * 
	 * @return Navnet på denne attribut
	 */
	public String navn() {
		return navn;
	}

	/**
	 * Returnerer værdien i det specificerede felt for denne attribut
	 * 
	 * @param feltNavn
	 *            Navn på det felt hvis værdi skal returneres
	 * @return Værdi i det specificerede felt
	 */
	public String feltVaerdi(String feltNavn) {
		if ("navn".equalsIgnoreCase(feltNavn)) {
			return navn;
		}
		String vaerdi = felter.get(feltNavn);
		return vaerdi == null ? DEFAULT_FELT_VAERDI : vaerdi;
	}

	/**
	 * Ændrer det specificerede felt til den specificerede værdi
	 * 
	 * @param feltNavn
	 *            Navn på felt der skal ændres
	 * @param feltVaerdi
	 *            Ny feltværdi
	 */
	public void setFeltVaerdi(String feltNavn, String feltVaerdi) {
		if ("navn".equalsIgnoreCase(feltNavn)) {
			navn = feltVaerdi;
		}
		if (!type.feltNavne().contains(feltNavn)) {
			throw new IllegalArgumentException("Det specificerede feltnavn: '"
					+ feltNavn + "' er ikke understøttet af attributtypen");
		}
		felter.put(feltNavn, feltVaerdi);
	}
}
