package stelling.model;

import java.util.List;

/**
 * Repr¾senterer typen af en opgaveattribut, f.eks. 'glastype' ved en indramning
 * <p>
 * En opgaveattributtype har et s¾t af feltnavne, som kan bruges til at hente
 * konkrete v¾rdier fra opgaveattributter af den givne type
 * <p>
 * Der er visse faste felter, som en opgaveattribut altid har, f.eks. 'navn' og
 * 'pris'
 * 
 */
public interface IOpgaveAttributType {

	/**
	 * Returnerer navnet pŒ denne type opgaveattribut
	 * 
	 * @return Navn pŒ attributtypen
	 */
	String navn();

	/**
	 * Returnerer navnene pŒ de felter som denne attributtype har, f.eks.
	 * {'navn', 'pris', 'materiale', 'id'}
	 * 
	 * @return Navnene pŒ felter i denne attributtype
	 */
	List<String> feltNavne();

	/**
	 * Returnerer defaultv¾rdien (en konkret attributv¾rdi) for denne type
	 * opgave attribut
	 * 
	 * @return Defaultv¾rdi for denne type opgaveattribut
	 */
	IOpgaveAttribut defaultVaerdi();

}
