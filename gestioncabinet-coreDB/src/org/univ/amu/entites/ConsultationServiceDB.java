/**
 * 
 */
package org.univ.amu.entites;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import miage.gestioncabinet.api.Consultation;
import miage.gestioncabinet.api.ConsultationRemoteService;
import miage.gestioncabinet.api.GestionCabinetException;
import miage.gestioncabinet.api.Produit;

/**
 * @author Moe1
 *
 */
@Stateful
@Remote(ConsultationRemoteService.class)
public class ConsultationServiceDB implements ConsultationRemoteService {
	
	@PersistenceContext(unitName = "TPBDD")
    private EntityManager entityManager;
	
	private Consultation consultation;

	@Override
	public Consultation getConsultation() {
		return this.consultation;
	}

	@Override
	public void setConsultation(Consultation consultation) {
		this.consultation = consultation;
	}

	@Override
	public Consultation creerRdv(Calendar date) {
		Consultation consultation = null;
		try{
			consultation = new ConsultationDB();
			consultation.setDebut(date);
			Calendar dateFin = (Calendar)date.clone();
			dateFin.add(Calendar.MINUTE, 30);
			consultation.setFin(dateFin);
			this.setConsultation(consultation);
			this.enregistrer();
		}catch(Exception e){
			System.err.println(this + " n'a pas réussi à créer un rendez-vous pour " + date);
			e.printStackTrace();
		}
		return this.getConsultation();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Produit> rechercherMedicament(String keyword)
			throws GestionCabinetException {
		List<Produit> produits = null;
		try{
			Query query = this.entityManager.createNamedQuery(ProduitDB.RECHERCHER_MEDICAMENT);
			query.setParameter("keyword", keyword);
			produits = (List<Produit>)query.getResultList();
		}catch(Exception e){
			System.err.println(this + "n'a pas pu trouver les médicaments correspondant à " + keyword);
			e.printStackTrace();
		}
		return produits;
	}

	//TODO A implémenter avec le Interaction Service
	@Override
	public void analyserPrescription() throws GestionCabinetException {
		
	}

	@Override
	public Consultation enregistrer() throws GestionCabinetException {
		try{
			this.entityManager.persist(this.getConsultation());
		}catch(Exception e){
			e.printStackTrace();
			System.err.println(this + " n'a pas réussi à enregistrer " + this.getConsultation());
		}
		
		return this.getConsultation();
	}

	@Override
	public void supprimer() throws GestionCabinetException {
		try{
			this.entityManager.remove(this.getConsultation());
		}catch(Exception e){
			e.printStackTrace();
			System.err.println(this + " n'a pas réussi à supprimer " + this.getConsultation());
		}
		
		
	}

}
