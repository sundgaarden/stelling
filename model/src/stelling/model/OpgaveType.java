package stelling.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Repræsenterer en generel specifikation af en opgavetype i form af et sæt
 * attributtyper. En konkret opgave af denne type vil så have et tilsvarende sæt
 * af attributværdier
 */
public class OpgaveType {
	private final String navn;
	private final List<AttributType> attributTyper;

	/**
	 * En blank opgavetype helt uden attributtyper
	 */
	public static final OpgaveType BLANK = new OpgaveType("<BLANK>",
			new ArrayList<AttributType>());

	/**
	 * Kreerer en ny opgavetype med det specificerede navn og sæt af
	 * attributtyper
	 * 
	 * @param inNavn
	 *            Opgavetypens navn
	 * @param inAttributTyper
	 *            Attributtyper i den nye opgvetype
	 */
	public OpgaveType(String inNavn, List<AttributType> inAttributTyper) {
		navn = inNavn;
		attributTyper = new ArrayList<AttributType>(inAttributTyper);
	}

	/**
	 * Kreerer en ny konkret opgave af denne opgavetype med defaultværdier for
	 * alle attributtyperne
	 * 
	 * @return Ny opgave af denne opgavetype
	 */
	public Opgave nyOpgave() {
		return new Opgave(this);
	}

	/**
	 * Returnerer navnet på denne opgavetype
	 * 
	 * @return Navn på denne opgavetype
	 */
	public String navn() {
		return navn;
	}

	/**
	 * Returnerer sættet af attributtyper som foreskrives af denne opgavetype
	 * 
	 * @return Attributtyper i denne opgavetype
	 */
	public List<AttributType> attributTyper() {
		return Collections.unmodifiableList(attributTyper);
	}

	/**
	 * Returnerer et map med denne opgavetypes attributtyper og deres respektive
	 * defaultværdier
	 * 
	 * @return Map over attributtypernes defaultværdier
	 */
	Map<AttributType, Attribut> defaultAttributter() {
		Map<AttributType, Attribut> map = new HashMap<AttributType, Attribut>();
		for (AttributType attributType : attributTyper) {
			map.put(attributType, attributType.nyAttribut());
		}
		return map;
	}

	/**
	 * Sorterer de specificerede opgavetyper efter navn
	 * 
	 * @param opgaveTyper
	 *            Opgavetyper der skal sorteres
	 * @return Opgavetyper sorteret efter navn
	 */
	public static List<OpgaveType> sorterOpgaveTyper(
			Collection<OpgaveType> opgaveTyper) {
		List<OpgaveType> typer = new ArrayList<OpgaveType>(opgaveTyper);
		Collections.sort(typer, new Comparator<OpgaveType>() {
			@Override
			public int compare(OpgaveType o1, OpgaveType o2) {
				return o1.navn().compareTo(o2.navn());
			}
		});
		return typer;
	}
}
