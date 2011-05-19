package stelling.controller;

import java.util.Collection;
import stelling.model.*;

public class Konfiguration {

	private Collection<OpgaveType> opgaveTyper;
	private Collection<MaterialeType> materialeTyper;
	private OpgaveType defaultOpgave;

/**
 * Konstruerer en ny konfiguration
 * 
 * @param	opgaveTyper		en liste af de opgavetyper i konfigurationen
 * @param	materialeTyper	en liste af de materialetyper i konfigurationen
 */

	public Konfiguration(Collection<OpgaveType> opgaveTyper,
			Collection<MaterialeType> materialeTyper,
			OpgaveType defaultOpgave)
	{
		this.opgaveTyper = opgaveTyper;
		this.materialeTyper = materialeTyper;
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
 * Henter listen af materialetyper fra konfigurationen
 * 
 * @return liste af materialetyper
 */

	public Collection<MaterialeType> getMaterialeTyper()
{
	return materialeTyper;
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
