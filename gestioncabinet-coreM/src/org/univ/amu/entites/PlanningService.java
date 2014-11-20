package org.univ.amu.entites;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Remote;
import javax.ejb.Stateful;

import miage.gestioncabinet.api.Consultation;
import miage.gestioncabinet.api.GestionCabinetException;
import miage.gestioncabinet.api.Medecin;
import miage.gestioncabinet.api.Patient;
import miage.gestioncabinet.api.PlanningRemoteService;
import miage.gestioncabinet.api.Utilisateur;

@Remote(PlanningRemoteService.class)
@Stateful
public class PlanningService implements PlanningRemoteService{

	private List<Consultation> lstConsultation;
	private Medecin doc;
	private Utilisateur user;
	private List<Patient> lstPatient;
	
	@PostConstruct
	private void init(){
		
	  user = new UtilisateurM();
	  user.setNom("Bak");
	  user.setPrenom("BakBak");
	  
	  doc= new MedecinM();
	  doc.setNom("Bik");
	  doc.setPrenom("BikBik");
	
	  this.lstConsultation= new ArrayList<Consultation>();
	  this.lstPatient = new ArrayList<Patient>();
	  
	}
	  
	 
	@Override
	public Utilisateur getUtilisateur() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Medecin> rechercherMedecins() throws GestionCabinetException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Patient> rechercherPatients(String nom, String prenom,
			Calendar dateNaissance) throws GestionCabinetException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Calendar getDateDebut() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDateDebut(Calendar date) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Calendar getDateFin() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDateFin(Calendar date) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Medecin getMedecin() {
		
		return null;
	}

	@Override
	public void setMedecin(Medecin medecin) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Consultation> listerRdv() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Consultation getRdvCourant() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRdvCourant(Consultation rdv) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Consultation creerRdv(Calendar date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Consultation enregistrerRdv() throws GestionCabinetException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void supprimerRdv() throws GestionCabinetException {
		// TODO Auto-generated method stub
		
	}

}
