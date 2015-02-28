package org.univ.amu.entites;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import miage.gestioncabinet.api.Patient;

@Entity
@Table (name="t_personne") 
@DiscriminatorValue("patient")
@NamedQueries({
	@NamedQuery(name=PatientDB.RECHERCHER_PATIENT, query="select p from PatientDB p where p.nom LIKE :nom OR p.prenom LIKE :prenom OR p.dateNaissance = :dateNaissance")
})
public class PatientDB extends PersonneDB implements Patient{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6496214406468282537L;
	public static final String RECHERCHER_PATIENT = "rechercher_patient";
	@Column(name="c_sexe")
	private String sexe;
	
	@Column(name="c_datenaiss")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dateNaissance;
	
	@Override
	public Calendar getDateNaissance() {
		
		return dateNaissance;
	}
	

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof PatientDB){
			if(((PatientDB)obj).getNom().equals(this.getNom()) && ((PatientDB)obj).getPrenom().equals(this.getPrenom())){
				return true;
			}
			return false;
		}
		return false;
	}

	@Override
	public String toString() {
		return "PatientM [getNom()=" + getNom() + ", getPrenom()="
				+ getPrenom() + "]";
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
