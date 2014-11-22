package org.univ.amu.entites;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateful;

import miage.gestioncabinet.api.Consultation;
import miage.gestioncabinet.api.ConsultationRemoteService;
import miage.gestioncabinet.api.GestionCabinetException;
import miage.gestioncabinet.api.Produit;

@Remote(ConsultationRemoteService.class)
@Stateful
public class ConsultationService implements ConsultationRemoteService {

	@Override
	public Consultation getConsultation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setConsultation(Consultation consultation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Consultation creerRdv(Calendar date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Produit> rechercherMedicament(String keyword)
			throws GestionCabinetException {
		// TODO Auto-generated method stub
		return null;
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
