package stelling.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Repr�senterer en konkret attribut, sammensat af underattributter - f.eks. et
 * passepartout, som er lavet af en bestemt slags karton med en bestemt antal
 * udsk�ringer mv.
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
	 *            Navn p� den nye attribut
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
	 * �ndrer den specificerede underattribut
	 * 
	 * @param attribut
	 *            Attribut der skal �ndres
	 */
	public void setUnderAttribut(Attribut attribut) {
		checkUnderAttributType(attribut.attributType());
		underAttributter.put(attribut.attributType(), attribut);
	}

	/**
	 * Checker at den specificerede underattributtype er underst�ttet af den
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
							+ "' er ikke underst�ttet af den sammensatte attribut: "
							+ navn());
		}
	}
}
