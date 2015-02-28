/**
 * 
 */
package org.univ.amu.entites;

import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import miage.gestioncabinet.api.Consultation;
import miage.gestioncabinet.api.ConsultationRemoteService;
import miage.gestioncabinet.api.GestionCabinetException;
import miage.gestioncabinet.api.Medecin;
import miage.gestioncabinet.api.Patient;
import miage.gestioncabinet.api.PlanningRemoteService;
import miage.gestioncabinet.api.Utilisateur;

/**
 * @author Moe1
 *
 */
public class PlanningServiceDB implements PlanningRemoteService {
	private Utilisateur utilisateur;
	private Calendar debut;
	private Calendar fin;
	private Medecin medecin;
	private Consultation rdvCourant;
	@EJB
	private ConsultationRemoteService consultationService;
	
	@PersistenceContext(unitName = "TPBDD")
    private EntityManager entityManager;
	
	@Override
	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Medecin> rechercherMedecins() throws GestionCabinetException {
		List<Medecin> medecins = null;
		try{
			Query query = this.entityManager.createNamedQuery(MedecinDB.RECHERCHER_MEDECIN);
			medecins = (List<Medecin>)query.getResultList();
		}catch(Exception e){
			System.err.println(this + "n'a pas pu trouver la liste de medecins ");
			e.printStackTrace();
		}
		return medecins;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Patient> rechercherPatients(String nom, String prenom,
			Calendar dateNaissance) throws GestionCabinetException {
		List<Patient> patients = null;
		try{
			Query query = this.entityManager.createNamedQuery(PatientDB.RECHERCHER_PATIENT);
			query.setParameter("nom", "%" +nom+ "%");
			query.setParameter("prenom", "%" +prenom+ "%");
			query.setParameter("dateNaissance", dateNaissance);
			patients = (List<Patient>)query.getResultList();
		}catch(Exception e){
			System.err.println(this + "n'a pas pu trouver la liste de patients "
					+ "avec pour nom " + nom + " prenom " + prenom + " date de "
							+ "naissance " + dateNaissance);
			e.printStackTrace();
		}
		
		return patients;
	}

	@Override
	public Calendar getDateDebut() {
		return this.debut;
	}

	@Override
	public void setDateDebut(Calendar date) {
		this.debut = date;
		
	}

	@Override
	public Calendar getDateFin() {
		return this.fin;
	}

	@Override
	public void setDateFin(Calendar date) {
		this.fin = date;
	}

	@Override
	public Medecin getMedecin() {
		return this.medecin;
	}

	@Override
	public void setMedecin(Medecin medecin) {
		this.medecin = medecin;
		
	}

	@Override
	public List<Consultation> listerRdv() {
		return null;
	}

	@Override
	public Consultation getRdvCourant() {
		return this.rdvCourant;
	}

	@Override
	public void setRdvCourant(Consultation rdv) {
		this.rdvCourant = rdv;
	}

	@Override
	public Consultation creerRdv(Calendar date) {
		return this.consultationService.creerRdv(date);
	}

	@Override
	public Consultation enregistrerRdv() throws GestionCabinetException {
		this.consultationService.setConsultation(this.getRdvCourant());
		return this.consultationService.enregistrer();
	}

	@Override
	public void supprimerRdv() throws GestionCabinetException {
		this.consultationService.setConsultation(this.getRdvCourant());
		this.consultationService.supprimer();
		
	}

}
