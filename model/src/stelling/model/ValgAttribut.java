package stelling.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Repræsenterer en konkret attributværdi, som er en af et fast sæt af mulige
 * værdier
 * <p>
 * En ValgAttribut har ud over feltet 'navn', som er fælles for alle
 * attributtyper, også altid feltet 'pris'. Denne prisinformation bruges som
 * basispris til udregning af attributtens samlede pris
 */
public class ValgAttribut extends Attribut {
	private final ValgAttributType type;
	private final Beloeb basisPris;
	private final Map<String, String> felter;

	private static String DEFAULT_FELT_VAERDI = "Ingen";

	/**
	 * Kreerer en ny valgattribut
	 * 
	 * @param inType
	 *            Type af ny attribut
	 * @param inNavn
	 *            Navn på ny attribut
	 * @param inBasisPris
	 *            Basispris associeret med ny attribut
	 */
	ValgAttribut(ValgAttributType inType, String inNavn, Beloeb inBasisPris) {
		super(inType, inNavn);
		type = inType;
		basisPris = inBasisPris;
		felter = new HashMap<String, String>();
	}

	@Override
	public Beloeb pris(LaengdeMaal hoejde, LaengdeMaal bredde) {
		return type.beregner().beregnPris(hoejde, bredde, basisPris);
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
			return navn();
		}
		if ("pris".equalsIgnoreCase(feltNavn)) {
			return basisPris.toString();
		}
		String vaerdi = felter.get(feltNavn);
		return vaerdi == null ? DEFAULT_FELT_VAERDI : vaerdi;
	}

	/**
	 * Ændrer det specificerede felt til den specificerede værdi
	 * 
	 * @param feltNavn
	 *            Navn på felt, der skal ændres
	 * @param feltVaerdi
	 *            Ny feltværdi
	 */
	public void setFeltVaerdi(String feltNavn, String feltVaerdi) {
		if ("navn".equalsIgnoreCase(feltNavn)) {
			throw new IllegalArgumentException(
					"En attributs navn kan ikke ændres som en feltværdi");
		}
		if (!type.feltNavne().contains(feltNavn)) {
			throw new IllegalArgumentException("Det specificerede feltnavn: '"
					+ feltNavn + "' er ikke understøttet af attributtypen");
		}
		felter.put(feltNavn, feltVaerdi);
	}
}
