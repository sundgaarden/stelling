package stelling.model;

import java.util.Map;

/**
 * Repr�senterer en opgave i en foresp�rgsel, f.eks. en indramning eller en
 * opsp�nding
 */
public class Opgave implements IBeskrivelig {
	private final OpgaveType opgaveType;
	private final Map<AttributType, Attribut> attributter;
	private LaengdeMaal hoejde;
	private LaengdeMaal bredde;

	Opgave(OpgaveType inOpgaveType) {
		opgaveType = inOpgaveType;
		attributter = opgaveType.defaultAttributter();
		hoejde = LaengdeMaal.NUL;
		bredde = LaengdeMaal.NUL;
	}

	/**
	 * Returnerer h�jden p� opgaven
	 * 
	 * @return H�jden p� opgaven
	 */
	public LaengdeMaal getHoejde() {
		return hoejde;
	}

	/**
	 * �ndrer h�jden p� opgaven
	 * 
	 * @param inHoejde
	 *            Den nye h�jde p� opgaven
	 */
	public void setHoejde(LaengdeMaal inHoejde) {
		hoejde = inHoejde;
	}

	/**
	 * Returnerer bredden p� opgaven
	 * 
	 * @return Bredden p� opgaven
	 */
	public LaengdeMaal getBredde() {
		return bredde;
	}

	/**
	 * �ndrer bredden p� opgaven
	 * 
	 * @param inBredde
	 *            Den nye bredde p� opgaven
	 */
	public void setBredde(LaengdeMaal inBredde) {
		bredde = inBredde;
	}

	/**
	 * Returnerer samlet pris for opgaven
	 * 
	 * @return Samlet pris for opgaven
	 */
	public Beloeb samletPris() {
		Beloeb sum = Beloeb.NUL;
		for (Attribut att : attributter.values()) {
			sum = sum.adder(att.pris(hoejde, bredde));
		}
		return sum;
	}

	@Override
	public final String beskriv() {
		return beskriv("");
	}

	@Override
	public final String beskriv(String linjePrefix) {
		StringBuilder builder = new StringBuilder();
		builder.append(linjePrefix).append(opgaveType.navn()).append("\n");
		builder.append(linjePrefix + "\t").append("M�l (B x H): ")
				.append(bredde).append(" x ").append(hoejde).append("\n");
		builder.append(linjePrefix + "\t").append("Attributter:\n");
// TODO: Implementer IBeskrivelig p� attributtyperne
//		for (Attribut att : attributter.values()) {
//			builder.append(linjePrefix + "\t\t").append(beskrivAttribut(att))
//					.append("\n");
//		}
		builder.append(linjePrefix + "\t").append("Pris: ")
				.append(samletPris().toString()).append("\n");
		return builder.toString();
	}

//	// TODO: F�les ikke helt som det rigtige sted til metoden beskrivAttribut
//	private String beskrivAttribut(Attribut attribut) {
//		StringBuilder builder = new StringBuilder();
//		builder.append(attribut.attributType().navn()).append(": ");
//		for (String feltNavn : attribut.attributType().feltNavne()) {
//			builder.append(feltNavn).append(" = ");
//			builder.append(attribut.feltVaerdi(feltNavn)).append(", ");
//		}
//		return builder.substring(0, builder.length() - 2);
//	}
}
