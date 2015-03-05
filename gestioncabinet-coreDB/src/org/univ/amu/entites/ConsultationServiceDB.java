/**
 * 
 */
package org.univ.amu.entites;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.univ.amu.PrescriptionService;

import miage.gestioncabinet.api.Consultation;
import miage.gestioncabinet.api.ConsultationRemoteService;
import miage.gestioncabinet.api.GestionCabinetException;
import miage.gestioncabinet.api.Interaction;
import miage.gestioncabinet.api.Produit;
import miage.gestioncabinet.api.Traitement;
import fr.vidal.webservices.interactionservice.ArrayOfInt;

/**
 * @author Moe1
 *
 */
@Stateful
@LocalBean
@Remote(ConsultationRemoteService.class)
public class ConsultationServiceDB implements ConsultationRemoteService {

	@PersistenceContext(unitName = "TPBDD")
	private EntityManager entityManager;
	
	@EJB
	private PrescriptionServiceDB prescriptionService;

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

	@Override
	public List<Produit> rechercherMedicament(String keyword)
			throws GestionCabinetException {
		List<Produit> produits = null;
		produits = prescriptionService.findProduits(keyword);
		return produits;
	}

	//TODO A implémenter avec le Interaction Service
	@Override
	public void analyserPrescription() throws GestionCabinetException {
		ArrayOfInt ints = new ArrayOfInt();
		try{
			this.getConsultation().getInteractions().clear();
			Set<Produit> prodSet = new HashSet<Produit>();
			for(Traitement traitement : this.getConsultation().getPrescription())
				prodSet.add(traitement.getProduit());
			List<Interaction> interactions = prescriptionService.findInteractions(new ArrayList<Produit>(prodSet));
			this.entityManager.persist(this.getConsultation());
		}catch(Exception e){
			System.err.println(this + " n'a pas réussi à analyser " + this.getConsultation());
			e.printStackTrace();
		}
	}

	@Override
	public Consultation enregistrer() throws GestionCabinetException {
		try{
			if(((ConsultationDB)consultation).getId() == null)
				this.entityManager.persist(this.getConsultation());
			else
				consultation = this.entityManager.merge(this.consultation);
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
