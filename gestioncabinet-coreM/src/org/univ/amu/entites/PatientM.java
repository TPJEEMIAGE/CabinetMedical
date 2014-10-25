package org.univ.amu.entites;

import java.util.Calendar;

import miage.gestioncabinet.api.Patient;

public class PatientM extends PersonneM implements Patient{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6496214406468282537L;

	private String sexe;
	private Calendar dateNaissance;
	
	@Override
	public Calendar getDateNaissance() {
		
		return dateNaissance;
	}

	@Override
	public void setDateNaissance(Calendar dateNaissance) {
		this.dateNaissance=dateNaissance;
		
	}

	@Override
	public Integer getAge() {
		Calendar c;
		c= Calendar.getInstance();
		Integer annéeActuelle = c.get(Calendar.YEAR);
		Integer annéeNaissancePatient = dateNaissance.get(Calendar.YEAR);
		c.set(Calendar.YEAR, dateNaissance.get(Calendar.YEAR));
		if(c.after(dateNaissance))
			return annéeActuelle - annéeNaissancePatient;
		else
			return annéeActuelle - 1 - annéeNaissancePatient;
	}
	
	

}
