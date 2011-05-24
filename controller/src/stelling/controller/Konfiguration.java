package stelling.controller;

import java.util.Collection;
import stelling.model.*;

public class Konfiguration {

	private Collection<OpgaveType> opgaveTyper;
	private OpgaveType defaultOpgave;

/**
 * Konstruerer en ny konfiguration
 * 
 * @param	opgaveTyper		en liste af de opgavetyper i konfigurationen
 * @param	defaultOpgave   konfigurationens default opgave
 */

	public Konfiguration(Collection<OpgaveType> opgaveTyper, OpgaveType defaultOpgave)
	{
		this.opgaveTyper = opgaveTyper;
		this.defaultOpgave = defaultOpgave;
	}

/**
 * Henter listen af opgavetyper fra konfigurationen
 * 
 * @return	liste af opgavetyper
 */

	public Collection<OpgaveType> getOpgaveTyper()
{
	return opgaveTyper;
}


/**
 * Angiver hvilken opgave der at sat som default i konfigurationen
 * 
 * @return default opgave
 */

	public OpgaveType getDefaultOpgave()
{
	return defaultOpgave;
}
	

}
