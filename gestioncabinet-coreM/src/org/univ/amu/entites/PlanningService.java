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

	private Consultation rdvCourant;
	private List<Consultation> lstConsultation;
	private Medecin doc;
	private Utilisateur user;
	private List<Patient> lstPatient;
	private Calendar dateDebut;
	private Calendar dateFin;

	@PostConstruct
	private void init(){
		
	  user = new UtilisateurM();
	  user.setNom("Bak");
	  user.setPrenom("BakBak");
	  
	  doc= new MedecinM();
	  doc.setNom("Bik");
	  doc.setPrenom("BikBik");
	  
	  dateDebut = Calendar.getInstance();
	  dateDebut.roll(Calendar.DAY_OF_MONTH,-5);
	  dateFin =  Calendar.getInstance();
	  dateFin.roll(Calendar.DAY_OF_MONTH,5);
	
	  this.lstConsultation= new ArrayList<Consultation>();
	  this.lstPatient = new ArrayList<Patient>();
	  
	}
	  
	 
	@Override
	public Utilisateur getUtilisateur() {
		
		return user;
	}

	@Override
	public List<Medecin> rechercherMedecins() throws GestionCabinetException {
		
		return null;
	}

	@Override
	public List<Patient> rechercherPatients(String nom, String prenom,
			Calendar dateNaissance) throws GestionCabinetException {
		List<Patient> listP = new ArrayList<Patient>();
		for(Patient p: lstPatient){
			
			if(nom == null || p.getNom().contains(nom)  
				&& prenom==null || p.getPrenom().contains(prenom) 
				&& dateNaissance.equals(null) || p.getDateNaissance().equals(dateNaissance))
			{
				listP.add(p);
			}
		}
		
		
		return listP;
	}

	@Override
	public Calendar getDateDebut() {
		
		return dateDebut;
	}

	@Override
	public void setDateDebut(Calendar date) {
		this.dateDebut = date;
		
	}

	@Override
	public Calendar getDateFin() {
		
		return dateFin;
	}

	@Override
	public void setDateFin(Calendar date) {
		
		this.dateFin = date;
		
	}

	@Override
	public Medecin getMedecin() {
		
		return doc;
	}

	@Override
	public void setMedecin(Medecin medecin) {
		this.doc=medecin;
	}

	@Override
	public List<Consultation> listerRdv() {
		return lstConsultation;
	}

	@Override
	public Consultation getRdvCourant() {
		return rdvCourant;
	}

	@Override
	public void setRdvCourant(Consultation rdv) {
		rdvCourant = rdv;
	}

	@Override
	public Consultation creerRdv(Calendar date) {
		rdvCourant = new ConsultationM();
		rdvCourant.setDebut(dateDebut);
		rdvCourant.setFin(dateFin);
		rdvCourant.setMedecin(this.getMedecin());
		return rdvCourant;
	}

	@Override
	public Consultation enregistrerRdv() throws GestionCabinetException {
		this.setRdvCourant(rdvCourant);
		lstConsultation.add(rdvCourant);
		return rdvCourant;
	}

	@Override
	public void supprimerRdv() throws GestionCabinetException {
		if(lstConsultation.contains(rdvCourant))
		{
			lstConsultation.remove(rdvCourant);
		}
		
	}

}
