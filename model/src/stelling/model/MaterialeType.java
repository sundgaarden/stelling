package stelling.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Repr�senterer en generel materialetype, f.eks. 'glastype' eller 'rammetype'
 * <p>
 * Denne klasse tr�der i stedet for dedikerede klasser til specifikke
 * materialetyper, idet nye kategorier af materialetyper s� kan tilf�jes
 * dynamisk
 */
abstract class MaterialeType implements IOpgaveAttributType {
	private final String navn;
	private final List<String> feltNavne;
	private final List<Materiale> materialer;
	private final Materiale nil;
	private Materiale defaultVaerdi;

	/**
	 * Kreerer en MaterialeType med det specificerede navn
	 * 
	 * @param typeNavn
	 *            Navn p� den nye materialetype
	 */
	MaterialeType(String typeNavn) {
		navn = typeNavn;
		feltNavne = new ArrayList<String>();
		materialer = new ArrayList<Materiale>();
		nil = new Materiale(this, "Ingen", Beloeb.NUL);
		initDefaultMaterialer();
	}

	private void initDefaultMaterialer() {
		defaultVaerdi = nil;
		materialer.clear();
		materialer.add(nil);
	}

	@Override
	public String navn() {
		return navn;
	}

	@Override
	public List<String> feltNavne() {
		return Collections.unmodifiableList(feltNavne);
	}

	/**
	 * �ndrer s�ttet af feltnavne underst�ttet af denne materialetype
	 * <p>
	 * Obs: Kald til denne metode fjerner alle eksisterende materialer af denne
	 * type
	 * 
	 * @param inFeltNavne
	 *            Navne p� ny felter som denne materialetype skal underst�tte
	 */
	public void setFeltNavne(List<String> inFeltNavne) {
		feltNavne.clear();
		feltNavne.addAll(inFeltNavne);
		initDefaultMaterialer();
	}

	/**
	 * Returnerer de tilg�ngelige materialer af denne type
	 * 
	 * @return Tilg�ngelige materialer
	 */
	public List<Materiale> materialer() {
		return Collections.unmodifiableList(materialer);
	}

	@Override
	public IOpgaveAttribut defaultVaerdi() {
		return defaultVaerdi;
	}

	/**
	 * �ndrer denne materialetypes defaultv�rdi
	 * 
	 * @param defaultVaerdi
	 *            Ny defaultv�rdi
	 */
	public void setDefaultVaerdi(Materiale inDefaultVaerdi) {
		if (inDefaultVaerdi.attributType() != this) {
			throw new IllegalArgumentException(
					"Den specificerede default v�rdi var af en forkert type.");
		}
		defaultVaerdi = inDefaultVaerdi;
	}

	/**
	 * Beregner en pris ud fra de angivne oplysninger
	 * 
	 * @param hoejde
	 *            Opgavens h�jde
	 * @param bredde
	 *            Opgavens bredde
	 * @param pris
	 *            Materialets pris
	 * @return Pris for det angivne materiale i den angivne st�rrelse
	 */
	public abstract Beloeb beregnPris(LaengdeMaal hoejde, LaengdeMaal bredde,
			Beloeb pris);
}
