package stelling.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Repr�senterer en foresp�rgsel p� et s�t af opgaver
 */
public class Forespoergsel implements IBeskrivelig {
	private final Session session;
	private final List<Opgave> opgaver;

	/**
	 * Kreerer ny foresp�rgsel med en default opgave
	 */
	Forespoergsel(Session inSession) {
		opgaver = new ArrayList<Opgave>();
		session = inSession;
		nyOpgave(session.defaultOpgaveType());
	}

	/**
	 * Returnerer de underst�ttede opgavetyper sorteret efter navn
	 * 
	 * @return Opgavetyper sorteret efter navn
	 */
	public List<OpgaveType> opgaveTyper() {
		return session.opgaveTyper();
	}

	/**
	 * Returnerer de eksisterende opgaver i denne foresp�rgsel
	 * 
	 * @return Eksisterende opgaver
	 */
	public List<Opgave> opgaver() {
		return Collections.unmodifiableList(opgaver);
	}

	@Override
	public String beskriv() {
		return beskriv("");
	}

	@Override
	public String beskriv(String linjePrefix) {
		StringBuilder builder = new StringBuilder();
		builder.append(linjePrefix).append(
				"Foresp�rgsel med f�lgende opgaver:\n");
		for (Opgave opgave : opgaver) {
			builder.append(opgave.beskriv(linjePrefix + "\t"));
		}
		return builder.toString();
	}

	/**
	 * Tilf�jer en ny opgave til foresp�rgslen af den specificerede type
	 * 
	 * @param opgaveType
	 *            Type af den nye opgave
	 * @return Ny opgave af den specificerede type
	 */
	public Opgave nyOpgave(OpgaveType opgaveType) {
		Opgave opgave = opgaveType.nyOpgave();
		opgaver.add(opgave);
		return opgave;
	}
}
