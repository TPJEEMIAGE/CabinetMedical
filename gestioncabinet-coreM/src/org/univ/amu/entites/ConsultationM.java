package org.univ.amu.entites;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import miage.gestioncabinet.api.Consultation;
import miage.gestioncabinet.api.Interaction;
import miage.gestioncabinet.api.Medecin;
import miage.gestioncabinet.api.Patient;
import miage.gestioncabinet.api.Produit;
import miage.gestioncabinet.api.Traitement;

public class ConsultationM implements Consultation {

	/**
	 * 
	 */
	private static final long serialVersionUID = -278148284796775331L;
	private Patient patient;
	private Medecin medecin;
	private Long id;
	private Calendar dateRdv;
	private Date heureDebut;
	private String compteRendu;
	

	@Override
	public int compareTo(Consultation arg0) {
		
		return 0;
	}

	@Override
	public Patient getPatient() {
		
		return patient;
	}

	@Override
	public void setPatient(Patient patient) {
		
		this.patient = patient;
	}

	@Override
	public Medecin getMedecin() {
		
		return medecin;
	}

	@Override
	public void setMedecin(Medecin medecin) {
		
		this.medecin = medecin;
	}

	@Override
	public Calendar getDebut() {
		
		return dateRdv;
	}

	@Override
	public void setDebut(Calendar date) {
		
		this.dateRdv = date;
	}

	@Override
	public Calendar getFin() {
		
		return null;
	}

	@Override
	public void setFin(Calendar date) {
		
		
	}

	@Override
	public String getCompteRendu() {
		
		return null;
	}

	@Override
	public void setCompteRendu(String texte) {
		
		
	}

	@Override
	public List<Traitement> getPrescription() {
		
		return null;
	}

	@Override
	public Boolean ajouterTraitement(Produit produit) {
		
		return null;
	}

	@Override
	public Boolean supprimerTraitement(Traitement medicament) {
		
		return null;
	}

	@Override
	public List<Interaction> getInteractions() {
		
		return null;
	}

	@Override
	public void setInteractions(List<Interaction> interactions) {
		
		
	}

}
