package org.univ.amu.entites;

import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateful;

import org.univ.amu.PrescriptionService;

import miage.gestioncabinet.api.Consultation;
import miage.gestioncabinet.api.ConsultationRemoteService;
import miage.gestioncabinet.api.GestionCabinetException;
import miage.gestioncabinet.api.Produit;

@Remote(ConsultationRemoteService.class)
@Stateful
public class ConsultationService implements ConsultationRemoteService {

	@EJB
	private PrescriptionService pService;
	
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Produit> rechercherMedicament(String keyword)
			throws GestionCabinetException {
		return pService.findProduits(keyword);
	}

	@Override
	public void analyserPrescription() throws GestionCabinetException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Consultation enregistrer() throws GestionCabinetException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void supprimer() throws GestionCabinetException {
		// TODO Auto-generated method stub
		
	}

}