package org.univ.amu.entites;

import java.util.ArrayList;
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
	private Calendar heureDebut;
	private Calendar heurefin;
	private String compteRendu;
	private List<Traitement> traitement;
	private List<Interaction> interaction;
	

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
		
		return heureDebut;
	}

	@Override
	public void setDebut(Calendar date) {
		
		this.heureDebut = date;
	}

	@Override
	public Calendar getFin() {
		
		return heurefin;
	}

	@Override
	public void setFin(Calendar date) {
		this.heurefin=date;		
	}

	@Override
	public String getCompteRendu() {
		
		return compteRendu;
	}

	@Override
	public void setCompteRendu(String texte) {
		this.compteRendu = texte;
		
	}

	@Override
	public List<Traitement> getPrescription() {
		if(this.traitement == null)
			this.traitement = new ArrayList<Traitement>();
		return traitement;
	}

	@Override
	public Boolean ajouterTraitement(Produit produit) {
		
		try{
			Traitement traitement2 = new TraitementM();
			traitement2.setProduit(produit);			
			this.getPrescription().add(traitement2);
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Boolean supprimerTraitement(Traitement medicament) {
		
		
		return traitement.remove(medicament);
	}

	@Override
	public List<Interaction> getInteractions() {
		if(this.interaction == null)
			this.interaction = new ArrayList<Interaction>();
		return interaction;
	}

	@Override
	public void setInteractions(List<Interaction> interactions) {
		
		this.interaction = interactions ;
		
	}

}
