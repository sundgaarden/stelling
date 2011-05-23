package stelling.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Repr�senterer en specifik type af materiale, f.eks. en Firenzeramme eller
 * museumsglas
 */
public class Materiale implements IOpgaveAttribut {
	private final MaterialeType type;
	private String navn;
	private Beloeb pris;
	private final Map<String, String> felter;

	/**
	 * Kreerer en ny type materiale
	 * 
	 * @param materialeType
	 *            Type af det nye materiale
	 * @param inNavn
	 *            Navn p� det nye materiale
	 * @param inPris
	 *            Pris p� det nye materiale
	 */
	Materiale(MaterialeType inType, String inNavn, Beloeb inPris) {
		type = inType;
		navn = inNavn;
		pris = inPris;
		felter = new HashMap<String, String>();
	}

	/**
	 * Returnerer materialets navn
	 * 
	 * @return Navn p� materialet
	 */
	@Override
	public String navn() {
		return navn;
	}

	@Override
	public Beloeb pris(LaengdeMaal hoejde, LaengdeMaal bredde) {
		return type.beregnPris(hoejde, bredde, pris);
	}

	@Override
	public IOpgaveAttributType attributType() {
		return type;
	}

	@Override
	public String feltVaerdi(String feltNavn) {
		if ("navn".equalsIgnoreCase(feltNavn)) {
			return navn;
		}
		if ("pris".equalsIgnoreCase(feltNavn)) {
			return pris.toString();
		}
		String vaerdi = felter.get(feltNavn);
		return vaerdi == null ? "" : vaerdi;
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
		if ("pris".equalsIgnoreCase(feltNavn)) {
			pris = Beloeb.parse(feltVaerdi);
		}
		if (!type.feltNavne().contains(feltNavn)) {
			throw new IllegalArgumentException("Det specificerede feltnavn: '"
					+ feltNavn + "' er ikke underst�ttet af materialetypen");
		}
		felter.put(feltNavn, feltVaerdi);
	}
}
