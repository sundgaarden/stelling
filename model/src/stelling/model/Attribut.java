package stelling.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Repr�senterer en konkret attributv�rdi ved en specifik opgave, f.eks.
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
	 * Returnerer prisen for den p�g�ldende attribut iht. den specificerede
	 * st�rrelse. Udregningen foretages forskelligt af forskellige attributtyper
	 * 
	 * @param hoejde
	 *            H�jden p� opgaven
	 * @param bredde
	 *            Bredden p� opgaven
	 * @return Pris for denne attribut givet de specificerede m�l
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
	 * Returnerer navnet p� denne attribut
	 * 
	 * @return Navnet p� denne attribut
	 */
	public String navn() {
		return navn;
	}

	/**
	 * Returnerer v�rdien i det specificerede felt for denne attribut
	 * 
	 * @param feltNavn
	 *            Navn p� det felt hvis v�rdi skal returneres
	 * @return V�rdi i det specificerede felt
	 */
	public String feltVaerdi(String feltNavn) {
		if ("navn".equalsIgnoreCase(feltNavn)) {
			return navn;
		}
		String vaerdi = felter.get(feltNavn);
		return vaerdi == null ? DEFAULT_FELT_VAERDI : vaerdi;
	}

	/**
	 * �ndrer det specificerede felt til den specificerede v�rdi
	 * 
	 * @param feltNavn
	 *            Navn p� felt der skal �ndres
	 * @param feltVaerdi
	 *            Ny feltv�rdi
	 */
	public void setFeltVaerdi(String feltNavn, String feltVaerdi) {
		if ("navn".equalsIgnoreCase(feltNavn)) {
			navn = feltVaerdi;
		}
		if (!type.feltNavne().contains(feltNavn)) {
			throw new IllegalArgumentException("Det specificerede feltnavn: '"
					+ feltNavn + "' er ikke underst�ttet af attributtypen");
		}
		felter.put(feltNavn, feltVaerdi);
	}
}
