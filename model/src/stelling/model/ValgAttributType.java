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
 * <p>
 * Denne attributtype har et s�t feltnavne, som kan bruges til at l�se v�rdier
 * fra konkrete attributter af den p�g�ldende type
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
	 *            Navne p� attributtypens felter
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
	 * Checker at de specificerede feltnavne indeholder b�de 'navn' og 'pris'
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
				+ " b�de feltet '" + NAVN_FELT + "' og feltet '"
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
	 * Returnerer navnene p� de felter som denne attributtype har, f.eks.
	 * {'navn', 'pris', 'materiale', 'id'}
	 * 
	 * @return Navne p� felter i denne attributtype
	 */
	public List<String> feltNavne() {
		return Collections.unmodifiableList(feltNavne);
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
	 * Tilf�jer en ny attribut til attributtypen
	 * <p>
	 * Det angivne felter skal specificeres i samme r�kkef�lge som feltnavne
	 * blev specificeret
	 * 
	 * @param felter
	 *            Feltv�rdier for den nye attribut
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
	 * Tilf�jer den specificerede attribut til attributtypen
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
