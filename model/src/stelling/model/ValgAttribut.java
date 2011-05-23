package stelling.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Repr�senterer en konkret attributv�rdi, som er en af et fast s�t af mulige
 * v�rdier
 * <p>
 * En ValgAttribut har ud over feltet 'navn', som er f�lles for alle
 * attributtyper, ogs� altid feltet 'pris'. Denne prisinformation bruges som
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
	 *            Navn p� ny attribut
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
	 * Returnerer v�rdien i det specificerede felt for denne attribut
	 * 
	 * @param feltNavn
	 *            Navn p� det felt hvis v�rdi skal returneres
	 * @return V�rdi i det specificerede felt
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
	 * �ndrer det specificerede felt til den specificerede v�rdi
	 * 
	 * @param feltNavn
	 *            Navn p� felt, der skal �ndres
	 * @param feltVaerdi
	 *            Ny feltv�rdi
	 */
	public void setFeltVaerdi(String feltNavn, String feltVaerdi) {
		if ("navn".equalsIgnoreCase(feltNavn)) {
			throw new IllegalArgumentException(
					"En attributs navn kan ikke �ndres som en feltv�rdi");
		}
		if (!type.feltNavne().contains(feltNavn)) {
			throw new IllegalArgumentException("Det specificerede feltnavn: '"
					+ feltNavn + "' er ikke underst�ttet af attributtypen");
		}
		felter.put(feltNavn, feltVaerdi);
	}
}
