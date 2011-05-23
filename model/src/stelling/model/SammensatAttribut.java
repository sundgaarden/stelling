package stelling.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Repræsenterer en konkret attribut, sammensat af underattributter - f.eks. et
 * passepartout, som er lavet af en bestemt slags karton med en bestemt antal
 * udskæringer mv.
 */
public class SammensatAttribut extends Attribut {
	private final SammensatAttributType type;
	private final Map<AttributType, Attribut> underAttributter;

	/**
	 * Kreerer en ny sammensat attribut
	 * 
	 * @param inType
	 *            Type af den nye attribut
	 * @param inNavn
	 *            Navn på den nye attribut
	 */
	SammensatAttribut(SammensatAttributType inType, String inNavn) {
		super(inType, inNavn);
		type = inType;
		underAttributter = new HashMap<AttributType, Attribut>();
	}

	@Override
	public Beloeb pris(LaengdeMaal hoejde, LaengdeMaal bredde) {
		Beloeb pris = Beloeb.NUL;
		for (Attribut attribut : underAttributter.values()) {
			pris = pris.adder(attribut.pris(hoejde, bredde));
		}
		return pris;
	}

	/**
	 * Returnerer underattributten med den specificerede attributtype
	 * 
	 * @param attributType
	 *            Type af den efterspurgte attribut
	 * @return Underattribut med den specificerede attributtype
	 */
	public Attribut underAttribut(AttributType attributType) {
		checkUnderAttributType(attributType);
		Attribut attribut = underAttributter.get(attributType);
		if (attribut == null) {
			attribut = attributType.nyAttribut();
			underAttributter.put(attributType, attribut);
		}
		return attribut;
	}

	/**
	 * Ændrer den specificerede underattribut
	 * 
	 * @param attribut
	 *            Attribut der skal ændres
	 */
	public void setUnderAttribut(Attribut attribut) {
		checkUnderAttributType(attribut.attributType());
		underAttributter.put(attribut.attributType(), attribut);
	}

	/**
	 * Checker at den specificerede underattributtype er understøttet af den
	 * sammensatte attribut
	 * 
	 * @param attributType
	 *            Typen af underattributten
	 */
	private void checkUnderAttributType(AttributType attributType) {
		if (!type.underAttributTyper().contains(attributType)) {
			throw new IllegalArgumentException(
					"Den specificerede underattributtype: '"
							+ attributType.navn()
							+ "' er ikke understøttet af den sammensatte attribut: "
							+ navn());
		}
	}
}
