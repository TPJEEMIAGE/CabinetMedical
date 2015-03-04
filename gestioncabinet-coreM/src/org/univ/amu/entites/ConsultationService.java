package org.univ.amu.entites;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateful;

import miage.gestioncabinet.api.Consultation;
import miage.gestioncabinet.api.ConsultationRemoteService;
import miage.gestioncabinet.api.GestionCabinetException;
import miage.gestioncabinet.api.Produit;
import miage.gestioncabinet.api.Traitement;

import org.univ.amu.PrescriptionService;

@Remote(ConsultationRemoteService.class)
@LocalBean
@Stateful
public class ConsultationService implements ConsultationRemoteService {

	@EJB
	private PrescriptionService pService;
	
	@EJB
	private PlanningService planningService;
	
	private Consultation consultation;
	
	@Override
	public Consultation getConsultation() {
		
		return consultation;
	}

	@Override
	public void setConsultation(Consultation consultation) {
		this.consultation = consultation;
		
	}

	@Override
	public Consultation creerRdv(Calendar date) {
		Consultation consultation = new ConsultationM();
		Calendar fin = (Calendar)date.clone();
		fin.add(Calendar.MINUTE, 30);
		consultation.setDebut(date);
		consultation.setFin(fin);
		return consultation;
	}

	//TODO Faire la méthode
	@Override
	public List<Produit> rechercherMedicament(String keyword)
			throws GestionCabinetException {
		return null;
	}


	
	@Override
	public Consultation enregistrer() throws GestionCabinetException {
		if(planningService.getLstConsultation().contains(consultation)){
			planningService.getLstConsultation().remove(consultation);
		}
		planningService.getLstConsultation().add(consultation);
		
		return consultation;
	}

	@Override
	public void supprimer() throws GestionCabinetException {
		if(planningService.getLstConsultation().contains(consultation)){
			planningService.getLstConsultation().remove(consultation);
		}
		
	}

	@Override
	public void analyserPrescription() throws GestionCabinetException {
		List<Produit> lstProd = new ArrayList<Produit>();
		for(Traitement t : consultation.getPrescription()){
			lstProd.add(t.getProduit());
		}
		consultation.setInteractions(pService.findInteractions(lstProd));
	}


}
