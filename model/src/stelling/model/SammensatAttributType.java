package stelling.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Repræsenterer en type attribut, som er sammensat af underattributter
 */
public class SammensatAttributType extends AttributType {
	private final List<AttributType> underAttributTyper;

	/**
	 * Kreerer en ny sammensat attributtype
	 * 
	 * @param inNavn
	 *            Navn på den nye attributtype
	 * @param inUnderAttributTyper
	 *            Underattributtyper for den nye sammensatte attributtype
	 */
	public SammensatAttributType(String inNavn,
			List<AttributType> inUnderAttributTyper) {
		super(inNavn);
		underAttributTyper = new ArrayList<AttributType>(inUnderAttributTyper);
	}

	/**
	 * Returnerer denne attributtypes underattributtyper
	 * 
	 * @return Underattributtyper
	 */
	public List<AttributType> underAttributTyper() {
		return Collections.unmodifiableList(underAttributTyper);
	}

	@Override
	public Attribut nyAttribut() {
		return new SammensatAttribut(this);
	}
}
