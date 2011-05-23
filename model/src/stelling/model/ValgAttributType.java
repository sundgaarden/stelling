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
 * <p>
 * Denne attributtype har et sæt feltnavne, som kan bruges til at læse værdier
 * fra konkrete attributter af den pågældende type
 */
public class ValgAttributType extends AttributType {
	private final List<String> feltNavne;
	private final List<Attribut> attributter;
	private final AttributBeregner beregner;
	private Attribut defaultAttribut;

	static String NAVN_FELT = "navn";
	static String BASIS_PRIS_FELT = "pris";

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
		super(inNavn);
		feltNavne = checkFeltNavne(inFeltNavne);
		attributter = new ArrayList<Attribut>();
		defaultAttribut = nil();
		beregner = inBeregner;
	}

	/**
	 * Checker at de specificerede feltnavne indeholder både 'navn' og 'pris'
	 */
	private static List<String> checkFeltNavne(List<String> feltNavne) {
		boolean harNavn = false;
		boolean harBasisPris = false;
		for (String feltNavn : feltNavne) {
			if (NAVN_FELT.equalsIgnoreCase(feltNavn)) {
				harNavn = true;
				continue;
			}
			if (BASIS_PRIS_FELT.equalsIgnoreCase(feltNavn)) {
				harBasisPris = true;
				continue;
			}
		}
		if (harNavn && harBasisPris) {
			return feltNavne;
		}
		throw new IllegalArgumentException("En ValgAttribut skal have"
				+ " både feltet '" + NAVN_FELT + "' og feltet '"
				+ BASIS_PRIS_FELT + "'");
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
	 * Returnerer navnene på de felter som denne attributtype har, f.eks.
	 * {'navn', 'pris', 'materiale', 'id'}
	 * 
	 * @return Navne på felter i denne attributtype
	 */
	public List<String> feltNavne() {
		return Collections.unmodifiableList(feltNavne);
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
	 * Tilføjer en ny attribut til attributtypen
	 * <p>
	 * Det angivne felter skal specificeres i samme rækkefølge som feltnavne
	 * blev specificeret
	 * 
	 * @param felter
	 *            Feltværdier for den nye attribut
	 */
	public void tilfoejAttribut(List<String> felter) {
		String navn = findFelt(NAVN_FELT, felter);
		Beloeb basisPris = Beloeb.parse(findFelt(BASIS_PRIS_FELT, felter));
		ValgAttribut attribut = new ValgAttribut(this, navn, basisPris);
		for (int i = 0; i < feltNavne.size(); i++) {
			if (feltNavne.get(i).equalsIgnoreCase(NAVN_FELT)) {
				continue;
			}
			if (feltNavne.get(i).equalsIgnoreCase(BASIS_PRIS_FELT)) {
				continue;
			}
			attribut.setFeltVaerdi(feltNavne.get(i), felter.get(i));
		}
		tilfoejAttribut(attribut);
	}

	private String findFelt(String feltNavn, List<String> felter) {
		for (int i = 0; i < feltNavne.size(); i++) {
			if (feltNavn.equalsIgnoreCase(feltNavne.get(i))) {
				return felter.get(i);
			}
		}
		throw new IllegalArgumentException("Feltet blev ikke fundet: "
				+ feltNavn);
	}

	/**
	 * Tilføjer den specificerede attribut til attributtypen
	 * 
	 * @param attribut
	 *            Den nye attribut
	 */
	public void tilfoejAttribut(ValgAttribut attribut) {
		if (attributter.contains(attribut)) {
			throw new IllegalArgumentException("Attributten findes allerede: "
					+ attribut.navn());
		}
		if (attribut.attributType() != this) {
			throw new IllegalArgumentException("Attributten: "
					+ attribut.navn() + " er ikke af denne type: " + navn());
		}
		attributter.add(attribut);
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
