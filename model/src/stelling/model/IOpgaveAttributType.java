package stelling.model;

import java.util.List;

/**
 * Repr�senterer typen af en opgaveattribut, f.eks. 'glastype' ved en indramning
 * <p>
 * En opgaveattributtype har et s�t af feltnavne, som kan bruges til at hente
 * konkrete v�rdier fra opgaveattributter af den givne type
 * <p>
 * Der er visse faste felter, som en opgaveattribut altid har, f.eks. 'navn' og
 * 'pris'
 * 
 */
public interface IOpgaveAttributType {

	/**
	 * Returnerer navnet p� denne type opgaveattribut
	 * 
	 * @return Navn p� attributtypen
	 */
	String navn();

	/**
	 * Returnerer navnene p� de felter som denne attributtype har, f.eks.
	 * {'navn', 'pris', 'materiale', 'id'}
	 * 
	 * @return Navnene p� felter i denne attributtype
	 */
	List<String> feltNavne();

	/**
	 * Returnerer defaultv�rdien (en konkret attributv�rdi) for denne type
	 * opgave attribut
	 * 
	 * @return Defaultv�rdi for denne type opgaveattribut
	 */
	IOpgaveAttribut defaultVaerdi();

}
