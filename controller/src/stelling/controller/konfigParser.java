package stelling.controller;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * 
 * @author Christian
 * Det antages at mappen (konfig) med konfigurationsfilerne 
 * ligger i samme mappe som den executable jar fil.
 * 
 */

public class konfigParser {


/**
 * Returnerer et array af konfig filer
 * 
 */

private File[] getKonfigFiler() throws FileNotFoundException{ 
	//sti til executable jar fil hentes
	new File(System.getProperty("java.class.path"));
	//sti til konfig filer angives
	File bibliotek = new File("path/konfig");
	File[] listeAfFiler = bibliotek.listFiles();
	return listeAfFiler;
}

/**
 * Henter konfiguration - to be implemented
 */

}

	
	
	
