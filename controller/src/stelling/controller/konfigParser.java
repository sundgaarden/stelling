package stelling.controller;

import stelling.model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


import javax.lang.model.element.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 
 * @author Christian
 * 
 * 
 * 
 */

public class konfigParser {


private Document dom;
private Collection<ValgAttributType> myAtt;
private List<String> feltNavne = new ArrayList<String> ();
private List<String> attributter = new ArrayList<String> ();

/**
 * Returnerer et array af konfig filer
 * 
 */

//private File[] getKonfigFiler() throws FileNotFoundException{ 
	////sti til executable jar fil hentes
	//new File(System.getProperty("java.class.path"));
	//sti til konfig filer angives
	//File bibliotek = new File("path/konfig");
	//File[] listeAfFiler = bibliotek.listFiles();
	//return listeAfFiler;
//}

/**
 * Henter konfiguration - to be implemented
 */
public void parseXmlFil(){
	//hent document builder factory
	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

try{	
	DocumentBuilder db = dbf.newDocumentBuilder();
	//parse konfig filen med builderen for at hente dom repræsentation af xml filen
	dom = db.parse("konfig.xml");

}catch(ParserConfigurationException pce) {
	pce.printStackTrace();
}catch(SAXException se) {
	se.printStackTrace();
}catch(IOException ioe) {
	ioe.printStackTrace();
}
}


private void parseDokumentet(){
	//hent root elementet
	org.w3c.dom.Element dokEle = dom.getDocumentElement();
	
	//hent nodeliste af elementer
	NodeList nl = dokEle.getElementsByTagName("attributTypeValg");
	if(nl !=null && nl.getLength()>0){
		for(int i = 0 ; i < nl.getLength();i++) {
			
			//hent attributTypeValg elementet
			Element el = (Element)nl.item(i);
			
			//hent attributTypeValg objektet
			ValgAttributType a = getAttributTypeValg(el);
			
			
		}
	}
}

private ValgAttributType getAttributTypeValg(Element El){

	//for hver <attributTypeValg> element hent text og int værdier
	
	String navn = getTextValue(El, "type");
	String beregner = getTextValue(El, "beregner");
	String placering = getTextValue(El, "placering");
	String feltnavn = getTextValue(El, "feltnavn");
	String feltpris = getTextValue(El, "feltpris");
	
	feltNavne.add("navn");
	feltNavne.add("pris");
	attributter.add(feltnavn);
	attributter.add(feltpris);
	
	//ValgAttributType va = new ValgAttributType(navn, feltNavne, beregner);
	return va;
}

private String getTextValue(Element ele, String tagNavn) {
	String textVal = null;
	NodeList nl = ((org.w3c.dom.Element) ele).getElementsByTagName(tagNavn);
	if(nl !=null && nl.getLength()> 0){
		Element el = (Element)nl.item(0);
		textVal = ((Node) el).getFirstChild().getNodeValue();
	}
	return textVal;
}


}

	
	
	
